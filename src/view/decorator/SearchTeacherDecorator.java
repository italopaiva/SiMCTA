package view.decorator;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Teacher;
import model.datatype.CPF;
import util.ButtonColumn;
import view.TeacherForm;
import view.TeacherView;
import controller.TeacherController;
import exception.CPFException;
import exception.PersonException;
import exception.TeacherException;

public class SearchTeacherDecorator extends TeacherDecorator {

	private JButton searchTeacherBtn;
	private JTextField searchedTeacherField;
	private JInternalFrame internalFrame;
	private JTable tableOfTeachers;
	private JScrollPane scrollPane;
	private JButton backButton;
	private DefaultTableModel tableModel;
	private TeacherController teacherController;

	public SearchTeacherDecorator(TeacherView viewToDecorate) {
		super(viewToDecorate);
	}

	@Override
	public void createLabelsAndFields(JFrame viewToDecorate, int fieldStatus) {
		this.frame = viewToDecorate;
		try {
			addFields();
			createButtons(frame);
		} 
		catch(TeacherException e){
			
		}
	}

	public void addFields() throws TeacherException{
		
        searchedTeacherField = new JTextField();
		searchedTeacherField.setBounds(141, 24, 446, 30);
		frame.getContentPane().add(searchedTeacherField);
		searchedTeacherField.setColumns(10);
		
		internalFrame = new JInternalFrame();
		internalFrame.setBounds(113, 64, 882, 618);
		frame.getContentPane().add(internalFrame);
		
		internalFrame.setVisible(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 141, 557, 317);
		frame.getContentPane().add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		
		String [] columns = { "Professor", "Ação", "CPF"};
		
		tableModel = new DefaultTableModel(null, columns);
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
					TeacherView teacherFrame = new ShowTeacherDecorator(new TeacherForm());
					teacherFrame.buildScreen(teacherFrame, NON_EDITABLE_FIELDS);
					teacherFrame.setVisible(true);
				} 
				catch (CPFException | TeacherException | PersonException e1) {
					
				}
				
			}

		};
		
		ButtonColumn buttonColumn2 = new ButtonColumn(tableOfTeachers, visualizeTeacher, 1);
		
		((JScrollPane) scrollPane).setViewportView(tableOfTeachers);
		
		backButton = new JButton("Voltar");
		backButton.setBounds(727, 26, 117, 25);
		frame.getContentPane().add(backButton);
		backButton.setVisible(false);
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
			throw new TeacherException(e.getMessage());
		}	
		
	}

	@Override
	public void createMasks(JFrame frame, int fieldStatus) {
		
		
	}
	
	@Override
	public void createButtons(JFrame frame) {
		searchTeacherBtn = new JButton("Pesquisar");
		frame.getContentPane().add(searchTeacherBtn);
		searchTeacherBtn.setBounds(599, 26, 117, 25);
		searchTeacherBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			

			}
		});
	}

}
