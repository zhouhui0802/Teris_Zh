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
		System.out.println("����LayerNext�е�paint����");
		System.out.println("����LayerNext�е�createWindow����");
		this.createWindow(g);
		if(this.dto.isStart())
		{
			this.drawImageAtCenter(Img.NEXT_ACT[this.dto.getNext()], g);
		}		
	}
	

}
