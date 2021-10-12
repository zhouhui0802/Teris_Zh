package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerDisk extends LayerData{


	
	public LayerDisk(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	public void paint(Graphics g)
	{
		System.out.println("调用LayerDisk中的paint函数");
		System.out.println("调用LayerDisk中的createWindow函数");
		this.createWindow(g);
		System.out.println("调用LayerDisk中的showData函数");
		this.showData(Img.DISK, this.dto.getDiskRecode(), g);
	}
}
