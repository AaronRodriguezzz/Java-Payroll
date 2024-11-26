package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import backend.EmployeeSectionFunc;

public class DisplayEmpInfo extends JFrame {
	
	public String id; 
	public boolean thisFrameisVisible = false;
	JLabel profileImg,empIDlbl,empName, empContactlbl, empRatelbl, qrCode;
	JLabel oldpasswordLbl, newpasswordLbl;
	JPasswordField oldPassword, newPassword;
	JButton changePassword, viewQrcode,exit,saveButt;
	JPanel profilePanel,changepassPanel, veiwqrPanel;
	
	
	EmployeeSectionFunc empsecFunc = new EmployeeSectionFunc();
	DisplayEmpInfo(String id){
		this.id = id;
		
		profileImg = new JLabel();
		profileImg.setBounds(390, 20, 180, 180);
		profileImg.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		
		empIDlbl = new JLabel("Identification : " + "23000001");
		empIDlbl.setBounds(40, 20, 250, 30);
		empIDlbl.setFont(new Font("ARIAL", Font.BOLD, 16));

		empName = new JLabel("Full Name : " + "FRANCIS AARON RODRIGUEZ");
		empName.setBounds(40, 70, 350, 20);
		empName.setFont(new Font("ARIAL", Font.BOLD, 16));
		
		empContactlbl = new JLabel("Contact Number : " + "09091230932");
		empContactlbl.setBounds(40, 120, 250, 20);
		empContactlbl.setFont(new Font("ARIAL", Font.BOLD, 16));
		
		empRatelbl = new JLabel("Rate : " + "1500.25");
		empRatelbl.setBounds(40, 170, 200, 20);
		empRatelbl.setFont(new Font("ARIAL", Font.BOLD, 16));
		
		changePassword = new JButton("Change Password");
		changePassword.setFont(new Font("ARIAL", Font.PLAIN,13));
		changePassword.setFocusable(false);
		changePassword.setBackground(Color.decode("#5EEB29"));
		changePassword.setForeground(Color.BLACK);
		changePassword.setBounds(40,230,140,30);
		changePassword.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		changePassword.addActionListener(e -> {
			changepassPanel.setVisible(true);
			profilePanel.setVisible(false);
		});
		
		viewQrcode = new JButton("View QR Code");
		viewQrcode.setFont(new Font("ARIAL", Font.PLAIN,13));
		viewQrcode.setFocusable(false);
		viewQrcode.setBackground(Color.decode("#FFA500"));
		viewQrcode.setForeground(Color.BLACK);
		viewQrcode.setBounds(190,230,140,30);
		viewQrcode.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		viewQrcode.addActionListener(e -> {
			if(viewQrcode.getText().equals("View QR Code")) {
				ImageIcon img0 = new ImageIcon(id + ".png");
				Image image=img0.getImage().getScaledInstance(180, 180, java.awt.Image.SCALE_AREA_AVERAGING);
				img0 = new ImageIcon(image);
				profileImg.setIcon(img0);
				viewQrcode.setText("Show Profile Image");
			}else{
				ImageIcon img0 = new ImageIcon(empsecFunc.getInfo(id));
				Image image=img0.getImage().getScaledInstance(180, 180, java.awt.Image.SCALE_AREA_AVERAGING);
				img0 = new ImageIcon(image);
				profileImg.setIcon(img0);
				viewQrcode.setText("View QR Code");
			}
				
		});
		
		exit = new JButton("X");
		exit.setFont(new Font("ARIAL", Font.PLAIN,13));
		exit.setFocusable(false);
		exit.setBackground(Color.WHITE);
		exit.setForeground(Color.RED);
		exit.setBounds(580,00,20,20);
		exit.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		exit.addActionListener(e -> {
			thisFrameisVisible = false;
			this.dispose();
		});
		
		profilePanel = new JPanel();
		profilePanel.setBounds(0,20,600,300);
		profilePanel.setLayout(null);
		profilePanel.setBackground(Color.white);
		profilePanel.add(profileImg);
		profilePanel.add(empIDlbl);
		profilePanel.add(empContactlbl);
		profilePanel.add(empName);
		profilePanel.add(empRatelbl);
		profilePanel.add(changePassword);
		profilePanel.add(viewQrcode);
		
		oldPassword = new JPasswordField();
		oldPassword.setHorizontalAlignment(JTextField.CENTER);
		oldPassword.setBounds(150,20,300,40);
		oldPassword.setBackground(Color.black);
		oldPassword.setForeground(Color.WHITE);
		oldPassword.setFont(new Font(Font.DIALOG, Font.PLAIN,  15));
		oldPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		
		oldpasswordLbl = new JLabel("Old Password");
		oldpasswordLbl.setBounds(150, 60, 130, 20);
		oldpasswordLbl.setFont(new Font("ARIAL", Font.BOLD, 13));
		
		newPassword = new JPasswordField();
		newPassword.setBounds(150,100,300,40);
		newPassword.setBackground(Color.black);
		newPassword.setHorizontalAlignment(JTextField.CENTER);
		newPassword.setForeground(Color.WHITE);
		newPassword.setFont(new Font(Font.DIALOG, Font.PLAIN,  15));
		newPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		
		newpasswordLbl = new JLabel("New Password");
		newpasswordLbl.setBounds(150, 140, 130, 20);
		newpasswordLbl.setFont(new Font("ARIAL", Font.BOLD, 13));
		
		
		saveButt = new JButton("SAVE");
		saveButt.setFont(new Font(Font.DIALOG, Font.PLAIN,15));
		saveButt.setFocusable(false);
		saveButt.setBackground(Color.decode("#27AAE1"));
		saveButt.setForeground(Color.white);
		saveButt.setBounds(250,200,100,30);
		saveButt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		saveButt.addActionListener(e -> {
			if(empsecFunc.oldpassVerified(id, String.valueOf(oldPassword.getPassword()))) {
				saveButt.setEnabled(false);
				empsecFunc.updatePassword(id, String.valueOf(newPassword.getPassword()));
				saveButt.setEnabled(true);
			}else JOptionPane.showMessageDialog(null, "Old Password Invalid");
		});
		
		changepassPanel = new JPanel();
		changepassPanel.setBounds(0,20,600,300);
		changepassPanel.setVisible(false);
		changepassPanel.setLayout(null);
		changepassPanel.setBackground(Color.white);
		changepassPanel.add(oldPassword);
		changepassPanel.add(oldpasswordLbl);
		changepassPanel.add(newPassword);
		changepassPanel.add(newpasswordLbl);
		changepassPanel.add(saveButt);
		
		this.add(changepassPanel);
		this.add(profilePanel);
		this.add(exit);
		this.setSize(600,300);
		this.getContentPane().setBackground(Color.white);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);
		setemployeeInfo();

	}
	
	public void setemployeeInfo() {
	
		
		ImageIcon img0 = new ImageIcon(empsecFunc.getInfo(id));
		Image image=img0.getImage().getScaledInstance(180, 180, java.awt.Image.SCALE_AREA_AVERAGING);
		img0 = new ImageIcon(image);
		profileImg.setIcon(img0);
		
		empIDlbl.setText("Identification : " + id);
		empName.setText("Full Name : " +  empsecFunc.showName);
		empContactlbl.setText("Position : " + empsecFunc.position);
		empRatelbl.setText("Rate : " + empsecFunc.empRate);
	}
}
