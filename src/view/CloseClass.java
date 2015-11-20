package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Class;
import model.Student;
import model.datatype.CPF;
import view.decorator.class_decorator.ClassDecorator;
import controller.StudentClassController;
import exception.CPFException;
import exception.PersonException;
import exception.StudentClassException;

public class CloseClass extends ClassDecorator {
	
	private Class enrolledClass;
	private JLabel titleLbl;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable tableOfStudents;
	private JButton backBtn;


	public CloseClass(ClassView classViewToDecorate) {
		super(classViewToDecorate);
	}
	
	@Override
	public void createLabelsAndFields(JFrame frame, Class enrolledClass) {

		super.createLabelsAndFields(frame, enrolledClass);
						
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 91, 853, 569);
		frame.getContentPane().add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		
		String [] columns = {"Nome do aluno", "Número de faltas", "Nota" , "Situação", "CPF"};
		
		tableModel = new DefaultTableModel(null, columns);			

		tableOfStudents = new JTable(tableModel);
		
		disposeColumns(tableOfStudents);
		tableOfStudents.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableOfStudents);
		
		this.enrolledClass = enrolledClass;
		getAllStudentsClass();
		
	}

	/**
	 * Get all students enrolled in the class
	 * @param enrolledClass2
	 */
	private void getAllStudentsClass() {
		
		StudentClassController studentClassController = new StudentClassController();
		ArrayList<Student> students;
		try {
			students = studentClassController.getStudents(enrolledClass);
			for(Student student : students){
				String [] studentsClass = new String [5];
				studentsClass[0] = student.getName();
				studentsClass[1] = "";
				studentsClass[2] = "";
				studentsClass[3] = "";
				CPF studentCPF = student.getCpf();			
				studentsClass[4] = studentCPF.getCpf();
				
				tableModel.addRow(studentsClass);
			}
		} 
		catch (StudentClassException e) {
			showInfoMessage(e.getMessage());
		}		
	}

	/**
	 * Dispose the id and duration columns
	 * @param table - Receives the table to dispose columns
	 */
	private void disposeColumns(JTable tableOfStudents) {
		
		TableColumnModel tableModel = tableOfStudents.getColumnModel();
		
		tableModel.getColumn(4).setMinWidth(0);     
		tableModel.getColumn(4).setPreferredWidth(0);  
		tableModel.getColumn(4).setMaxWidth(0);    		
	}

	@Override
	public void createMasks(JFrame frame) {
		
	}

	@Override
	public void createButtons(JFrame frame) {
		super.createButtons(frame);
		actionBtn = new JButton("Fechar turma");
		frame.getContentPane().add(actionBtn);
		actionBtn.setBounds(679, 42, 117, 25);
		actionBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			
				
				ArrayList<String> studentCpfs = new ArrayList<String>();
				ArrayList<String> grades = new ArrayList<String>();
				ArrayList<String> absences = new ArrayList<String>();
				boolean dataOk = false;
				
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					
					boolean absenceNotEmpty = tableModel.getValueAt(i, 1).toString() != "";
					boolean gradeNotEmpty = tableModel.getValueAt(i, 2).toString() != "";
					
					if(absenceNotEmpty && gradeNotEmpty){
						
						absences.add(tableModel.getValueAt(i, 1).toString());
						grades.add(tableModel.getValueAt(i, 2).toString());
						studentCpfs.add(tableModel.getValueAt(i, 4).toString());
						
						dataOk = true;
					}
					else{
						showInfoMessage("Você deve preencher o número de faltas e a nota de todos os alunos");
						
						absences.clear();
						grades.clear();
						studentCpfs.clear();
						
						dataOk = false;
						break;
					}
				}
						
				if(dataOk){
					
					for (int i = 0; i < studentCpfs.size(); i++) {
						
						String studentCpf = studentCpfs.get(i);
						Integer grade = new Integer(grades.get(i));
						Integer absence = new Integer(absences.get(i));
						
						try {
							setStudentSituation(studentCpf, grade, absence);
						} 
						catch (StudentClassException | CPFException
								| PersonException e1) {
							showInfoMessage(e1.getMessage());
						}
					}
				}
				else{
					// Nothing to do
				}
			}
		});
		
		backBtn = new JButton("Voltar");
		frame.getContentPane().add(backBtn);
		backBtn.setBounds(798, 42, 117, 25);
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			
				dispose();	
			}
		});
	}

	private void setStudentSituation(String studentCpf, Integer grade, Integer absence) throws StudentClassException, CPFException, PersonException{
		
		StudentClassController studentClassController = new StudentClassController();
		studentClassController.setStudentSituation(studentCpf, grade, absence, enrolledClass);
	}
	
	
	private void closeClass(String enrolledClassId){
		
	}

}
