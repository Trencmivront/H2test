package services;

import java.sql.*;

public class SetService {
	
	String url, user, password;
	
	public SetService(String url, String user, String password){
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public void updateStudent(int id, int newAge) {

        String sql = "UPDATE students SET age = ? WHERE id = ?";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, newAge); // ilk ? → yeni yaş
            pstmt.setInt(2, id);     // ikinci ? → güncellenecek id

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " kayıt güncellendi.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}
