package frontend;
import backend.*;
import db.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class NewemployeeSection {
	ImageIcon qrImg;
	JPanel newemployeePanel;
	JPasswordField empPassword;
	JButton profileImg;
	JButton showPassButton;
	JButton doneCreating;
	JButton cancelCreating;
	
	JLabel qrCode;
	JLabel empIDlbl, empPasswordlbl, empLastnamelbl, empFirstnamelbl, empContactlbl, empRatelbl, empPositionlbl,answerslbl,questionlbl;
	JTextField empID, empLastname, empFirstname, empContact, empRate, empPosition,answers; //new employee fields
	JComboBox<String> questions;

	String[] authenticatorQuestions = {"What is your bestfriend's name?", 
			   "When is mother's birthday?",
			   "Who's your favorite artist?",
			   "What's your favorite music?",
			   "What's your favorite color?",
			   "What's your dream car?",
			   "Who's your childhood crush?"};
	
	public NewemployeeSection(){
		NewEmpFuncClass f = new NewEmpFuncClass();
		Dbclass db = new Dbclass();
		
		empID = new JTextField();
		empID.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empID.setOpaque(false);
		empID.setBounds(40, 25, 270, 40);
		empID.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		empIDlbl = new JLabel("EMPLOYEE ID");
		empIDlbl.setBounds(40, 65, 100, 20);
		empIDlbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		empPassword = new JPasswordField();
		empPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empPassword.setOpaque(false);
		empPassword.setBounds(40, 90, 270, 40);
		empPassword.setFont(new Font("ARIAL", Font.PLAIN, 15));
		empPassword.setOpaque(false);

		empPasswordlbl = new JLabel("PASSWORD");
		empPasswordlbl.setBounds(40, 130, 100, 20);
		empPasswordlbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
	
		showPassButton = new JButton("SHOW PASSWORD");
		showPassButton.setHorizontalAlignment(JTextField.CENTER);
		showPassButton.setBounds(230, 130, 80, 30);
		showPassButton.setFocusable(false);
		showPassButton.setBackground(Color.WHITE);
		showPassButton.setForeground(Color.BLACK);
		showPassButton.setFont(new Font("Arial",Font.PLAIN,8));
		showPassButton.setOpaque(false);
		showPassButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		showPassButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) { empPassword.setEchoChar((char) 0); }
			public void mouseReleased(MouseEvent e) { empPassword.setEchoChar('•'); }
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}	
		});
		
		empLastname = new JTextField();
		empLastname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empLastname.setOpaque(false);
		empLastname.setBounds(40, 155, 270, 40);
		empLastname.setFont(new Font("ARIAL", Font.PLAIN, 15));
	
		empLastnamelbl = new JLabel("LAST NAME");
		empLastnamelbl.setBounds(40, 195, 100, 20);
		empLastnamelbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		empFirstname = new JTextField();
		empFirstname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empFirstname.setOpaque(false);
		empFirstname.setBounds(40, 220, 270, 40);
		empFirstname.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		empFirstnamelbl = new JLabel("FIRST NAME");
		empFirstnamelbl.setBounds(40, 260, 100, 20);
		empFirstnamelbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		empContact = new JTextField();
		empContact.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empContact.setOpaque(false);
		empContact.setBounds(40, 285, 270, 40);
		empContact.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		empContactlbl = new JLabel("CONTACT NUMBER");
		empContactlbl.setBounds(40, 325, 100, 20);
		empContactlbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		empRate = new JTextField();
		empRate.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empRate.setOpaque(false);
		empRate.setBounds(40, 370, 270, 20);
		empRate.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		empRatelbl = new JLabel("RATE");
		empRatelbl.setBounds(40, 390, 100, 20);
		empRatelbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		empPosition = new JTextField();
		empPosition.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empPosition.setOpaque(false);
		empPosition.setBounds(40, 415, 270, 40);
		empPosition.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		empPositionlbl = new JLabel("POSITION");
		empPositionlbl.setBounds(40, 455, 100, 20);
		empPositionlbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
	
		profileImg = new JButton("(+) ADD PROFILE PHOTO");
		profileImg.setBounds(390, 30, 250, 250);
		profileImg.setHorizontalAlignment(JTextField.CENTER);
		profileImg.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 2, 2, 2, true));		
		profileImg.setFocusable(false);
		profileImg.setBackground(Color.white);
		profileImg.setForeground(Color.black);
		profileImg.setFont(new Font("Arial",Font.PLAIN,12));
		profileImg.addActionListener( e -> { 
			profileImg.setIcon(f.getProfileImage()); 
		});

		
		questions = new JComboBox<>(authenticatorQuestions);
		questions.setBounds(390, 350, 250, 40);
		questions.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD,  12));
		questions.setBackground(Color.white);
		questions.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		questions.setToolTipText(" Answer this question incase you forget your password "); 
		
		answerslbl = new JLabel("QUESTIONS");
		answerslbl.setBounds(390, 390, 100, 20);
		answerslbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		answers = new JTextField();
		answers.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		answers.setOpaque(false);
		answers.setBounds(390, 415, 270, 40);
		answers.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		questionlbl = new JLabel("ANSWER");
		questionlbl.setBounds(390, 455, 100, 20);
		questionlbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		qrCode = new JLabel("Qr Code Generator");
		qrCode.setBounds(720, 30, 300, 350);
		qrCode.setHorizontalAlignment(JTextField.CENTER);
		qrCode.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		
		doneCreating = new JButton("DONE");
		doneCreating.setBounds(920, 450, 80, 40);
		doneCreating.setFocusable(false);
		doneCreating.setBackground(Color.GREEN);
		doneCreating.setForeground(Color.BLACK);
		doneCreating.setFont(new Font("Arial",Font.PLAIN,13));
		doneCreating.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		doneCreating.addActionListener(e -> {
			int num = addingnewEmporAdmin();

			try {
				if(num == 0) {
					db.addEmployee
					(Integer.parseInt(empID.getText()),
					String.valueOf(empPassword.getPassword()),
					empLastname.getText(),
					empFirstname.getText(),
					empContact.getText(),
					Double.parseDouble(empRate.getText()),
					empPosition.getText(),
					f.imagePath,
					(String)questions.getSelectedItem(),
					answers.getText(),num);
				}else if(num == 1) {
					db.addEmployee
					(Integer.parseInt(empID.getText()),
					String.valueOf(empPassword.getPassword()),
					empLastname.getText(),
					empFirstname.getText(),
					empContact.getText(),
					Double.parseDouble(empRate.getText()),
					empPosition.getText(),
					f.imagePath,
					(String)questions.getSelectedItem(),
					answers.getText(),num);
				}
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "INPUT ERROR", "WARNING", JOptionPane.WARNING_MESSAGE); 
			}
			
			
				
			if(db.validAdditionEmp) qrCode.setIcon(f.qrcodeFunction(empID.getText()));
			if(db.validAdditionEmp && qrCode.getIcon() != null) {
				JOptionPane.showMessageDialog(null, "SCAN THE QR CODE FIRST", "NOTE", JOptionPane.INFORMATION_MESSAGE);
				empID.setText("");
				empPassword.setText("");
				empLastname.setText("");
				empFirstname.setText(""); 
				empContact.setText("");
				empRate.setText("");
				empPosition.setText("");
				profileImg.setIcon(null);
				profileImg.setText("(+) ADD PROFILE PHOTO");
				qrCode.setText("Qr Code Generator");
				qrCode.setIcon(null);
				answers.setText("");
			}
		});

		cancelCreating = new JButton("CANCEL");
		cancelCreating.setBounds(830, 450, 80, 40);
		cancelCreating.setFocusable(false);
		cancelCreating.setBackground(Color.RED);
		cancelCreating.setForeground(Color.BLACK);
		cancelCreating.setFont(new Font("Arial",Font.PLAIN,13));
		cancelCreating.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		
		
		
		newemployeePanel = new JPanel();
		newemployeePanel.setBackground(Color.white);
		newemployeePanel.setBounds(10,80,1050,580);
		newemployeePanel.setVisible(false);
		newemployeePanel.setLayout(null);
		newemployeePanel.add(empID);
		newemployeePanel.add(empIDlbl);
		newemployeePanel.add(empPassword);
		newemployeePanel.add(empPasswordlbl);
		newemployeePanel.add(showPassButton);
		newemployeePanel.add(empLastname);
		newemployeePanel.add(empLastnamelbl);
		newemployeePanel.add(empFirstname);
		newemployeePanel.add(empFirstnamelbl);
		newemployeePanel.add(empContact);
		newemployeePanel.add(empContactlbl);
		newemployeePanel.add(empRate);
		newemployeePanel.add(empRatelbl);
		newemployeePanel.add(empPosition);
		newemployeePanel.add(empPositionlbl);
		newemployeePanel.add(questions);
		newemployeePanel.add(questionlbl);
		newemployeePanel.add(answers);
		newemployeePanel.add(answerslbl);
		newemployeePanel.add(profileImg);
		newemployeePanel.add(qrCode);
		newemployeePanel.add(doneCreating);
		newemployeePanel.add(cancelCreating);
		
	}
	
	public int addingnewEmporAdmin() {
	    String[] options = {"Admin", "Employee"};

	        int choice = JOptionPane.showOptionDialog(
	                null, 
	                "Choose an option:", // Message
	                "Option Dialog", // Title
	                JOptionPane.DEFAULT_OPTION, // Option type
	                JOptionPane.PLAIN_MESSAGE, // Message type
	                null, // Icon (null for default)
	                options, // Options array
	                options[0] // Default option
	        );
			return choice;
	}
	
	

}
