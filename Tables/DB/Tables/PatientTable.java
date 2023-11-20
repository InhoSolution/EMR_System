package DB.Tables;

import java.sql.ResultSet;

import Managed.DataBaseManager;
import Managed.Param;

public class PatientTable 
{
	private static PatientTable instance = null; // 싱글톤 패턴 참조
	public static PatientTable Instacne() // 싱글톤 패턴
	{
		if(instance == null)
			instance = new PatientTable();
		return instance;
	}
	
	public static String tableName = "patient";
	
	public boolean AddRow(Param param)
	{
		String query = String.format("INSERT INTO patient %s %s", param.GetQueryColumn(), param.GetQueryValue());
		System.out.println(query);
		if(DataBaseManager.Instacne().ExecuteUpdate(query))
		{
			return true;
		}
		return false;
	}
	public boolean Has(String getKey, Param param)
	{
		System.out.println(String.format("SELECT %s FROM patient %s", getKey, param.GetWhere("AND")));
		try
		{
			String query = String.format("SELECT %s FROM patient %s", getKey, param.GetWhere("AND"));
			
			var rs = DataBaseManager.Instacne().ExecuteQuery(query);
			return rs.next();
		}
		catch(Exception e)
		{
			return true;
		}

	}
	public ResultSet SelectAll(Param param)
	{
		try
		{
			String query = String.format("SELECT * FROM patient %s", param.GetWhere("AND"));	
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
			String query = String.format("SELECT * FROM patient");	
			var rs = DataBaseManager.Instacne().ExecuteQuery(query);
			return rs;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
