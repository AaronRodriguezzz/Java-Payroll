package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class AnnouncementFunc {
	LocalDate currentDate;
	public String announcementMess;
	public boolean emptyAnnouncement = false;
	public AnnouncementFunc(){}
	
	public String showAnnouncement() {
		currentDate = LocalDate.now();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pstmt = con.prepareStatement("SELECT message FROM announcements");
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				emptyAnnouncement = false;
				announcementMess = rs.getString("message");
			}else {
				emptyAnnouncement = true;
			}
			
			pstmt.close();
			rs.close();
			con.close();
			
		} catch (Exception e1) {
			System.out.println("CHECK ANNOUNCEMENT SECTION");
		}
		return announcementMess; 
	}
	
	public void changeAnnouncement(String message) {
		currentDate = LocalDate.now();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee" , "root" , "");
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM announcements");
			pstmt.execute();

			PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO announcements (announceDate,message) values (?,?); ");
			pstmt1.setDate(1, Date.valueOf(currentDate));
			pstmt1.setString(2, message);
			
			pstmt1.executeUpdate();
			pstmt1.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("CHECK ANNOUNCEMENT SECTION");
		}
	}
}
