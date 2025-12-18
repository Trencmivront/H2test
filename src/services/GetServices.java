package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Student;
public class GetServices {
	
	Connection con;
	
	public GetServices(Connection con){
		this.con = con;
	}
	
	public List<Student> getStudents() {

        String sql = "SELECT id, name, age FROM students";
        
        try (Statement st = con.createStatement();
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
