package gui;

import java.awt.BorderLayout;
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
	
	private JTextField link = linkTextField(), user = userTextField();
	private JPasswordField password = passwordTextField();
	
	Boolean remember = false;
	private UserData userData;
	
	public LogInWindow() {
		
		setSize(400,250);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		loadData();
		
		addComponents();
	}
	
	private void loadData() {
		
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

	private void addComponents() {
	    setLayout(new BorderLayout(10, 10));
	    add(center(), BorderLayout.CENTER);
	    add(choicePanel(), BorderLayout.EAST);
	    add(new JLabel("Test Application", JLabel.CENTER), BorderLayout.SOUTH);
	}

	private JPanel center() {
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	    panel.add(urlPanel());
	    panel.add(Box.createVerticalStrut(10));
	    panel.add(userPanel());
	    panel.add(Box.createVerticalStrut(10));
	    panel.add(passwordPanel());

	    return panel;
	}

	private JPanel urlPanel() {
	    JPanel panel = new JPanel(new BorderLayout(5, 0));
	    panel.add(new JLabel("URL           "), BorderLayout.WEST);
	    panel.add(link, BorderLayout.CENTER);
	    return panel;
	}

	private JPanel userPanel() {
	    JPanel panel = new JPanel(new BorderLayout(5, 0));
	    panel.add(new JLabel("User Name "), BorderLayout.WEST);
	    panel.add(user, BorderLayout.CENTER);
	    return panel;
	}

	private JPanel passwordPanel() {
	    JPanel panel = new JPanel(new BorderLayout(5, 0));
	    panel.add(new JLabel("Password  "), BorderLayout.WEST);
	    panel.add(password, BorderLayout.CENTER);
	    return panel;
	}

	private JPanel choicePanel() {
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.add(rememberLink());
	    panel.add(logInButton());
	    return panel;
	}
	
	public JTextField linkTextField() {
		
		JTextField link = new JTextField(10);
		link.setToolTipText("Link to the database");
		
		link.setBounds(0, 0, 50, 20);
		
		return link;
	}
	
	public JTextField userTextField() {
		JTextField user = new JTextField(10);
		user.setToolTipText("User name");
		
		user.setBounds(0, 0, 50, 20);
		
		return user;
	}
	
	public JPasswordField passwordTextField() {
		
		JPasswordField password = new JPasswordField(10);
		password.setToolTipText("password");
		password.setEchoChar('•');
		password.setBounds(0,0,50,20);
		
		return password;
	}
	
	public JCheckBox rememberLink() {
		JCheckBox save = new JCheckBox("Remember Me");
		
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
								
								new MainWindow(con).setVisible(true);
								ConfigSave.save(userData);
								dispose();
							}catch(Exception e) {
								// error if cannot open main by using con parameter
								JOptionPane.showMessageDialog(new JDialog(), e.getMessage());
								e.printStackTrace();
							}
							
						}
					});
					
					
				}catch(SQLException s) {
					JOptionPane.showMessageDialog(new JDialog(), s.getMessage());
					s.printStackTrace();
				}catch(ClassNotFoundException c) {
					JOptionPane.showMessageDialog(new JDialog(), c.getMessage());
					System.out.println("Class is not found");
					c.printStackTrace();
				}
				
			}
		});
	
		return button;
	}


}
