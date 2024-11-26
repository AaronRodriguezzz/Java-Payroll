package db;
import frontend.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Dbclass {
	public boolean validAdditionEmp = false;
	public boolean additionempConditions = false;
	
	public Dbclass(){
		
	}
	
	public void addEmployee(int empID, String password, String lName, String fName, String contactNum, double rate, String position, String path,String question, String answer, int addingIdentity){
		Pattern pattern0 = Pattern.compile(".*[a-zA-Z].*");
		Matcher matcher0 = pattern0.matcher(String.valueOf(empID));
		Pattern pattern1 = Pattern.compile(".*[-!@#$%^&*()+=_}{|\"':;?/>.<,\\\\[\\\\]].*");
		Matcher matcher1 = pattern1.matcher(String.valueOf(empID));
		
		if(String.valueOf(empID).length() < 7) JOptionPane.showMessageDialog(null, "ID INPUT ERROR", "WARNING", JOptionPane.WARNING_MESSAGE); 
		if(password.length() < 8) JOptionPane.showMessageDialog(null, "WEAK PASSWORD", "WARNING", JOptionPane.WARNING_MESSAGE); 
		if(String.valueOf(contactNum).length() != 11)  JOptionPane.showMessageDialog(null, "INVALID CONTACT NUMBER", "WARNING", JOptionPane.WARNING_MESSAGE); 
		if(rate < 90) JOptionPane.showMessageDialog(null, "INVALID HOURLY RATE", "WARNING", JOptionPane.WARNING_MESSAGE); 
		if(matcher0.matches() || matcher1.matches()) JOptionPane.showMessageDialog(null, "INVALID ID", "WARNING", JOptionPane.WARNING_MESSAGE); 
		if(String.valueOf(empID).length() >= 7 && password.length() >= 8 && String.valueOf(contactNum).length() == 11 && rate >= 90 && !matcher0.matches() && !matcher1.matches()) additionempConditions = true;

		if(additionempConditions) {	
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
				PreparedStatement pstmt = con.prepareStatement("CREATE TABLE a" + empID + " (date varchar(60), INHOUR varchar(60), INMINUTES varchar(60),  OUTHOUR varchar(60), OUTMINUTES varchar(60), totalHours varchar(60));");	
				PreparedStatement pstmt2 = con.prepareStatement("insert into a" + empID + "(date,INHOUR,INMINUTES,OUTHOUR,OUTMINUTES,TOTALHOURS) "+ "values (0,0,0,0,0,0)");		
				PreparedStatement pst = con.prepareStatement("INSERT INTO employee(empID,password,lastname,firstname,contactnum,rate,position,picture,question,answer)"+ " values (?,?,?,?,?,?,?,?,?,?); ");
				PreparedStatement pst1 = con.prepareStatement("INSERT INTO admin(empID,password,lastname,firstname,contactnum,rate,position,picture,question,answer)"+ " values (?,?,?,?,?,?,?,?,?,?); ");
				InputStream is =  new FileInputStream(new File(path));
			
				pst.setInt(1,empID);
				pst.setString(2,password);
				pst.setString(3, lName);
				pst.setString(4, fName);
				pst.setString(5, contactNum);
				pst.setDouble(6, rate);
				pst.setString(7, position);
				pst.setBlob(8, is);
				pst.setString(9, question);
				pst.setString(10, answer);
				
				pst1.setInt(1,empID);
				pst1.setString(2,password);
				pst1.setString(3, lName);
				pst1.setString(4, fName);
				pst1.setString(5, contactNum);
				pst1.setDouble(6, rate);
				pst1.setString(7, position);
				pst1.setBlob(8, is);
				pst1.setString(9, question);
				pst1.setString(10, answer);
		    
				if(addingIdentity == 1) {
					pstmt.execute();
					pstmt.close();
					pstmt2.execute();
					pstmt2.close();
					pst.execute();
					pst.close();
				}else {
					pst1.execute();
					pst1.close();
				}
				
				validAdditionEmp = true;
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "PLEASE CHECK YOUR INPUT");
				validAdditionEmp = false;
			}
		}
	}
	
	public void employeeJTable(DefaultTableModel model) {
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
			PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM employee"); 
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getString("empID"),
						rs.getString("password"),
						rs.getString("lastname"),
						rs.getString("firstname"),
						rs.getString("contactnum")});
			}
			 rs.close();
			 pstmt.close();
			 Conn.close();
			
		}catch(SQLException e2) {
			 e2.printStackTrace();
		}
	}
	
	public void employeesMessages(DefaultTableModel model) {
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
			PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM messagetoadmin"); 
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getString("sentDate"),
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("message")});
			}
			 rs.close();
			 pstmt.close();
			 Conn.close();
			
		}catch(SQLException e2) {
			 e2.printStackTrace();
		}
	}
	
	
	public void workingEmploeyees(DefaultTableModel model) {
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
			PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM workingemp");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				model.addRow(new Object[] {
					rs.getInt("id"),
					rs.getString("lastname"),
					rs.getString("firstname"),
					rs.getString("position")});
			}
			 rs.close();
			 pstmt.close();
			 Conn.close();
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	public void updateInformation(String id, String password, String lName, String fName, String contact, String rate, String position, String imgPath) {
		try {				
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pst = con.prepareStatement("UPDATE employee" + " SET  password= '" + password + "' WHERE empID = " + id + ";");
			PreparedStatement pst1 = con.prepareStatement("UPDATE employee" + " SET lastname = '" + lName + "' WHERE empID = " + id + ";");
			PreparedStatement pst2 = con.prepareStatement("UPDATE employee" + " SET firstname = '" + fName + "' WHERE empID = " + id + ";");
			PreparedStatement pst3 = con.prepareStatement("UPDATE employee" + " SET contactnum = '" + contact + "' WHERE empID = " + id + ";");
			PreparedStatement pst4 = con.prepareStatement("UPDATE employee" + " SET rate = '" + rate + "' WHERE empID = " + id + ";");
			PreparedStatement pst5 = con.prepareStatement("UPDATE employee" + " SET position = ' " + position + "' WHERE empID = " + id + ";");
			
			if(imgPath != null) {
				PreparedStatement pst6 = con.prepareStatement("UPDATE employee" + " SET picture = UNHEX('" + imgPath + "') WHERE empID = " + id + ";");
				pst6.execute();
				pst6.close();
			}
		
			pst.execute();
			pst1.execute();	
			pst2.execute();
			pst3.execute();
			pst4.execute();
			pst5.execute();

			pst.close();
			pst1.close();
			pst2.close();
			pst3.close();
			pst4.close();
			pst5.close();
			con.close();
			JOptionPane.showMessageDialog(null, "UPDATE SUCESS");
		}catch(Exception e) {
			System.out.println("CHECK UPDATE INFORMATION IN DB CLASS");
		}
		
	}
	
	public void deleteEmployee(DefaultTableModel model, JTable table, int i) {
		int check = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
		if(check == JOptionPane.YES_OPTION) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
				PreparedStatement pst0 = con.prepareStatement("SELECT id FROM workingemp");
				
				ResultSet rs1 = pst0.executeQuery();
				while(rs1.next()) {
					if(Integer.parseInt((String)table.getValueAt(i, 0)) == rs1.getInt(1)) {
						PreparedStatement pst = con.prepareStatement("DELETE FROM workingemp WHERE id = " + (String) table.getValueAt(i, 0) + ";");
						pst.executeUpdate();
						pst.close();
						pst0.close();
						rs1.close();
						break;
					}
				}
				
				PreparedStatement pst = con.prepareStatement("DELETE FROM employee WHERE empID = " + (String) table.getValueAt(i, 0) + ";");
				PreparedStatement pst1 = con.prepareStatement("DROP TABLE a" + (String) table.getValueAt(i, 0));
				model.removeRow(i);

				pst.execute();	
				pst.close();
				pst1.execute();
				pst1.close();
				con.close();
				
				
			}catch (Exception e1) {
				System.out.println("CHECK DELETE INFORMATION IN DB CLASS");
			}
			
		}
	}
	
	public void deletetimeRecord(DefaultTableModel model, JTable table, int i, String id) {
		try {
		int check = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
		if(check == JOptionPane.YES_OPTION) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
				PreparedStatement pst = con.prepareStatement("DELETE FROM a" + id +" WHERE date ='" + (String) table.getValueAt(i, 0) + "' ;");
				model.removeRow(i);

				pst.execute();	
				pst.close();
				con.close();
		}
		
		}catch (Exception e1) {
			System.out.println("CHECK TIME RECORD IN DB CLASS");
		}
	}
	
	public void deleteMail(DefaultTableModel model, int i, String message) {
		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pst = con.prepareStatement("DELETE FROM messagetoadmin WHERE message = '" + message + "' ;");

			pst.execute();	
			model.removeRow(i);

			pst.close();
			con.close();
			
		
		}catch (Exception e1) {
			System.out.println("CHECK TIME RECORD IN DB CLASS");
		}
	}
	
}
