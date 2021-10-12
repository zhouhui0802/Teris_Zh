package Control;

import java.awt.event.*;

public class PlayerControl extends KeyAdapter{

	private GameControl gameControl;
	
	public PlayerControl(GameControl gameControl)
	{
		this.gameControl=gameControl;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//逻辑上的调用关系是  PlayerControl调用GameControl,GameControl调用GameService	
		this.gameControl.actionByKeyCode(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
