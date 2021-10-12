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
	
	//游戏界面层
	private JPanelGame panelGame;
	
	//游戏逻辑层
	private GameService gameService;
	
	//游戏控制窗口
	private FrameConfig frameConfig;
	
	//游戏控制行为
	private Map<Integer, Method> actionList;
	
	//游戏线程
	private Thread gameThread=null;
	
	//游戏数据源
	private GameDto dto=null;

	//保存游戏分数的窗口
	private JFrameSavePoint frameSavePoint;
	

	public GameControl()
	{
		//创建游戏数据源
		System.out.println("GameControl初始化的第一步，创建游戏数据源");
		this.dto=new GameDto();
		System.out.println("GameControl初始化的第一步，创建游戏数据源结束");
		
		//创建游戏逻辑快
		System.out.println("GameControl初始化的第二步，创建游戏逻辑块");
		this.gameService=new GameTetris(dto);
		System.out.println("GameControl初始化的第二步，创建游戏逻辑块结束");
		
		//开始读取数据
		System.out.println("GameControl初始化的第三步，通过数据库读取数据");
		this.dataA=new DataTest();//获取数据库的数据，接口已经写好
		this.gameService.setDbRecode(dataA.loadData());
		System.out.println("GameControl初始化的第三步，数据库读取数据结束");
		
		//从数据接口获取到本地磁盘数据
		System.out.println("GameControl初始化的第四步，读取本地的数据");
		dataB=createDataObject(GameConfig.getDataConfig().getDataB());
		//设置本地磁盘记录到游戏
		this.dto.setDiskRecode(dataB.loadData());
		System.out.println("GameControl初始化的第四步，读取本地数据结束");
		
		//创建游戏面板
		System.out.println("GameControl初始化的第五步，创建游戏面板，将逻辑游戏快gameControl和游戏方块dto传入面板");
		this.panelGame=new JPanelGame(this,dto);  //创建游戏面板，将游戏控制和俄罗斯方块传入
		System.out.println("GameControl初始化的第五步，创建游戏面板结束");
		
		//读取用户控制设备
		System.out.println("GameControl初始化的第六步，读取用户控制设备");
		this.setControlConfig();
		System.out.println("GameControl初始化的第六步，读取用户控制设备结束");
		
		//初始化用户配置窗口
		System.out.println("GameControl初始化的第七步，初始化用户配置窗口");
		this.frameConfig=new FrameConfig(this);
		System.out.println("GameControl初始化的第七步，初始化用户配置窗口结束");
		
		//初始化保存分数窗口
		System.out.println("GameControl初始化的第八步，初始化保存分数窗口");
		this.frameSavePoint=new JFrameSavePoint(this);
		System.out.println("GameControl初始化的第八步，初始化保存分数窗口结束");
		
		//创建游戏窗口，安装游戏面板
		System.out.println("GameControl初始化的第九步，创建游戏窗口，安装游戏面板");
		new JFrameGame(this.panelGame);
		System.out.println("GameControl初始化的第九步，创建游戏窗口，安装游戏面板结束");
		
	}
	
	//读取用户控制设备
	private void setControlConfig()
	{
		//创建键盘跟方法名的用户数组
		System.out.println("进入GameControl中的setControlConfig中的方法，创建键盘跟方法名的用户数组");
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
	
	//创建数据对象
	private Data createDataObject(DataInterfaceConfig cfg)
	{
		System.out.println("进入GameControl中的createDataObject函数，将GameConfig.getDataConfig().getDataB()获取到的内容传入");
		try{
			//获得类对象
			Class<?> cls=Class.forName(cfg.getClassName());
			System.out.println("cfg.getClassName()="+cfg.getClassName());
			//获得构造器
			Constructor<?> ctr=cls.getConstructor(HashMap.class);
			//创建对象
			System.out.println("cfg.getParam()="+cfg.getParam());
			System.out.println("进入GameControl中的createDataObject函数结束");
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
	
	//根据玩家控制来决定行为
	public void actionByKeyCode(int keyCode)
	{
		System.out.println("进入GameControl中的actionByKeyCode函数");
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
	
	//显示玩家控制窗口
	public void showUserConfig()
	{
		System.out.println("点击设置按钮，对用户操作进行设置");
		this.frameConfig.setVisible(true);
	}
	
	public void setOver()
	{
		System.out.println("进入GameControl中的setOver函数，再次执行setControlConfig");
		this.panelGame.repaint();
		this.setControlConfig();
	}
	
	//开始按钮事件
	public void start()
	{
		System.out.println("点击开始按钮");
		
		//按钮设置为不可点击
		this.panelGame.buttonSwitch(false);
		
		//关闭窗口
		this.frameConfig.setVisible(false);
		this.frameSavePoint.setVisible(false);
		
		//游戏数据初始化
		System.out.println("当点击开始按钮之后，游戏开始");
		this.gameService.startGame();
		System.out.println("当点击开始按钮之后，游戏开始操作结束");
		
		//创建线程对象
		this.gameThread=new MainThread();
		
		//启动线程
		System.out.println("启动GameControl中的游戏线程");
		this.gameThread.start();
		
		//刷新面板
		this.panelGame.repaint();
	}
	
	//失败之后的处理
	public void afterLose()
	{
		System.out.println("当游戏失败之后进入afterLose函数");
		if(!this.dto.isCheat())
		{
			//显示保存得分窗口
			this.frameSavePoint.showWindow(this.dto.getNowPoint());
		}
		
		//使得按钮可以点击
		this.panelGame.buttonSwitch(true);
	}
	
	private class MainThread extends Thread
	{

		public void run()
		{
			System.out.println("启动GameControl中的游戏线程之后，开始进入run()函数");
			//刷新画面
			panelGame.repaint();
			
			//主循环
			while(dto.isStart())
			{
				try{
					//等待0.5秒 线程睡眠
					Thread.sleep(dto.getSleepTime());
					
					//如果暂停，不执行主线程
					if(dto.isPause())
					{
						continue;
					}
					
					//方块下落,游戏主要行为
					gameService.mainAction();
					
					//刷新画面
					panelGame.repaint();
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			afterLose();
		}

	}
	
	//保存分数
	public void savePoint(String name)
	{
		System.out.println("进入GameControl中的savePoint函数");
		Player pla=new Player(name,this.dto.getNowPoint());
		this.dataB.saveData(pla);
		this.dto.setDiskRecode(dataB.loadData());
		this.panelGame.repaint();
	}
	
	//刷新画面
	public void repaint()
	{
		this.panelGame.repaint();
	}

	
}
