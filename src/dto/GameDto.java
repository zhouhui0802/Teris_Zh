package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.GameFunction;
import config.GameConfig;
import entity.GameAct;

public class GameDto {
	
	//��Ϸ�Ŀ��
	public static final int GAMEZONE_W=GameConfig.getSystemConfig().getMaxX()+1;
	
	//��Ϸ�߶�
	public  static final int GAMEZONE_H=GameConfig.getSystemConfig().getMaxY()+1;
	
	//���ݿ�ļ�¼
	private List<Player> dbRecode;
	
	//���صļ�¼
	private List<Player> diskRecode;
	
	//���еĵ�ͼ����
	private boolean[][] gameMap;
	
	//����˹����
	private GameAct gameAct;
	
	//��һ������˹����
	private int next;
	
	//��ǰ�ĵȼ�
	private int nowLevel;
	
	//��ǰ�ķ���
	private int nowPoint;
	
	//��ǰ������������
	private int nowRemoveLine;
	
	//�ж���Ϸ�Ƿ��ǿ�ʼ״̬
	private boolean start;
	
	//�Ƿ���ʾ��Ӱ
	private boolean showShadow;
	
	//��ͣ
	private boolean pause;
	
	//�Ƿ�ʹ������
	private boolean cheat;
	
	//�̵߳ȴ�ʱ��
	private long sleepTime;
	
	public GameDto()
	{
		System.out.println("����GameDto�Ĺ��캯��");
		dtoInit();
		System.out.println("����GameDto�Ĺ��캯������");
	}
	
	public void dtoInit()
	{
		System.out.println("����GameDto��dtoInit()����");
		this.gameMap=new boolean[GAMEZONE_W][GAMEZONE_H];
		System.out.println("gameMap[0][0]="+gameMap[0][0]);
		this.nowLevel=0;
		this.nowPoint=0;
		this.nowRemoveLine=0;
		this.pause=false;
		this.cheat=false;
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowLevel);
		System.out.println("GameDto��dtoInit()��������");
	}
	


	public List<Player> getDbRecode() {
		return dbRecode;
	}
	
	public void setDbRecode(List<Player> dbRecode) {
		this.dbRecode = this.setFillRecode(dbRecode);
	}
	
	public List<Player> getDiskRecode() {
		return diskRecode;
	}
	
	public void setDiskRecode(List<Player> diskRecode) {
		
		this.diskRecode = this.setFillRecode(diskRecode);
	}
	
	private List<Player> setFillRecode(List<Player> players)
	{
		//����������ǿգ���ô����
		System.out.println("����GameDto�е�setFillRecode�ĺ������жϼ������Ա�Ƿ�Ϊ�գ��Ƿ񳬹�5��");
		if(players==null)
		{
			players=new ArrayList<Player>();
		}
		//��μ�¼С��5����ô�ͼӵ�5��Ϊֹ
		while(players.size()<5)
		{
			players.add(new Player("No Data",0));
		}
		
		Collections.sort(players);
		System.out.println("GameDto�е�setFillRecode�ĺ�������");
		return players;
	}
	
	
	public boolean[][] getGameMap() {
		return gameMap;
	}
	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}
	public GameAct getGameAct() {
		return gameAct;
	}
	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getNowLevel() {
		return nowLevel;
	}
	public void setNowLevel(int nowLevel) {
		System.out.println("����GameDto�е�setNowLevel����");
		this.nowLevel = nowLevel;
		//�����߳�˯��ʱ�䣨�����ٶȣ�
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowLevel);
		System.out.println("GameDto�е�setNowLevel��������");
	}
	public int getNowPoint() {
		return nowPoint;
	}
	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}
	public int getNowRemoveLine() {
		return nowRemoveLine;
	}
	public void setNowRemoveLine(int nowRemoveLine) {
		this.nowRemoveLine = nowRemoveLine;
	}
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}
	public boolean isShowShadow() {
		return showShadow;
	}

	public void changeShowShadow() {
		this.showShadow=!this.showShadow;
	}
	public boolean isPause() {
		return pause;
	}

	public void changePause() {
			this.pause =!this.pause;
	}
	
	public boolean isCheat() {
		return cheat;
	}

	public void setCheat(boolean cheat) {
		this.cheat = cheat;
	}
	public long getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}
}
