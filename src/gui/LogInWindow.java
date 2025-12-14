package gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.Border;

import data.UserData;
import main.App;

public class LogInWindow extends JFrame{
	
	JTextField link = linkTextField(), user = userTextField(), password = passwordTextField();
	
	Boolean rememberLink = false, rememberUser = false;
	
	public LogInWindow() {
		
		setSize(400,250);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		addComponents();
	}
	
	public void addComponents() {
		
		add(panel());
	}
	
	public JPanel panel() {
		JPanel pnl = new JPanel();
		
		pnl.setSize(100, 100);
		pnl.setLayout(new GridLayout(4, 3));
		
		
		pnl.setLocation(getLocation());
		
		pnl.add(new JLabel("URL", JLabel.CENTER), 0);
		pnl.add(link, 1);
		pnl.add(rememberLink(), 2);
		
		pnl.add(new JLabel("User Name", JLabel.CENTER), 3);
		pnl.add(user, 4);
		pnl.add(rememberUser(), 5);
		
		pnl.add(new JLabel("Password", JLabel.CENTER), 6);
		pnl.add(password, 7);
		
		pnl.add(new JLabel());
		pnl.add(new JLabel());
		pnl.add(logInButton());
		
		return pnl;
	}
	
	public JTextField linkTextField() {
		
		JTextField link = new JTextField();
		link.setToolTipText("Link to the database");
		
		link.setBounds(0, 0, 50, 20);
		
		return link;
	}
	
	public JTextField userTextField() {
		JTextField user = new JTextField();
		user.setToolTipText("User name");
		
		user.setBounds(0, 0, 50, 20);
		
		return user;
	}
	
	public JTextField passwordTextField() {
		JTextField password = new JTextField();
		password.setToolTipText("password...");
		
		password.setBounds(0,0,50,20);
		
		return password;
	}
	
	public JRadioButton rememberLink() {
		
		JRadioButton link = new JRadioButton("Remember");
		
		link.addActionListener(e -> {rememberLink = rememberLink == false ? true:false;
		System.out.println(rememberLink);
		});
		
		return link;
	}
	
	public JRadioButton rememberUser() {
		
		JRadioButton user = new JRadioButton("Remember");
		
		user.addActionListener(e -> {rememberUser = rememberUser == false ? true:false;
		System.out.println(rememberUser);
		});
		
		return user;
		
	}
	
	public JButton logInButton() {
		
		JButton button = new JButton("Enter");
		
		button.setBounds(0,0,50,20);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con = DriverManager.getConnection(link.getText(), user.getText(), password.getText());
					Class.forName("org.h2.Driver");
					System.out.println("Connection Established");					
					
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							try {
								
								new MainWindow(con).setVisible(true);
								dispose();
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
		});
	
		return button;
	}

}
