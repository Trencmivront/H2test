package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.Student;

public class GetService {
	
	String url, user, password;
	
	public GetService(String url, String user, String password){
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public Student getStudent(int id) {
		
		String sql = "SELECT * FROM students WHERE id=?";
		
		try (Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement pst = con.prepareStatement(sql)){
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()){
				if(rs.next()) {
					return new Student(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getInt("age")
							);
				}
				return null;
			}
			
		}catch(SQLException s) {
			System.out.println("Get Service accoured an error.");
			s.printStackTrace();
			return null;
		}
	}
	
}
