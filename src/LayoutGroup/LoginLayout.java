package LayoutGroup;

import Main.CustomGridBagLayout;
import Main.Main;
import Main.MessageBox;
import Managed.DataBaseManager;
import Managed.Param;
import ExpandLayout.LinearLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DB.Tables.AccountTable;

public class LoginLayout extends LayoutGroup
{
	private JTextField input_id;
	private JPasswordField input_pw;

	GridBagLayout gb;
    GridBagConstraints gbc;
    JPanel centerPanel;

	protected void Init()
	{	
		GetPanel().setLayout(new BorderLayout(40, -300));

		GetPanel().add(NorthPanel(), BorderLayout.NORTH); // line 1
		GetPanel().add(new JPanel(), BorderLayout.EAST); // line 1
		GetPanel().add(new JPanel(), BorderLayout.WEST); // line 1
		GetPanel().add(CenterPanel(), BorderLayout.CENTER); // line 2
	}
	
	private JPanel NorthPanel()
	{
		JPanel northPanel = new JPanel();
		var programName = new JLabel("EMR System 체계 로그인");
		northPanel.add(programName);
		return northPanel; 
	}
	private JPanel CenterPanel()
	{
		var gridBag = new CustomGridBagLayout();
		gridBag.gbAdd(SetLabelOption("아이디 : "), 0, 1, 1, 1);
		input_id = new JTextField(20);
		gridBag.gbAdd(input_id, 1, 1, 3, 1);
        
		gridBag.gbAdd(SetLabelOption("비밀번호 : "),0, 2, 1, 1);
    	input_pw = new JPasswordField(10);
    	gridBag.gbAdd(input_pw, 1, 2, 3, 1);
        
        var loginBtn = new JButton("로그인");
        loginBtn.addActionListener(new ActionHandler());
        var signupBtn = new JButton("회원가입");
        signupBtn.addActionListener(new ActionHandler());
        
        gridBag.gbAdd(loginBtn, 1, 3, 1, 1);
        gridBag.gbAdd(signupBtn, 2, 3, 1, 1);
        
		return gridBag;
	}
	private void gbAdd(JComponent c, int x, int y, int w, int h)
	{
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;

        gbc.insets = new Insets(2, 2, 2, 2);
        centerPanel.add(c, gbc);
    }	
	
	public class ActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			var btn = (JButton)e.getSource();
			if(btn.getText().equals("로그인"))
			{
				Param param = new Param();
				param.AddParam("id", input_id.getText());
				param.AddParam("pw", new String(input_pw.getPassword()));
				TryLogin(param);
			}
			else if(btn.getText().equals("회원가입"))
			{
				Main.Instacne().EnterPane(new SignUpLayout());
			}
		}
	}
	
	private boolean TryLogin(Param param)
	{
		var rs = AccountTable.Instacne().SelectAll(new Param("id", param.GetValue("id")));
		if(DataBaseManager.Next(rs))
		{
			if(param.GetValue("pw").equals(DataBaseManager.GetString(rs, "pw")))
			{
				if(DataBaseManager.GetInt(rs, "approve") == 0)
				{
					MessageBox.Instacne().ShowDialog(JOptionPane.ERROR_MESSAGE, "계정 승인이 필요합니다.", "로그인 실패");
				}
				else
				{
					layoutParam.AddParam(rs);
					MessageBox.Instacne().ShowDialog(JOptionPane.INFORMATION_MESSAGE, "로그인 성공", "로그인 성공");
					Main.Instacne().EnterPane(new MainLayout());
					return true;
				}
			}
			else
			{
				MessageBox.Instacne().ShowDialog(JOptionPane.ERROR_MESSAGE, "비밀번호 오류", "로그인 실패");
			}
		}
		else
			MessageBox.Instacne().ShowDialog(JOptionPane.ERROR_MESSAGE, "계정이 없습니다.", "로그인 실패");
		return false;
	}
}
