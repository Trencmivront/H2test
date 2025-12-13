package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Student;
public class GetServices {
	
	String url, user, password;
	
	public GetServices(String url, String user, String password){
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public List<Student> getStudents() {

        String sql = "SELECT id, name, age FROM students";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
        	
        	List<Student> ls = new ArrayList<>();
        	
        	while(rs.next()) {
        		ls.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
        	}
        	return ls;

        }catch (SQLException e) {
        	System.out.println("Get Services accoured an error.");
            e.printStackTrace();
            return null;
        }
    }

}
