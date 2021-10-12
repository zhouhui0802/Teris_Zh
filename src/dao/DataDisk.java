package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import dto.Player;

public class DataDisk implements Data{

	private static String filePath;
	
	public DataDisk(HashMap<String,String> param)
	{
		this.filePath=param.get("path");
	}
	
	@Override
	public List<Player> loadData() {
		// TODO Auto-generated method stub
		ObjectInputStream ois=null;
		List<Player> players=null;
		
		try{
			ois=new ObjectInputStream(new FileInputStream(filePath));
			players=(List<Player>)ois.readObject();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			try{
				ois.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return players;
	}

	@Override
	public void saveData(Player pla) {
		// TODO Auto-generated method stub
		
		//取出数据
		List<Player> players=this.loadData();
		//追加新的记录
		players.add(pla);
		//重新写入
		ObjectOutputStream oos = null;
		try{
			oos=new ObjectOutputStream(new FileOutputStream(filePath));
			oos.writeObject(players);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
				oos.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	

	
}
