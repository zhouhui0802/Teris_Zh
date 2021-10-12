package service;

import java.util.List;

import dto.Player;

//操作游戏方块的逻辑接口
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
	
	//启动主线程，开始游戏
	public void startGame();
	
	//游戏主要行为
	public void mainAction();
	
}
