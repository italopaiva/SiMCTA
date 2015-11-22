package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import model.Class;
import model.Student;
import model.StudentClass;
import view.decorator.class_decorator.ClassDecorator;
import controller.ClassController;
import controller.StudentClassController;
import datatype.CPF;
import exception.CPFException;
import exception.ClassException;
import exception.PersonException;
import exception.StudentClassException;

public class CloseClass extends ClassDecorator{
		
	private Class enrolledClass;
	private DefaultTableModel tableModel;
	private JTable tableOfStudents;
	private JButton backBtn;
	private MaskFormatter gradeMask;
	private JFormattedTextField gradeField;
	private MaskFormatter absenceMask;
	private JFormattedTextField absenceField;

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
		
		tableModel = new DefaultTableModel(null, columns){
			// Overriding the method to set non editable the name, situation and CPF columns
			@Override
			public boolean isCellEditable(int row, int column) {
				
				boolean isEditable = false;
				
				if(column == 0 || column == 3 || column == 4){
					isEditable = false;
				}
				else{
					isEditable = true;
				}
				
				return isEditable;
				
			};
		};

		tableOfStudents = new JTable(tableModel);
		tableOfStudents.setRowHeight(30);

		disposeColumns(tableOfStudents);
		tableOfStudents.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableOfStudents);
		
		this.enrolledClass = enrolledClass;
		
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
				studentsClass[1] = absenceField.getText();
				studentsClass[2] = gradeField.getText();
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
		  
	    try {
			gradeMask = new MaskFormatter("##.#");
			gradeMask.setValidCharacters("0123456789");
			gradeMask.setValueContainsLiteralCharacters(true);
		    gradeMask.setPlaceholder("00.0");

		    gradeField = new JFormattedTextField(gradeMask);

		    TableColumn gradeCollumn = tableOfStudents.getColumnModel().getColumn(2);  
		    gradeCollumn.setCellEditor(new DefaultCellEditor(gradeField));
		 
			absenceMask = new MaskFormatter("##");
			absenceMask.setValidCharacters("0123456789");
			absenceMask.setPlaceholder("00");

			absenceField = new JFormattedTextField(absenceMask); 
		    absenceField.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT); 
  
		    TableColumn absenceCollumn = tableOfStudents.getColumnModel().getColumn(1);  
		    absenceCollumn.setCellEditor(new DefaultCellEditor(absenceField));
	    
			getAllStudentsClass();
	    } 
	    catch (ParseException e) {
		
		}  
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
				
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					
					String studentCpf = tableModel.getValueAt(i, 4).toString();
					String grade = tableModel.getValueAt(i, 2).toString();
					String absence = tableModel.getValueAt(i, 1).toString();
					
					try {
						StudentClass studentClass = setStudentSituation(studentCpf, grade, absence);
						tableModel.setValueAt(studentClass.getStudentSituation(), i, 3);
						showInfoMessage("Notas e faltas inseridas com sucesso");
						
						int confirm = 0;				
						confirm = JOptionPane.showConfirmDialog(tableOfStudents, "Deseja realmente fechar a turma?", "Fechar turma", JOptionPane.YES_NO_OPTION);
						
						if (confirm == JOptionPane.YES_OPTION) {
							closeClass(enrolledClass);
						}
						else{
							// Voltar para consulta
						}

					} 
					catch (StudentClassException | CPFException
							| PersonException e1) {
						showInfoMessage(e1.getMessage());
					}
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

	private StudentClass setStudentSituation(String studentCpf, String grade, String absence) throws StudentClassException, CPFException, PersonException{
		
		StudentClassController studentClassController = new StudentClassController();
		StudentClass studentClass = studentClassController.setStudentSituation(studentCpf, grade, absence, enrolledClass);
		
		return studentClass;
	}
	
	
	private void closeClass(Class enrolledClass){

		ClassController classController = new ClassController();
		
		try {
			classController.closeClass(enrolledClass);
			showInfoMessage("Turma fechada com sucesso");
			actionBtn.setVisible(false);
		} 
		catch (ClassException e) {
			showInfoMessage(e.getMessage());
		}

	}

}
