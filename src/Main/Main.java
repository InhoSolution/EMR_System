package Main;

import java.awt.Container;
import java.sql.ResultSet;

import javax.swing.JFrame;

import LayoutGroup.LayoutGroup;
import LayoutGroup.LoginLayout;
import Managed.DataBaseManager;

public class Main extends JFrame
{
	Container c = getContentPane();
	
	private static Main instance = null; // 싱글톤 패턴 참조
	public static Main Instacne() // 싱글톤 패턴
	{
		if(instance == null)
			instance = new Main();
		return instance;
	}
	private String id;
	private String name;
	private String position;
	
	public static void main(String[] args)
	{
		Instacne();
	}
	
	public Main()
	{
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setTitle("EMR Text");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setVisible(true);
		DataBaseManager.Instacne();
		EnterPane(new LoginLayout()); // 시작 팬 연결
	}
	
	private LayoutGroup thisLayout = null;
	public void EnterPane(LayoutGroup _layout) // 팬 접속
	{
		if(thisLayout != null)
		{
			thisLayout.ExitPane();
			c.removeAll();
		}
		thisLayout = _layout;
		c.add(thisLayout.GetPanel());
		thisLayout.GetPanel().updateUI();
	}
	
}
