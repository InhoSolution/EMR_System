package LayoutGroup;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;


public class RegistrationAndPaymentLayout extends TapLayoutGroup
{
	protected void Init() // 자식 호출 후 부모 호출
	{	
		super.Init();
		panelName = "접수 및 수납";
		
		AddTap(new RegistrationLayout());
		AddTap(new PaymentLayout());
		GetPanel().add(tap);
	}
}
