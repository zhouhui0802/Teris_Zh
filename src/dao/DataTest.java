package dao;

import java.util.*;

import dto.Player;

public class DataTest  implements Data{

	@Override
	public List<Player> loadData() {
		// TODO Auto-generated method stub
		List<Player> players=new ArrayList<Player>();
		players.add(new Player("С��",200));
		players.add(new Player("С��",1000));
		players.add(new Player("С��",500));
		players.add(new Player("С��",300));
		players.add(new Player("С��",100));
		return players;
	}

	@Override
	public void saveData(Player players) {
		// TODO Auto-generated method stub
		System.out.println();
	}

}
