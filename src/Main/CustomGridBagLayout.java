package Main;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CustomGridBagLayout extends JPanel
{
	GridBagLayout gb;
    GridBagConstraints gbc;
    public CustomGridBagLayout()
    {
    	setLayout(new FlowLayout());
    	gb = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 0;
        
        setLayout(gb);        
    }
	public void gbAdd(JComponent c, int x, int y, int w, int h)
	{
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;

        gbc.insets = new Insets(2, 2, 2, 2);
        add(c, gbc);
    }	
}
