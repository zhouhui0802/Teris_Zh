package entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import config.GameConfig;

//对方块单个的处理
public class GameAct {
	
	private Point[] actPoints=null;
	
	//方块编号
	private int typeCode;
	
	//取出方块可以走动的边界
	private final static int MIN_X=GameConfig.getSystemConfig().getMinX();
	private final static int MAX_X=GameConfig.getSystemConfig().getMaxX();
	private final static int MIN_Y=GameConfig.getSystemConfig().getMinY();
	private final static int MAX_Y=GameConfig.getSystemConfig().getMaxY();
	
	private final static List<Point[]> TYPE_CONFIG=GameConfig.getSystemConfig().getTypeConfig();
	private final static List<Boolean> TYPE_ROUND=GameConfig.getSystemConfig().getTypeRound();
	

	
	public GameAct(int typeCode)
	{
		System.out.println("进入GameAct的初始化函数");
		this.init(typeCode);
	}

	public void init(int typeCode)
	{
		System.out.println("进入GameAct的init()初始化函数");
		this.typeCode=typeCode;
		
		Point[] points=TYPE_CONFIG.get(typeCode);
		actPoints=new Point[points.length];
		for(int i=0;i<points.length;i++)
		{
			actPoints[i]=new Point(points[i].x,points[i].y);
		}
	}
	
	public Point[] getActPoints() {
		return actPoints;
	}

	public void setActPoints(Point[] actPoints) {
		this.actPoints = actPoints;
	}
	
	//判断方块的移动
	public boolean move(int moveX,int moveY,boolean[][] gameMap)
	{
		System.out.println("GameAct中的move函数判断方块是否可以移动");
		for(int i=0;i<actPoints.length;i++)
		{
			int newX=actPoints[i].x+moveX;
			int newY=actPoints[i].y+moveY;
			if(isOverZone(newX,newY,gameMap))
			{
				return false;
			}
		}
		for(int i=0;i<actPoints.length;i++)
		{
			actPoints[i].x+=moveX;
			actPoints[i].y+=moveY;
		}
		return true;
	}
	
	//方块的旋转
	public void round(boolean[][] gameMap)
	{
		System.out.println("GameAct中的round函数判断方块是否可以旋转");
		if(!TYPE_ROUND.get(this.typeCode))
		{
			return;
		}
		for(int i=1;i<actPoints.length;i++)
		{
			int newX=actPoints[0].y+actPoints[0].x-actPoints[i].y;
			int newY=actPoints[0].y-actPoints[0].x+actPoints[i].x;
			if(this.isOverZone(newX, newY,gameMap))
			{
				return;
			}
		}
		
		for(int i=1;i<actPoints.length;i++)
		{
			int newX=actPoints[0].y+actPoints[0].x-actPoints[i].y;
			int newY=actPoints[0].y-actPoints[0].x+actPoints[i].x;
			actPoints[i].x=newX;
			actPoints[i].y=newY;
		}
	}
	
	//判断方块是否出界
	private boolean isOverZone(int x,int y,boolean[][] gameMap)
	{
		//只要有一个为true就返回true
		System.out.println("只要有一个为true就返回true,都为false才继续执行");
		return x<MIN_X||x>MAX_X||y<MIN_Y||y>MAX_Y||gameMap[x][y];
	}
	
	public int getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}
}
