package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.TrayIcon.MessageType;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;

import controller.CourseController;
import exception.CourseException;

import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JInternalFrame;
import javax.swing.JTable;

import model.Course;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JTextArea;

import java.awt.Panel;

import javax.swing.JDesktopPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchCourse extends View {

	private final static int NUMBER_OF_COLUMNS = 0;
	
	private JPanel contentPane;
	private JLabel courseResultLabel;
	private JTextArea descriptionResultLabel;
	private JLabel durationResultLabel;
	private JLabel valueResultLabel;
	final DefaultTableModel tableModel;
	JScrollPane scrollPane;

	private Integer courseId;
	private String courseName;
	private String courseDescription;
	private Integer courseDuration;
	private Integer courseValue;
	
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

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SearchCourse() throws SQLException{
		
		super();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextField searchedCourseField = new JTextField();
		searchedCourseField.setBounds(29, 12, 161, 19);
		contentPane.add(searchedCourseField);
		searchedCourseField.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(202, 9, 117, 25);
		contentPane.add(btnConsultar);
		
		
		final JInternalFrame internalFrame = new JInternalFrame();
		internalFrame.setEnabled(false);
		internalFrame.setBackground(Color.WHITE);
		internalFrame.setBounds(12, 43, 409, 196);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JButton editCourseBtn = new JButton("Editar");
		editCourseBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				
				dispose();
				
				EditCourse editCourseFrame = new EditCourse(SearchCourse.this.courseId, SearchCourse.this.courseName, SearchCourse.this.courseDescription, SearchCourse.this.courseDuration, SearchCourse.this.courseValue);
				editCourseFrame.setVisible(true);
			}
		});
		editCourseBtn.setBounds(268, 127, 117, 25);
		internalFrame.getContentPane().add(editCourseBtn);
		
		JLabel courseLabel = new JLabel("Curso:");
		courseLabel.setBounds(12, 12, 70, 15);
		internalFrame.getContentPane().add(courseLabel);
		
		courseResultLabel = new JLabel("a");
		courseResultLabel.setBounds(65, 12, 273, 15);
		internalFrame.getContentPane().add(courseResultLabel);
		
		JLabel descriptionLabel = new JLabel("Descrição:");
		descriptionLabel.setBounds(12, 70, 86, 15);
		internalFrame.getContentPane().add(descriptionLabel);
		
		descriptionResultLabel = new JTextArea("a");
		descriptionResultLabel.setEditable(false);
		descriptionResultLabel.setBounds(22, 86, 328, 66);
		internalFrame.getContentPane().add(descriptionResultLabel);
		
		JLabel durationLabel = new JLabel("Duração:");
		durationLabel.setBounds(12, 43, 70, 15);
		internalFrame.getContentPane().add(durationLabel);
		
		durationResultLabel = new JLabel("a");
		durationResultLabel.setBounds(75, 43, 129, 15);
		internalFrame.getContentPane().add(durationResultLabel);
		
		JLabel valueLabel = new JLabel("Valor:");
		valueLabel.setBounds(216, 43, 70, 15);
		internalFrame.getContentPane().add(valueLabel);
		
		valueResultLabel = new JLabel("a");
		valueResultLabel.setBounds(268, 43, 70, 15);
		internalFrame.getContentPane().add(valueResultLabel);
		
		final JInternalFrame internalFrame_1 = new JInternalFrame();
		internalFrame_1.setEnabled(false);
		internalFrame_1.getContentPane().setLayout(null);
		internalFrame_1.setVisible(true);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 409, 196);
		scrollPane.setBackground(Color.WHITE);
		contentPane.add(scrollPane);

		String [] columns = { "Curso"};
		
		tableModel = new DefaultTableModel(null, columns);
		final JTable tableOfCourses = new JTable(tableModel);
		tableOfCourses.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableOfCourses);

		internalFrame_1.setBackground(Color.WHITE);
		internalFrame_1.setBounds(12, 43, 409, 196);
		//contentPane.add(internalFrame_1);
				
		final JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setVisible(false);
				searchedCourseField.setText("");
			}
		});
		btnVoltar.setBounds(321, 9, 117, 25);
		contentPane.add(btnVoltar);
		btnVoltar.setVisible(false);
		
		final CourseController courseController = new CourseController();			
		getAllCourses(courseController);
		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchedCourse = searchedCourseField.getText();
				int tableRow = tableOfCourses.getSelectedRow();
				
				boolean enteredCourse = !searchedCourse.isEmpty();
				boolean selectedCourse = tableRow != -1;
				
				if(enteredCourse == true || selectedCourse == true){
					try {
						if(enteredCourse == false){
							searchedCourse = (String) tableOfCourses.getValueAt(tableRow, NUMBER_OF_COLUMNS);
						}
						showCourses(searchedCourse);
						btnVoltar.setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
				else{
					JOptionPane.showMessageDialog(null, "Digite ou selecione um curso");
				}
			}

			private void showCourses(String searchedCourse) throws SQLException{
				
				try {
					ResultSet resultOfTheSearch = courseController.showCourse(searchedCourse);
					boolean courseFound = false;
					String current_name = "";
					
					while(resultOfTheSearch.next()){
						current_name = resultOfTheSearch.getString("course_name");
						if(current_name.equals(searchedCourse)){
							courseFound = true;
							break;
						}
						else{
							
						}
					}
					if(courseFound == true){
						internalFrame_1.dispose();
						internalFrame.setVisible(true);
						showDataOfCourse(resultOfTheSearch);
					}
					else{
						JOptionPane.showMessageDialog(searchedCourseField, "Curso não encontrado!");
						searchedCourseField.setText("");
					}
					
				} catch (CourseException e1) {
					e1.printStackTrace();
				}
			}

			private void showDataOfCourse(ResultSet resultOfTheSearch) throws SQLException {
				
				String courseName = "";
				String description = "";
				Integer value = 0;
				Integer duration = 0;
				Integer courseId = 0;
				String valueText = "";
				String durationString = "";
				
				courseId = resultOfTheSearch.getInt("id_course");
				courseName = resultOfTheSearch.getString("course_name");
				description = resultOfTheSearch.getString("description");
				value = resultOfTheSearch.getInt("value");
				duration = resultOfTheSearch.getInt("duration");
				
				SearchCourse.this.courseId = courseId;
				SearchCourse.this.courseName = courseName;
				SearchCourse.this.courseDescription = description;
				SearchCourse.this.courseDuration = duration;
				SearchCourse.this.courseValue = value;
				
				valueText = passValueToMonetaryForm(value);
				durationString = duration.toString() + " semanas";
							
				courseResultLabel.setText(courseName);
				descriptionResultLabel.setText(description);
				valueResultLabel.setText(valueText);
				durationResultLabel.setText(durationString);
			}

			// Method used to pass the course value to monetary form
			private String passValueToMonetaryForm(Integer value) {
				
				String valueText= "";
				String entireValue = "";
				String decimalValue = "";
				int lengthOfValue = 0;
				
				valueText = value.toString();
				lengthOfValue = valueText.length();
				entireValue = valueText.substring(0, (lengthOfValue - 2));
				decimalValue = valueText.substring((lengthOfValue - 2), lengthOfValue);
				valueText = entireValue + "," + decimalValue;
				
				return valueText;
			}
		});

	}

	// Method used to show all existing courses
	private void getAllCourses(CourseController courses) throws SQLException{
					
		ResultSet resultOfTheSelect = courses.showCourse();		

		while(resultOfTheSelect.next()){
			String[] allCourses = new String[2];
			allCourses[0] = (resultOfTheSelect.getString("course_name"));	
			allCourses[1] = ("Visualizar");
			tableModel.addRow(allCourses);
			//allCourses.clear();
		}		
	}
}
