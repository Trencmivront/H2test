package services;

import java.sql.*;

public class PutService {
	
	String url, user, password;
	
	public PutService(String url, String user, String password){
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public void putStudent(String name, int age) {

        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, name); // 1. ? yerine name
            pstmt.setInt(2, age);    // 2. ? yerine age

            int rows =  pstmt.executeUpdate();
            System.out.println(rows + " data added.");
        } catch (SQLException e) {
        	System.out.println("Put Service accoured an error.");
            e.printStackTrace();
        }

    }

}
