package frontend;

import backend.*;
import db.AnnouncementFunc;
import db.Dbclass;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class EmployeeSection extends JFrame{
	private static final long serialVersionUID = 1L;
	Object timeRecord[]= {"DATE","IN-HOUR","IN-MINUTES","OUT-HOUR","OUT-MINUTES","TOTAL HOURS"};
	DefaultTableModel timerecordModel;
	JScrollPane timerecordScrollP;
	JTable timerecordTable;
	JPanel timerecordPanel;
	JPanel salaryPanel;
	JLabel employeeId;
	JLabel salaryVal;
	JLabel totalTime;
	JLabel rate;
	JButton showSalary;
	JButton homeButt;
	JButton account;
	JButton sendMessage;
	JButton okButton;
	JButton update;
	JButton delete;
	

	JTextField messageTitle;
	JButton send, cancel;
	JFrame messageFrame;
	JLabel titleLbl;
	JLabel messageLbl;
	JLabel messageLengthLbl;
	
	String id;
	EmployeeSectionFunc empFunc = new EmployeeSectionFunc();
	DisplayEmpInfo dei;
	Dbclass db = new Dbclass();
	
	int messagecharCount = 0;
	public EmployeeSection(String id){
		ImageIcon mailIcon = new ImageIcon("mail.png");
		ImageIcon accIcon = new ImageIcon("account.png");
		ImageIcon createmessIcon = new ImageIcon("createmessage.png");


		totalTime = new JLabel();
		totalTime.setBounds(30, 80, 200, 30);
		totalTime.setBackground(Color.black);
		totalTime.setForeground(Color.white);
		totalTime.setFont(new Font("ARIAL", Font.PLAIN,17));
		
		rate = new JLabel();
		rate.setBounds(30, 120, 200, 30);
		rate.setBackground(Color.black);
		rate.setForeground(Color.white);
		rate.setFont(new Font("ARIAL", Font.PLAIN,17));
		
		salaryVal = new JLabel();
		salaryVal.setBounds(30, 170, 200, 30);
		salaryVal.setBackground(Color.black);
		salaryVal.setForeground(Color.white);
		salaryVal.setFont(new Font("ARIAL", Font.PLAIN,17));
		
		okButton = new JButton("OK");
		okButton.setFont(new Font("ARIAL", Font.PLAIN,17));
		okButton.setFocusable(false);
		okButton.setBackground(Color.BLACK);
		okButton.setForeground(Color.WHITE);
		okButton.setBounds(130,240,40,40);
		okButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		okButton.addActionListener(e -> {
			salaryPanel.setVisible(false);
			timerecordScrollP.setVisible(true);
			showSalary.setEnabled(true);
			employeeId.setVisible(true);
			empFunc.totalTime = 0;
			empFunc.rate = 0;
		});
		
		salaryPanel = new JPanel();
		salaryPanel.setBackground(Color.black);
		salaryPanel.setBounds(380,120,300,300);
		salaryPanel.setVisible(false);
		salaryPanel.setLayout(null);
		salaryPanel.add(okButton);
		salaryPanel.add(totalTime);
		salaryPanel.add(rate);
		salaryPanel.add(salaryVal);

		showSalary = new JButton("SHOW SALARY");
		showSalary.setFont(new Font("ARIAL", Font.PLAIN,17));
		showSalary.setFocusable(false);
		showSalary.setBackground(Color.BLACK);
		showSalary.setForeground(Color.white);
		showSalary.setBounds(80,530,240,50);
		showSalary.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		showSalary.addActionListener(e -> {	
			empFunc.showSalary(id);
			totalTime.setText("Total Time : " + String.valueOf(empFunc.totalTime));
			rate.setText("Employee Rate : " + String.valueOf(empFunc.rate));
			salaryVal.setText("Total Salary : " + String.valueOf(empFunc.totalTime * empFunc.rate));
			showSalary.setEnabled(false);
			timerecordScrollP.setVisible(false);
			employeeId.setVisible(false);
			salaryPanel.setVisible(true); 
		});
		
		employeeId = new JLabel(id);
		employeeId.setBounds(880, 40, 100, 30);
		employeeId.setFont(new Font("ARIAL", Font.PLAIN, 15));
		employeeId.setHorizontalAlignment(JTextField.CENTER);
		employeeId.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.black));

		    
		timerecordModel = new	DefaultTableModel();
		timerecordScrollP = new JScrollPane();
		timerecordTable = new JTable();
			
		timerecordModel.setColumnIdentifiers(timeRecord);
		timerecordTable.setModel(timerecordModel);
		timerecordTable.getTableHeader().setBackground(Color.white);
		timerecordTable.getTableHeader().setForeground(Color.black);
		timerecordTable.setSelectionBackground(Color.black);
		timerecordTable.setSelectionForeground(Color.white);
		timerecordTable.getTableHeader().setFont(new Font("Arial",Font.BOLD,13));
		timerecordTable.getTableHeader().setEnabled(false);
		timerecordTable.getTableHeader().setPreferredSize(new Dimension(0,20));
		timerecordTable.setRowHeight(30);
			
		timerecordScrollP = new JScrollPane(timerecordTable);
		timerecordScrollP.getViewport().setBackground(Color.white);
		timerecordScrollP.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		timerecordScrollP.setBounds(80,70,900,450);
		timerecordScrollP.setVisible(true);
		
		homeButt = new JButton("HOME");
		homeButt.setFont(new Font("ARIAL", Font.PLAIN,17));
		homeButt.setFocusable(false);
		homeButt.setBackground(Color.white);
		homeButt.setForeground(Color.black);
		homeButt.setBounds(10,10,80,40);
		homeButt.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		homeButt.addActionListener(e -> {
			this.dispose();
		});
		
		account = new JButton();
		account.setIcon(accIcon);
		account.setFont(new Font("ARIAL", Font.PLAIN,17));
		account.setFocusable(false);
		account.setBackground(Color.white);
		account.setForeground(Color.black);
		account.setBounds(15,90,50,50);
		account.setBorder(BorderFactory.createMatteBorder(0,0, 0, 0, Color.gray));
		account.addActionListener(e -> {
			dei = new DisplayEmpInfo(id);
			if(!dei.thisFrameisVisible) {
				dei.setVisible(false);
				dei.setVisible(true);
				dei.thisFrameisVisible = true;
				
			}

		});
		
		sendMessage = new JButton();
		sendMessage.setIcon(createmessIcon);
		sendMessage.setFont(new Font("ARIAL", Font.PLAIN,17));
		sendMessage.setFocusable(false);
		sendMessage.setBackground(Color.white);
		sendMessage.setForeground(Color.black);
		sendMessage.setBounds(15,150,50,50);
		sendMessage.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		sendMessage.addActionListener(e -> {
			messageFrame.setVisible(false);
			messageFrame.setVisible(true);
		});
		
		timerecordModel.setRowCount(0);
		empFunc.setdatatoTable(timerecordModel, id);
		
		delete = new JButton("DELETE");
		delete.setBounds(810, 530, 80, 40);
		delete.setFocusable(false);
		delete.setVisible(false);
		delete.setBackground(Color.RED);
		delete.setForeground(Color.BLACK);
		delete.setFont(new Font("Arial",Font.PLAIN,13));
		delete.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		delete.addActionListener(e -> {
			if(timerecordTable.getRowCount() != 1) {
				db.deletetimeRecord(timerecordModel,timerecordTable,timerecordTable.getSelectedRow(),id);
				timerecordModel.setRowCount(0);
				empFunc.setdatatoTable(timerecordModel, id);
			}else JOptionPane.showMessageDialog(null, "YOU CAN'T REMOVE ALL THE ROWS");
		});

		update = new JButton("UPDATE");
		update.setBounds(900, 530, 80, 40);
		update.setFocusable(false);
		update.setVisible(false);
		update.setBackground(Color.GREEN);
		update.setForeground(Color.BLACK);
		update.setFont(new Font("Arial",Font.PLAIN,13));
		update.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		update.addActionListener( e -> {
			JOptionPane.showMessageDialog(null, "COMING SOON");
		});
		
		timerecordPanel = new JPanel();
		timerecordPanel.setBackground(Color.white);
		timerecordPanel.setBounds(0,0,1050,600);
		timerecordPanel.setVisible(true);
		timerecordPanel.setLayout(null);
		timerecordPanel.add(timerecordScrollP);
		timerecordPanel.add(employeeId);
		timerecordPanel.add(showSalary);
		timerecordPanel.add(homeButt);
		timerecordPanel.add(account);
		timerecordPanel.add(sendMessage);
		timerecordPanel.add(salaryPanel);
		timerecordPanel.add(delete);
		timerecordPanel.add(update);
		
		
		
		titleLbl = new JLabel("Title");
		titleLbl.setBounds(10, 10, 130, 20);
		titleLbl.setFont(new Font("ARIAL", Font.BOLD, 13));
		
		messageTitle = new JTextField(30);
		messageTitle.setBounds(10,30,460,30);
		messageTitle.setBackground(Color.white);
		messageTitle.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
		messageTitle.setFont(new Font("ARIAL", Font.PLAIN, 15));

		
		messageLbl = new JLabel("Message");
		messageLbl.setBounds(10, 60, 130, 20);
		messageLbl.setFont(new Font("ARIAL", Font.BOLD, 13));
		
		messageLengthLbl = new JLabel(messagecharCount + "/ 250" );
		messageLengthLbl.setBounds(10, 330, 130, 20);
		messageLengthLbl.setFont(new Font("ARIAL", Font.BOLD, 13));
		
		JTextArea textArea = new JTextArea();
        textArea.setBounds(10, 80, 460, 230);  
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.white);
        textArea.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        textArea.setFont(new Font("ARIAL", Font.PLAIN, 15));
        textArea.getDocument().addDocumentListener(new DocumentListener() {
        	void getLength () {
        		messagecharCount = textArea.getText().length();
        		messageLengthLbl.setText(messagecharCount + "/ 250" );
        	}
        	
			@Override
			public void insertUpdate(DocumentEvent e) {
				 getLength();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				 getLength();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				 getLength();
			}
			
		});


		send = new JButton("Send");
		send.setBounds(390, 330, 80, 25);
		send.setFocusable(false);
		send.setBackground(Color.GREEN);
		send.setForeground(Color.BLACK);
		send.setFont(new Font("Arial",Font.PLAIN,13));
		send.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		send.addActionListener( e -> {
			
			if(!messageTitle.getText().isEmpty() && !textArea.getText().isEmpty()) {
				if(messageTitle.getText().length() < 101 && textArea.getText().length() < 301) {
						empFunc.sendnewMessage(id,messageTitle.getText(),textArea.getText());
						messageTitle.setText("");
						textArea.setText("");
						messageFrame.setVisible(false);
						this.setVisible(true);
				}else JOptionPane.showMessageDialog(null, "INVALID MESSAGE/TITLE LENGTH");
			}else JOptionPane.showMessageDialog(null, "FILL ALL THE BLANKS");
		});
		
		cancel = new JButton("Cancel");
		cancel.setBounds(300, 330, 80, 25);
		cancel.setFocusable(false);
		cancel.setBackground(Color.RED);
		cancel.setForeground(Color.WHITE);
		cancel.setFont(new Font("Arial",Font.PLAIN,13));
		cancel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.gray));
		cancel.addActionListener(e -> {
			this.setVisible(true);
			messageFrame.setVisible(false);
			messageTitle.setText("");
			textArea.setText("");
		});

		messageFrame = new JFrame();
		messageFrame.setSize(500,400);
		messageFrame.getContentPane().setBackground(Color.white);
		messageFrame.setLayout(null);
		messageFrame.setResizable(false);
		messageFrame.setLocationRelativeTo(null);
		messageFrame.setVisible(false);
		messageFrame.add(textArea);
		messageFrame.add(messageTitle);
		messageFrame.add(messageLbl);
		messageFrame.add(messageLengthLbl);
		messageFrame.add(titleLbl);
		messageFrame.add(send);
		messageFrame.add(cancel);
		
		add(timerecordPanel);
		this.setSize(1050,600);
		this.getContentPane().setBackground(Color.white);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(false);
	}
	
	
}
