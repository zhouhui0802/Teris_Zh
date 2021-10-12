package service;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import config.GameConfig;
import dto.GameDto;
import dto.Player;
import entity.GameAct;

public class GameTetris implements GameService{
	
	private GameDto dto;
	
	private Random random=new Random();
	
	private static final int MAX_TYPE=GameConfig.getSystemConfig().getTypeConfig().size()-1; //����˹���������
	
	public static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();  //��Ӧ�������Ϸ�ȼ�
	
	//�������з�����
	private static final Map<Integer,Integer> PLUS_POINT=GameConfig.getSystemConfig().getPlusPoint();
	
	public GameTetris(GameDto dto)
	{
		System.out.println("��GameDto��GameTetris");
		this.dto=dto;
	}
	
	public void gameTest()
	{
		int temp=dto.getNowPoint();
		dto.setNowPoint(temp+1);
	}
	
	public boolean keyUp()
	{
		System.out.println("����GameTetris�е�keyUp����������ת");
		if(this.dto.isPause())
		{
			return true;
		}
		synchronized(this.dto)
		{
			this.dto.getGameAct().round(this.dto.getGameMap());
		}
		return true;
	}
	
	public boolean keyDown()
	{
		System.out.println("����GameTetris�е�keyDown�������и���ɧ����");
		
		if(this.dto.isPause())
		{
			return true;
		}
		
		synchronized(this.dto)
		{
			//���������ƶ� �ж��Ƿ��ƶ��ɹ�
			if(this.dto.getGameAct().move(0, 1,this.dto.getGameMap()))
			{
					return false;
			}
			
			//�����Ϸ��ͼ����
			boolean[][] map=this.dto.getGameMap();
			
			//��÷������
			Point[] act=this.dto.getGameAct().getActPoints();
			
			//���������ѻ�����ͼ����
			for(int i=0;i<act.length;i++)
			{
				map[act[i].x][act[i].y]=true;
			}	
			
			//���ؼӷ�
			int plusExp=this.plusExp();
			
			//�����������
			if(plusExp>0)
			{	
				//���Ӿ���ֵ
				this.plusPoint(plusExp);
			}
			
			//������һ������
			this.dto.getGameAct().init(this.dto.getNext());
			
			//���������һ������
			this.dto.setNext(random.nextInt(MAX_TYPE));
			
			//�����Ϸ�Ƿ�ʧ��
			if(this.isLose())
			{
				//������Ϸ
				this.dto.setStart(false);
			}
		}
		return true;
	}
	
	
	
	private boolean isLose()
	{
		System.out.println("����GameTetris�е�isLose�����ж��Ƿ�������Ϸ");
		//������еĻ����
		Point[] actPoints=this.dto.getGameAct().getActPoints();
		
		//������е���Ϸ��ͼ
		boolean[][] map=this.dto.getGameMap();
		for(int i=0;i<actPoints.length;i++)
		{
			if(map[actPoints[i].x][actPoints[i].y])
			{
				return true;
			}
		}
		
		return false;
	}
	
	//�ӷ���������
	private void plusPoint(int plusExp)
	{
		System.out.println("����GameTetris�е�plusPoint�������мӷ���������");
		int lv=this.dto.getNowLevel();
		int rmLine=this.dto.getNowRemoveLine();
		int point=this.dto.getNowPoint();
		
		if(rmLine%LEVEL_UP+plusExp>=LEVEL_UP)
		{
			this.dto.setNowLevel(++lv);
		}
		
		this.dto.setNowRemoveLine(rmLine+plusExp);
		this.dto.setNowPoint(point+PLUS_POINT.get(plusExp));
	}
	
	//
	private int plusExp()
	{
		System.out.println("����GameTetris�е�plusExp�������е�����");
		//�����Ϸ��ͼ
		boolean[][] map=this.dto.getGameMap();
		int exp=0;
		//ɨ����Ϸ��ͼ���鿴�Ƿ��������
		for(int y=0;y<GameDto.GAMEZONE_H;y++)
		{
			//�ж��Ƿ��������
			if(this.isCanRemoveLine(y,map))
			{
				//������ԣ��Ǿ�����
				this.removeLine(y,map);
				exp++;
			}
		}
		
		return exp;
	}
	
	//���в���
	private void removeLine(int rowNumber,boolean[][] map)
	{
		System.out.println("���в���");
		for(int x=0;x<GameDto.GAMEZONE_W;x++)
		{
			for(int y=rowNumber;y>0;y--)
			{
				map[x][y]=map[x][y-1];
			}
			map[x][0]=false;
		}
	}
	
	//�ж�ĳһ���Ƿ��������
	private boolean isCanRemoveLine(int y,boolean[][] map)
	{
		System.out.println("�ж�ĳһ���Ƿ������");
		//�����ڶ�ÿһ����λ����ɨ��
		for(int x=0;x<GameDto.GAMEZONE_W;x++)
		{
			if(!map[x][y])
			{
				//�����һ������Ϊfalse��ֱ��������һ��
				return false;
			}
		}
		return true;
	}
	
	public boolean keyLeft()
	{
		System.out.println("����GameTetris�е�keyLeft�ж��Ƿ������ת");
		if(this.dto.isPause())
		{
			return true;
		}
		synchronized(this.dto)
		{
			this.dto.getGameAct().move(-1,0,this.dto.getGameMap());
		}
		return true;
	}
	
	public boolean keyRight()
	{
		System.out.println("����GameTetris�е�keyRight�ж��Ƿ������ת");
		if(this.dto.isPause())
		{
			return true;
		}
		
		synchronized(this.dto)
		{
			this.dto.getGameAct().move(1,0,this.dto.getGameMap());
		}
		return true;
	}
	
	public void setDbRecode(List<Player> players)
	{
		this.dto.setDbRecode(players);
	}
	
	public void setDiskRecode(List<Player> players)
	{
		this.dto.setDiskRecode(players);
	}

	@Override
	public boolean keyFunUp() {
		// TODO Auto-generated method stub
		//���׼�
		this.dto.setCheat(true);
		this.plusPoint(4);
		return true;
	}

	@Override
	public boolean keyFunDown() {
		// TODO Auto-generated method stub
		//˲������
		if(this.dto.isPause())
		{
			return true;
		}
		while(!this.keyDown());
		return true;
	}

	@Override
	public boolean keyFunLeft() {
		// TODO Auto-generated method stub
		//�ر���Ӱ
		this.dto.changeShowShadow();
		return true;
	}

	@Override
	public boolean keyFunRight() {
		// TODO Auto-generated method stub
		//��ͣ��
		if(this.dto.isStart())
		{
			this.dto.changePause();
		}
		return true;
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
		//���������һ������
		this.dto.setNext(random.nextInt(MAX_TYPE));
		
		//������ɻ��淽��
		this.dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
		
		//����Ϸ״̬����Ϊ��ʼ
		this.dto.setStart(true);
		
		//dto��ʼ��
		this.dto.dtoInit();
		
	}

	@Override
	public void mainAction() {
		// TODO Auto-generated method stub
		this.keyDown();
	}
	
}
