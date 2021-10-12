package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import dto.Player;

public class DataBase implements Data{
	
//	private final String driver;
	
	private final String dbUrl;
	
	private final String dbUser;
	
	private final String dbPwd;
	
	private static String LOAD_SQL="SELECT TOP 5 user_name,point FROM user_point WHERE type_id=1 ORDER BY point";
	
	private static String SAVE_SQL="INSERT INTO user_point(user_name,point,type_id) VALUES (?,?,?)";
	

	
	public DataBase(HashMap<String, String> param)
	{
		this.dbUrl=param.get("dbUrl");
		this.dbUser=param.get("dbUser");
		this.dbPwd=param.get("dbPwd");
		try{
			Class.forName(param.get("driver"));
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	//读取数据
	public List<Player>  loadData()
	{
		
		Connection coon=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Player> players=new ArrayList<Player>();
		
		try{
			coon=DriverManager.getConnection(dbUrl,dbUser,dbPwd);
			stmt=coon.prepareStatement(LOAD_SQL);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				players.add(new Player(rs.getString(1),rs.getInt(2)));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{
				if(coon!=null)
				{
					coon.close();
				}
				if(stmt!=null)
				{
					stmt.close();
				}
				if(rs!=null)
				{
					rs.close();
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return players;
	}
	
	//存储数据
	public void saveData(Player players)
	{
		Connection coon=null;
		PreparedStatement stmt=null;
		
		try{
			coon=DriverManager.getConnection(dbUrl,dbUser,dbPwd);
			stmt=coon.prepareStatement(SAVE_SQL);
			stmt.setObject(1, players.getPoint());
			stmt.setObject(2, players.getName());
			stmt.setObject(3, 1);
			stmt.execute();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{
				if(coon!=null)
				{
					coon.close();
				}
				if(stmt!=null)
				{
					stmt.close();
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
