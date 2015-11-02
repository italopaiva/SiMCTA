package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Course;
import util.ButtonColumn;
import view.decorator.ShowCourseDecorator;
import view.forms.CourseForm;
import controller.CourseController;
import exception.CourseException;

@SuppressWarnings("serial")
public class SearchCourse extends CourseView {
	
	protected static final Integer COURSE_ACTIVE = 1;
	
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JTextField searchedCourseField;
	private JTable tableOfCourses;
	private JInternalFrame internalFrame;
	private JInternalFrame internalFrame_1;
	private JButton backButton;

	private CourseController courseController;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchCourse frame = new SearchCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	@Override
	public void createLabelsAndFields(JFrame frame, Course course) {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchedCourseField = new JTextField();
		searchedCourseField.setBounds(140, 56, 446, 39);
		add(searchedCourseField);
		searchedCourseField.setColumns(10);
				
		internalFrame = new JInternalFrame();
		internalFrame.setEnabled(false);
		internalFrame.setBackground(Color.WHITE);
		internalFrame.setBounds(227, 141, 557, 317);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		String [] columns = { "Curso", "Status", "Ação", "Id"};
		
		tableModel = new DefaultTableModel(null, columns);
		tableOfCourses = new JTable(tableModel);
		
		tableOfCourses.removeColumn(tableOfCourses.getColumnModel().getColumn(3));
		tableOfCourses.setBackground(Color.WHITE);
			
		Action showCourse = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				JTable table = (JTable)e.getSource();
				int selectedRow = table.getSelectedRow();
				
				int idCourse = Integer.parseInt(table.getModel().getValueAt(selectedRow,3).toString());
				courseController = new CourseController();
				try {
					Course course = courseController.showCourse(idCourse);
					dispose();
					CourseView courseFrame = new ShowCourseDecorator(new CourseForm());
					courseFrame.buildScreen(courseFrame, course);
					courseFrame.setVisible(true);
				} 
				catch(CourseException e1) {

				}

			}
		};
		
		ButtonColumn buttonColumn2 = new ButtonColumn(tableOfCourses, showCourse, 2);
			
		internalFrame_1 = new JInternalFrame();
		internalFrame_1.setEnabled(false);
		internalFrame_1.getContentPane().setLayout(null);
		internalFrame_1.setVisible(true);


		internalFrame_1.setBackground(Color.WHITE);
		internalFrame_1.setBounds(12, 43, 409, 196);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 141, 557, 317);
		add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableOfCourses);

		final CourseController courseController = new CourseController();			
		try {
			getAllCourses(courseController);
		} 
		catch(CourseException e1){

		} 
		catch(SQLException e1){

		}		
	}

	@Override
	public void createButtons(JFrame frame) {

		JButton btnConsultar = new JButton("Pesquisar");
		btnConsultar.setBounds(598, 53, 117, 25);
		add(btnConsultar);
		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchedCourse = searchedCourseField.getText();
				boolean enteredCourse = !searchedCourse.isEmpty();
				
				if(enteredCourse){
					try {
						internalFrame.dispose();
						internalFrame_1.setVisible(true);
						tableOfCourses.setVisible(true);
						buildTableWithSearchedCourse(searchedCourse);
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
				else{
					JOptionPane.showMessageDialog(null, "Digite o nome de um curso");
				}
			}
					
		});			
	}
	

	@Override
	public void createMasks(JFrame frame) {
		
	}
	
	/**
	 * Method used to search a course by name on database
	 * @param searchedCourse - Receives the course name entered by user
	 * @throws SQLException
	 */
	private void buildTableWithSearchedCourse(String searchedCourse) throws SQLException{
		
		courseController = new CourseController();
		tableModel.setRowCount(0);

		try {
			ArrayList<Course> courses = courseController.showCourse(searchedCourse);

			int indexOfCourses = 0;
			while(indexOfCourses < courses.size()){
				
				String[] allCourses = new String[4];
				Course course = courses.get(indexOfCourses);
				Integer courseId = course.getId();
				
				allCourses[0] = (course.getName());
				allCourses[1] = (showActiveOrInactive(course.getStatus()));
				allCourses[2] = ("Ver");
				allCourses[3] = (courseId.toString());
				
				tableModel.addRow(allCourses);
				
				indexOfCourses++;
			}
						
		} 
		catch(CourseException e1) {
			e1.printStackTrace();
		}
	}

	
	/**
	 *  Method used to show all existing courses
	 * @param courses - Receives an instance of the class CourseController 
	 * @throws SQLException
	 * @throws CourseException 
	 */
	public void getAllCourses(CourseController courses) throws SQLException, CourseException{
					
		ArrayList<Course> foundCourses = courses.showCourse();		
		
		int indexOfCourses = 0;
		if(foundCourses != null){
			while(indexOfCourses < foundCourses.size()){
				
				String[] allCourses = new String[4];
				Course course = foundCourses.get(indexOfCourses);
				
				Integer courseId = course.getId();
				allCourses[0] = (course.getName());
				allCourses[1] = (showActiveOrInactive(course.getStatus()));
				allCourses[2] = ("Ver");
				allCourses[3] = (courseId.toString());
				
				tableModel.addRow(allCourses);
				
				indexOfCourses++;
			}
		}
		else{
			showInfoMessage("Nenhum curso cadastrado ainda");
		}
				
	}

	private String showActiveOrInactive(int status){
		
		String statusToShow = null;
		
		switch(status){
			case 0:
				statusToShow = "Desativado";
				break;
			case 1:
				statusToShow = "Ativado";
		}
		
		return statusToShow;
	}

}
