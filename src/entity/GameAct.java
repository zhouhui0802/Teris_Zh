package entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import config.GameConfig;

//�Է��鵥���Ĵ���
public class GameAct {
	
	private Point[] actPoints=null;
	
	//������
	private int typeCode;
	
	//ȡ����������߶��ı߽�
	private final static int MIN_X=GameConfig.getSystemConfig().getMinX();
	private final static int MAX_X=GameConfig.getSystemConfig().getMaxX();
	private final static int MIN_Y=GameConfig.getSystemConfig().getMinY();
	private final static int MAX_Y=GameConfig.getSystemConfig().getMaxY();
	
	private final static List<Point[]> TYPE_CONFIG=GameConfig.getSystemConfig().getTypeConfig();
	private final static List<Boolean> TYPE_ROUND=GameConfig.getSystemConfig().getTypeRound();
	

	
	public GameAct(int typeCode)
	{
		System.out.println("����GameAct�ĳ�ʼ������");
		this.init(typeCode);
	}

	public void init(int typeCode)
	{
		System.out.println("����GameAct��init()��ʼ������");
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
	
	//�жϷ�����ƶ�
	public boolean move(int moveX,int moveY,boolean[][] gameMap)
	{
		System.out.println("GameAct�е�move�����жϷ����Ƿ�����ƶ�");
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
	
	//�������ת
	public void round(boolean[][] gameMap)
	{
		System.out.println("GameAct�е�round�����жϷ����Ƿ������ת");
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
	
	//�жϷ����Ƿ����
	private boolean isOverZone(int x,int y,boolean[][] gameMap)
	{
		//ֻҪ��һ��Ϊtrue�ͷ���true
		System.out.println("ֻҪ��һ��Ϊtrue�ͷ���true,��Ϊfalse�ż���ִ��");
		return x<MIN_X||x>MAX_X||y<MIN_Y||y>MAX_Y||gameMap[x][y];
	}
	
	public int getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}
}
