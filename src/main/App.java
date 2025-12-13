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
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					
					new LogInWindow().setVisible(true);
					
				}catch(Exception e) {
					e.printStackTrace();
					return;
				}
				
			}
		});
		
	}
}