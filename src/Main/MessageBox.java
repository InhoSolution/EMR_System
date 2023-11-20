package Main;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MessageBox 
{
	private static MessageBox instance = null; // 싱글톤 패턴 참조
	public static MessageBox Instacne() // 싱글톤 패턴
	{
		if(instance == null)
			instance = new MessageBox();
		return instance;
	}
	
	public void ShowDialog(int dialogType, String dialog, String message)
	{
		JOptionPane.showMessageDialog(null, dialog, message, dialogType);
	}

}
