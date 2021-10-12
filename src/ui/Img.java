package ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import config.GameConfig;

public class Img {
	
	private Img(){}
	
	//窗口图片
	public static Image WINDOW=new ImageIcon("graphics/window/Window.png").getImage();
	
	//个人签名
	public static Image SIGN=new ImageIcon("graphics/string/sign.png").getImage();
	
	//矩形曹植
	public static  Image RECT=new ImageIcon("graphics/window/rect.png").getImage();
	
	//数字图片
	public static  Image NUMBER=new ImageIcon("graphics/string/num.png").getImage();
	
	//数据库窗口
	public static Image DB=new ImageIcon("graphics/string/db.png").getImage();
	
	//本地记录窗口
	public  static Image DISK=new ImageIcon("graphics/string/disk.png").getImage();
	
	//方块图片窗口
	public static Image ACT=new ImageIcon("graphics/game/rect.png").getImage();
	
	//标题图片
	public static  Image LEVEL=new ImageIcon("graphics/string/level.png").getImage();
	
	//窗口标题（分数）
	public static  Image POINT=new ImageIcon("graphics/string/point.png").getImage();
	
	//窗口标题（消行）
	public static Image RMLINE=new ImageIcon("graphics/string/rmline.png").getImage();
	
	//阴影消行
	public static Image SHADOW=new ImageIcon("graphics/game/shodow.png").getImage();
	
	//开始按钮
	public static ImageIcon BTN_START=new ImageIcon("graphics/string/strat.png");
	
	//设置按钮
	public static ImageIcon BTN_CONFIG=new ImageIcon("graphics/string/config.png");
	
	//暂停图片
	public static Image PAUSE=new ImageIcon("graphics/string/pause.png").getImage();
	
	//下一个图片数组
	public static Image[] NEXT_ACT;
	
	//下一个图片数组
	public static List<Image> BG_LIST;
	
	static{
		//下一个方块图片
		NEXT_ACT=new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		for(int i=0;i<NEXT_ACT.length;i++)
		{
			NEXT_ACT[i]=new ImageIcon("graphics/game/"+i+".png").getImage();
		}
		
		//背景图片数组
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
