package services;

import java.sql.*;
import java.util.Scanner;

public class DeleteService {
	
	String url, user, password;
	
	public DeleteService(String url, String user, String password){
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " data deleted.");
        } catch (SQLException e) {
        	System.out.println("Delete Service accoured an error.");
            e.printStackTrace();
        }
        
    }

}
