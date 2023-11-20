package Main;

import javax.swing.JTextField;

public class StringLib 
{
	static StringBuilder sb = new StringBuilder();
	public static boolean EmptyCheck(String tf)
	{
		return tf == null || tf.isEmpty();
	}
	public static boolean EmptyCheck(JTextField tf)
	{
		return tf == null || tf.getText().isEmpty();
	}
	public static boolean EmptyCheck(String...str)
	{
		for(String a:str)
		{
			if(EmptyCheck(a))
				return true;
		}
		return false;
	}
	public static boolean EmptyCheck(JTextField...tfs)
	{
		for(JTextField a:tfs)
		{
			if(EmptyCheck(a))
				return true;
		}
		return false;
	}
	public static String AddRRN(String front, String back)
	{
		sb.setLength(0);
		sb.append(front);
		sb.append("-");
		sb.append(back);
		return sb.toString();
	}
	public static String AddPN(String...str)
	{
		sb.setLength(0);
		int count = 0;
		for(String a:str)
		{
			sb.append(a);
			count++;
			if(count < str.length)
				sb.append("-");
		}
		return sb.toString();
	}
}
