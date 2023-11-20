package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class MaxInputLength implements KeyListener
{
	private int maxLength = 6;
	public MaxInputLength(int length)
	{
		maxLength = length;
	}
	@Override
	public void keyTyped(KeyEvent ke)
	{
		if(((JTextField)ke.getSource()).getText().length() >= maxLength)
		{
			ke.consume();
		}
		else if(!(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_ENTER))
		{
			ke.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
