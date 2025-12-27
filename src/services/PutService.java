package services;

import java.sql.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class PutService {
	
	Connection con;
	
	public PutService(Connection con){
		this.con = con;
	}
	
	public void putStudent(String name, int age) {

        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name); // 1. ? yerine name
            pstmt.setInt(2, age);    // 2. ? yerine age

            int rows =  pstmt.executeUpdate();
            System.out.println(rows + " data added.");
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(new JDialog(), e.getMessage());
            e.printStackTrace();
        }

    }

}
