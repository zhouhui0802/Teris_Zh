package ui.window;

import java.awt.*;

import javax.swing.JFrame;



import util.FrameUtil;
import config.FrameConfig;
import config.GameConfig;

public class JFrameGame extends JFrame{
	
	public JFrameGame(JPanelGame panelGame)
	{
		//获得游戏配置
		FrameConfig fCfg=GameConfig.getFrameConfig();
		
		//设置标题
		this.setTitle(fCfg.getTitle());
		
		//设置默认关闭属性
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//设置窗口大小
		this.setSize(fCfg.getWidth(),fCfg.getHeight());
		
		//不允许用户改变窗口大小
		this.setResizable(false);	
		
		//将最终位置设置在电脑屏幕的中端
		FrameUtil.setFrameCenter(this);	
		
		//设置默认平板
		this.setContentPane(panelGame);
		
		//默认窗口为显示
		this.setVisible(true);
	}
}
