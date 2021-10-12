package ui.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;


public class TextCtrl extends JTextField{
	
	private int keyCode;
	
	private final String methodName;
	
	
	public TextCtrl(int x,int y,int w,int h,int keyCode,String methodName)
	{
		//设置文本框的位置
		this.setBounds(x, y, w, h);
		
		//初始化KeyCode
		this.keyCode=keyCode;
		
		//初始化方法名
		this.methodName=methodName;
		
		//增加事件监听
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
