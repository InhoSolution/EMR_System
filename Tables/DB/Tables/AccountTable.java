package DB.Tables;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import LayoutGroup.LoginLayout;
import Main.Main;
import Main.MessageBox;
import Managed.DataBaseManager;
import Managed.Param;

public class AccountTable 
{
	private static AccountTable instance = null; // 싱글톤 패턴 참조
	public static AccountTable Instacne() // 싱글톤 패턴
	{
		if(instance == null)
			instance = new AccountTable();
		return instance;
	}
	
	public static String tableName = "account";
	
	public boolean AddRow(String id, String pw, String name, String position)
	{
		Param param = new Param();
		param.AddParam("id", id);
		param.AddParam("pw", pw);
		param.AddParam("name", name);
		param.AddParam("position", position);	
		String query = String.format("INSERT INTO account %s %s", param.GetQueryColumn(), param.GetQueryValue());
		System.out.println(query);
		if(DataBaseManager.Instacne().ExecuteUpdate(query))
		{
			return true;
		}
		return false;
	}
	public boolean AddRow(Param param)
	{
		String query = String.format("INSERT INTO account %s %s", param.GetQueryColumn(), param.GetQueryValue());
		System.out.println(query);
		if(DataBaseManager.Instacne().ExecuteUpdate(query))
		{
			return true;
		}
		return false;
	}
	public boolean Has(String getKey, Param param)
	{
		System.out.println(String.format("SELECT %s FROM account %s", getKey, param.GetWhere("AND")));
		try
		{
			String query = String.format("SELECT %s FROM account %s", getKey, param.GetWhere("AND"));
			
			var rs = DataBaseManager.Instacne().ExecuteQuery(query);
			return rs.next();
		}
		catch(Exception e)
		{
			System.out.println("abc");
			return true;
		}

	}
	public ResultSet SelectAll(Param param)
	{
		try
		{
			String query = String.format("SELECT * FROM account %s", param.GetWhere("AND"));	
			System.out.println(query);
			var rs = DataBaseManager.Instacne().ExecuteQuery(query);
			return rs;
		}
		catch(Exception e)
		{
			System.out.println("hi");
			return null;
		}
	}
	public ResultSet SelectAll()
	{
		try
		{
			String query = String.format("SELECT * FROM account");	
			var rs = DataBaseManager.Instacne().ExecuteQuery(query);
			return rs;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
