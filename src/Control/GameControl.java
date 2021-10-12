package Control;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;
import dao.DataDisk;
import dao.DataTest;
import dto.GameDto;
import dto.Player;
import service.GameService;
import service.GameTetris;
import ui.window.JFrameGame;
import ui.window.JFrameSavePoint;
import ui.window.JPanelGame;
import ui.window.FrameConfig;
import util.GameFunction;

public class GameControl {
	
	private Data dataA;
	private Data dataB;
	
	//��Ϸ�����
	private JPanelGame panelGame;
	
	//��Ϸ�߼���
	private GameService gameService;
	
	//��Ϸ���ƴ���
	private FrameConfig frameConfig;
	
	//��Ϸ������Ϊ
	private Map<Integer, Method> actionList;
	
	//��Ϸ�߳�
	private Thread gameThread=null;
	
	//��Ϸ����Դ
	private GameDto dto=null;

	//������Ϸ�����Ĵ���
	private JFrameSavePoint frameSavePoint;
	

	public GameControl()
	{
		//������Ϸ����Դ
		System.out.println("GameControl��ʼ���ĵ�һ����������Ϸ����Դ");
		this.dto=new GameDto();
		System.out.println("GameControl��ʼ���ĵ�һ����������Ϸ����Դ����");
		
		//������Ϸ�߼���
		System.out.println("GameControl��ʼ���ĵڶ�����������Ϸ�߼���");
		this.gameService=new GameTetris(dto);
		System.out.println("GameControl��ʼ���ĵڶ�����������Ϸ�߼������");
		
		//��ʼ��ȡ����
		System.out.println("GameControl��ʼ���ĵ�������ͨ�����ݿ��ȡ����");
		this.dataA=new DataTest();//��ȡ���ݿ�����ݣ��ӿ��Ѿ�д��
		this.gameService.setDbRecode(dataA.loadData());
		System.out.println("GameControl��ʼ���ĵ����������ݿ��ȡ���ݽ���");
		
		//�����ݽӿڻ�ȡ�����ش�������
		System.out.println("GameControl��ʼ���ĵ��Ĳ�����ȡ���ص�����");
		dataB=createDataObject(GameConfig.getDataConfig().getDataB());
		//���ñ��ش��̼�¼����Ϸ
		this.dto.setDiskRecode(dataB.loadData());
		System.out.println("GameControl��ʼ���ĵ��Ĳ�����ȡ�������ݽ���");
		
		//������Ϸ���
		System.out.println("GameControl��ʼ���ĵ��岽��������Ϸ��壬���߼���Ϸ��gameControl����Ϸ����dto�������");
		this.panelGame=new JPanelGame(this,dto);  //������Ϸ��壬����Ϸ���ƺͶ���˹���鴫��
		System.out.println("GameControl��ʼ���ĵ��岽��������Ϸ������");
		
		//��ȡ�û������豸
		System.out.println("GameControl��ʼ���ĵ���������ȡ�û������豸");
		this.setControlConfig();
		System.out.println("GameControl��ʼ���ĵ���������ȡ�û������豸����");
		
		//��ʼ���û����ô���
		System.out.println("GameControl��ʼ���ĵ��߲�����ʼ���û����ô���");
		this.frameConfig=new FrameConfig(this);
		System.out.println("GameControl��ʼ���ĵ��߲�����ʼ���û����ô��ڽ���");
		
		//��ʼ�������������
		System.out.println("GameControl��ʼ���ĵڰ˲�����ʼ�������������");
		this.frameSavePoint=new JFrameSavePoint(this);
		System.out.println("GameControl��ʼ���ĵڰ˲�����ʼ������������ڽ���");
		
		//������Ϸ���ڣ���װ��Ϸ���
		System.out.println("GameControl��ʼ���ĵھŲ���������Ϸ���ڣ���װ��Ϸ���");
		new JFrameGame(this.panelGame);
		System.out.println("GameControl��ʼ���ĵھŲ���������Ϸ���ڣ���װ��Ϸ������");
		
	}
	
