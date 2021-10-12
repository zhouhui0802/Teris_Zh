package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.*;

import config.GameConfig;
import dto.Player;

public abstract class LayerData extends Layer{

	//��������������ļ�
	private static final int MAX_ROW=GameConfig.getDataConfig().getMaxRow();
	
	//��ֵ���⾶
	private static final int RECT_H=IMG_RECT_H+4;
	
	//���
	private static  int SPA=0;
	
	//��ʼY����
	private static int START_Y=0;
	
	protected LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		
		//�����¼����ʱ���
		SPA=(this.h-RECT_H*5-(PADDING<<1)-Img.DB.getHeight(null))/MAX_ROW;
		//������ʼY����
		START_Y=PADDING+Img.DB.getHeight(null)+SPA;
	}


	
	//�������еĲ�ֲ
	public void showData(Image imgTitle,List<Player> players,Graphics g)
	{
		System.out.println("����LayerData�ж�ȡ���ݵ�ר�Ż��Ƶ����ݲ�ֲ");
		//���Ʊ���
		g.drawImage(imgTitle, this.x+PADDING, this.y+PADDING, null);
		//��ȡ���ڵķ���
		int nowPoint=this.dto.getNowPoint();
		//ѭ�����Ƽ�¼
		for(int i=0;i<MAX_ROW;i++)
		{
			//���һ����Ҽ�¼
			Player pla=players.get(i);
			//��ø÷���
			int recodePoint=pla.getPoint();
			//System.out.println(pla.getName());
			//System.out.println(recodePoint);
			//�������ڷ������¼������ֵ
			double percent=(double)nowPoint/pla.getPoint();
			//����Ѿ����˼�¼����ֵΪ100%
			percent=percent>1?1.0:percent;
			//���Ʒ�����¼
			String strPoint=(recodePoint==0?null:Integer.toString(recodePoint));
			this.drawRect(START_Y+i*(RECT_H+SPA), 
					pla.getName(), strPoint, 
					percent,  g);	
		}
	}
}
