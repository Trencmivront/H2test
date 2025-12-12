package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import services.PutService;

public class AddingStudentWindow extends JFrame{
	
	PutService putService;
	
	protected AddingStudentWindow(PutService put){
		this.putService = put;
		
		setSize(100, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		addComponents();
	}
	
	public void addComponents() {
		
		
		
	}

}
