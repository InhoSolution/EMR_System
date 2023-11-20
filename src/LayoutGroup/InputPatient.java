package LayoutGroup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

import DB.Tables.PatientTable;
import ExpandLayout.LinearLayout;
import LayoutGroup.LoginLayout.ActionHandler;
import Main.CustomGridBagLayout;
import Main.Main;
import Main.MaxInputLength;
import Main.MessageBox;
import Main.StringLib;
import Managed.Param;

public class InputPatient extends LayoutGroup
{
    JTextField input_name;
    JTextField input_rrn_front;
    JTextField input_rrn_back;
    JTextField input_pn_front;
    JTextField input_pn_center;
    JTextField input_pn_back;
    
    JTextField input_age;
    JTextField input_blood_type;
    JTextField input_gender;
    JTextField input_inDate;
    JTextField input_reservation_date;
    
    JButton serachBtn;
    JButton addPatientBtn;
    protected void Init()
    {
    	GetPanel().setLayout(new BorderLayout());
    	GetPanel().setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 1, true), "환자 정보"));
    	InitComponent();
    	Draw();
    }
    private void InitComponent()
    {
        input_name = new JTextField(6);
        input_rrn_front = new JTextField(6);
        input_rrn_back = new JTextField(7);
        input_pn_front = new JTextField(3);
        input_pn_center = new JTextField(4);
        input_pn_back = new JTextField(4);
        
        input_age= new JTextField(3);
        input_blood_type = new JTextField(6);
        input_gender = new JTextField(2);
        input_inDate = new JTextField(15);
        input_reservation_date = new JTextField(15);
        
        serachBtn = new JButton("조회");
        addPatientBtn = new JButton("환자 등록");
        SetEditor(false);
    }
    private void SetEditor(boolean isTrue)
    {
    	input_name.setEditable(isTrue);
    	input_pn_front.setEditable(isTrue);
    	input_pn_center.setEditable(isTrue);
    	input_pn_back.setEditable(isTrue);
    	input_age.setEditable(isTrue);
    	input_blood_type.setEditable(isTrue);
    	input_gender.setEditable(isTrue);
    	input_inDate.setEditable(false);
    	input_reservation_date.setEditable(isTrue);
    	input_name.setText("");
    	input_pn_front.setText("");
    	input_pn_center.setText("");
    	input_pn_back.setText("");
    	input_age.setText("");
    	input_blood_type.setText("");
    	input_gender.setText("");
    	input_inDate.setText("");
    	input_reservation_date.setText("");
    	
        addPatientBtn.setEnabled(isTrue);
        
        if(isTrue)
        {
        	Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        	String currentTimestampToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentTimestamp);
        	input_inDate.setText(currentTimestampToString);
        }
    }
    
    private void Draw()
    {
    	JPanel finalPanel = new JPanel();
    	finalPanel.setLayout(new LinearLayout(FlowLayout.LEFT));
    	
		JPanel lineOne = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		input_rrn_front.addKeyListener(new MaxInputLength(6));
		input_rrn_front.addKeyListener(new ChangeKey());
		input_rrn_back.addKeyListener(new MaxInputLength(7));
		input_rrn_back.addKeyListener(new ChangeKey());
		lineOne.add(SetLabelOption("주민번호 : "));
		lineOne.add(input_rrn_front);
		lineOne.add(new JLabel(" - "));
		lineOne.add(input_rrn_back);
		
		lineOne.add(SetLabelOption("이름 : "));
		lineOne.add(input_name);

		input_pn_front.addKeyListener(new MaxInputLength(3));
		input_pn_center.addKeyListener(new MaxInputLength(4));
		input_pn_back.addKeyListener(new MaxInputLength(4));
		lineOne.add(SetLabelOption("전화번호 : "));
		lineOne.add(input_pn_front);
		lineOne.add(new JLabel(" - "));
		lineOne.add(input_pn_center);
		lineOne.add(new JLabel(" - "));
		lineOne.add(input_pn_back);
		finalPanel.add(lineOne);
		
		JPanel lineTwo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lineTwo.add(SetLabelOption("나이 : "));
		lineTwo.add(input_age);
		lineTwo.add(SetLabelOption("혈액형 : "));
		lineTwo.add(input_blood_type);
		lineTwo.add(SetLabelOption("성별 : "));
		lineTwo.add(input_gender);
		lineTwo.add(SetLabelOption("초진 날짜 : "));
		lineTwo.add(input_inDate);
		lineTwo.add(SetLabelOption("진료 예약일 : "));
		lineTwo.add(input_reservation_date);
		finalPanel.add(lineTwo);
		
        GetPanel().add(finalPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout());
        serachBtn.addActionListener(new ActionHandler());
        addPatientBtn.addActionListener(new ActionHandler());
        southPanel.add(serachBtn);
        southPanel.add(addPatientBtn);
        
        GetPanel().add(southPanel, BorderLayout.SOUTH);
    }
    public class ActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			var btn = (JButton)e.getSource();
			if(btn.getText().equals("조회"))
			{
				boolean isNotEmptyRRN = !StringLib.EmptyCheck(input_rrn_front, input_rrn_back);
				boolean isLengthRRN = input_rrn_front.getText().length() + input_rrn_back.getText().length() == 13;
				if(isLengthRRN && isNotEmptyRRN)
				{
					String rrn = StringLib.AddRRN(input_rrn_front.getText(), input_rrn_back.getText());
					if(PatientTable.Instacne().Has("rrn", new Param("rrn", rrn)))
					{

					}
					else
					{
						MessageBox.Instacne().ShowDialog(JOptionPane.ERROR_MESSAGE, "환자 정보 없음", "환자 조회");
						SetEditor(true);
					}
				}
				else
				{
					if(isNotEmptyRRN == false)
						MessageBox.Instacne().ShowDialog(JOptionPane.ERROR_MESSAGE, "주민번호 공백", "환자 조회");
					else if(isLengthRRN == false)
						MessageBox.Instacne().ShowDialog(JOptionPane.ERROR_MESSAGE, "13자리 전부 입력하세요", "환자 조회");
				}
			}
			else if(btn.getText().equals("환자 등록"))
			{
				boolean isEmpty = StringLib.EmptyCheck(input_name, input_pn_front, 
						input_pn_back, input_blood_type, input_age, input_gender);
				
				if(isEmpty == false)
				{
					Param param = new Param();
					param.AddParam("name", input_name.getText());
					String rrn = StringLib.AddRRN(input_rrn_front.getText(), input_rrn_back.getText());
					param.AddParam("rrn", rrn);
					String pn = StringLib.AddPN(input_pn_front.getText(), input_pn_center.getText(), 
							input_pn_back.getText());
					param.AddParam("pn", pn);
					param.AddParam("inDate", input_inDate.getText());
					param.AddParam("blood_type", input_blood_type.getText());
					param.AddParam("age", input_age.getText());
					param.AddParam("gender", input_gender.getText());
					param.AddParam("id", LoginLayout.layoutParam.GetValue("id"));
					PatientTable.Instacne().AddRow(param);
					SetEditor(false);
					input_rrn_front.setText("");
					input_rrn_back.setText("");
				}

			}
		}
	}
    public class ChangeKey implements KeyListener
    {
    	@Override
    	public void keyTyped(KeyEvent ke)
    	{
    		SetEditor(false);
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
}
