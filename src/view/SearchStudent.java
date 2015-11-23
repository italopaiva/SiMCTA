package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Course;
import model.Payment;
import model.PaymentDescription;
import model.Person;
import model.Service;
import model.ServiceItem;
import model.Student;
import model.Package;
import controller.CourseController;
import controller.ServiceController;
import controller.StudentController;
import util.ButtonColumn;
import view.decorator.ShowStudentDecorator;
import view.forms.PersonForm;
import view.forms.StudentForm;
import exception.AddressException;
import exception.CPFException;
import exception.CourseException;
import exception.DateException;
import exception.PaymentException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

import datatype.Address;
import datatype.CPF;
import datatype.Date;
import datatype.Phone;
import datatype.RG;


public class SearchStudent extends PersonView {
	public SearchStudent() {
	}
	
	private JPanel contentPane;
	private JTextField searchedStudentField;
	private JTable tableOfStudents;
	private DefaultTableModel tableModel;
	private Component scrollPane;
	private JButton searchButtton;
	private JButton backButton;
	private Student student;

	@Override
	public void createLabelsAndFields(JFrame frame, Person person) {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		JLabel searchLabel = new JLabel("Pesquisa de Aluno");
		searchLabel.setBounds(310, 14, 446, 30);
		searchLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(searchLabel);
		
		searchedStudentField = new JTextField();
		searchedStudentField.setBounds(141, 54, 446, 30);
		contentPane.add(searchedStudentField);
		searchedStudentField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 141, 557, 317);
		contentPane.add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setVisible(false);
		
		String [] columns = { "Aluno", "Ação", "CPF"};
		
		tableModel = new DefaultTableModel(null, columns){
			// Overriding the method to set non editable the name and CPF columns
			@Override
			public boolean isCellEditable(int row, int column) {
				
				boolean isEditable = false;
				
				if(column == 0 || column == 2){
					isEditable = false;
				}
				else{
					isEditable = true;
				}
				
				return isEditable;
				
			};
		};
		tableOfStudents = new JTable(tableModel);
		
		tableOfStudents.removeColumn(tableOfStudents.getColumnModel().getColumn(2));
		tableOfStudents.setBackground(Color.WHITE);
		
		Action visualizeStudent = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				JTable table = (JTable)e.getSource();
				int selectedRow = table.getSelectedRow();
				
				String cpfSelectedStudent  = table.getModel().getValueAt(selectedRow,2).toString();
				CPF selectedStudent;
				try{
					selectedStudent = new CPF(cpfSelectedStudent);
					visualizeStudent(selectedStudent);
				}
				catch(CPFException | StudentException | PhoneException | DateException |
						AddressException | RGException | CourseException | ServiceException |PaymentException e1){
					
					showInfoMessage(e1.getMessage());
				}
				catch(SQLException e2){
					e2.printStackTrace();
				} 
				catch(PersonException e1){
					e1.printStackTrace();
				}
			}

		};
		
		ButtonColumn buttonColumn2 = new ButtonColumn(tableOfStudents, visualizeStudent, 1);
		
		((JScrollPane) scrollPane).setViewportView(tableOfStudents);
		
		backButton = new JButton("Voltar");
		backButton.setBounds(727, 26, 117, 25);
		contentPane.add(backButton);
		backButton.setVisible(false);
				
	}

	@Override
	public void createMasks(JFrame frame) {
		
	}

	@Override
	public void createButtons(JFrame frame) {

		searchButtton = new JButton("Pesquisar");
		searchButtton.setBounds(599, 54, 117, 30);
		contentPane.add(searchButtton);
		searchButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchedStudent = searchedStudentField.getText();
				
				boolean enteredStudent = !searchedStudent.isEmpty();
				
				if(enteredStudent){
					try {
						scrollPane.setVisible(true);
						buildTableWithSearchedStudent(searchedStudent);
					}
					catch(StudentException e1){
						
					} 
					catch(CPFException| PersonException e1){

					} 
				}
				else{
					JOptionPane.showMessageDialog(null, "Digite o nome de um aluno");
				}
			}


		});			
	}
	

	/**
	 * Builds the table to show the found students
	 * @param searchedStudent - Name of the searched student
	 * @throws StudentException 
	 * @throws CPFException 
	 * @throws PersonException 
	 */
	private void buildTableWithSearchedStudent(String searchedStudent) throws StudentException, CPFException, PersonException {

		StudentController studentController = new StudentController();
		tableModel.setRowCount(0);
		ArrayList<Student> resultOfTheSearch = studentController.searchStudent(searchedStudent);
		int arrayIndex = 0;
		
		if(!resultOfTheSearch.isEmpty()){
			while(arrayIndex < resultOfTheSearch.size()){
				String[] student = new String[4];
				student[0] = resultOfTheSearch.get(arrayIndex).getName();
				student[1] = ("Ver");
				CPF cpf = resultOfTheSearch.get(arrayIndex).getCpf();
				student[2] = cpf.getCpf();
				tableModel.addRow(student);
				arrayIndex++;
			}
		}
		else{
			showInfoMessage("Nenhum aluno com esse nome foi encontrado");
		}
		
	}
	
	/**
	 * Show the information of the selected student
	 * @param studentCPF - CPF of the selected student
	 * @throws StudentException 
	 * @throws SQLException 
	 * @throws RGException 
	 * @throws AddressException 
	 * @throws DateException 
	 * @throws CPFException 
	 * @throws PhoneException 
	 * @throws ServiceException 
	 * @throws CourseException 
	 * @throws PaymentException 
	 * @throws PersonException 
	 */
	private void visualizeStudent(CPF studentCPF) throws SQLException, StudentException, PhoneException, 
												CPFException, DateException, AddressException, RGException, CourseException, ServiceException, PaymentException, PersonException {
		
		final StudentController studentController = new StudentController();
		try{
			student = studentController.getStudent(studentCPF);
			dispose();
			PersonView showStudentFrame = new ShowStudentDecorator(new StudentForm());
			showStudentFrame.buildScreen(showStudentFrame, student);
			showStudentFrame.setVisible(true);
		}
		catch(StudentException e){
			showInfoMessage(e.getMessage());
		}
	
	}
}