	//��ȡ�û������豸
	private void setControlConfig()
	{
		//�������̸����������û�����
		System.out.println("����GameControl�е�setControlConfig�еķ������������̸����������û�����");
		this.actionList=new HashMap<Integer,Method>();
		try{
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("data/control.dat"));
			HashMap<Integer,String> cfgset=(HashMap<Integer,String>)ois.readObject();
			Set<Entry<Integer,String>> entryset=cfgset.entrySet();
			for(Entry<Integer,String> e: entryset)
			{
				System.out.println("e.getKey()="+e.getKey()+": "+"e.getValue()="+e.getValue());
				actionList.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	//�������ݶ���
	private Data createDataObject(DataInterfaceConfig cfg)
	{
		System.out.println("����GameControl�е�createDataObject��������GameConfig.getDataConfig().getDataB()��ȡ�������ݴ���");
		try{
			//��������
			Class<?> cls=Class.forName(cfg.getClassName());
			System.out.println("cfg.getClassName()="+cfg.getClassName());
			//��ù�����
			Constructor<?> ctr=cls.getConstructor(HashMap.class);
			//��������
			System.out.println("cfg.getParam()="+cfg.getParam());
			System.out.println("����GameControl�е�createDataObject��������");
			return (Data)ctr.newInstance(cfg.getParam());
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	

	public void test()
	{
		//this.gameService.gameTest();
		this.panelGame.repaint();
	}
	
	//������ҿ�����������Ϊ
	public void actionByKeyCode(int keyCode)
	{
		System.out.println("����GameControl�е�actionByKeyCode����");
		try{
			if(this.actionList.containsKey( keyCode))
			{
					this.actionList.get(keyCode).invoke(this.gameService);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		this.panelGame.repaint();
	}
	
	//��ʾ��ҿ��ƴ���
	public void showUserConfig()
	{
		System.out.println("������ð�ť�����û�������������");
		this.frameConfig.setVisible(true);
	}
	
	public void setOver()
	{
		System.out.println("����GameControl�е�setOver�������ٴ�ִ��setControlConfig");
		this.panelGame.repaint();
		this.setControlConfig();
	}
	
	//��ʼ��ť�¼�
	public void start()
	{
		System.out.println("�����ʼ��ť");
		
		//��ť����Ϊ���ɵ��
		this.panelGame.buttonSwitch(false);
		
		//�رմ���
		this.frameConfig.setVisible(false);
		this.frameSavePoint.setVisible(false);
		
		//��Ϸ���ݳ�ʼ��
		System.out.println("�������ʼ��ť֮����Ϸ��ʼ");
		this.gameService.startGame();
		System.out.println("�������ʼ��ť֮����Ϸ��ʼ��������");
		
		//�����̶߳���
		this.gameThread=new MainThread();
		
		//�����߳�
		System.out.println("����GameControl�е���Ϸ�߳�");
		this.gameThread.start();
		
		//ˢ�����
		this.panelGame.repaint();
	}
	
	//ʧ��֮��Ĵ���
	public void afterLose()
	{
		System.out.println("����Ϸʧ��֮�����afterLose����");
		if(!this.dto.isCheat())
		{
			//��ʾ����÷ִ���
			this.frameSavePoint.showWindow(this.dto.getNowPoint());
		}
		
		//ʹ�ð�ť���Ե��
		this.panelGame.buttonSwitch(true);
	}
	
	private class MainThread extends Thread
	{

		public void run()
		{
			System.out.println("����GameControl�е���Ϸ�߳�֮�󣬿�ʼ����run()����");
			//ˢ�»���
			panelGame.repaint();
			
			//��ѭ��
			while(dto.isStart())
			{
				try{
					//�ȴ�0.5�� �߳�˯��
					Thread.sleep(dto.getSleepTime());
					
					//�����ͣ����ִ�����߳�
					if(dto.isPause())
					{
						continue;
					}
					
					//��������,��Ϸ��Ҫ��Ϊ
					gameService.mainAction();
					
					//ˢ�»���
					panelGame.repaint();
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			afterLose();
		}

	}
	
	//�������
	public void savePoint(String name)
	{
		System.out.println("����GameControl�е�savePoint����");
		Player pla=new Player(name,this.dto.getNowPoint());
		this.dataB.saveData(pla);
		this.dto.setDiskRecode(dataB.loadData());
		this.panelGame.repaint();
	}
	
	//ˢ�»���
	public void repaint()
	{
		this.panelGame.repaint();
	}

	
}
