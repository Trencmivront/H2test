package main;

import javax.swing.SwingUtilities;
import gui.LogInWindow;

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