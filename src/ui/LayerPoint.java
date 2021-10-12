package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import config.GameConfig;

public class LayerPoint extends Layer {

	//升级行数
	public static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();

	private  final int rmLineY;
	
	//分数Y坐标
	private  final int pointY;
	//分数X坐标
	private final int comX;
	
	//分数最大位数
	private static final int POINT_BIT=5;
	
	//经验值的Y坐标
	private final int expY;
	

	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		//初始化分数显示的X坐标
		this.comX=this.w-IMG_NUMBER_W*POINT_BIT-PADDING;
		//初始化分数显示的Y坐标
		this.pointY=PADDING;
		//初始化消行显示的Y坐标
		this.rmLineY=this.pointY+Img.RMLINE.getHeight(null)+PADDING;
		//初始化经验值显示的Y坐标
		this.expY=this.rmLineY+Img.POINT.getHeight(null)+PADDING;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("调用LayerPoint中的paint函数");
		System.out.println("调用LayerPoint中的createWindow函数");
		this.createWindow(g);
		
		//绘制标题
		g.drawImage(Img.POINT,this.x+PADDING,this.y+pointY,null);
		//显示分数
		System.out.println("在LayerPoint中调用drawNumberLeftPad方法");
		this.drawNumberLeftPad(comX, pointY, this.dto.getNowPoint(), POINT_BIT, g);
		//绘制标题
		g.drawImage(Img.RMLINE,this.x+PADDING,this.y+rmLineY,null);
		//显示消行
		System.out.println("在LayerPoint中调用drawNumberLeftPad方法");
		this.drawNumberLeftPad(comX, rmLineY, this.dto.getNowRemoveLine(), POINT_BIT, g);
		//绘制曹植
		int rmLine=this.dto.getNowRemoveLine();
		this.drawRect(expY,"下一级",null,(double)(rmLine%LEVEL_UP)/(double)LEVEL_UP,g);
	}

}
