package LayoutGroup;

import java.awt.Graphics;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Managed.DataBaseManager;
import Managed.Param;

public class LayoutGroup extends JPanel
{
	
	
	public JPanel GetPanel()
	{
		return this;
	}
	protected String panelName;
	public String GetPanelName()
	{
		return panelName;
	}
	public static Param layoutParam = new Param();
	public LayoutGroup() // 생성자와 함께 자식에게 초기화 함수 Override해서 실행
	{
		Init();
	}
	
	protected void Init() // 자식 호출 후 부모 호출
	{

	}
	public void ExitPane()
	{

	}
	public static JLabel SetLabelOption(String text)
	{
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(JLabel.RIGHT);
		return label;
	}
	public static JLabel SetLabelOption(String text, int anch)
	{
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(anch);
		return label;
	}
}
