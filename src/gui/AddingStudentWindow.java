package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import services.PutService;

public class AddingStudentWindow extends JDialog{
	
	PutService putService;
	
	int height, width;
	
	JTextField name = new JTextField() 
			, age = new JTextField();
	
	protected AddingStudentWindow(MainWindow main ,PutService put){
		this.putService = put;
		
		setSize(300,200);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLayout(new GridLayout(3, 3));
		setModal(true);
		
		setLocation(main.getLocation());
		
		addComponents();
	}
	
	public void addComponents() {
		
		add(new JLabel("Name-Surname"));
		add(name);
		add(new JPanel());
		
		add(new JLabel("Age"));
		add(age);
		add(new JPanel());
		
		add(new JPanel());
		
		JButton but = new JButton("Save");
		
		but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					putService.putStudent(name.getText(), Integer.parseInt(age.getText()));
					dispose();
				}catch(NumberFormatException n) {
					n = new NumberFormatException("Age must be a number!");
					JOptionPane.showMessageDialog(new JDialog(), n.getMessage());
				}
			}
		});
		add(but);
	}

}
