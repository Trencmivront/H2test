package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

import data.Student;
import services.DeleteService;
import services.GetService;
import services.GetServices;
import services.PutService;
import services.SetService;

public class MainWindow extends JFrame{
	
	private static DeleteService deleteService;
	private static GetService getService;
	private static GetServices getServices;
	private static PutService putService;
	private static SetService setService;
	
	public MainWindow(DeleteService del, GetService get, GetServices gets, PutService put, SetService set) {
		
		this.deleteService = del;
		this.getService = get;
		this.getServices = gets;
		this.putService = put;
		this.setService = set;
		
		setSize(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		addComponents();
	}
	
	private void addComponents() {
		
		addToolBar();
		addListOfStudents();
	}
	
	private void addToolBar() {
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		toolBar.add(createMenu());
		
		JMenu settings = new JMenu("Settings");
		toolBar.add(settings);
		
		add(toolBar, BorderLayout.NORTH);
	}
	
	private void addListOfStudents() {
		
		JScrollPane scrollPane = new JScrollPane(createStudentTable(getServices.getStudents()));
		
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private JTable createStudentTable(List<Student> students) {

	    String[] columns = {"ID", "Name", "Age"};

	    Object[][] data = new Object[students.size()][3];

	    for (int i = 0; i < students.size(); i++) {
	        Student s = students.get(i);
	        data[i][0] = s.id();
	        data[i][1] = s.name();
	        data[i][2] = s.age();
	    }

	    return new JTable(data, columns) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		return false;
	    	}
	    };
	}
	
	private JMenu createMenu() {
		
		JMenu menu = new JMenu("Menu");
		
		JMenuItem add = new JMenuItem("Add Student");
		add.addActionListener(e -> new AddingStudentWindow(putService));
		
		menu.add(add);
		
		return menu;
	}


}
