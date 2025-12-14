package services;

import java.sql.*;

public class DeleteService {
	
	private final Connection conn;
	
	public DeleteService(Connection con){
		this.conn = con;
	}
	
	public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " data deleted.");
        } catch (SQLException e) {
        	System.err.println("Delete Service accoured an error.");
            e.printStackTrace();
        }
        
    }

}
