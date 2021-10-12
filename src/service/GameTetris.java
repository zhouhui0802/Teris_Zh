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
	
	private static final int MAX_TYPE=GameConfig.getSystemConfig().getTypeConfig().size()-1; //俄罗斯方块的类型
	
	public static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();  //对应的最高游戏等级
	
	//连续消行分数表
	private static final Map<Integer,Integer> PLUS_POINT=GameConfig.getSystemConfig().getPlusPoint();
	
	public GameTetris(GameDto dto)
	{
		System.out.println("将GameDto给GameTetris");
		this.dto=dto;
	}
	
	public void gameTest()
	{
		int temp=dto.getNowPoint();
		dto.setNowPoint(temp+1);
	}
	
	public boolean keyUp()
	{
		System.out.println("进入GameTetris中的keyUp函数进行旋转");
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
		System.out.println("进入GameTetris中的keyDown函数进行各种骚操作");
		
		if(this.dto.isPause())
		{
			return true;
		}
		
		synchronized(this.dto)
		{
			//方块向下移动 判断是否移动成功
			if(this.dto.getGameAct().move(0, 1,this.dto.getGameMap()))
			{
					return false;
			}
			
			//获得游戏地图对象
			boolean[][] map=this.dto.getGameMap();
			
			//获得方块对象
			Point[] act=this.dto.getGameAct().getActPoints();
			
			//将方块对象堆积到地图数组
			for(int i=0;i<act.length;i++)
			{
				map[act[i].x][act[i].y]=true;
			}	
			
			//返回加分
			int plusExp=this.plusExp();
			
			//如果发生消行
			if(plusExp>0)
			{	
				//增加经验值
				this.plusPoint(plusExp);
			}
			
			//创建下一个方块
			this.dto.getGameAct().init(this.dto.getNext());
			
			//随机生成下一个方块
			this.dto.setNext(random.nextInt(MAX_TYPE));
			
			//检查游戏是否失败
			if(this.isLose())
			{
				//结束游戏
				this.dto.setStart(false);
			}
		}
		return true;
	}
	
	
	
	private boolean isLose()
	{
		System.out.println("进入GameTetris中的isLose函数判断是否输了游戏");
		//获得现有的活动方块
		Point[] actPoints=this.dto.getGameAct().getActPoints();
		
		//获得现有的游戏地图
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
	
	//加分升级操作
	private void plusPoint(int plusExp)
	{
		System.out.println("进入GameTetris中的plusPoint函数进行加分升级操作");
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
		System.out.println("进入GameTetris中的plusExp返回消行的行数");
		//获得游戏地图
		boolean[][] map=this.dto.getGameMap();
		int exp=0;
		//扫描游戏地图，查看是否可以消行
		for(int y=0;y<GameDto.GAMEZONE_H;y++)
		{
			//判断是否可以消行
			if(this.isCanRemoveLine(y,map))
			{
				//如果可以，那就消行
				this.removeLine(y,map);
				exp++;
			}
		}
		
		return exp;
	}
	
	//消行操作
	private void removeLine(int rowNumber,boolean[][] map)
	{
		System.out.println("消行操作");
		for(int x=0;x<GameDto.GAMEZONE_W;x++)
		{
			for(int y=rowNumber;y>0;y--)
			{
				map[x][y]=map[x][y-1];
			}
			map[x][0]=false;
		}
	}
	
	//判断某一行是否可以消行
	private boolean isCanRemoveLine(int y,boolean[][] map)
	{
		System.out.println("判断某一行是否可以消");
		//单行内对每一个单位进行扫描
		for(int x=0;x<GameDto.GAMEZONE_W;x++)
		{
			if(!map[x][y])
			{
				//如果有一个方格为false就直接跳到下一行
				return false;
			}
		}
		return true;
	}
	
	public boolean keyLeft()
	{
		System.out.println("进入GameTetris中的keyLeft判断是否可以左转");
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
		System.out.println("进入GameTetris中的keyRight判断是否可以右转");
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
		//作弊键
		this.dto.setCheat(true);
		this.plusPoint(4);
		return true;
	}

	@Override
	public boolean keyFunDown() {
		// TODO Auto-generated method stub
		//瞬间落下
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
		//关闭阴影
		this.dto.changeShowShadow();
		return true;
	}

	@Override
	public boolean keyFunRight() {
		// TODO Auto-generated method stub
		//暂停键
		if(this.dto.isStart())
		{
			this.dto.changePause();
		}
		return true;
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
		//随机生成下一个方块
		this.dto.setNext(random.nextInt(MAX_TYPE));
		
		//随机生成画面方块
		this.dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
		
		//把游戏状态设置为开始
		this.dto.setStart(true);
		
		//dto初始化
		this.dto.dtoInit();
		
	}

	@Override
	public void mainAction() {
		// TODO Auto-generated method stub
		this.keyDown();
	}
	
}
