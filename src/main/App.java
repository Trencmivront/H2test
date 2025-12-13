package main;

import java.sql.*;

import javax.swing.SwingUtilities;

import gui.MainWindow;
import services.DeleteService;
import services.GetService;
import services.PutService;
import services.SetService;
import services.GetServices;
import data.UserData;

public class App{
	
	static String url = UserData.url;
	static String user = UserData.user;
	static String password = UserData.password;
	
	private static DeleteService deleteService = new DeleteService(url, user, password);
	private static GetService getService = new GetService(url, user, password);
	private static GetServices getServices = new GetServices(url, user, password);
	private static PutService putService = new PutService(url, user, password);
	private static SetService setService = new SetService(url, user, password);
	
	
	public static void main(String[] args) {
		
		try (Connection con = DriverManager.getConnection(url, user, password)){
			
			Class.forName("org.h2.Driver");
			System.out.println("Connection Established");					
			
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						
						new MainWindow(deleteService, getService, getServices, putService, setService).setVisible(true);
						
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