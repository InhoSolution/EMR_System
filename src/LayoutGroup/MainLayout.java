package LayoutGroup;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Main.Main;
import Main.MessageBox;
import Main.StringLib;
import Managed.Param;

public class MainLayout extends TapLayoutGroup
{
	protected void Init() // 자식 호출 후 부모 호출
	{	
		super.Init();
		
		Main.Instacne().setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		panelName = "메인";
		AddTap(new RegistrationAndPaymentLayout());
		System.out.println(LoginLayout.layoutParam.GetValue("position"));
		if(LoginLayout.layoutParam.GetValue("position").equals("의사"))
			AddTap(new ClinicLayout());
		AddTap(new LogLayout());
		
		GetPanel().add(tap);
	}
	
}
