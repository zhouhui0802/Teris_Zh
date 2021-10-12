package ui.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;


public class TextCtrl extends JTextField{
	
	private int keyCode;
	
	private final String methodName;
	
	
	public TextCtrl(int x,int y,int w,int h,int keyCode,String methodName)
	{
		//�����ı����λ��
		this.setBounds(x, y, w, h);
		
		//��ʼ��KeyCode
		this.keyCode=keyCode;
		
		//��ʼ��������
		this.methodName=methodName;
		
		//�����¼�����
		this.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				setKeyCode(e.getKeyCode());
			}

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}


	public int getKeyCode() {
		return keyCode;
	}


	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		this.setText(KeyEvent.getKeyText(this.keyCode));
	}


	public String getMethodName() {
		return methodName;
	}
}
