package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerLevel extends Layer {
	

	
	private static final int IMG_LV_W=Img.LEVEL.getWidth(null);

	public LayerLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g)
	{
		System.out.println("调用LayerLevel中的paint函数");
		System.out.println("调用LayerLevel中的createWindow函数");
		this.createWindow(g);
		//窗口标题
		int centerX=this.w-IMG_LV_W>>1;
		g.drawImage(Img.LEVEL, this.x+centerX, this.y+PADDING, null);
		//显示等级
		this.drawNumberLeftPad(centerX, 64, this.dto.getNowLevel(), 2, g);
	}
	


}
