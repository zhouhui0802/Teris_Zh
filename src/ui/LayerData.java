package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.*;

import config.GameConfig;
import dto.Player;

public abstract class LayerData extends Layer{

	//最大数据行配置文件
	private static final int MAX_ROW=GameConfig.getDataConfig().getMaxRow();
	
	//数值槽外径
	private static final int RECT_H=IMG_RECT_H+4;
	
	//间距
	private static  int SPA=0;
	
	//起始Y坐标
	private static int START_Y=0;
	
	protected LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		
		//计算记录绘制时间框
		SPA=(this.h-RECT_H*5-(PADDING<<1)-Img.DB.getHeight(null))/MAX_ROW;
		//计算起始Y坐标
		START_Y=PADDING+Img.DB.getHeight(null)+SPA;
	}


	
	//绘制所有的曹植
	public void showData(Image imgTitle,List<Player> players,Graphics g)
	{
		System.out.println("这是LayerData中读取数据的专门绘制的数据曹植");
		//绘制标题
		g.drawImage(imgTitle, this.x+PADDING, this.y+PADDING, null);
		//获取现在的分数
		int nowPoint=this.dto.getNowPoint();
		//循环绘制记录
		for(int i=0;i<MAX_ROW;i++)
		{
			//获得一条玩家记录
			Player pla=players.get(i);
			//获得该分数
			int recodePoint=pla.getPoint();
			//System.out.println(pla.getName());
			//System.out.println(recodePoint);
			//计算现在分数与记录分数比值
			double percent=(double)nowPoint/pla.getPoint();
			//如果已经破了记录，比值为100%
			percent=percent>1?1.0:percent;
			//绘制分数记录
			String strPoint=(recodePoint==0?null:Integer.toString(recodePoint));
			this.drawRect(START_Y+i*(RECT_H+SPA), 
					pla.getName(), strPoint, 
					percent,  g);	
		}
	}
}
