package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import services.PutService;

public class AddingStudentWindow extends JDialog{
	
	PutService putService;
	
	protected AddingStudentWindow(MainWindow main ,PutService put){
		this.putService = put;
		
		setSize(100, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLayout(new GridBagLayout());
		
		setLocationRelativeTo(main);
		setModal(true);
		
		addComponents();
	}
	
	public void addComponents() {
		
		Object c = new GridBagConstraints();
		
		
		
	}

}
