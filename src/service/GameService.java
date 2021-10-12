package service;

import java.util.List;

import dto.Player;

//������Ϸ������߼��ӿ�
public interface GameService {
	
	public boolean keyUp();
	
	public boolean keyDown();
	
	public boolean keyLeft();
	
	public boolean keyRight();
	
	public boolean keyFunUp();
	
	public boolean keyFunDown();
	
	public boolean keyFunLeft();
	
	public boolean keyFunRight();
	
	public void setDbRecode(List<Player> players);
	
//	public void setDiskRecode(List<Player> players);
	
	//�������̣߳���ʼ��Ϸ
	public void startGame();
	
	//��Ϸ��Ҫ��Ϊ
	public void mainAction();
	
}
