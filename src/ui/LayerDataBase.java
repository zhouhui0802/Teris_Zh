package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import dto.Player;

public class LayerDataBase extends LayerData {



	
	public LayerDataBase(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("调用LayerDataBase中的paint函数");
		System.out.println("调用LayerDataBase中的createWindow函数");
		this.createWindow(g);
		System.out.println("调用LayerDataBase中的showData函数");
		this.showData(Img.DB, this.dto.getDbRecode(), g);
	}

}
