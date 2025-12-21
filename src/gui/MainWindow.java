package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

import data.Student;
import services.DeleteService;
import services.GetService;
import services.GetServices;
import services.PutService;
import services.SetService;

public final class MainWindow extends JFrame{
	
	private final DeleteService deleteService;
	private final GetService getService;
	private final GetServices getServices;
	private final PutService putService;
	private final SetService setService;
	
	public MainWindow(Connection conn) {

	    this.deleteService = new DeleteService(conn);
	    this.getService    = new GetService(conn);
	    this.getServices   = new GetServices(conn);
	    this.putService    = new PutService(conn);
	    this.setService    = new SetService(conn);
		
		setSize(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        try {
		            conn.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		addComponents();
	}
	
	private void addComponents() {
		
		addToolBar();
		addListOfStudents();
	}
	
	private void addToolBar() {
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		JMenuBar menuBar = createMenuBar();

		toolBar.add(menuBar);

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
	
	private JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.add(help());
		
		return menuBar;
	}
	
	private  JMenu help() {
		
		JMenu options = new JMenu("Options");
		
		JMenuItem add = new JMenuItem("Add");
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					new AddingStudentWindow(MainWindow.this, putService).setVisible(true);;
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		options.add(add);
		
		return options;
	}
}
