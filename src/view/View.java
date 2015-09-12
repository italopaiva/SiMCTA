package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import view.SearchCourse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class View extends JFrame {
	
	protected JMenuBar menuBar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	protected void instantiateMenuBar(){
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu courseMenu = new JMenu("Cursos");
		menuBar.add(courseMenu);
		
		JMenuItem registerCourse = new JMenuItem("Cadastrar Curso");
		registerCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				
				//NewCourse newCourseFrame = new NewCourse();
				//newCourseFrame.setVisible(true);
			}
		});
		courseMenu.add(registerCourse);
		JMenuItem searchCourse = new JMenuItem("Visualizar Curso");
		searchCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				
				SearchCourse searchCourseFrame;
				try {
					searchCourseFrame = new SearchCourse();
					searchCourseFrame.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		courseMenu.add(searchCourse);
	}

	/**
	 * Create the frame.
	 */
	public View(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		instantiateMenuBar();
	}
}
