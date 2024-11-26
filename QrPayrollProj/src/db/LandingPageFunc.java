package db;

import frontend.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class LandingPageFunc {
	public boolean successloginCheck = false;
	public boolean successadminLogin = false;
	public boolean dateExisted = false;
	public boolean emptyTable = false;
	public boolean nottimedIn = false;
	public boolean doneforToday = false;
	public boolean forgotpassValid = false;
	
	public String questionStr = null;
	public String answerStr = null;


	double finalinHour;
	double finalinMinutes;
	double finaloutHour;
	double finaloutMinutes;
	double finaltotalHour;
	double finaltotalMinutes;
	double totalTime;
	
	int hour;
	int minutes;
	int tempId;

	LocalTime currentTime;
	DecimalFormat decimalFormat = new DecimalFormat("#.##");;
	String formattedtotalTime;
	String typeofEmp;
	public LandingPageFunc(){
		
	}
	
	public void forgotEmployeepassword(int id, String table) {
		typeofEmp = table;
		tempId = id;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pstmt = con.prepareStatement("SELECT empID FROM " + table + " where empID = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				forgotpassValid = true;
			}else{
				forgotpassValid = false;
			}
			
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("CHECK FORGOT PASS 1 SECTION");
		} 
	}
	

	public String forgotEmployeepassSteptwo(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + typeofEmp + " where empID = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				questionStr = rs.getString("question");
				answerStr = rs.getString("answer");
				System.out.println("ANSWER TO THE QUESTION : " + answerStr);
			}
			
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("CHECK FORGOT PASS 1 SECTION");
		}
		
		return questionStr; 
	}
	
	public String forgotEmployeepassStepThree(String password) {
		System.out.println(password);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pstmt = con.prepareStatement("UPDATE " + typeofEmp + " SET password = ? WHERE empID = ?");
			pstmt.setString(1,password );
			pstmt.setInt(2, tempId);
			
			pstmt.execute();
			pstmt.close();
			con.close();
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("CHECK FORGOT PASS 1 SECTION");
		}
		return questionStr; 
	}
	
	
	
	public boolean employeelogin(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pstmt = con.prepareStatement("SELECT empID,password FROM employee where empID = ? AND password = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, String.valueOf(password));
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "LOG IN SUCCESS", "CHECK", JOptionPane.INFORMATION_MESSAGE);
				successloginCheck = true;
			}else{
				JOptionPane.showMessageDialog(null, "LOG IN FAILED", "CHECK", JOptionPane.INFORMATION_MESSAGE);
				successloginCheck = false;
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("CHECK LOG IN SECTION");
		}
		return successloginCheck; 
	}
	
	public boolean adminLogin(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pstmt = con.prepareStatement("SELECT empID,password FROM admin where empID = ? AND password = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, String.valueOf(password));
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "LOG IN SUCCESS", "CHECK", JOptionPane.INFORMATION_MESSAGE);
				successadminLogin = true;
			}else{
				JOptionPane.showMessageDialog(null, "LOG IN FAILED", "CHECK", JOptionPane.INFORMATION_MESSAGE);
				successadminLogin = false;
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("CHECK LOG IN SECTION");
		}
		return successadminLogin; 		
	}
	
	public void timeGetter(String id, String todayDate, int inHour, int inMinutes, int outHour, int outMinutes, double totalHour) {
		doneforToday = false;
	    LocalTime currentTime = LocalTime.now();
	    hour = currentTime.getHour();
		minutes = currentTime.getMinute();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM a" + id);
			ResultSet rs1 = pstmt1.executeQuery();
			
			
			while(rs1.next()) {
				
				if(rs1.getString(1).equalsIgnoreCase(todayDate) && !(rs1.getInt("OUTHOUR") == 0) && !(rs1.getInt("OUTMINUTES") == 0)) {
					JOptionPane.showMessageDialog(null, "YOU ALREADY TIMED OUT", "WARNING", JOptionPane.WARNING_MESSAGE );
					doneforToday = true;
					break;
				}
				
				if(rs1.getString("date").equalsIgnoreCase(todayDate)) {
					nottimedIn = false;
					break;
				}else {
					nottimedIn = true;	
				}
			} 

			if(nottimedIn && !doneforToday) {
				try {
				hour = currentTime.getHour();
				minutes = currentTime.getMinute();
				PreparedStatement pstmt2 = con.prepareStatement("insert into a" + id + "(date,INHOUR,INMINUTES,OUTHOUR,OUTMINUTES,TOTALHOURS) "+ "values (?,?,?,?,?,?)");
				PreparedStatement pstmt21 = con.prepareStatement("delete from a" + id + " where date = 0");		

				pstmt2.setString(1, todayDate);
				pstmt2.setInt(2,hour);
				pstmt2.setInt(3, minutes);
				pstmt2.setInt(4, 0);
				pstmt2.setInt(5, 0);
				pstmt2.setInt(6, 0);
					
				pstmt2.execute();
				pstmt2.close();	
				pstmt21.executeUpdate();
				pstmt21.close();
				
				PreparedStatement pstmt11 = con.prepareStatement("SELECT empID, lastname, firstname, position FROM employee where empID =" + id);
				ResultSet rs11 = pstmt11.executeQuery();
				while(rs11.next()) {
					PreparedStatement pstmt22 = con.prepareStatement("insert into workingemp" + "(id,lastname,firstname,position) "+ " values (?,?,?,?)");		

					pstmt22.setInt(1, rs11.getInt("empID"));
					pstmt22.setString(2,rs11.getString("lastname"));
					pstmt22.setString(3,rs11.getString("firstname"));
					pstmt22.setString(4, rs11.getString("position"));

					pstmt22.execute();
					pstmt22.close();
				}
					
				JOptionPane.showMessageDialog(null, "TIME IN : " + hour + " : " + minutes + "|| " + id, "INFORMATION",JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e) {
					System.out.println("CHECK TIME IN SECTION");
				}
				
				
			}else if(!nottimedIn && !doneforToday){
				try {
				hour = currentTime.getHour();
				minutes = currentTime.getMinute();
				PreparedStatement pstmt3 = con.prepareStatement("UPDATE a" + id + " SET OUTHOUR = '" + hour + "' WHERE date = '" + todayDate + "' ;");
				PreparedStatement pstmt4 = con.prepareStatement("UPDATE a" + id + " SET OUTMINUTES = '" + minutes + "' WHERE date = '" + todayDate + "' ;");

				pstmt3.executeUpdate();
				pstmt3.close();
				pstmt4.executeUpdate();
				pstmt4.close();
				
				computeTotaltime(id, hour, minutes, todayDate);
				PreparedStatement pstmt5 = con.prepareStatement("UPDATE a" + id + " SET totalHours = '" + totalTime + "' WHERE date = '" + todayDate + "' ;");
				pstmt5.executeUpdate();
				pstmt5.close();
				
				PreparedStatement pstmt6 = con.prepareStatement("DELETE FROM workingemp WHERE id = '" + id + "'");		
				pstmt6.executeUpdate();
				pstmt6.close();
				
				JOptionPane.showMessageDialog(null, "TIME OUT : " + hour + " : " + minutes, "INFORMATION",JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e) {
					System.out.println("CHECK TIME OUT SECTION");
				}
			}
			
			pstmt1.close();
			rs1.close();
		}catch(Exception e) {
			System.out.println("CHECK TIME GETTER SECTION");
		}
	}
	
	public void computeTotaltime(String id, int outHour, int outMinutes, String date) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM a" + id + " WHERE date = '" + date + "'");
			ResultSet rs1 = pstmt1.executeQuery();
			
			while(rs1.next()) {
				finalinHour = rs1.getInt(2);
				finalinMinutes = rs1.getInt(3);
				finaloutHour = rs1.getInt(4);
				finaloutMinutes = rs1.getInt(5);
				break;
			}
			System.out.println(finalinHour + " " +  finalinMinutes);
			System.out.println(finaloutHour +":" + finaloutMinutes);
			
			if(finalinHour < finaloutHour) {
				finaltotalHour = finaloutHour - finalinHour;
			}else if(finalinHour > finaloutHour) {
				finaltotalHour = (24 - finalinHour) + finaloutHour;
			}else if(finalinHour == finaloutHour) {
				finaltotalHour = 0;
			}
			
			if(finalinMinutes < finaloutMinutes){
				finaltotalMinutes = (finaloutMinutes - finalinMinutes) / 60;
			}else if(finalinMinutes > finaloutMinutes){
				finaltotalMinutes = ((60 - finalinMinutes) + finaloutMinutes) / 60;
				if(finaltotalHour != 0) finaltotalHour--;
			}else if(finalinMinutes == finaloutMinutes) {
				finaltotalMinutes=0;
			}
			
		
			totalTime = finaltotalHour + finaltotalMinutes;
			formattedtotalTime = decimalFormat.format(totalTime);
			totalTime = Double.parseDouble(formattedtotalTime);
			System.out.println(totalTime);
		}catch(Exception e) {
			System.out.println("CHECK SALARY COMPUTATION SECTION");
		}
		
	}
}
