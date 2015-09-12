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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View extends JFrame {
	
	protected JMenuBar menuBar;
	
	protected void instantiateMenuBar(){
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu courseMenu = new JMenu("Cursos");
		menuBar.add(courseMenu);
		
		JMenuItem registerCourse = new JMenuItem("Cadastrar Curso");
		registerCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				
				NewCourse newCourseFrame = new NewCourse();
				newCourseFrame.setVisible(true);
			}
		});
		courseMenu.add(registerCourse);
	}

	/**
	 * Create the frame.
	 */
	public View(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		instantiateMenuBar();
	}
	
	protected void showInfoMessage(String message){
		
		JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
	}
}
