package backend;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmployeeSectionFunc {
	public double totalTime;
	public int rate;
	public byte[] imageData;
	public String showId;
	public String showName;
	public String empRate;
	public String position;
	
	public boolean oldpassVerified(String id, String oldPassword) {
		boolean verified = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pst0 = con.prepareStatement("SELECT * FROM employee where empID = '" + id + "'");
			ResultSet rs0 = pst0.executeQuery();
			
			if(rs0.next()) {
				if(rs0.getString("password").equals(oldPassword)) verified = true;
			}
			
			con.close();
			pst0.close();
			rs0.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		return verified;
	}
	
	public void updatePassword(String id, String newPassword) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pst0 = con.prepareStatement("UPDATE employee SET password = " + newPassword + " where empID = '" + id + "'");
			
			
			pst0.execute();
			JOptionPane.showMessageDialog(null, "Password Updated");

			con.close();
			pst0.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public byte[] getInfo(String id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pst0 = con.prepareStatement("SELECT * FROM employee where empID = '" + id + "'");
			ResultSet rs0 = pst0.executeQuery();
			
			while(rs0.next()) {
                Blob blob = rs0.getBlob("picture");
                this.imageData = blob.getBytes(1, (int) blob.length());
				this.showId = String.valueOf(rs0.getInt("empID"));
				this.showName = rs0.getString("firstname") + " " + rs0.getString("lastname");
				this.position = rs0.getString("position");
				this.empRate = rs0.getBigDecimal("rate").toString();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return imageData;		
	}
	
	public void setdatatoTable(DefaultTableModel model, String id) {
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
			PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM a" + id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getString("date"),
						rs.getString("INHOUR"),
						rs.getString("INMINUTES"),
						rs.getString("OUTHOUR"),
						rs.getString("OUTMINUTES"),
						rs.getString("totalHours")});
			}
			 rs.close();
			 pstmt.close();
			 Conn.close();
			
		}catch(SQLException e2) {
			e2.printStackTrace();
			System.out.println("CHECK SHOW EMPLOYEE TABLES DB");
		}
	}
	
	public void showSalary(String id) {
		try {
			Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
			PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM a" + id);
			PreparedStatement pstmt2 = Conn.prepareStatement("SELECT * FROM employee where empID = " + id);

			ResultSet rs = pstmt.executeQuery();
			ResultSet rs2 = pstmt2.executeQuery();
			System.out.println("show salary success");
			
			while(rs.next()) {
				System.out.println(Double.parseDouble(rs.getString("totalHours")));
				totalTime += Double.parseDouble(rs.getString("totalHours"));
			}
			
			while(rs2.next()) {
				rate = rs2.getInt("rate");
				System.out.println(rs2.getInt("rate"));
			}
		}catch(Exception e) {
			System.out.println("CHECK SHOW SALARY");
		}
	}
	
	public void sendnewMessage(String id, String title, String message) {
		LocalDate currentDate = LocalDate.now();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pst0 = con.prepareStatement("INSERT INTO messagetoadmin (sentDate,id,title,message) value (?,?,?,?);");
			
			pst0.setDate(1, Date.valueOf(currentDate));
			pst0.setInt(2, Integer.parseInt(id));
			pst0.setString(3, title);
			pst0.setString(4, message);
			
			System.out.println("MESSAGE SENT");
			pst0.executeUpdate();
			pst0.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
