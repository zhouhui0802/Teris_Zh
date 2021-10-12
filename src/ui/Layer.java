package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

public abstract class Layer {
	
	protected final int x;
	protected final int y;
	protected final int w;
	protected final int h;
	
	//内边距
	protected static final int PADDING;
	//边框宽度
	protected static final int BORDER;
	

	protected static final int IMG_NUMBER_W=Img.NUMBER.getWidth(null)/10;
	protected static final int IMG_NUMBER_H=Img.NUMBER.getHeight(null);
	
	
	static{
		FrameConfig fCfg=GameConfig.getFrameConfig();
		PADDING=fCfg.getPadding();
		BORDER=fCfg.getBorder();
	}
	
	
	private static int WINDOW_W=Img.WINDOW.getWidth(null);
	private static int WINDOW_H=Img.WINDOW.getHeight(null);
	


	protected static final int IMG_RECT_H=Img.RECT.getHeight(null);
	private static final int IMG_RECT_W=Img.RECT.getWidth(null);
	private  final int rectW;
	private static final Font EDF_FONT=new Font("黑体",Font.BOLD,20);
	
	protected GameDto dto=null;
	
	protected Layer(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.rectW=this.w-(PADDING<<1);
	}
	
	public GameDto getDto() {
		return dto;
	}

	public void setDto(GameDto dto) {
		this.dto = dto;
	}

	protected void drawNumberLeftPad(int x,int y,int num,int maxBit,Graphics g)
	{
		System.out.println("Layer中实现把要打印的数字转变为字符串");
		//把要打印的数字转变为字符串
		String strNum=Integer.toString(num);
		//循环绘制数字右对齐
		for(int i=0;i<maxBit;i++)
		{
			//判断是否满足绘制条件
			if(maxBit-i<=strNum.length())
			{
				//获得数字在字符串的下标
				int idx=i-maxBit+strNum.length();
				//把数字number的每一位取出
				int bit=strNum.charAt(idx)-'0';
				//绘制数字
				g.drawImage(Img.NUMBER,
						this.x+x+IMG_NUMBER_W*i,this.y+y, 
						this.x+x+IMG_NUMBER_W*(i+1),this.y+y+IMG_NUMBER_H,
						bit*IMG_NUMBER_W,0,
						(bit+1)*IMG_NUMBER_W,IMG_NUMBER_H,null);
			}

		}
	}
	
	
	protected void createWindow(Graphics g)
	{	
		System.out.println("Layer中实现创建游戏的各个窗口");
		//左上角
		g.drawImage(Img.WINDOW,x,y, x+BORDER,y+BORDER, 0, 0, BORDER, BORDER, null);
		//中上
		g.drawImage(Img.WINDOW,x+BORDER,y, x+w-BORDER,y+BORDER, BORDER, 0, WINDOW_W-BORDER, BORDER, null);
		//右上
		g.drawImage(Img.WINDOW,x+w-BORDER,y, x+w,y+BORDER, WINDOW_W-BORDER, 0, WINDOW_W, BORDER, null);
		//左中
		g.drawImage(Img.WINDOW,x,y+BORDER, x+BORDER,y+h-BORDER,0, BORDER, BORDER, WINDOW_H-BORDER, null);
		//中
		g.drawImage(Img.WINDOW,x+BORDER,y+BORDER, x+w-BORDER,y+h-BORDER,BORDER, BORDER, WINDOW_W-BORDER, WINDOW_H-BORDER, null);
		//右中
		g.drawImage(Img.WINDOW,x+w-BORDER,y+BORDER, x+w,y+h-BORDER, WINDOW_W-BORDER, BORDER, WINDOW_W, WINDOW_H-BORDER, null);
		//左下
		g.drawImage(Img.WINDOW,x,y+h-BORDER, x+BORDER,y+h, 0, WINDOW_H-BORDER, BORDER, WINDOW_H, null);
		//中下
		g.drawImage(Img.WINDOW,x+BORDER,y+h-BORDER, x+w-BORDER,y+h, BORDER, WINDOW_H-BORDER, WINDOW_W-BORDER, WINDOW_H, null);
		//右下
		g.drawImage(Img.WINDOW,x+w-BORDER,y+h-BORDER, x+w,y+h, WINDOW_W-BORDER, WINDOW_H-BORDER, WINDOW_W, WINDOW_H, null);
	}
	
	//绘制曹植
	protected void drawRect(int y,String title,String number,double percent,Graphics g)
	{
		System.out.println("Layer中实现绘制曹植");
		//参数初始化
		int rect_x=this.x+PADDING;
		int rect_y=this.y+y;
		//绘制背景
		g.setColor(Color.BLACK);
		g.fillRect(rect_x, rect_y,this.rectW, IMG_RECT_H+4);
		g.setColor(Color.WHITE);
		g.fillRect(rect_x+1, rect_y+1,this.rectW-2, IMG_RECT_H+2);
		g.setColor(Color.BLACK);
		g.fillRect(rect_x+2, rect_y+2,this.rectW-4, IMG_RECT_H);
		//绘制曹植
		//求出宽度
		int w=(int)(percent*(this.rectW-4));
		//求出颜色
		int subIdx=(int)(percent*IMG_RECT_W)-1;
		g.drawImage(Img.RECT, 
				rect_x+2,rect_y+2, 
				rect_x+2+w, rect_y+2+IMG_RECT_H, 
				subIdx, 0,subIdx+1, IMG_RECT_H, 
				null);
		g.setColor(Color.WHITE);
		g.setFont(EDF_FONT);
		g.drawString(title, rect_x+4, rect_y+22);
		if(number!=null)
		{
			g.drawString(number,rect_x+232,rect_y+22);
		}
	}
	
	protected void drawImageAtCenter(Image img,Graphics g)
	{
		System.out.println("Layer中实现把图片绘制到中心");
		int imgW=img.getWidth(null);
		int imgH=img.getHeight(null);
		g.drawImage(img, this.x+(this.w-imgW>>1), this.y+(this.h-imgH>>1), null);
	}
	
	
	abstract public void paint(Graphics g);
}
