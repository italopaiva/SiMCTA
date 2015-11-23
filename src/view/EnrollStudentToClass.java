package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Class;
import model.Student;
import controller.StudentClassController;
import controller.StudentController;
import exception.CourseException;
import exception.PersonException;
import exception.StudentClassException;
import exception.StudentException;

public class EnrollStudentToClass extends View {
	
	private static final int SELECTED_ROW = -1;
	
	private Class classToEnroll;
	private SearchClass classSearch;
	
	private JPanel contentPane;
	private DefaultTableModel availableStudentsTableModel;
	private ArrayList <String> studentsCpf;
	private DefaultTableModel addedStudentsTableModel;
	
	public EnrollStudentToClass(Class classToEnroll, SearchClass classSearch) throws CourseException, SQLException, StudentException{
		super();
		
		this.classToEnroll = classToEnroll;
		this.classSearch = classSearch;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		studentsCpf = null;
		
		createLabelsAndFields();
			
		getStudentsToSelect();
			
		enrollStudents();
	}
	
	/**
	 * Creates a new package
	 */
	private void enrollStudents() {
		
		JButton enrollStudentsButton = new JButton("Matricular alunos");
		enrollStudentsButton.setBounds(398, 302, 222, 25);
		enrollStudentsButton.setBackground(Color.WHITE);
		contentPane.add(enrollStudentsButton);
		
		enrollStudentsButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				StudentClassController studentClassController = new StudentClassController();
				
				String message = "";
				try{
					studentClassController.enrollStudentToClass(classToEnroll.getClassId(), studentsCpf);
					
					message = "Alunos matriculados com sucesso!";
				}
				catch(PersonException | StudentClassException e1){
					message = e1.getMessage();
				}finally{
					showInfoMessage(message);
					dispose();
					classSearch.setVisible(true);
				}
			}

		});

	}

	/**
	 * Creates all labels and fields on frame
	 */
	public void createLabelsAndFields(){
		
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Matricular alunos na turma "+ classToEnroll.getClassId());
		titleLabel.setBounds(219, 12, 700, 28);
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(titleLabel);
		
		JLabel studentsToSelectLabel = new JLabel("Alunos");
		studentsToSelectLabel.setBounds(79, 73, 144, 15);
		contentPane.add(studentsToSelectLabel);

		JLabel lblAlunosParaMatricular = new JLabel("Alunos para matricular na turma");
		lblAlunosParaMatricular.setBounds(579, 73, 277, 25);
		contentPane.add(lblAlunosParaMatricular);
				
		JScrollPane scrollPaneStudentsToSelect = new JScrollPane();
		scrollPaneStudentsToSelect.setBounds(79, 102, 353, 169);
		contentPane.add(scrollPaneStudentsToSelect);
		scrollPaneStudentsToSelect.setBackground(Color.WHITE);
		
		JScrollPane scrollPaneAddedStudents = new JScrollPane();
		scrollPaneAddedStudents.setBounds(579, 102, 353, 169);
		contentPane.add(scrollPaneAddedStudents);
		scrollPaneAddedStudents.setBackground(Color.WHITE);
		
		String [] columns = {"CPF", "Nome"};
		
		availableStudentsTableModel = new DefaultTableModel(null, columns);			

		final JTable tableOfStudentsToSelect = new JTable(availableStudentsTableModel);
		
		tableOfStudentsToSelect.setBackground(Color.WHITE);
		scrollPaneStudentsToSelect.setViewportView(tableOfStudentsToSelect);
		
		String [] columnsAddedStudents = {"CPF", "Nome"};
		
		addedStudentsTableModel = new DefaultTableModel(null, columnsAddedStudents);			

		final JTable tableOfAddedStudents = new JTable(addedStudentsTableModel);
		scrollPaneAddedStudents.setViewportView(tableOfAddedStudents);
		
		addButtons(tableOfStudentsToSelect, tableOfAddedStudents);
	}

	/**
	 * Adds the "Adicionar" button to table
	 * @param tableOfStudents
	 * @param tableOfAddedStudents 
	 */
	private void addButtons(final JTable tableOfStudents, final JTable tableOfAddedStudents){
		 
		studentsCpf = new ArrayList<String>();
					
		JButton addStudentButton = new JButton("Adicionar");
		addStudentButton.setBounds(455, 102, 114, 25);
		addStudentButton.setBackground(Color.WHITE);
		addStudentButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				
				int currentRow = tableOfStudents.getSelectedRow();
							    
				if(currentRow != SELECTED_ROW){
					
					String addedStudentCpf = (String) tableOfStudents.getValueAt(currentRow, 0);
					studentsCpf.add(addedStudentCpf); 
					
					String addedStudentName = (String) tableOfStudents.getValueAt(currentRow, 1);
					
					// Remove the course from available courses table
					availableStudentsTableModel.removeRow(currentRow);
					
					String [] addedStudent = new String[2];
					addedStudent[0] = addedStudentCpf;
					addedStudent[1] = addedStudentName;
					addedStudentsTableModel.addRow(addedStudent);
				}	
				else{
					showInfoMessage("Selecione um aluno da lista de alunos.");
				}
			}
			

		});
		contentPane.add(addStudentButton);
		
		JButton removeStudentButton = new JButton("Remover");
		removeStudentButton.setBounds(455, 152, 114, 25);
		removeStudentButton.setBackground(Color.WHITE);
		removeStudentButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				int currentRow = tableOfAddedStudents.getSelectedRow();
			
				if(currentRow != SELECTED_ROW){
				
					String studentToRemoveCpf = (String) tableOfAddedStudents.getValueAt(currentRow, 0);
					String studentToRemoveName = (String) tableOfAddedStudents.getValueAt(currentRow, 1);
										
					// Remove the student from added student list
					studentsCpf.remove(studentToRemoveCpf);
					
					String [] removedStudent = new String[2];
					removedStudent[0] = studentToRemoveCpf;
					removedStudent[1] = studentToRemoveName;
					
					// Add the student to available students table
					availableStudentsTableModel.addRow(removedStudent);
					
					// Remove the student from added students table
					addedStudentsTableModel.removeRow(currentRow);
				    
				}
				else{
					showInfoMessage("Selecione um curso da lista de cursos adicionados");
				}
			}

		});
		contentPane.add(removeStudentButton);
		
		JButton backButton = new JButton("Voltar");
		backButton.setBounds(455, 400, 114, 25);
		backButton.setBackground(Color.WHITE);
		backButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				dispose();
				classSearch.setVisible(true);
			}

		});
		contentPane.add(backButton);
		
	}

	/**
	 *  Method used to get students of a class 
	 * @throws SQLException
	 * @throws CourseException 
	 * @throws StudentException 
	 */
	public void getStudentsToSelect() throws SQLException, CourseException, StudentException {
		
		StudentController studentController = new StudentController();
		ArrayList<Student> students = studentController.getStudentsOfCourse(classToEnroll.getCourse());
		
		if(students.isEmpty()){
			throw new StudentException("Não há alunos matriculados no curso desta turma.");
		}
		
		int indexOfCourses = 0;
		while(indexOfCourses < students.size()){
			
			Student student = students.get(indexOfCourses);
			String studentCpf = student.getCpf().getCpf();

			String[] allStudents = new String[2];
	
			allStudents[0] = (studentCpf);
			allStudents[1] = (student.getName());
			
			availableStudentsTableModel.addRow(allStudents);
			
			indexOfCourses++;
		}
	}
}
