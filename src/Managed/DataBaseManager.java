package Managed;

import java.sql.*;
import java.util.Map;

import javax.swing.JOptionPane;

import Main.MessageBox;

public class DataBaseManager
{
	private static DataBaseManager instance = null; // 싱글톤 패턴 참조
	public static DataBaseManager Instacne() // 싱글톤 패턴
	{
		if(instance == null)
			instance = new DataBaseManager();
		return instance;
	}
	
	Connection conn;
	Statement stmt = null;
	ResultSet rs = null;
	public DataBaseManager()
	{
		ConnectDB();
	}
	
	private void ConnectDB()
	{
		String url="jdbc:mysql://localhost:3306/maindatabase?serverTimezone=Asia/Seoul";
	    String id="root";
	    String password="ax!80254177";
	    try
	    {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Drive Success");
	        conn=DriverManager.getConnection(url, id, password);
	        System.out.println("Database Connect");
	    }
	    catch(ClassNotFoundException e)
	    {
	        System.out.println("Missing Drive");
	        e.getStackTrace();
	    }
	    catch(SQLException e)
	    {

	        System.out.println("SQLEXception"+e.getMessage());
	        System.out.println("SQLState"+e.getSQLState());
	        System.out.println("VendorError"+e.getErrorCode());
	    	System.out.println("VendorError"+e.getErrorCode());
	    }
	}
	public void DisconnectDB() 
	{
	    try
	    {
	        rs.close();
	        stmt.close();
	        conn.close();
	    }
	    catch(SQLException e)
	    {
	    	System.out.println(e.getMessage());
	    }
	}
	public ResultSet ExecuteQuery(String query)
	{
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			return rs;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	public boolean ExecuteUpdate(String query)
	{
		try
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	public static String GetString(ResultSet rs, String key)
	{
		try
		{
			return rs.getString(key);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public static int GetInt(ResultSet rs, String key)
	{
		try
		{
			return rs.getInt(key);
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	public static boolean Next(ResultSet rs)
	{
		try
		{
			return rs.next();
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
