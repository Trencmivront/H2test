package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import data.Student;
import services.DeleteService;
import services.GetService;
import services.GetServices;
import services.PutService;
import services.SetService;

public final class MainWindow extends JFrame {
	
	// services
	private final DeleteService deleteService;
	private final GetService getService;
	private final GetServices getServices;
	private final PutService putService;
	private final SetService setService;
	// table needs to be initialized
	private JTable studentTable = new JTable();
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JButton refresh;
	
	protected MainWindow(Connection conn) {

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
		        	JOptionPane.showMessageDialog(new JDialog(), ex.getMessage());
		            ex.printStackTrace();
		        }
		    }
		});
		updateStudentTable(getServices.getStudents());
		addComponents();
		
	}
	
	private void addComponents() {
		addToolBar();
		add(searchBar(), BorderLayout.NORTH);
		addListOfStudents();
		
	}
	
	private void addToolBar() {
		
		JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
		toolBar.setFloatable(false);
		toolBar.setLayout(new GridLayout(1, 5));
		
		JMenuBar menuBar = createMenuBar();

		toolBar.add(menuBar);
		
		toolBar.add(refresh());

		add(toolBar, BorderLayout.NORTH);
	}
	
	private void addListOfStudents() {
		
		scrollPane = new JScrollPane(studentTable);
		
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void updateStudentTable(List<Student> students) {

	    String[] columns = {"ID", "Name", "Age"};

	    Object[][] data = new Object[students.size()][3];

	    for (int i = 0; i < students.size(); i++) {
	        Student s = students.get(i);
	        data[i][0] = s.id();
	        data[i][1] = s.name();
	        data[i][2] = s.age();
	    }
	    
	    // model being updated and we change the model in table
	    model = new DefaultTableModel(data, columns);
	    
	    studentTable.setModel(model);
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
					JOptionPane.showMessageDialog(new JDialog(), e.getMessage());
					e.printStackTrace();
				}
				
			}
		});
		options.add(add);
		
		return options;
	}
	
	private JButton refresh() {
		
		refresh = new JButton("<->");
		refresh.setToolTipText("Refresh Table");
		
		refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				updateStudentTable(getServices.getStudents());
				
			}
		});
		
		return refresh;
	}
	private JTextField searchBar() {
		
		JTextField search = new JTextField();
		search.setToolTipText("Search");
		
		search.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				String key = search.getText();
				
				studentTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(key));
			}
		});
		
		return search;
		
	}
	
}
