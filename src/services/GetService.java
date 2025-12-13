package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.Student;

public class GetService {
	
	private final Connection con;
	
	public GetService(Connection con){
		this.con = con;
	}
	
	public Student getStudent(int id) {
		
		String sql = "SELECT * FROM students WHERE id=?";
		
		try (PreparedStatement pst = con.prepareStatement(sql)){
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
