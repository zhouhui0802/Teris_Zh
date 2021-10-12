package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import config.GameConfig;

public class LayerPoint extends Layer {

	//��������
	public static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();

	private  final int rmLineY;
	
	//����Y����
	private  final int pointY;
	//����X����
	private final int comX;
	
	//�������λ��
	private static final int POINT_BIT=5;
	
	//����ֵ��Y����
	private final int expY;
	

	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		//��ʼ��������ʾ��X����
		this.comX=this.w-IMG_NUMBER_W*POINT_BIT-PADDING;
		//��ʼ��������ʾ��Y����
		this.pointY=PADDING;
		//��ʼ��������ʾ��Y����
		this.rmLineY=this.pointY+Img.RMLINE.getHeight(null)+PADDING;
		//��ʼ������ֵ��ʾ��Y����
		this.expY=this.rmLineY+Img.POINT.getHeight(null)+PADDING;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("����LayerPoint�е�paint����");
		System.out.println("����LayerPoint�е�createWindow����");
		this.createWindow(g);
		
		//���Ʊ���
		g.drawImage(Img.POINT,this.x+PADDING,this.y+pointY,null);
		//��ʾ����
		System.out.println("��LayerPoint�е���drawNumberLeftPad����");
		this.drawNumberLeftPad(comX, pointY, this.dto.getNowPoint(), POINT_BIT, g);
		//���Ʊ���
		g.drawImage(Img.RMLINE,this.x+PADDING,this.y+rmLineY,null);
		//��ʾ����
		System.out.println("��LayerPoint�е���drawNumberLeftPad����");
		this.drawNumberLeftPad(comX, rmLineY, this.dto.getNowRemoveLine(), POINT_BIT, g);
		//���Ʋ�ֲ
		int rmLine=this.dto.getNowRemoveLine();
		this.drawRect(expY,"��һ��",null,(double)(rmLine%LEVEL_UP)/(double)LEVEL_UP,g);
	}

}
