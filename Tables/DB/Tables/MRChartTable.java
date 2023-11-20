package DB.Tables;

import java.sql.ResultSet;

import Managed.DataBaseManager;
import Managed.Param;

public class MRChartTable 
{
	private static MRChartTable instance = null; // 싱글톤 패턴 참조
	public static MRChartTable Instacne() // 싱글톤 패턴
	{
		if(instance == null)
			instance = new MRChartTable();
		return instance;
	}
	
	public static String tableName = "mr_chart";
	
	public boolean AddRow(Param param)
	{
		String query = String.format("INSERT INTO mr_chart %s %s", param.GetQueryColumn(), param.GetQueryValue());
		System.out.println(query);
		if(DataBaseManager.Instacne().ExecuteUpdate(query))
		{
			return true;
		}
		return false;
	}
	public boolean Has(String getKey, Param param)
	{
		System.out.println(String.format("SELECT %s FROM mr_chart %s", getKey, param.GetWhere("AND")));
		try
		{
			String query = String.format("SELECT %s FROM mr_chart %s", getKey, param.GetWhere("AND"));
			
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
			String query = String.format("SELECT * FROM mr_chart %s", param.GetWhere("AND"));	
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
			String query = String.format("SELECT * FROM mr_chart");	
			var rs = DataBaseManager.Instacne().ExecuteQuery(query);
			return rs;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
