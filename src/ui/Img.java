package ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import config.GameConfig;

public class Img {
	
	private Img(){}
	
	//����ͼƬ
	public static Image WINDOW=new ImageIcon("graphics/window/Window.png").getImage();
	
	//����ǩ��
	public static Image SIGN=new ImageIcon("graphics/string/sign.png").getImage();
	
	//���β�ֲ
	public static  Image RECT=new ImageIcon("graphics/window/rect.png").getImage();
	
	//����ͼƬ
	public static  Image NUMBER=new ImageIcon("graphics/string/num.png").getImage();
	
	//���ݿⴰ��
	public static Image DB=new ImageIcon("graphics/string/db.png").getImage();
	
	//���ؼ�¼����
	public  static Image DISK=new ImageIcon("graphics/string/disk.png").getImage();
	
	//����ͼƬ����
	public static Image ACT=new ImageIcon("graphics/game/rect.png").getImage();
	
	//����ͼƬ
	public static  Image LEVEL=new ImageIcon("graphics/string/level.png").getImage();
	
	//���ڱ��⣨������
	public static  Image POINT=new ImageIcon("graphics/string/point.png").getImage();
	
	//���ڱ��⣨���У�
	public static Image RMLINE=new ImageIcon("graphics/string/rmline.png").getImage();
	
	//��Ӱ����
	public static Image SHADOW=new ImageIcon("graphics/game/shodow.png").getImage();
	
	//��ʼ��ť
	public static ImageIcon BTN_START=new ImageIcon("graphics/string/strat.png");
	
	//���ð�ť
	public static ImageIcon BTN_CONFIG=new ImageIcon("graphics/string/config.png");
	
	//��ͣͼƬ
	public static Image PAUSE=new ImageIcon("graphics/string/pause.png").getImage();
	
	//��һ��ͼƬ����
	public static Image[] NEXT_ACT;
	
	//��һ��ͼƬ����
	public static List<Image> BG_LIST;
	
	static{
		//��һ������ͼƬ
		NEXT_ACT=new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		for(int i=0;i<NEXT_ACT.length;i++)
		{
			NEXT_ACT[i]=new ImageIcon("graphics/game/"+i+".png").getImage();
		}
		
		//����ͼƬ����
		File dir=new File("graphics/background");
		File[] files=dir.listFiles();
		BG_LIST=new ArrayList();
		for(File file: files)
		{
			if(!file.isDirectory())
			{
				BG_LIST.add(new ImageIcon(file.getPath()).getImage());
			}
			
		}
	}
}
