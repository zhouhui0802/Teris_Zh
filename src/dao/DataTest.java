package dao;

import java.util.*;

import dto.Player;

public class DataTest  implements Data{

	@Override
	public List<Player> loadData() {
		// TODO Auto-generated method stub
		List<Player> players=new ArrayList<Player>();
		players.add(new Player("小明",200));
		players.add(new Player("小红",1000));
		players.add(new Player("小黄",500));
		players.add(new Player("小明",300));
		players.add(new Player("小明",100));
		return players;
	}

	@Override
	public void saveData(Player players) {
		// TODO Auto-generated method stub
		System.out.println();
	}

}
