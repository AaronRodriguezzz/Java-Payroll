package frontend;

import db.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminSection extends JFrame{
	private static final long serialVersionUID = 1L;

	Object columns[]= {"ID","PASSWORD","LAST NAME","FIRST NAME","CONTACT NUM"};
	Object columns1[]= {"DATE","EMPLOYEE ID","TITLE","MESSAGE"};
	Object columns2[]= {"DATE","ANNOUNCEMENT"};


	DefaultTableModel model;
	JScrollPane scrollPane;
	JTable table;
	JButton adminId; 
	JButton newAdmin;
	JButton newEmployee;
	JButton workingEmployee;
	JButton delete;
	JButton update;
	JButton viewEmployee;
	JButton viewMessage;
	JButton addNews;
	JButton mail;
	JButton x;
	JButton reset;


	JTextArea message;
	JTextField messageTitle;
	JButton delMessage, dispose;
	JFrame messageFrame;
	JLabel titleLbl;
	JLabel messageLbl;
	
	String titlestr,messagestr;
	
	NewemployeeSection newEmp = new NewemployeeSection();
	UpdateInformationEmp updc = new UpdateInformationEmp();
	WorkingEmployee workingEmp = new WorkingEmployee();
	AnnouncementFunc af = new AnnouncementFunc();
	EmployeeSection empSec;
	Dbclass db = new Dbclass();
	
	String infoOne,infoTwo,infoThree,infoFour,infoFive,infoSix,infoSeven;
	public AdminSection(){
		ImageIcon mailIcon = new ImageIcon("mail.png");

		mail = new JButton("VIEW MAIL");
		mail.setFocusable(false);
		mail.setBounds(150, 20, 130, 40);
		mail.setBackground(Color.white);
		mail.setForeground(Color.black);
		mail.setFont(new Font("Arial",Font.PLAIN,13));
		mail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		mail.addActionListener(e -> {
			newEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
			workingEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
			workingEmp.workingEmpPanel.setVisible(false);
			scrollPane.setVisible(true);
			newEmp.newemployeePanel.setVisible(false);
			updc.updateinfoPanel.setVisible(false);
			viewMessage.setEnabled(true);
			viewEmployee.setEnabled(false);
			viewMessage.setVisible(true);
			viewEmployee.setVisible(true);
			update.setEnabled(false);
			delete.setEnabled(false);
			update.setVisible(true);
			delete.setVisible(true);
			model.setColumnIdentifiers(columns1);
			model.setRowCount(0);
			db.employeesMessages(model);
		});
		
		
		adminId = new JButton("VIEW ALL");
		adminId.setBounds(10, 20, 130, 40);
		adminId.setFocusable(false);
		adminId.setBackground(Color.white);
		adminId.setForeground(Color.black);
		adminId.setFont(new Font("Arial",Font.PLAIN,13));
		adminId.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		adminId.addActionListener(e -> {
			newEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
			workingEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
			workingEmp.workingEmpPanel.setVisible(false);
			scrollPane.setVisible(true);
			newEmp.newemployeePanel.setVisible(false);
			updc.updateinfoPanel.setVisible(false);
			update.setVisible(true);
			delete.setVisible(true);
			viewMessage.setEnabled(false);
			viewEmployee.setEnabled(true);
			update.setEnabled(true);
			delete.setEnabled(true);
			viewMessage.setVisible(true);
			viewEmployee.setVisible(true);
			model.setColumnIdentifiers(columns);
    		model.setRowCount(0);
			db.employeeJTable(model);
		});
		
		x = new JButton("X");
		x.setBounds(1030, 0, 20, 20);
		x.setFocusable(false);
		x.setBackground(Color.white);
		x.setForeground(Color.RED);
		x.setFont(new Font("Arial",Font.PLAIN,15));
		x.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		x.addActionListener(e -> { 
			this.dispose(); 
		});
		
		newAdmin = new JButton("NEW ADMIN");
		newAdmin.setBounds(580, 20, 120, 40);
		newAdmin.setFocusable(false);
		newAdmin.setBackground(Color.white);
		newAdmin.setForeground(Color.black);
		newAdmin.setFont(new Font("Arial",Font.PLAIN,15));
		newAdmin.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		newAdmin.addActionListener(e -> {
			newAdmin.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.gray));
			newEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
			workingEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
			workingEmp.workingEmpPanel.setVisible(false);
			scrollPane.setVisible(false);
			newEmp.newemployeePanel.setVisible(true);
			updc.updateinfoPanel.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			viewEmployee.setVisible(false);
			model.setRowCount(0);
			db.employeeJTable(model);
		});
		
		newEmployee = new JButton("NEW STUDENT");
		newEmployee.setBounds(700, 20, 150, 40);
		newEmployee.setFocusable(false);
		newEmployee.setBackground(Color.white);
		newEmployee.setForeground(Color.black);
		newEmployee.setFont(new Font("Arial",Font.PLAIN,15));
		newEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		newEmployee.addActionListener(e -> { 
			newAdmin.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
			newEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.gray));
			workingEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
			workingEmp.workingEmpPanel.setVisible(false);
			scrollPane.setVisible(false);
			newEmp.newemployeePanel.setVisible(true);
			updc.updateinfoPanel.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			viewEmployee.setVisible(false);
			model.setRowCount(0);
			db.employeeJTable(model);
			
		});
		
		workingEmployee = new JButton("WHO'S PRESENT");
		workingEmployee.setBounds(860, 20, 150, 40);
		workingEmployee.setFocusable(false);
		workingEmployee.setBackground(Color.white);
		workingEmployee.setForeground(Color.black);
		workingEmployee.setFont(new Font("Arial",Font.PLAIN,15));
		workingEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		workingEmployee.addActionListener(e -> {
			newAdmin.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
			newEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
			workingEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.gray));
			workingEmp.workingEmpPanel.setVisible(true);
			scrollPane.setVisible(false);
			newEmp.newemployeePanel.setVisible(false);
			updc.updateinfoPanel.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			viewEmployee.setVisible(false);
		});

		delete = new JButton("DELETE");
		delete.setBounds(830, 520, 80, 40);
		delete.setFocusable(false);
		delete.setBackground(Color.RED);
		delete.setForeground(Color.BLACK);
		delete.setFont(new Font("Arial",Font.PLAIN,13));
		delete.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		delete.addActionListener(e -> {
			if(table.getSelectedRow() > -1) {
				db.deleteEmployee(model,table,table.getSelectedRow());
			}else JOptionPane.showMessageDialog(null, "SELECT A ROW", "WARNING", JOptionPane.WARNING_MESSAGE);
		});

		update = new JButton("RESET");
		update.setBounds(920, 520, 80, 40);
		update.setFocusable(false);
		update.setBackground(Color.GREEN);
		update.setForeground(Color.BLACK);
		update.setFont(new Font("Arial",Font.PLAIN,13));
		update.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		update.addActionListener( e -> {
			reset();

			/*if(table.getSelectedRow() > -1) {	
				updateInformationAvailable();
				updc.updateinfoPanel.setVisible(true);
				updc.empID.setEditable(false);
				newEmp.newemployeePanel.setVisible(false);
				scrollPane.setVisible(false);
				update.setVisible(false);
				delete.setVisible(false);
				viewEmployee.setVisible(false);
				model.setRowCount(0);
				db.employeeJTable(model);
			}else {
				JOptionPane.showMessageDialog(null, "SELECT A ROW FIRST", "WARNING", JOptionPane.WARNING_MESSAGE);
			}*/
		});
		
		viewEmployee = new JButton("VIEW STUDENT");
		viewEmployee.setBounds(680, 520, 140, 40);
		viewEmployee.setFocusable(false);
		viewEmployee.setBackground(Color.yellow);
		viewEmployee.setForeground(Color.black);
		viewEmployee.setFont(new Font("Arial",Font.PLAIN,13));
		viewEmployee.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		viewEmployee.addActionListener(e -> {
			if(table.getSelectedRow() > -1) {
				empSec = new EmployeeSection((String) table.getValueAt(table.getSelectedRow(), 0));
				empSec.setVisible(true);
				empSec.update.setVisible(true);
				empSec.delete.setVisible(true);
				empSec.account.setVisible(true);
				empSec.sendMessage.setVisible(false);
			}

		});
		
		viewMessage = new JButton("VIEW MESSAGE");
		viewMessage.setBounds(530, 520, 140, 40);
		viewMessage.setFocusable(false);
		viewMessage.setBackground(Color.yellow);
		viewMessage.setForeground(Color.black);
		viewMessage.setFont(new Font("Arial",Font.PLAIN,13));
		viewMessage.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		viewMessage.setEnabled(false);
		viewMessage.addActionListener(e -> {
			titlestr = (String) table.getValueAt(table.getSelectedRow(), 2);
			messagestr = (String) table.getValueAt(table.getSelectedRow(), 3);
			messageTitle.setText(titlestr);
			message.setText(messagestr);
			messageFrame.setVisible(true);
			this.dispose();
		});
		
		
		addNews = new JButton("CHANGE NEWS");
		addNews.setBounds(50, 520, 120, 40);
		addNews.setFocusable(false);
		addNews.setBackground(Color.BLACK);
		addNews.setForeground(Color.WHITE);
		addNews.setFont(new Font("Arial",Font.PLAIN,12));
		addNews.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		addNews.addActionListener( e -> { 
			try {
				addnews();
			}catch(Exception e1) {
				
			}
		});
		
		
		model = new	DefaultTableModel();
		scrollPane = new JScrollPane();
		table = new JTable();
		
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.getTableHeader().setBackground(Color.white);
		table.getTableHeader().setForeground(Color.black);
		table.setSelectionBackground(Color.black);
		table.setSelectionForeground(Color.white);
		table.getTableHeader().setFont(new Font("Arial",Font.BOLD,13));
		table.getTableHeader().setEnabled(false);
		table.getTableHeader().setPreferredSize(new Dimension(0,20));
		table.setRowHeight(30);
		
		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		scrollPane.setBounds(50,90,950,400);
		scrollPane.setVisible(true);
		
		model.setRowCount(0);
		db.employeeJTable(model);
		
		titleLbl = new JLabel("Title");
		titleLbl.setBounds(10, 10, 130, 20);
		titleLbl.setFont(new Font("ARIAL", Font.BOLD, 13));
		
		messageTitle = new JTextField();
		messageTitle.setEditable(false);
		messageTitle.setBounds(10,30,460,30);
		messageTitle.setBackground(Color.white);
		messageTitle.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
		messageTitle.setFont(new Font("ARIAL", Font.PLAIN, 15));

		
		messageLbl = new JLabel("Message");
		messageLbl.setBounds(10, 60, 130, 20);
		messageLbl.setFont(new Font("ARIAL", Font.BOLD, 13));
		
		message = new JTextArea();
		message.setEditable(false);
		message.setBounds(10,90,460,230);
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		message.setBackground(Color.white);
		message.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
		message.setFont(new Font("ARIAL", Font.PLAIN, 15));
		
		delMessage = new JButton("Delete");
		delMessage.setBounds(390, 330, 80, 25);
		delMessage.setFocusable(false);
		delMessage.setBackground(Color.RED);
		delMessage.setForeground(Color.WHITE);
		delMessage.setFont(new Font("Arial",Font.PLAIN,13));
		delMessage.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		delMessage.addActionListener( e -> {
			if(table.getSelectedRow() > -1) {
				db.deleteMail(model,table.getSelectedRow(),messagestr);
				messageFrame.dispose();
				this.setVisible(true);
			} 
		
		});
		
		dispose = new JButton("X");
		dispose.setBounds(460, 0, 20, 20);
		dispose.setFocusable(false);
		dispose.setBackground(Color.white);
		dispose.setForeground(Color.red);
		dispose.setFont(new Font("Arial",Font.PLAIN,13));
		dispose.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		dispose.addActionListener(e -> {
			messageFrame.dispose();
			this.setVisible(true);
		});
		
		messageFrame = new JFrame();
		messageFrame.setSize(480,400);
		messageFrame.getContentPane().setBackground(Color.white);
		messageFrame.setLayout(null);
		messageFrame.setResizable(false);
		messageFrame.setLocationRelativeTo(null);
		messageFrame.setUndecorated(true);
		messageFrame.setVisible(false);
		messageFrame.add(messageTitle);
		messageFrame.add(messageLbl);
		messageFrame.add(titleLbl);
		messageFrame.add(delMessage);
		messageFrame.add(message);
		messageFrame.add(dispose);

		add(newEmp.newemployeePanel);
		add(updc.updateinfoPanel);
		add(workingEmp.workingEmpPanel);
		add(adminId);
		add(newAdmin);
		add(newEmployee);
		add(workingEmployee);
		add(scrollPane);
		add(update);
		add(delete);
		add(viewEmployee);
		//add(viewMessage);
		//add(addNews);
		//add(mail);
		add(x);
		this.setSize(1050,600);
		this.getContentPane().setBackground(Color.white);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(false);
	}
	
	void updateInformationAvailable() {

		infoOne = (String) table.getValueAt(table.getSelectedRow(), 0);
		infoTwo = (String) table.getValueAt(table.getSelectedRow(), 1);
		infoThree = (String) table.getValueAt(table.getSelectedRow(), 2);
		infoFour = (String) table.getValueAt(table.getSelectedRow(), 3);
		infoFive = (String) table.getValueAt(table.getSelectedRow(), 4);
		infoSix = String.valueOf(table.getValueAt(table.getSelectedRow(), 5)) ;
		infoSeven = (String) table.getValueAt(table.getSelectedRow(), 6);
		updc.getselectedInfo(infoOne,infoTwo, infoThree, infoFour, infoFive, infoSix, infoSeven);
		
	}
	
	void addnews() {
		boolean errorAnnouncement = false;
		do {
			String announcement = JOptionPane.showInputDialog("INPUT YOUR ANNOUNCEMENT");
			if(announcement.length() <= 40) {
				af.changeAnnouncement(announcement);
				break;
			}else {
				JOptionPane.showMessageDialog(null, "YOUR MESSAGE IS TOO LONG");
				errorAnnouncement = true;
			}
		}while(errorAnnouncement);
	}
	
	void reset() {
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
			PreparedStatement pstmt = Conn.prepareStatement("DELETE FROM workingemp"); 
			
			pstmt.execute();
			pstmt.close();
			Conn.close();
			
		}catch(SQLException e2) {
			 e2.printStackTrace();
		}
	}
	
	
}
