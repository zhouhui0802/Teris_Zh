package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerNext extends Layer {


	
	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g)
	{
		System.out.println("调用LayerNext中的paint函数");
		System.out.println("调用LayerNext中的createWindow函数");
		this.createWindow(g);
		if(this.dto.isStart())
		{
			this.drawImageAtCenter(Img.NEXT_ACT[this.dto.getNext()], g);
		}		
	}
	

}
