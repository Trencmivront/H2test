package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import data.ConfigRead;
import data.ConfigSave;
import javax.swing.*;
import data.UserData;

public class LogInWindow extends JFrame{
	
	JTextField link = linkTextField(), user = userTextField();
	JPasswordField password = passwordTextField();
	
	Boolean remember = false;
	
	UserData userData;
	
	public LogInWindow() {
		
		setSize(400,250);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		loadData();
		
		addComponents();
	}
	
	public void loadData() {
		
		try {
			userData = ConfigRead.load();
		}catch(IOException i) {
			i.printStackTrace();
		}
		
		if(userData != null) {
			
			remember = userData.isRemember;
			
			if(remember) {
				user.setText(userData.user);
				link.setText(userData.url);
			}
		}
		else {
			userData = new UserData();
		}
		
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
		pnl.add(new JPanel(), 5);
		
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
	
	public JPasswordField passwordTextField() {
		
		JPasswordField password = new JPasswordField();
		password.setToolTipText("password");
		password.setEchoChar('•');
		password.setBounds(0,0,50,20);
		
		return password;
	}
	
	public JRadioButton rememberLink() {
		JRadioButton save = new JRadioButton("Keep URL-User");
		
		save.setSelected(remember);
		
		save.addActionListener(e -> {remember = remember == false ? true:false;
		});
		
		return save;
	}
	
	public JButton logInButton() {
		
		JButton button = new JButton("Enter");
		
		button.setBounds(0,0,50,20);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					String pass = new String(password.getPassword());
					Connection con = DriverManager.getConnection(link.getText(), user.getText(), pass);
					pass = "";
					Class.forName("org.h2.Driver");
					System.out.println("Connection Established");					
					
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							try {
								
								if(!remember) {									
									userData.url = "";
									userData.user = "";
									userData.isRemember = false;
								}
								else {
									userData.url = link.getText();
									userData.user = user.getText();
									userData.isRemember = true;
								}
								
								ConfigSave.save(userData);
								
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
