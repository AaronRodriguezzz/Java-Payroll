package frontend;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import backend.NewEmpFuncClass;
import db.*;

public class UpdateInformationEmp {
	ImageIcon qrImage;
	JPasswordField empPassword;
	JButton showPassButton;
	JButton profileImg;
	JButton doneUpdating;
	JPanel updateinfoPanel;
	JLabel qrCode;
	JLabel empIDlbl, empPasswordlbl, empLastnamelbl, empFirstnamelbl, empContactlbl, empRatelbl, empPositionlbl;
	JTextField empID, empLastname, empFirstname, empContact, empRate, empPosition;
	String id,password,lName,fName,contact,rate,position;
	String getUpdatedImage;
	
	NewEmpFuncClass f = new NewEmpFuncClass();
	Dbclass db = new Dbclass();
	UpdateInformationEmp(){ 	
		

		empID = new JTextField();
		empID.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empID.setOpaque(false);
		empID.setBounds(40, 40, 270, 40);
		empID.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		empIDlbl = new JLabel("EMPLOYEE ID");
		empIDlbl.setBounds(40, 80, 100, 20);
		empIDlbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		empPassword = new JPasswordField();
		empPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empPassword.setOpaque(false);
		empPassword.setBounds(40, 130, 270, 40);
		empPassword.setFont(new Font("ARIAL", Font.PLAIN, 15));
		empPassword.setOpaque(false);

		empPasswordlbl = new JLabel("PASSWORD");
		empPasswordlbl.setBounds(40, 170, 100, 20);
		empPasswordlbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
	
		showPassButton = new JButton("SHOW PASSWORD");
		showPassButton.setHorizontalAlignment(JTextField.CENTER);
		showPassButton.setBounds(230, 170, 80, 30);
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
		empLastname.setBounds(40, 220, 270, 40);
		empLastname.setFont(new Font("ARIAL", Font.PLAIN, 15));
	
		empLastnamelbl = new JLabel("LAST NAME");
		empLastnamelbl.setBounds(40, 260, 100, 20);
		empLastnamelbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		empFirstname = new JTextField();
		empFirstname.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empFirstname.setOpaque(false);
		empFirstname.setBounds(40, 320, 270, 40);
		empFirstname.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		empFirstnamelbl = new JLabel("FIRST NAME");
		empFirstnamelbl.setBounds(40, 360, 100, 20);
		empFirstnamelbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		empContact = new JTextField();
		empContact.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empContact.setOpaque(false);
		empContact.setBounds(40, 420, 270, 40);
		empContact.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		empContactlbl = new JLabel("CONTACT NUMBER");
		empContactlbl.setBounds(40, 460, 100, 20);
		empContactlbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
	
		profileImg = new JButton("CLICK HERE TO REPLACE THE PHOTO");
		profileImg.setBounds(390, 30, 250, 250);
		profileImg.setHorizontalAlignment(JTextField.CENTER);
		profileImg.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 2, 2, 2, true));		
		profileImg.setFocusable(false);
		profileImg.setBackground(Color.white);
		profileImg.setForeground(Color.black);
		profileImg.setFont(new Font("Arial",Font.PLAIN,12));
		profileImg.setEnabled(false);
		profileImg.addActionListener( e -> { 
			profileImg.setIcon(f.getProfileImage()); 
			getUpdatedImage = f.imagePath;
		});

	
		
		empRate = new JTextField();
		empRate.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empRate.setOpaque(false);
		empRate.setBounds(380, 320, 270, 40);
		empRate.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		empRatelbl = new JLabel("RATE");
		empRatelbl.setBounds(380, 360, 100, 20);
		empRatelbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		empPosition = new JTextField();
		empPosition.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		empPosition.setOpaque(false);
		empPosition.setBounds(380, 420, 270, 40);
		empPosition.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		empPositionlbl = new JLabel("POSITION");
		empPositionlbl.setBounds(380, 460, 100, 20);
		empPositionlbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		
		qrCode = new JLabel("Qr Code Generator");
		qrCode.setBounds(720, 30, 300, 350);
		qrCode.setHorizontalAlignment(JTextField.CENTER);
		qrCode.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		
		doneUpdating = new JButton("UPDATE");
		doneUpdating.setBounds(920, 450, 80, 40);
		doneUpdating.setFocusable(false);
		doneUpdating.setBackground(Color.GREEN);
		doneUpdating.setForeground(Color.BLACK);
		doneUpdating.setFont(new Font("Arial",Font.PLAIN,13));
		doneUpdating.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		doneUpdating.addActionListener(e -> {
			db.updateInformation(id,String.valueOf(empPassword.getPassword()),empLastname.getText(),
					 empFirstname.getText(),empContact.getText(),empRate.getText(),empPosition.getText(),
					 getUpdatedImage);
			clearupdatedInfo();
		});
		 
		updateinfoPanel = new JPanel();
		updateinfoPanel.setBackground(Color.white);
		updateinfoPanel.setBounds(10,80,1050,500);
		updateinfoPanel.setVisible(false);
		updateinfoPanel.setLayout(null);
		updateinfoPanel.add(empID);
		updateinfoPanel.add(empIDlbl);
		updateinfoPanel.add(empPassword);
		updateinfoPanel.add(empPasswordlbl);
		updateinfoPanel.add(showPassButton);
		updateinfoPanel.add(empLastname);
		updateinfoPanel.add(empLastnamelbl);
		updateinfoPanel.add(empFirstname);
		updateinfoPanel.add(empFirstnamelbl);
		updateinfoPanel.add(empContact);
		updateinfoPanel.add(empContactlbl);
		updateinfoPanel.add(empRate);
		updateinfoPanel.add(empRatelbl);
		updateinfoPanel.add(empPosition);
		updateinfoPanel.add(empPositionlbl);
		updateinfoPanel.add(profileImg);
		updateinfoPanel.add(qrCode);
		updateinfoPanel.add(doneUpdating);

	}
	
	
	public void getselectedInfo(String id, String password, String lName, String fName, String contact, String rate, String position) {
		this.id = id;
		this.password = password;
		this.lName = lName;
		this.fName = fName;
		this.contact = contact;
		this.rate = rate;
		this.position = position;
		
		empID.setText(id);
		empPassword.setText(password);
		empLastname.setText(lName);
		empFirstname.setText(fName);
		empContact.setText(contact);
		empRate.setText(rate);
		empPosition.setText(position);
		
		qrImage = new ImageIcon(id + ".png");
		qrCode.setIcon(qrImage);
	}
	
	public void clearupdatedInfo() {
		empID.setText("");
		empPassword.setText("");
		empLastname.setText("");
		empFirstname.setText("");
		empContact.setText("");
		empRate.setText("");
		empPosition.setText("");
		
		qrCode.setText("CLICK HERE TO UPDATE THE PHOTO");
		qrImage = new ImageIcon();
		qrCode.setIcon(qrImage);
	}
	
	
}
