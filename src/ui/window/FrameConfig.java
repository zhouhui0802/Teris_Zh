package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Control.GameControl;
import util.FrameUtil;

//设计用户设置控制面板
public class FrameConfig extends JFrame{
	
	private JButton btnOk=new JButton("确定");
	
	private JButton btnCancel=new JButton("取消");
	
	private JButton btnUser=new JButton("应用");
	
	private TextCtrl[] keyText=new TextCtrl[8];
	
	private JLabel errorMsg=new JLabel();
	
	private JList skinList=null;
	
	private JPanel skinView=null;
	
	private DefaultListModel skinData=new DefaultListModel();
	
	private final static Image IMG_PSP=new ImageIcon("data/psp.jpg").getImage();
	
	private Image[] skinViewList=null;
	
	private final static String[] METHOD_NAMES={
		"keyRight","keyLeft","keyUp","keyDown",
		"keyFunLeft","keyFunUp","keyFunRight","keyFunDown"
	};
	
	private GameControl gameControl;
	
	private final static String PATH="data/control.dat";
	
	public FrameConfig(GameControl gameControl)
	{
		System.out.println("进入FrameConfig中的构造函数");
		//获得游戏控制对象
		this.gameControl=gameControl;
		
		//设置布局管理为边界布局
		this.setLayout(new BorderLayout());
		this.setTitle("配置");
		
		//初始化按键输入框
		System.out.println("进入FrameConfig中的initKeyText函数");
		this.initKeyText();
		System.out.println("进入FrameConfig中的initKeyText函数结束");
		
		//添加主面板
		this.add(createMainPanel(),BorderLayout.CENTER);
		
		//添加按钮面板
		this.add(this.createButtonPanel(),BorderLayout.SOUTH);
		this.setSize(600,360);
		
		//设置不能改变大小
		this.setResizable(false);
		
		//居中
		FrameUtil.setFrameCenter(this);
	}
	
	//创建主面板
	private JTabbedPane createMainPanel()
	{
		JTabbedPane jtp=new JTabbedPane();
		jtp.addTab("控制设置",this.createControlPanel());
		jtp.addTab("皮肤设置",this.createSkinPanel());
		return jtp;
	}
	
	
	//创建按钮面板
	private JPanel createButtonPanel()
	{
		//创建按钮面板，游戏布局
		JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		//给确定按钮添加事件监听
		this.btnOk.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(writeConfig())
				{
					setVisible(false);
					gameControl.setOver();
				}

			}		
		});
		this.errorMsg.setForeground(Color.RED);
		jp.add(this.errorMsg);	
		jp.add(this.btnOk);
		jp.add(this.btnCancel);		
		this.btnCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//cancelButtonEvent();
				setVisible(false);
				gameControl.setOver();
			}		
		});
		jp.add(this.btnCancel);	
		this.btnUser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				writeConfig();
				gameControl.repaint();

			}
			
		});
		jp.add(this.btnUser);
		return jp;
	}
	
	private void okButtonEvent()
	{
		System.out.println("执行FrameConfig中的okButtonEvent函数");
		this.writeConfig();
		this.setVisible(false);
	}
	
	//取消按钮
	private void cancelButtonEvent()
	{
		System.out.println("执行FrameConfig中的cancelButtonEvent函数");
		this.setVisible(false);
	}
	
	private boolean writeConfig()
	{
		HashMap<Integer, String> keySet=new HashMap<Integer,String>();
		for(int i=0;i<this.keyText.length;i++)
		{
			int keyCode=this.keyText[i].getKeyCode();
			if(keyCode==0)
			{
				this.errorMsg.setText("错误按键");
				return false;
			}
			keySet.put(this.keyText[i].getKeyCode(), this.keyText[i].getMethodName());
			System.out.println("this.keyText[i].getKeyCode()="+this.keyText[i].getKeyCode()+": "+"this.keyText[i].getMethodName()="+this.keyText[i].getMethodName());
		}
		
		if(keySet.size()!=8)
		{
			this.errorMsg.setText("重复按键");
			return false;
		}
		try{
			//切换皮肤
			this.skinData.get(this.skinList.getSelectedIndex());
			//写入控制配置
			ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
		}catch(Exception e)
		{
			this.errorMsg.setText(e.getMessage());
			return false;
		}
		
		System.out.println("写入成功");
		return true;
	}
	

	
	private JPanel createSkinPanel()
	{
		JPanel panel=new JPanel(new BorderLayout());
		File dir=new File("graphics/background");
		File[] files=dir.listFiles();
		this.skinViewList=new Image[files.length];
		for(int i=0;i<files.length;i++)
		{
			//增加选项
			this.skinData.addElement(files[i].getName());
			//增加预览图
			this.skinViewList[i]=new ImageIcon(files[i].getPath()).getImage();
		}
		//添加列表数据到列表
		this.skinList=new JList(this.skinData);
		//设置默认选择第一个
		this.skinList.setSelectedIndex(0);
		this.skinList.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				repaint();
			}	
		});
		this.skinView=new JPanel(){
			public void paintComponent(Graphics g)
			{
				Image showImg=skinViewList[skinList.getSelectedIndex()];
				int x=this.getWidth()-showImg.getWidth(null)>>1;
				int y=this.getHeight()-showImg.getHeight(null)>>1;
				g.drawImage(showImg,0,0,null);
			}
		};
		
		panel.add(new JScrollPane(this.skinList),BorderLayout.WEST);
		panel.add(this.skinView,BorderLayout.CENTER);
		return panel;
	}
	
	//玩家控制面板设置
	private JPanel createControlPanel()
	{
		
		JPanel jp=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				g.drawImage(IMG_PSP,0,0,null);
			}
		};
		
		//设置布局管理
		jp.setLayout(null);
		for(int i=0;i<keyText.length;i++)
		{
			jp.add(keyText[i]);
		}
		return jp;
	}
	
	
	
	private void initKeyText()
	{
		int x=15;
		int y=56;
		int w=64;
		int h=20;
		for(int i=0;i<4;i++)
		{
			keyText[i]=new TextCtrl(x,y,w,h,0,METHOD_NAMES[i]);
			y+=28;
		}
		x=560;
		y=56;
		for(int i=4;i<8;i++)
		{
			keyText[i]=new TextCtrl(x,y,w,h,0,METHOD_NAMES[i]);
			y+=28;
		}
		try{
			ObjectInputStream ois= new ObjectInputStream(new FileInputStream(PATH));
			HashMap<Integer,String> cfgset=(HashMap)ois.readObject();
			ois.close();
			Set<Entry<Integer,String>> entryset=cfgset.entrySet();
			for(Entry<Integer,String> e: entryset)
			{
				System.out.println(e.getKey()+"-->"+e.getValue());
				for(TextCtrl tc: keyText)
				{
					if(tc.getMethodName().equals(e.getValue()))
					{
						tc.setKeyCode(e.getKey());
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
