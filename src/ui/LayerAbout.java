package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerAbout extends Layer {

	private static Image IMG_SIGN=new ImageIcon("graphics/string/sign.png").getImage();
	public LayerAbout(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g)
	{
		System.out.println("����LayerAbout�е�paint����");
		System.out.println("����LayerAbout�е�createWindow����");
		this.createWindow(g);
		System.out.println("����LayerAbout�е�drawImageAtCenter����");
		this.drawImageAtCenter(Img.SIGN, g);
	}

}
