package LayoutGroup;

import java.awt.GridLayout;

import javax.swing.JLabel;

public class RegistrationLayout extends LayoutGroup
{
	protected void Init()
	{
		super.Init();
		panelName = "접수";
		
		GetPanel().setLayout(new GridLayout(2, 2));
		GetPanel().add(new JLabel("공백"));
		GetPanel().add(new JLabel("공백"));
		GetPanel().add(new JLabel("공백"));
		GetPanel().add(new InputPatient());
	}
}
