package ui.window;



import javax.swing.*;

import service.GameTetris;
import ui.Img;
import ui.Layer;
import Control.GameControl;
import Control.PlayerControl;
import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import dto.GameDto;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.*;

public class JPanelGame extends JPanel{
	
	private static final int BTN_SIZE_W=GameConfig.getFrameConfig().getButtonConfig().getButtonW();
	
	private static final int BTN_SIZE_H=GameConfig.getFrameConfig().getButtonConfig().getButtonH();
	
	private List<Layer>  layers=null;
	
	private JButton btnStart;
	
	private JButton btnConfig;
	
	private GameControl gameControl=null;
	
	public JPanelGame(GameControl gameControl,GameDto dto)
	{
		//连接游戏控制器
		this.gameControl=gameControl;
		
		//设置布局管理器为自由布局
		this.setLayout(null);
		
		//初始化组件
		System.out.println("进入JPanelGame中的初始化函数中的initComponent函数");
		this.initComponent();
		System.out.println("进入JPanelGame中的初始化函数中的initComponent函数结束");
		
		//初始化所有模块
		System.out.println("进入JPanelGame中的初始化函数中的initLayer函数");
		this.initLayer(dto);
		System.out.println("进入JPanelGame中的初始化函数中的initLayer函数结束");
		
		//安装键盘监听器
		this.addKeyListener(new PlayerControl(gameControl));
	}
	

	
	public void setGameControl(PlayerControl control)
	{
		this.addKeyListener(control);
	}
	
	private void initComponent()
	{
		System.out.println("进入initComponent函数");
		//初始化开始按钮
		this.btnStart=new JButton(Img.BTN_START);
		
		//设置开始按钮的位置
		this.btnStart.setBounds(GameConfig.getFrameConfig().getButtonConfig().getStartX(),
				GameConfig.getFrameConfig().getButtonConfig().getStartY(), 
				BTN_SIZE_W,BTN_SIZE_H);
		
		//给开始按钮添加事件监听
		this.btnStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gameControl.start();
				//返回焦点
				requestFocus();
			}
			
		});
		this.add(btnStart);
		
		this.btnConfig=new JButton(Img.BTN_CONFIG);
		this.btnConfig.setBounds(GameConfig.getFrameConfig().getButtonConfig().getUserConfigX(),
				GameConfig.getFrameConfig().getButtonConfig().getUserConfigY(), 
				BTN_SIZE_W,BTN_SIZE_H);
		this.btnConfig.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gameControl.showUserConfig();
			}
			
		});
		this.add(btnConfig);
	}
	
	
	private void initLayer(GameDto dto)
	{
		try {
			//获得游戏配置
			System.out.println("进入initLayer函数");
			FrameConfig fCfg=GameConfig.getFrameConfig();
			List<LayerConfig> layersCfg=fCfg.getLayersConfig();
			layers=new ArrayList<Layer>(layersCfg.size());
			System.out.println("layersCfg.size()="+layersCfg.size());
			for(LayerConfig layerCfg: layersCfg)
			{
			Class<?> cls=Class.forName(layerCfg.getClassName());
			System.out.println("layerCfg.getClassName()="+layerCfg.getClassName());
			Constructor ctr=cls.getConstructor(int.class,int.class,int.class,int.class);
			Layer layer=(Layer)ctr.newInstance(layerCfg.getX(),layerCfg.getY(),layerCfg.getW(),layerCfg.getH());
			layer.setDto(dto);
			layers.add(layer);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		System.out.println("开始执行JPanelGame中的paintComponent方法");
		super.paintComponent(g);	
		for(int i=0;i<layers.size();i++)
		{
			layers.get(i).paint(g);
		}		
	}
	
	public GameControl getGameControl() {
		return gameControl;
	}

	public void setGameControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}
	
	//控制按钮是否可以点击
	public void buttonSwitch(boolean onOff)
	{
		this.btnConfig.setEnabled(onOff);
		this.btnStart.setEnabled(onOff);
	}
	
}
