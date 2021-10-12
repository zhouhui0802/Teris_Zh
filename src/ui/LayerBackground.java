package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerBackground extends Layer {

	private static Image IMG_GB_TEMP=new ImageIcon("graphics/background/01.jpg").getImage();
	
	public LayerBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("调用LayerBackground中的paint函数");
		int bgIdx=this.dto.getNowLevel()%Img.BG_LIST.size();
		g.drawImage(Img.BG_LIST.get(bgIdx), 0, 0,1162,654, null );
	}

}
