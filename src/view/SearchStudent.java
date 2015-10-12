package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Student;
import model.datatype.CPF;
import controller.StudentController;
import util.ButtonColumn;
import exception.CPFException;
import exception.StudentException;


public class SearchStudent extends View {
	
	private JPanel contentPane;
	private JTextField searchedStudentField;
	private JTable tableOfStudents;
	private DefaultTableModel tableModel;
	private Component scrollPane;
	private JButton searchButtton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchStudent frame = new SearchStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SearchStudent(){
		
		super();
		
		createLabelsAndFields();
		
		showStudent();		
		
	}

	/**
	 * Used to get the name of the searched student
	 */
	private void showStudent() {
		searchButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchedStudent = searchedStudentField.getText();
				
				boolean enteredStudent = !searchedStudent.isEmpty();
				
				if(enteredStudent){
					try {
						buildTableWithSearchedStudent(searchedStudent);
					}
					catch(StudentException e1){
						
					} catch (CPFException e1) {

					}	
				}
				else{
					JOptionPane.showMessageDialog(null, "Digite o nome de um aluno");
				}
			}


		});	

		
	}

	/**
	 * Creates the labels and fields of the frame
	 */
	private void createLabelsAndFields() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchedStudentField = new JTextField();
		searchedStudentField.setBounds(140, 56, 446, 30);
		contentPane.add(searchedStudentField);
		searchedStudentField.setColumns(10);
		
		searchButtton = new JButton("Pesquisar");
		searchButtton.setBounds(598, 53, 117, 25);
		contentPane.add(searchButtton);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 141, 557, 317);
		contentPane.add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		
		String [] columns = { "Aluno", "Ação", "CPF"};
		
		tableModel = new DefaultTableModel(null, columns);
		tableOfStudents = new JTable(tableModel);
		
		tableOfStudents.removeColumn(tableOfStudents.getColumnModel().getColumn(2));
		tableOfStudents.setBackground(Color.WHITE);
		
		Action visualizeStudent = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				JTable table = (JTable)e.getSource();
				int selectedRow = table.getSelectedRow();
				
				String cpfSelectedStudent  = table.getModel().getValueAt(selectedRow,2).toString();
				CPF selectedStudent;
				try {
					selectedStudent = new CPF(cpfSelectedStudent);
					getStudentForCPF(selectedStudent);
				} 
				catch(CPFException e1){
					
				}
				
			}

		};
		
		ButtonColumn buttonColumn2 = new ButtonColumn(tableOfStudents, visualizeStudent, 1);
		
		((JScrollPane) scrollPane).setViewportView(tableOfStudents);

		
	}

	/**
	 * Builds the table to show the found students
	 * @param searchedStudent - Name of the searched student
	 * @throws StudentException 
	 * @throws CPFException 
	 */
	private void buildTableWithSearchedStudent(String searchedStudent) throws StudentException, CPFException {

		StudentController studentController = new StudentController();
		tableModel.setRowCount(0);
		ArrayList<Student> resultOfTheSearch = studentController.searchStudent(searchedStudent);
		int arrayIndex = 0;
		
		if(!resultOfTheSearch.isEmpty()){
			while(arrayIndex < resultOfTheSearch.size()){
				String[] student = new String[4];
				student[0] = resultOfTheSearch.get(arrayIndex).getStudentName();
				student[1] = ("Ver");
				student[2] = resultOfTheSearch.get(arrayIndex).getStudentCpf().toString();
				tableModel.addRow(student);
				arrayIndex++;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Nenhum aluno com esse nome foi encontrado");
		}
		
	}
	
	/**
	 * Gets the information of the selected student
	 * @param studentCPF - CPF of the selected student
	 */
	private void getStudentForCPF(CPF studentCPF) {
		StudentController studentController = new StudentController();
		
		
			/*ResultSet resultOfTheSearch = studentController.searchStudent(studentCPF);
			String currentCPF = "";
			String receivedCPF = studentCPF.getCpf();
			while(resultOfTheSearch.next()){
				currentCPF = resultOfTheSearch.getString("cpf");
				if(currentCPF.equals(receivedCPF)){
					visualizeStudent(resultOfTheSearch);
				}
			}*/
			
	
	}

	/**
	 * Show the data of the student
	 * @param resultOfTheSearch - the result of the search from database
	 */
	private void visualizeStudent(ResultSet resultOfTheSearch) {
		
	}
				
}
