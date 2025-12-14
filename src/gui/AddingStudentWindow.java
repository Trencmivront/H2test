package gui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import services.PutService;

public class AddingStudentWindow extends JDialog{
	
	PutService putService;
	
	protected AddingStudentWindow(MainWindow main ,PutService put){
		this.putService = put;
		
		setSize(100, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(main);
		setModal(true);
		
		
		
		addComponents();
	}
	
	public void addComponents() {
		
		
		
	}

}
