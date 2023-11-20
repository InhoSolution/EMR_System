package LayoutGroup;

import Managed.*;
import Main.*;

import java.awt.BorderLayout;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DB.Tables.AccountTable;
import LayoutGroup.LoginLayout.ActionHandler;

public class SignUpLayout extends LayoutGroup
{
	private JTextField input_id;
	private JPasswordField input_pw;
	private JTextField input_name;
	
	private JComboBox comboBox_position;
	
	protected void Init() // 자식 호출 후 부모 호출
	{	
		GetPanel().setLayout(new BorderLayout(40, -300));

		GetPanel().add(NorthPanel(), BorderLayout.NORTH); // line 1
		GetPanel().add(new JPanel(), BorderLayout.EAST); // line 1
		GetPanel().add(new JPanel(), BorderLayout.WEST); // line 1
		GetPanel().add(CenterPanel(), BorderLayout.CENTER); // line 2
	}
	public void ExitPane()
	{
		
	}
	
	private JPanel NorthPanel()
	{
		JPanel northPanel = new JPanel();
		var programName = new JLabel("EMR System 체계 회원가입");
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
    	gridBag.gbAdd(input_pw, 1, 2, 2, 1);
        
    	gridBag.gbAdd(SetLabelOption("이름 : "),0, 3, 1, 1);
     	input_name = new JTextField(10);
     	gridBag.gbAdd(input_name, 1, 3, 2, 1);
        
        String[] positions = {"간호사", "의사", "관리자"};
        comboBox_position = new JComboBox(positions);
        gridBag.gbAdd(SetLabelOption("직책 : "),0, 4, 1, 1);
        gridBag.gbAdd(comboBox_position, 1, 4, 1, 1);
        
        var cancelBtn = new JButton("취소");
        cancelBtn.addActionListener(new ActionHandler());
        var signupBtn = new JButton("회원가입");
        signupBtn.addActionListener(new ActionHandler());
        
        gridBag.gbAdd(cancelBtn, 1, 5, 1, 1);
        gridBag.gbAdd(signupBtn, 2, 5, 1, 1);
        
		return gridBag;
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
	public class ActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			var btn = (JButton)e.getSource();
			if(btn.getText().equals("취소"))
			{
				Main.Instacne().EnterPane(new LoginLayout());
			}
			else if(btn.getText().equals("회원가입"))
			{
				boolean isEmpty = StringLib.EmptyCheck(input_id.getText(), input_name.getText(), new String(input_pw.getPassword()));
				if(isEmpty)
				{
					MessageBox.Instacne().ShowDialog(JOptionPane.ERROR_MESSAGE, "빈 칸이 있습니다.", "회원가입 실패");
					return;
				}
				
				var param = new Param();
				param.AddParam("id", input_id.getText());
				param.AddParam("pw", new String(input_pw.getPassword()));
				param.AddParam("name", input_name.getText());
				param.AddParam("position", comboBox_position.getSelectedItem().toString());
				TrySingUp(param);
			}
		}
	}
	
	private void TrySingUp(Param param)
	{
		Param hasKey = new Param();
		hasKey.AddParam("id", param.GetValue("id"));
		var rs = AccountTable.Instacne().SelectAll(new Param("id", hasKey.GetValue("id")));
		try
		{
			if(rs.next())
			{
				System.out.println("Has Key");
				MessageBox.Instacne().ShowDialog(JOptionPane.ERROR_MESSAGE, "중복된 ID가 존재합니다.", "회원가입 실패");
			}
			else
			{
				System.out.println("Empty Key");
				if(AccountTable.Instacne().AddRow(param));
				{
					MessageBox.Instacne().ShowDialog(JOptionPane.INFORMATION_MESSAGE, "계정 생성에 성공하였습니다.", "회원가입 성공");
					Main.Instacne().EnterPane(new LoginLayout());
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Empty Key");
			if(AccountTable.Instacne().AddRow(param));
			{
				MessageBox.Instacne().ShowDialog(JOptionPane.INFORMATION_MESSAGE, "계정 생성에 성공하였습니다.", "회원가입 성공");
				Main.Instacne().EnterPane(new LoginLayout());
			}
		}
	}
}
