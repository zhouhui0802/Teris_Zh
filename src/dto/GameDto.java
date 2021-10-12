package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.GameFunction;
import config.GameConfig;
import entity.GameAct;

public class GameDto {
	
	//游戏的宽度
	public static final int GAMEZONE_W=GameConfig.getSystemConfig().getMaxX()+1;
	
	//游戏高度
	public  static final int GAMEZONE_H=GameConfig.getSystemConfig().getMaxY()+1;
	
	//数据库的记录
	private List<Player> dbRecode;
	
	//本地的记录
	private List<Player> diskRecode;
	
	//所有的地图方块
	private boolean[][] gameMap;
	
	//俄罗斯方块
	private GameAct gameAct;
	
	//下一个俄罗斯方块
	private int next;
	
	//当前的等级
	private int nowLevel;
	
	//当前的分数
	private int nowPoint;
	
	//当前的消除的行数
	private int nowRemoveLine;
	
	//判断游戏是否是开始状态
	private boolean start;
	
	//是否显示阴影
	private boolean showShadow;
	
	//暂停
	private boolean pause;
	
	//是否使用作弊
	private boolean cheat;
	
	//线程等待时间
	private long sleepTime;
	
	public GameDto()
	{
		System.out.println("进入GameDto的构造函数");
		dtoInit();
		System.out.println("进入GameDto的构造函数结束");
	}
	
	public void dtoInit()
	{
		System.out.println("进入GameDto的dtoInit()函数");
		this.gameMap=new boolean[GAMEZONE_W][GAMEZONE_H];
		System.out.println("gameMap[0][0]="+gameMap[0][0]);
		this.nowLevel=0;
		this.nowPoint=0;
		this.nowRemoveLine=0;
		this.pause=false;
		this.cheat=false;
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowLevel);
		System.out.println("GameDto的dtoInit()函数结束");
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
		//如果进来的是空，那么创建
		System.out.println("进入GameDto中的setFillRecode的函数，判断加入的人员是否为空，是否超过5个");
		if(players==null)
		{
			players=new ArrayList<Player>();
		}
		//如何记录小于5，那么就加到5条为止
		while(players.size()<5)
		{
			players.add(new Player("No Data",0));
		}
		
		Collections.sort(players);
		System.out.println("GameDto中的setFillRecode的函数结束");
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
		System.out.println("进入GameDto中的setNowLevel函数");
		this.nowLevel = nowLevel;
		//计算线程睡眠时间（下落速度）
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowLevel);
		System.out.println("GameDto中的setNowLevel函数结束");
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
