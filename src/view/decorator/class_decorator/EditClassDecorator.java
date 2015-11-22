package view.decorator.class_decorator;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ClassController;
import controller.TeacherController;
import datatype.CPF;
import datatype.Date;
import exception.ClassException;
import exception.DateException;
import exception.TeacherException;
import model.Class;
import model.Teacher;
import view.ClassView;
import view.decorator.ShowTeacherDecorator;

public class EditClassDecorator extends ClassDecorator {

	private static final String CLASS_WAS_UPDATED = "Turma alterada com sucesso!"; 

	private Class classInstance;
	
	public EditClassDecorator(ClassView viewToDecorate) {
		super(viewToDecorate);
		
		teachersMap = new HashMap<String, CPF>();
	}
	
	@Override
	public void createLabelsAndFields(JFrame frame, Class classInstance){
		
		this.classInstance = classInstance;
		
		super.createLabelsAndFields(frame, classInstance);
		
		fillTeachersDropdown();
		
		titleLbl = new JLabel("Alterar dados da turma");
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 18));
		titleLbl.setBounds(410, 0, 246, 30);
		frame.getContentPane().add(titleLbl);
		
		availableTeachers = new JComboBox<String>(availableTeachersModel);
		availableTeachers.setBounds(286, 188, 250, 24);
		frame.getContentPane().add(availableTeachers);
		availableTeachers.setEditable(true);		
		
		availableCoursesModel = new DefaultComboBoxModel<String>();
		availableCoursesModel.addElement(classInstance.getCourse().getName());
		
		availableCourses = new JComboBox<String>(availableCoursesModel);
		availableCourses.setBounds(286, 122, 250, 24);
		frame.getContentPane().add(availableCourses);
		availableCourses.setEnabled(false);
		
		fillShiftsDropdown();
		
		shifts = new JComboBox<String>(shiftsModel);
		shifts.setBounds(600, 122, 121, 24);
		frame.getContentPane().add(shifts);
		
		classIdField = new JTextField();
		classIdField.setBounds(286, 65, 250, 24);
		frame.getContentPane().add(classIdField);
		classIdField.setColumns(10);
		classIdField.setText(classInstance.getClassId());
		classIdField.setEditable(false);
		classIdField.setEnabled(false);
	}
	
	private void fillShiftsDropdown(){
		
		shiftsModel = new DefaultComboBoxModel<String>();
		
		shiftsModel.addElement(Class.MORNING_SHIFT);
		shiftsModel.addElement(Class.AFTERNOON_SHIFT);
		shiftsModel.addElement(Class.NIGHT_SHIFT);
	}

	private void fillTeachersDropdown(){
		
		TeacherController teacherController = new TeacherController();
		
		availableTeachersModel = new DefaultComboBoxModel<String>();
				
		try{
			ArrayList<Teacher> teachers = teacherController.getTeachers();
			
			for(Teacher teacher : teachers){
				availableTeachersModel.addElement(teacher.getName());
				teachersMap.put(teacher.getName(), teacher.getCpf());
			}
		}
		catch(TeacherException e){
			showInfoMessage(COULDNT_LOAD_TEACHERS);
			availableTeachersModel.addElement(classInstance.getTeacher().getName());
		}
	}

	@Override
	public void createMasks(JFrame frame){
		super.createMasks(frame);
		
		// Mask for birth date
		MaskFormatter startDateMask;
		
		try{
			startDateMask = new MaskFormatter("##/##/####");
			startDateMask.setValidCharacters("0123456789");
			startDateMask.setValueContainsLiteralCharacters(true);
						
			startDateField = new JFormattedTextField(startDateMask);
			startDateField.setBounds(600, 189, 150, 24);
			frame.getContentPane().add(startDateField);
			startDateField.setColumns(10);
			startDateField.setText(classInstance.getStartDate().getSlashFormattedDate());
		}
		catch(ParseException e){
			e.printStackTrace();
		}
	}

	@Override
	public void createButtons(JFrame frame){
		
		super.createButtons(frame);
		
		actionBtn = new JButton("Alterar Turma");
		actionBtn.setBounds(415, 237, 140, 30);
		frame.getContentPane().add(actionBtn);
		actionBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e){			
				
				String message = "";
				
				try{
					
					String shift = (String) shifts.getSelectedItem();
					String selectedTeacher = (String) availableTeachers.getSelectedItem();
					CPF teacherCpf = teachersMap.get(selectedTeacher);
					
					String givenStartDate = startDateField.getText();
					
					Date startDate;
					
					startDate = new Date(givenStartDate);
					
					ClassController classController = new ClassController();
					classController.updateClass(classInstance.getClassId(), teacherCpf, shift, startDate);
					
					message = CLASS_WAS_UPDATED;
				}
				catch(DateException e1){
					message = e1.getMessage();
				}catch(ClassException e1){
					message = e1.getMessage();
				}finally{
					showInfoMessage(message);
				}
			}
		});
	}
}
