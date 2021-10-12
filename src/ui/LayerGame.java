package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import config.GameConfig;
import entity.GameAct;

public class LayerGame extends Layer {


	private static final int LEFT_SIDE=0;
	private static final int RIGHT_SIDE=GameConfig.getSystemConfig().getMaxX();
	
	private static final int SIZE_ROL=GameConfig.getFrameConfig().getSizeRol();
	
	private static final int LOSE_IDX=GameConfig.getFrameConfig().getLoseIdx();
	
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g)
	{
		System.out.println("����LayerGame�е�paint����");
		System.out.println("����LayerGame�е�createWindow����");
		this.createWindow(g);
		GameAct act=this.dto.getGameAct();
		if(act!=null)
		{
			//��÷��鼯��
			Point[] points=this.dto.getGameAct().getActPoints();
			
			//������Ӱ
			this.drawShadow(points,g);
			
			//���ƻ����
			this.drawMainAct(points,g);
		}
		
		//������Ϸ��ͼ
		this.drawMap(g);
		
		//��ͣ
		if(this.dto.isPause())
		{
			this.drawImageAtCenter(Img.PAUSE, g);
		}
		
    }
	
	//���������ο�
	private void drawActByPoint(int x,int y,int imgIdx,Graphics g)
	{
		System.out.println("����LayerGame�е�drawActByPoint�������������ο�");
		imgIdx=this.dto.isStart()?imgIdx:LOSE_IDX;
		g.drawImage(Img.ACT,
				this.x+(x<<SIZE_ROL)+BORDER, 
				this.y+(y<<SIZE_ROL)+BORDER,
				this.x+(x+1<<SIZE_ROL)+BORDER, 
				this.y+(y+1<<SIZE_ROL)+BORDER, 
				imgIdx<<SIZE_ROL,0,(imgIdx+1)<<SIZE_ROL,1<<SIZE_ROL, null);
	}
	
	//������Ӱ
	private void drawShadow(Point[] points,Graphics g)
	{
		System.out.println("����LayerGame�е�drawShadow����������Ӱ");
		if(!this.dto.isShowShadow())
		{
			return;
		}
		int leftX=RIGHT_SIDE;
		int rightX=LEFT_SIDE;

		for(Point p: points)
		{
			leftX=p.x<leftX?p.x:leftX;
			rightX=p.x>rightX?p.x:rightX;
		}
				
		g.drawImage(Img.SHADOW
				,this.x+BORDER+(leftX<<SIZE_ROL),
				this.y+BORDER,
				(rightX-leftX+1)<<SIZE_ROL,
				this.h-(BORDER<<1),null);
	}
	
	//������Ϸ��ͼ
	private void drawMap(Graphics g)
	{
		System.out.println("����LayerGame�е�drawMap����������Ϸ��ͼ");
		//��ӡ��ͼ
		boolean[][] map=this.dto.getGameMap();
		//���㵱ǰ�ѻ���ɫ
		int lv=this.dto.getNowLevel();
		int imgIdx=(lv==0?0:(lv-1)%7+1);
		for(int x=0;x<map.length;x++)
		{
			for(int y=0;y<map[x].length;y++)
			{
				if(map[x][y])
				{
						drawActByPoint(x,y,imgIdx,g);
				}
			}
		  }
	}
	
	private void drawMainAct(Point[] points,Graphics g)
	{
		System.out.println("ͨ����Ż�������˹����");
		//��÷������ͱ��(0-6)
		int typeCode=this.dto.getGameAct().getTypeCode();
		//typeCode=this.dto.isStart()?typeCode:LOSE_IDX;
		//��ӡ����
		for(int i=0;i<points.length;i++)
		{
			drawActByPoint(points[i].x,points[i].y,typeCode+1,g);
	     }
	}
	
}
