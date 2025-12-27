package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import gui.LogInWindow;

public class App{
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
		            
		            com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme("Large-Font");
		            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		            
		            new LogInWindow().setVisible(true);
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(new JDialog(), e.getMessage());
					e.printStackTrace();
					return;
				}
				
			}
		});
		
	}
}