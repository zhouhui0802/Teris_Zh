package ui;

import java.awt.Graphics;

public class LayerButton extends Layer {

	public LayerButton(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("调用LayerButton中的paint函数");
		System.out.println("调用LayerButton中的createWindow函数");
		this.createWindow(g);
	}

}
