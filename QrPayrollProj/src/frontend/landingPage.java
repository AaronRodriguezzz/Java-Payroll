package frontend;
import db.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class landingPage extends JFrame {
	private static final long serialVersionUID = 1L;  	
	boolean sectionCheck = true;
	public JPanel timestampSuccess;
	public JPanel employeePanel;
	public JPanel camPanel;
	BufferedImage img;
    LocalDate date;
    LocalDate previousDate;
    DateTimeFormatter formatter;
    DateTimeFormatter formatter2;
	LocalTime currentTime;
	LuminanceSource src;
	LocalDate localdate;
	BinaryBitmap bitmap;
	Result result;
	JTextField txtUsername;
	JLabel announcementMess;
	JPasswordField txtPassword;
	JPasswordField adminCode;
	JPasswordField newPassword;
	JPasswordField confirmNewPassword;
	JButton showPassButton;
	JButton employeeSide;
	JButton adminSide;
	JButton timingIn;
	JButton loggingIn;
	JButton adminLoggingIn;
	JButton loginButt;
	JButton forgotPassword;
	JButton proceedButt;
	JButton shownewPass;
	JButton showconfirmnewPass;
	JButton x;
	JLabel usernamelbl;
	JLabel passwordlbl;
	JLabel picturelbl;
	JLabel headPicture;
	Webcam webcam;
	ImageIcon img0;
	ImageIcon img1;
	ImageIcon loginIcon;
	ImageIcon usernameIcon;
	ImageIcon passwordIcon;


	int hour;
	int minutes;
	int i=30;
	int loginChoice = 0;

	String formattedDate;
	String formattedPreviousDate;
	String formattedTime;
	String message;
	
	LandingPageFunc lpf = new LandingPageFunc();
	AnnouncementFunc af = new AnnouncementFunc();
	AdminSection adsec = new AdminSection();
	EmployeeSection empSec;
	public landingPage(){
		loginIcon = new ImageIcon("login.png");
		usernameIcon = new ImageIcon("username.png");
		passwordIcon = new ImageIcon("password.png");

		
		img0 = new ImageIcon("no bg.png");
		Image image=img0.getImage().getScaledInstance(300, 200, java.awt.Image.SCALE_AREA_AVERAGING);
		img0 = new ImageIcon(image);
		
		img1 = new ImageIcon("DASHBOARD.png");
		Image image1=img1.getImage().getScaledInstance(100, 50, java.awt.Image.SCALE_AREA_AVERAGING);
		img1 = new ImageIcon(image1);
		
		Webcam webcam =  Webcam.getDefault();
	    webcam.setViewSize(new Dimension(640,480));
	    WebcamPanel webcamPanel  = new WebcamPanel(webcam);
	    webcamPanel.setMirrored(false);
	    
	    headPicture = new JLabel("Student Count: " + getPresentNum());
	    headPicture.setForeground(Color.green);
	    headPicture.setFont(new Font("Arial",Font.PLAIN,20));
	    headPicture.setBounds(100, 30, 150, 40);
	    
		x = new JButton("X");
		x.setBounds(1030, 0, 20, 20);
		x.setFocusable(false);
		x.setBackground(Color.white);
		x.setForeground(Color.RED);
		x.setFont(new Font("Arial",Font.PLAIN,15));
		x.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		x.addActionListener(e -> { this.dispose(); });
	    
	    employeeSide = new JButton("Student");
	    employeeSide.setFont(new Font("ARIAL", Font.PLAIN,17));
	    employeeSide.setBackground(Color.white);
	    employeeSide.setBounds(30,30,130,30);
	    employeeSide.setForeground(Color.decode("#5EEB29"));
	    employeeSide.setFocusable(false);
	    employeeSide.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.decode("#5EEB29")));
	    employeeSide.addActionListener( e -> {
		    employeeSide.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.decode("#5EEB29")));
		    adminSide.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		    loginButt.setText("LOG IN");
		    loginButt.setBackground(Color.decode("#5EEB29"));
		    usernamelbl.setForeground(Color.decode("#5EEB29"));
		    passwordlbl.setForeground(Color.decode("#5EEB29"));
		    showPassButton.setForeground(Color.decode("#EEB29"));
		    forgotPassword.setForeground(Color.decode("#EEB29"));
		    announcementMess.setForeground(Color.decode("#EEB29"));
		    forgotPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#5EEB29")));
	    });

	    adminSide = new JButton("Teacher");
	    adminSide.setFont(new Font("ARIAL", Font.PLAIN,17));
	    adminSide.setFocusable(false);
	    adminSide.setBackground(Color.white);
	    adminSide.setBounds(160,30,100,30);
	    adminSide.setForeground(Color.decode("#FFA500"));
	    adminSide.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode("#FFA500")));
	    adminSide.addActionListener( e -> {
		    employeeSide.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		    adminSide.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.decode("#FFA500")));
		    loginButt.setText("ADMIN LOG IN");
		    loginButt.setBackground(Color.decode("#FFA500"));
		    usernamelbl.setForeground(Color.decode("#FFA500"));
		    passwordlbl.setForeground(Color.decode("#FFA500"));
		    showPassButton.setForeground(Color.decode("#FFA500"));
		    forgotPassword.setForeground(Color.decode("#FFA500"));
		    announcementMess.setForeground(Color.decode("#FFA500"));
		    forgotPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#FFA500")));
	    });
	    
	    adminLoggingIn = new JButton("TEACHER LOG IN");
	    adminLoggingIn.setFont(new Font("ARIAL", Font.PLAIN,15));
	    adminLoggingIn.setFocusable(false);
	    adminLoggingIn.setBackground(Color.white);
	    adminLoggingIn.setBounds(480,25,150,35);
	    adminLoggingIn.setForeground(Color.decode("#5EEB29"));
	    adminLoggingIn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#5EEB29")));
	    adminLoggingIn.addActionListener( e -> {
	    	adminLoggingIn.setBackground(Color.decode("#5EEB29"));
	    	adminLoggingIn.setForeground(Color.white);
		    loggingIn.setForeground(Color.decode("#5EEB29"));
		    loggingIn.setBackground(Color.white);
		    timingIn.setBackground(Color.white);
		    timingIn.setForeground(Color.decode("#FFA500"));
		    loginChoice = 1;
	    });

	    loggingIn = new JButton("STUDENT LOG IN");
	    loggingIn.setFont(new Font("ARIAL", Font.PLAIN,15));
	    loggingIn.setFocusable(false);
	    loggingIn.setBackground(Color.white);
	    loggingIn.setBounds(630,25,150,35);
	    loggingIn.setForeground(Color.decode("#5EEB29"));
	    loggingIn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#5EEB29")));
	    loggingIn.addActionListener( e -> {
		    loggingIn.setForeground(Color.white);
		    loggingIn.setBackground(Color.decode("#5EEB29"));
		    timingIn.setBackground(Color.white);
		    timingIn.setForeground(Color.decode("#FFA500"));
	    	adminLoggingIn.setBackground(Color.white);
	    	adminLoggingIn.setForeground(Color.decode("#5EEB29"));
		    loginChoice = 2;
	    });
	    
	 
	    
	    timingIn = new JButton("TIME STAMP");
	    timingIn.setFont(new Font("ARIAL", Font.PLAIN,15));
	    timingIn.setFocusable(false);
	    timingIn.setBackground(Color.decode("#FFA500"));
	    timingIn.setBounds(780,25,150,35);
	    timingIn.setForeground(Color.white);
	    timingIn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#FFA500")));
	    timingIn.addActionListener( e -> {
		    loggingIn.setForeground(Color.decode("#5EEB29"));
		    loggingIn.setBackground(Color.white);
		    timingIn.setBackground(Color.decode("#FFA500"));
		    timingIn.setForeground(Color.white);
		    adminLoggingIn.setBackground(Color.white);
	    	adminLoggingIn.setForeground(Color.decode("#5EEB29"));
		    loginChoice = 3;
	    });
	    
	    usernamelbl = new JLabel("IDENTIFICATION");
	    usernamelbl.setBounds(20, 180, 100, 20);
	    usernamelbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
	    usernamelbl.setForeground(Color.decode("#5EEB29"));
		
	    txtUsername = new JTextField();
		txtUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		txtUsername.setOpaque(false);
		txtUsername.setBounds(20, 140, 270, 40);
		txtUsername.setFont(new Font("ARIAL", Font.PLAIN, 15));

		showPassButton = new JButton("SHOW");
		showPassButton.setHorizontalAlignment(JTextField.CENTER);
		showPassButton.setBounds(260, 280, 30, 20);
		showPassButton.setFocusable(false);
		showPassButton.setBackground(Color.WHITE);
		showPassButton.setForeground(Color.decode("#5EEB29"));
		showPassButton.setFont(new Font("Arial",Font.PLAIN,8));
		showPassButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		showPassButton.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) { txtPassword.setEchoChar((char) 0); }
			public void mouseReleased(MouseEvent e) { txtPassword.setEchoChar('•'); }
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}	
		});
		
		passwordlbl = new JLabel("PASSWORD");
		passwordlbl.setBounds(20, 280, 100, 20);
		passwordlbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
		passwordlbl.setForeground(Color.decode("#5EEB29"));
		
		
		
	    loginButt = new JButton("LOG IN");
	    loginButt.setFont(new Font("ARIAL", Font.PLAIN,17));
	    loginButt.setFocusable(false);
	    loginButt.setBackground(Color.decode("#5EEB29"));
	    loginButt.setForeground(Color.BLACK);
	    loginButt.setBounds(20,330,270,50);
	    loginButt.setIcon(loginIcon);
	    loginButt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
	    loginButt.addActionListener(e -> {
	    	if(loginButt.getText().equalsIgnoreCase("LOG IN")) {
	    		lpf.employeelogin(txtUsername.getText(), String.valueOf(txtPassword.getPassword()));
	    		loginsuccess(txtUsername.getText());
	    		txtUsername.setText("");
	    		txtPassword.setText("");
	    	}else {
	    		lpf.adminLogin(txtUsername.getText(), String.valueOf(txtPassword.getPassword()));
	    		loginsuccess(txtUsername.getText());
	    		txtUsername.setText("");
	    		txtPassword.setText("");
	    	}
	    });
	    txtPassword = new JPasswordField();
		txtPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		txtPassword.setOpaque(false);
		txtPassword.setBounds(20, 240, 270, 40);	
		txtPassword.setFont(new Font("ARIAL", Font.PLAIN, 15));
	    
	    txtPassword = new JPasswordField();
		txtPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		txtPassword.setOpaque(false);
		txtPassword.setBounds(20, 240, 270, 40);	
		txtPassword.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		newPassword = new JPasswordField();
		newPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		newPassword.setOpaque(false);
		newPassword.setBounds(20, 140, 270, 40);	
		newPassword.setFont(new Font("ARIAL", Font.PLAIN, 15));
		newPassword.setVisible(false);
		
		
		shownewPass = new JButton("SHOW");
		shownewPass.setHorizontalAlignment(JTextField.CENTER);
		shownewPass.setBounds(260, 180, 30, 20);
		shownewPass.setFocusable(false);
		shownewPass.setBackground(Color.WHITE);
		shownewPass.setForeground(Color.decode("#5EEB29"));
		shownewPass.setFont(new Font("Arial",Font.PLAIN,8));
		shownewPass.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		shownewPass.setVisible(false);
		shownewPass.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) { newPassword.setEchoChar((char) 0); }
			public void mouseReleased(MouseEvent e) { newPassword.setEchoChar('•'); }
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}	
		});
		
		confirmNewPassword = new JPasswordField();
		confirmNewPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		confirmNewPassword.setOpaque(false);
		confirmNewPassword.setBounds(20, 240, 270, 40);	
		confirmNewPassword.setFont(new Font("ARIAL", Font.PLAIN, 15));
		confirmNewPassword.setVisible(false);
		
		showconfirmnewPass = new JButton("SHOW");
		showconfirmnewPass.setHorizontalAlignment(JTextField.CENTER);
		showconfirmnewPass.setBounds(260, 280, 30, 20);
		showconfirmnewPass.setFocusable(false);
		showconfirmnewPass.setBackground(Color.WHITE);
		showconfirmnewPass.setForeground(Color.decode("#5EEB29"));
		showconfirmnewPass.setFont(new Font("Arial",Font.PLAIN,8));
		showconfirmnewPass.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		showconfirmnewPass.setVisible(false);
		showconfirmnewPass.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) { confirmNewPassword.setEchoChar((char) 0); }
			public void mouseReleased(MouseEvent e) { confirmNewPassword.setEchoChar('•'); }
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}	
		});

	    
	    proceedButt = new JButton("PROCEED");
	    proceedButt.setFont(new Font("ARIAL", Font.PLAIN,17));
	    proceedButt.setFocusable(false);
	    proceedButt.setBackground(Color.decode("#5EEB29"));
	    proceedButt.setForeground(Color.BLACK);
	    proceedButt.setBounds(20,330,270,50);
	    proceedButt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
	    proceedButt.setVisible(false);
	    proceedButt.addActionListener(e -> {
	    	
	    	if(proceedButt.getText().equals("PROCEED")) {
	    		if(lpf.answerStr.equals(String.valueOf(txtPassword.getPassword()))) {
		    		txtUsername.setVisible(false);
	    			txtPassword.setVisible(false);
	    			showPassButton.setVisible(false);
	    			newPassword.setVisible(true);
	    			confirmNewPassword.setVisible(true);
	    			shownewPass.setVisible(true);
	    			showconfirmnewPass.setVisible(true);
	    			txtUsername.setText("");
	    			txtPassword.setText("");
	    			usernamelbl.setText("New Password");
		    		passwordlbl.setText("Confirm Password");
	    			proceedButt.setText("SAVE");
		    	}else JOptionPane.showMessageDialog(null, "Invalid Answer");
	    		
	    	}else {
	    		String trimPass = String.valueOf(newPassword.getPassword());
		    	String trimconfirmPass = String.valueOf(confirmNewPassword.getPassword());

			    if(!trimPass.trim().isEmpty() && !trimconfirmPass.trim().isEmpty()) {
			    	if(trimPass.equals(trimconfirmPass)){
			    		lpf.forgotEmployeepassStepThree(trimPass);
		    			txtUsername.setVisible(true);
		    			txtPassword.setVisible(true);
			    		forgotPassword.setVisible(true);
			    		adminSide.setVisible(true);
			    		employeeSide.setVisible(true);
			    		showPassButton.setVisible(true);
			    		loginButt.setVisible(true);
		    			newPassword.setVisible(false);
		    			confirmNewPassword.setVisible(false);
		    			shownewPass.setVisible(false);
			    		proceedButt.setVisible(false);
			    		usernamelbl.setText("EMPLOYEE ID");
			    		passwordlbl.setText("PASSWORD");
		    			proceedButt.setText("PROCEED");

			    		txtUsername.setFont(new Font("ARIAL", Font.PLAIN, 15));
			    		txtUsername.setEnabled(true);
		    			showconfirmnewPass.setVisible(true);
		    			newPassword.setText("");
		    			confirmNewPassword.setText("");
			    	}else JOptionPane.showMessageDialog(null, "Password doesn't match");
			    }
	    	}
	    	
	    });
	    
	    forgotPassword = new JButton("Forgot Password?");
	    forgotPassword.setFont(new Font("ARIAL", Font.PLAIN,13));
	    forgotPassword.setFocusable(false);
	    forgotPassword.setBackground(Color.white);
	    forgotPassword.setForeground(Color.decode("#5EEB29"));
	    forgotPassword.setBounds(100,400,110,20);
	    forgotPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#5EEB29")));
	    forgotPassword.addActionListener(e -> {
	    	String usernameTrim = txtUsername.getText();

	    	if(!usernameTrim.trim().isEmpty()) {
	    		if(loginButt.getText().equalsIgnoreCase("LOG IN")) lpf.forgotEmployeepassword(Integer.parseInt(usernameTrim), "employee");
		    	else lpf.forgotEmployeepassword(Integer.parseInt(usernameTrim), "admin");
	    		
	    		if(lpf.forgotpassValid) {
		    		adminSide.setVisible(false);
		    		employeeSide.setVisible(false);
		    		forgotPassword.setVisible(false);
		    		loginButt.setVisible(false);
		    		proceedButt.setVisible(true);
		    		
		    		usernamelbl.setText("Question");
		    		passwordlbl.setText("Answer");
		    		txtUsername.setEnabled(false);
		    		txtUsername.setFont(new Font("ARIAL", Font.PLAIN, 13));
		    		txtUsername.setText(lpf.forgotEmployeepassSteptwo(Integer.parseInt(usernameTrim)));
		    	}
	    	}
	    	
	    });
	    
		
	    picturelbl = new JLabel();
	    picturelbl.setBounds(10, 400, 300, 200);
	    picturelbl.setIcon(img0);
	    
	    message = "ANNOUNCEMENT : " +  af.showAnnouncement() + "       " + "ANNOUNCEMENT : " +  af.showAnnouncement();
	    announcementMess = new JLabel(message);
	    announcementMess.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
	    announcementMess.setVisible(true);
	    announcementMess.setHorizontalAlignment(SwingConstants.CENTER);
	    announcementMess.setBackground(Color.black);
	    announcementMess.setBounds(30, 400, 1200, 160);
	    announcementMess.setForeground(Color.decode("#5EEB29"));
	    announcementMess.setFont(new Font("ARIAL", Font.BOLD, 20));	

	    
	    employeePanel = new JPanel();
	    employeePanel.setBounds(30, 60, 340, 520);
	    employeePanel.setLayout(null);
	    employeePanel.setBackground(Color.white);
	    employeePanel.add(txtUsername);
	    employeePanel.add(txtPassword);
	    employeePanel.add(adminSide);
	    employeePanel.add(employeeSide);
	    employeePanel.add(loginButt);
	    employeePanel.add(showPassButton);
	    employeePanel.add(usernamelbl);
	    employeePanel.add(passwordlbl);
	   // employeePanel.add(picturelbl);
	   // employeePanel.add(announcementMess);
	    employeePanel.add(forgotPassword);
	    employeePanel.add(newPassword);
	    employeePanel.add(confirmNewPassword);
	    employeePanel.add(proceedButt);
	    employeePanel.add(shownewPass);
	    employeePanel.add(showconfirmnewPass);
	    
	    currentTime = LocalTime.now();
	    date = LocalDate.now();
	    previousDate = date.minusDays(1);
	    formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	    formattedDate = date.format(formatter);
	    formattedPreviousDate = date.format(formatter);
	    formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	    formattedTime = currentTime.format(formatter2);

	
	    camPanel = new JPanel();
	    camPanel.setBounds(380, 80, 640, 480);
	    camPanel.add(webcamPanel);
	    
	    this.add(camPanel);
	    this.add(headPicture);
	    this.add(loggingIn);
	    this.add(adminLoggingIn);
	    this.add(timingIn);
	    this.add(employeePanel);
	    this.add(x);
		this.setSize(1050,600);
		this.getContentPane().setBackground(Color.white);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);
	

		
		do {
			try {
				img = webcam.getImage();
				src = new BufferedImageLuminanceSource(img);
				bitmap = new BinaryBitmap(new HybridBinarizer(src));
				result = new MultiFormatReader().decode(bitmap);
				
				System.out.println(result);
				if(loginChoice == 1) {
					System.out.println(loginChoice);
					String password = qrLoginChecking();
		    		lpf.adminLogin(result.getText(), password);
		    		loginsuccess(result.getText());
				}else if(loginChoice == 2) {
					System.out.println(loginChoice);
					String password = qrLoginChecking();
		    		lpf.employeelogin(result.getText(), password);
		    		loginsuccess(result.getText());
				}else {
					lpf.timeGetter(result.getText(), formattedDate, hour, minutes, hour, minutes, hour);
				}
			
				headPicture.setText("Student Count: " + getPresentNum());
				Thread.sleep(3000);
			}catch(Exception e) {
				System.out.println("SCANNING");
				try {
					Thread.sleep(1500);
				}catch(Exception e1) {
					
				}

			}
		}while(true);

	}
	
	void loginsuccess(String id) {
		if(lpf.successloginCheck) {
			lpf.successloginCheck = false;
			empSec = new EmployeeSection(id);
			empSec.setVisible(true);
			txtUsername.setText("");
			txtPassword.setText("");
		}else if(lpf.successadminLogin) {
			lpf.successadminLogin = false;
			adsec.setVisible(true);
			txtUsername.setText("");
			txtPassword.setText("");
		}
		
	}
	
	void showAnnouncement() {
	    message = "ANNOUNCEMENT : " +  af.showAnnouncement() + "       " + "ANNOUNCEMENT : " +  af.showAnnouncement();
	    announcementMess.setText(message);
		if(!af.showAnnouncement().isEmpty()) {
			try {
				if(i>-950) {
					i-=30;
					System.out.println(i);
					picturelbl.setVisible(false);
					announcementMess.setVisible(true);
					announcementMess.setBounds(i, 390, 1200, 160);
					Thread.sleep(400);
					
				}else {
					i=250;
					announcementMess.setBounds(i, 390, 1200, 160);
					picturelbl.setVisible(true);
					announcementMess.setVisible(false);
					Thread.sleep(7000);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public String qrLoginChecking() {
		 JPasswordField passwordField = new JPasswordField();
		 String password = null;
	        // Create a JOptionPane with the password field
	        int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter Password", JOptionPane.OK_CANCEL_OPTION);

	        if (option == JOptionPane.OK_OPTION) {
	            char[] passwordChars = passwordField.getPassword();
	            password = new String(passwordChars);
	        } else {
	            System.out.println("User canceled the operation.");
	        }
			return password;
	}
	
	public int getPresentNum() {
		int presentCount = 0;
		
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
			PreparedStatement pstmt = Conn.prepareStatement("SELECT COUNT(*) AS rowcount FROM workingemp"); 
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				presentCount = rs.getInt("rowcount");
				System.out.println("Count" + presentCount);
			}
			rs.close();
			pstmt.close();
			Conn.close();
			
		}catch(SQLException e2) {
			 e2.printStackTrace();
		}
		
		return presentCount;
		
	}
}
