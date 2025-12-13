package main;

import java.sql.*;

import javax.swing.SwingUtilities;

import gui.LogInWindow;
import gui.MainWindow;
import services.DeleteService;
import services.GetService;
import services.PutService;
import services.SetService;
import services.GetServices;
import data.UserData;

public class App{
	
	public static boolean isLogged = false;
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					
					new LogInWindow().setVisible(true);
					
					if(!isLogged) {
						return;
					}
					
				}catch(Exception e) {
					e.printStackTrace();
					return;
				}
				
			}
		});
		
		try (Connection con = DriverManager.getConnection(url, user, password)){
			
			Class.forName("org.h2.Driver");
			System.out.println("Connection Established");					
			
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						
						new MainWindow(con).setVisible(true);
						
					}catch(Exception e) {
						e.printStackTrace();
					}
					
				}
			});
			
			
		}catch(SQLException s) {
			s.printStackTrace();
		}catch(ClassNotFoundException c) {
			System.out.println("Class is not found");
			c.printStackTrace();
		}
		
	}
}