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
		System.out.println("����LayerButton�е�paint����");
		System.out.println("����LayerButton�е�createWindow����");
		this.createWindow(g);
	}

}
