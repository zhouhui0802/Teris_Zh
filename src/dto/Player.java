package dto;

import java.io.Serializable;

public class Player implements Comparable<Player> ,Serializable{
	
	//定义用户的名字和分数
	private String name;
	private int point;
	
	public Player(String name, int point) {
		this.name = name;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public int compareTo(Player pla) {
		// TODO Auto-generated method stub
		return pla.point-this.point;
	}	
}
