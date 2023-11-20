package Managed;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class Param
{ 
	StringBuilder stringBuilder;
	Map<String, Object> params;
	
	
	public Param()
	{
		params = new HashMap<String, Object>();
		stringBuilder = new StringBuilder();
	}
	public Param(String key, String value)
	{
		params = new HashMap<String, Object>();
		stringBuilder = new StringBuilder();
		AddParam(key, value);
	}
	public int GetCount()
	{
		return params.size();
	}
	public void AddParam(ResultSet rs)
	{
		params = new HashMap<String, Object>();
		stringBuilder = new StringBuilder();
		try
		{	
			var metaData = rs.getMetaData();
			for(int i = 1; i <= metaData.getColumnCount(); i++)
			{
				AddParam(metaData.getColumnName(i), rs.getString(i));
			}
		}
		catch(Exception e)
		{
			System.out.println("Error");
		}	
	}
	public void AddParam(String key, Object value)
	{
		params.put(key, value);
	}
	public void RemoveParam(String key)
	{
		params.remove(key);
	}
	public boolean IsKey(String key)
	{
		return params.containsKey(key);
	}
	public String GetValue(String key)
	{
		return params.get(key).toString();
	}
	public String GetQueryColumn()
	{
		stringBuilder.setLength(0);
		stringBuilder.append("(");
		int count = 0;
		for( Map.Entry<String, Object> entry : params.entrySet() )
		{
			stringBuilder.append(entry.getKey());
			count++;
			if(count < params.size())
				stringBuilder.append(",");
		}
		stringBuilder.append(")");
		return stringBuilder.toString();
	}
	public String GetQueryValue()
	{
		stringBuilder.setLength(0);
		stringBuilder.append("VALUES(");
		int count = 0;
		for( Map.Entry<String, Object> entry : params.entrySet() )
		{
			stringBuilder.append("'");
			stringBuilder.append(entry.getValue());
			stringBuilder.append("'");
			count++;
			if(count < params.size())
				stringBuilder.append(",");
		}
		stringBuilder.append(");");
		return stringBuilder.toString();
	}
	public String GetWhere(String andType)
	{
		stringBuilder.setLength(0);
		stringBuilder.append("WHERE ");
		int count = 0;
		for( Map.Entry<String, Object> entry : params.entrySet() )
		{
			stringBuilder.append(entry.getKey());
			stringBuilder.append(" = ");
			stringBuilder.append("'");
			stringBuilder.append(entry.getValue());
			stringBuilder.append("'");
			count++;
			if(count < params.size())
				stringBuilder.append(" " + andType + " ");
		}
		return stringBuilder.toString();
	}
}