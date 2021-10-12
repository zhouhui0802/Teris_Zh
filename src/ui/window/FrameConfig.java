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

//����û����ÿ������
public class FrameConfig extends JFrame{
	
	private JButton btnOk=new JButton("ȷ��");
	
	private JButton btnCancel=new JButton("ȡ��");
	
	private JButton btnUser=new JButton("Ӧ��");
	
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
		System.out.println("����FrameConfig�еĹ��캯��");
		//�����Ϸ���ƶ���
		this.gameControl=gameControl;
		
		//���ò��ֹ���Ϊ�߽粼��
		this.setLayout(new BorderLayout());
		this.setTitle("����");
		
		//��ʼ�����������
		System.out.println("����FrameConfig�е�initKeyText����");
		this.initKeyText();
		System.out.println("����FrameConfig�е�initKeyText��������");
		
		//��������
		this.add(createMainPanel(),BorderLayout.CENTER);
		
		//��Ӱ�ť���
		this.add(this.createButtonPanel(),BorderLayout.SOUTH);
		this.setSize(600,360);
		
		//���ò��ܸı��С
		this.setResizable(false);
		
		//����
		FrameUtil.setFrameCenter(this);
	}
	
	//���������
	private JTabbedPane createMainPanel()
	{
		JTabbedPane jtp=new JTabbedPane();
		jtp.addTab("��������",this.createControlPanel());
		jtp.addTab("Ƥ������",this.createSkinPanel());
		return jtp;
	}
	
	
	//������ť���
	private JPanel createButtonPanel()
	{
		//������ť��壬��Ϸ����
		JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		//��ȷ����ť����¼�����
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
		System.out.println("ִ��FrameConfig�е�okButtonEvent����");
		this.writeConfig();
		this.setVisible(false);
	}
	
	//ȡ����ť
	private void cancelButtonEvent()
	{
		System.out.println("ִ��FrameConfig�е�cancelButtonEvent����");
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
				this.errorMsg.setText("���󰴼�");
				return false;
			}
			keySet.put(this.keyText[i].getKeyCode(), this.keyText[i].getMethodName());
			System.out.println("this.keyText[i].getKeyCode()="+this.keyText[i].getKeyCode()+": "+"this.keyText[i].getMethodName()="+this.keyText[i].getMethodName());
		}
		
		if(keySet.size()!=8)
		{
			this.errorMsg.setText("�ظ�����");
			return false;
		}
		try{
			//�л�Ƥ��
			this.skinData.get(this.skinList.getSelectedIndex());
			//д���������
			ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
		}catch(Exception e)
		{
			this.errorMsg.setText(e.getMessage());
			return false;
		}
		
		System.out.println("д��ɹ�");
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
			//����ѡ��
			this.skinData.addElement(files[i].getName());
			//����Ԥ��ͼ
			this.skinViewList[i]=new ImageIcon(files[i].getPath()).getImage();
		}
		//����б����ݵ��б�
		this.skinList=new JList(this.skinData);
		//����Ĭ��ѡ���һ��
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
	
	//��ҿ����������
	private JPanel createControlPanel()
	{
		
		JPanel jp=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				g.drawImage(IMG_PSP,0,0,null);
			}
		};
		
		//���ò��ֹ���
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
