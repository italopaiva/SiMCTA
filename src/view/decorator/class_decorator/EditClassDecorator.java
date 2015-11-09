package view.decorator.class_decorator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ClassController;
import model.Class;
import view.ClassView;
import view.TeacherForm;
import view.decorator.ShowTeacherDecorator;

public class EditClassDecorator extends ClassDecorator {

	private Class classInstance;
	
	public EditClassDecorator(ClassView viewToDecorate) {
		super(viewToDecorate);
	}
	
	@Override
	public void createLabelsAndFields(JFrame frame, Class classInstance){
		
		this.classInstance = classInstance;
		
		super.createLabelsAndFields(frame, classInstance);
		
		availableTeachersModel = new DefaultComboBoxModel<String>();
		// Get from database all the teachers
		availableTeachersModel.addElement(classInstance.getTeacher().getName());
		
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
				
		shiftsModel = new DefaultComboBoxModel<String>();
		shiftsModel.addElement(Class.MORNING_SHIFT);
		shiftsModel.addElement(Class.AFTERNOON_SHIFT);
		shiftsModel.addElement(Class.NIGHT_SHIFT);
		
		shifts = new JComboBox<String>(shiftsModel);
		shifts.setBounds(600, 122, 121, 24);
		frame.getContentPane().add(shifts);
		
		classIdField = new JTextField();
		classIdField.setBounds(286, 65, 250, 24);
		frame.getContentPane().add(classIdField);
		classIdField.setColumns(10);
		classIdField.setText(classInstance.getClassId());
		classIdField.setEditable(false);
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
				
				ClassController classController = new ClassController();
				
				classController.updateClass(classInstance.getClassId(), teacherCpf, shift, startDate);
			}
		});
	}
}
