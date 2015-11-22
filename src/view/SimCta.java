package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Font;

@SuppressWarnings("serial")
public class SimCta extends View{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 try{ 
					    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
					 } 
					 catch(Exception e){ 
					
					 }
					SimCta frame = new SimCta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimCta() {
		super();
		getContentPane().setLayout(null);
		
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(SimCta.class.getResource("/resources/logo.png") ) );
		logo.setBounds(355, -117, 633, 392);
		getContentPane().add(logo);			
		
		createLinkToCourses();
		createLinkToClasses();
		createLinkToStudents();
		createLinkToPackages();
		
	}

	private void createLinkToStudents() {
		JLabel lblStudents = new JLabel("Alunos");
		lblStudents.setFont(new Font("Century Schoolbook L", Font.BOLD, 27));
		lblStudents.setBounds(660, 329, 319, 127);
		getContentPane().add(lblStudents);
		
		lblStudents.addMouseListener(new MouseAdapter() {
				
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();			
				PersonView searchStudentFrame = new SearchStudent();
				searchStudentFrame.buildScreen(searchStudentFrame, null);
				searchStudentFrame.setVisible(true);				
			}
		});
	}

	private void createLinkToPackages(){
			
		JLabel lblPackages = new JLabel("Pacotes");
		lblPackages.setFont(new Font("Century Schoolbook L", Font.BOLD, 27));
		lblPackages.setBounds(660, 173, 319, 127);
		getContentPane().add(lblPackages);
		
		lblPackages.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();			
				ServiceItemView searchPackageFrame = new SearchPackage();
				searchPackageFrame.buildScreen(searchPackageFrame, null);
				searchPackageFrame.setVisible(true);				
			}
		});
	}
	
	private void createLinkToClasses() {
		JLabel lblOpenClasses = new JLabel("Turmas abertas");
		lblOpenClasses.setFont(new Font("Century Schoolbook L", Font.BOLD, 27));
		lblOpenClasses.setBounds(147, 329, 319, 127);
		getContentPane().add(lblOpenClasses);
		
		lblOpenClasses.addMouseListener(new MouseAdapter() {
			
		@Override
		public void mouseClicked(MouseEvent arg0) {
			dispose();			
			new SearchClass().setVisible(true); 	
		}
	});
	}

	private void createLinkToCourses() {
		
		JLabel lblCourse = new JLabel("Cursos");
		lblCourse.setFont(new Font("Century Schoolbook L", Font.BOLD, 27));
		lblCourse.setBounds(205, 173, 319, 127);
		getContentPane().add(lblCourse);
		lblCourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();			
				ServiceItemView searchCourseFrame = new SearchCourse();
				searchCourseFrame.buildScreen(searchCourseFrame, null);
				searchCourseFrame.setVisible(true);				
			}
		});
	}
}
