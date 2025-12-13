package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import data.UserData;
import main.App;

public class LogInWindow extends JFrame{
	
	private String link;
	
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
		
		JTextField link = new JTextField(this.link);
		link.setToolTipText("Link to the database");
		
		
		
		return link;
	}

}
