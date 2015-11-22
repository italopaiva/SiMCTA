package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Person;
import model.Student;
import model.Teacher;
import util.ButtonColumn;
import view.decorator.ShowTeacherDecorator;
import view.decorator.PersonDecorator;
import view.forms.TeacherForm;
import controller.StudentController;
import controller.TeacherController;
import datatype.CPF;
import exception.CPFException;
import exception.PersonException;
import exception.TeacherException;

public class SearchTeacher extends PersonView {

	private JButton searchTeacherBtn;
	private JTextField searchedTeacherField;
	private JInternalFrame internalFrame;
	private JTable tableOfTeachers;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private TeacherController teacherController;

	@Override
	public void createLabelsAndFields(JFrame viewToDecorate, Person teacher) {
		this.frame = viewToDecorate;
		try {
			addFields();
			createButtons(frame);
		} 
		catch(TeacherException e){

		}
	}

	public void addFields() throws TeacherException{
		
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
	
        searchedTeacherField = new JTextField();
        searchedTeacherField.setBounds(227, 56, 446, 29);
		add(searchedTeacherField);
		searchedTeacherField.setColumns(10);
		
		internalFrame = new JInternalFrame();
		internalFrame.setBounds(113, 64, 882, 618);
		add(internalFrame);
		
		internalFrame.setVisible(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 141, 557, 317);
		add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		
		String [] columns = { "Professor", "Ação", "CPF"};
		
		tableModel = new DefaultTableModel(null, columns){
			// Overriding the method to set non editable the name and cpf columns
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
		tableOfTeachers = new JTable(tableModel);
		
		fillTableWithAllTeachers();
		
		tableOfTeachers.removeColumn(tableOfTeachers.getColumnModel().getColumn(2));
		tableOfTeachers.setBackground(Color.WHITE);
		
		Action visualizeTeacher = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				JTable table = (JTable)e.getSource();
				int selectedRow = table.getSelectedRow();
				
				String cpfSelectedTeacher  = table.getModel().getValueAt(selectedRow,2).toString();
				try {
					CPF selectedTeacher = new CPF(cpfSelectedTeacher);
					Teacher teacher = teacherController.getTeacher(selectedTeacher);
					dispose();
					PersonView teacherFrame = new ShowTeacherDecorator(new TeacherForm());
					teacherFrame.buildScreen(teacherFrame, teacher);
					teacherFrame.setVisible(true);
				} 
				catch (CPFException | TeacherException | PersonException e1) {
					showInfoMessage(e1.getMessage());
				}
				
			}

		};
		
		ButtonColumn buttonColumn2 = new ButtonColumn(tableOfTeachers, visualizeTeacher, 1);
		
		((JScrollPane) scrollPane).setViewportView(tableOfTeachers);

	}

	private void fillTableWithAllTeachers() throws TeacherException {
		
		teacherController = new TeacherController();
		ArrayList<Teacher> teachers;
		try {
			teachers = teacherController.getTeachers();
			int indexOfTeachers = 0;
			if(teachers != null){
				while(indexOfTeachers < teachers.size()){
					
					String[] allTeachers = new String[3];
					Teacher teacher = teachers.get(indexOfTeachers);
					
					CPF teacherCpf = teacher.getCpf();
					allTeachers[0] = (teacher.getName());
					allTeachers[1] = ("Ver");
					allTeachers[2] = (teacherCpf.getCpf());
					
					tableModel.addRow(allTeachers);
					
					indexOfTeachers++;
				}
			}
			else{
				showInfoMessage("Nenhum professor cadastrado ainda");
			}
		} 
		catch (TeacherException e) {
			showInfoMessage(e.getMessage());
		}	
		
	}

	@Override
	public void createMasks(JFrame frame) {
		
		
	}
	
	@Override
	public void createButtons(JFrame frame) {
		searchTeacherBtn = new JButton("Pesquisar");
		add(searchTeacherBtn);
		searchTeacherBtn.setBounds(675, 56, 117, 29);
		searchTeacherBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			
				
				String searchedTeacher = searchedTeacherField.getText();
				
				teacherController = new TeacherController();
				tableModel.setRowCount(0);
				ArrayList<Teacher> teachers;
				try {
					teachers = teacherController.getTeachers(searchedTeacher);
					int arrayIndex = 0;
					
					if(!teachers.isEmpty()){
						while(arrayIndex < teachers.size()){
							String[] teacher = new String[3];
							teacher[0] = teachers.get(arrayIndex).getName();
							teacher[1] = ("Ver");
							CPF cpf = teachers.get(arrayIndex).getCpf();
							teacher[2] = cpf.getCpf();
							tableModel.addRow(teacher);
							arrayIndex++;
						}
					}
					else{
						showInfoMessage("Nenhum professor com esse nome foi encontrado");
					}
				} 
				catch(TeacherException | PersonException e1){

				}
				
				
			}
		});
	}

}
