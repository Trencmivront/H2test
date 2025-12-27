package services;

import java.sql.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class SetService {
	
	Connection con;
	
	public SetService(Connection con){
		this.con = con;
	}

	public void updateStudent(int id, int newAge) {

        String sql = "UPDATE students SET age = ? WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, newAge); // ilk ? → yeni yaş
            pstmt.setInt(2, id);     // ikinci ? → güncellenecek id

            int rows = pstmt.executeUpdate();
            System.out.println(rows + " data updated.");
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(new JDialog(), e.getMessage());
            e.printStackTrace();
        }
    }
	
}
