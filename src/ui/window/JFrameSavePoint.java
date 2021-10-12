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
		this.setTitle("�����¼");
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
					errMsg.setText("������16λ���ϵ�����");
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
		System.out.println("����JFrameSavePoint�е�showWindow����");
		this.jbpoint.setText("���ķ�����: "+point);
		this.setVisible(true);
	}
	
	private void createCom()
	{
		//�����������
		JPanel north=new JPanel(new FlowLayout(FlowLayout.LEFT));
		//������������
		this.jbpoint=new JLabel();
		//��ӷ������������
		north.add(this.jbpoint);
		
		//����������Ϣ�ռ�
		this.errMsg=new JLabel();
		this.errMsg.setForeground(Color.red);
		north.add(this.errMsg);
		
		//�����������ӵ������
		this.add(north,BorderLayout.NORTH);
		
		//�����в����
		JPanel center=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		//�����ı�
		this.txName=new JTextField(10);
		
		//��������
		center.add(new JLabel("��������: "));
		
		//������ӵ��в����
		center.add(this.txName);
		
		//���в������ӵ������
		this.add(center,BorderLayout.CENTER);	
		
		//�����ϲ����
		JPanel south=new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		//����ȷ����ť
		this.btnOk=new JButton("ȷ��");
		
		//��ӵ��ϲ����
		south.add(btnOk);
		
		//��ӵ������
		this.add(south,BorderLayout.SOUTH);
	}
	

}
