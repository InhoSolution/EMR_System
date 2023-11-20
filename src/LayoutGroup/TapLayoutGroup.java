package LayoutGroup;

import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TapLayoutGroup extends LayoutGroup
{
	protected JTabbedPane tap;
	protected Vector<TapLayoutGroup> pages = new Vector<TapLayoutGroup>();
	

	
	protected void Init()
	{
		GetPanel().setLayout(new BoxLayout(GetPanel(), BoxLayout.X_AXIS));
		tap = new JTabbedPane();
		tap.addChangeListener(new ChangeHandle());
	}
	protected void AddTap(LayoutGroup layout)
	{
		tap.addTab(layout.GetPanelName(), layout.GetPanel());
	}
	
	public class ChangeHandle implements ChangeListener
	{
		@Override
		 public void stateChanged(ChangeEvent e) 
		{
	        System.out.println("Tab: " + tap.getSelectedIndex());
	    }
	}
	
	
}
