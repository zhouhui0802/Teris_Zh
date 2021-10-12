package ui.window;

import java.awt.*;

import javax.swing.JFrame;



import util.FrameUtil;
import config.FrameConfig;
import config.GameConfig;

public class JFrameGame extends JFrame{
	
	public JFrameGame(JPanelGame panelGame)
	{
		//�����Ϸ����
		FrameConfig fCfg=GameConfig.getFrameConfig();
		
		//���ñ���
		this.setTitle(fCfg.getTitle());
		
		//����Ĭ�Ϲر�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���ô��ڴ�С
		this.setSize(fCfg.getWidth(),fCfg.getHeight());
		
		//�������û��ı䴰�ڴ�С
		this.setResizable(false);	
		
		//������λ�������ڵ�����Ļ���ж�
		FrameUtil.setFrameCenter(this);	
		
		//����Ĭ��ƽ��
		this.setContentPane(panelGame);
		
		//Ĭ�ϴ���Ϊ��ʾ
		this.setVisible(true);
	}
}
