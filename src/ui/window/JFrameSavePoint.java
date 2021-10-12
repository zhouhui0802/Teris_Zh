package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Control.GameControl;
import util.FrameUtil;

public class JFrameSavePoint extends JFrame{
	
	private JButton btnOk=null;
	
	private JLabel jbpoint=null;
	
	private JTextField txName=null;
	
	private JLabel errMsg=null;
	
	private GameControl gameControl=null;
	
	public JFrameSavePoint(GameControl gameControl)
	{
		this.gameControl=gameControl;
		this.setTitle("保存记录");
		this.setSize(256,128);
		FrameUtil.setFrameCenter(this);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		this.createCom();
		
		this.createAction();
	}
	
	private void createAction()
	{
		this.btnOk.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String name=txName.getText();
				if(txName.getText().length()>16||name==null||"".equals(name))
				{
					errMsg.setText("请输入16位以上的名字");
				}else
				{
					setVisible(false);
					gameControl.savePoint(name);
				}
			}
			
		});
	}
	
	public void showWindow(int point)
	{
		System.out.println("进入JFrameSavePoint中的showWindow函数");
		this.jbpoint.setText("您的分数是: "+point);
		this.setVisible(true);
	}
	
	private void createCom()
	{
		//创建北部面板
		JPanel north=new JPanel(new FlowLayout(FlowLayout.LEFT));
		//创建分数文字
		this.jbpoint=new JLabel();
		//添加分数到北部面板
		north.add(this.jbpoint);
		
		//创建错误信息空间
		this.errMsg=new JLabel();
		this.errMsg.setForeground(Color.red);
		north.add(this.errMsg);
		
		//将北部面板添加到主面板
		this.add(north,BorderLayout.NORTH);
		
		//创建中部面板
		JPanel center=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		//创建文本
		this.txName=new JTextField(10);
		
		//设置文字
		center.add(new JLabel("您的名字: "));
		
		//文字添加到中部面板
		center.add(this.txName);
		
		//将中部面板添加到主面板
		this.add(center,BorderLayout.CENTER);	
		
		//创建南部面板
		JPanel south=new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		//创建确定按钮
		this.btnOk=new JButton("确定");
		
		//添加到南部面板
		south.add(btnOk);
		
		//添加到主面板
		this.add(south,BorderLayout.SOUTH);
	}
	

}
