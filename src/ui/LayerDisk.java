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
		System.out.println("����LayerDisk�е�paint����");
		System.out.println("����LayerDisk�е�createWindow����");
		this.createWindow(g);
		System.out.println("����LayerDisk�е�showData����");
		this.showData(Img.DISK, this.dto.getDiskRecode(), g);
	}
}
