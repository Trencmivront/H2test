package gui;

import javax.swing.*;

import data.UserData;
import main.App;

public class LogInWindow extends JFrame{
	
	public LogInWindow() {
		
		setSize(75,50);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addComponents();
	}
	
	public void addComponents() {
		
		add(linkTextField());
		
	}
	
	public JTextField linkTextField() {
		
		JTextField link = new JTextField();
		link.setToolTipText("Link to the database");
		
		UserData.url = link.getText();
		
		return link;
	}

}
