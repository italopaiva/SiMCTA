package view.decorator;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.Course;
import model.ServiceItem;
import view.SearchCourse;
import view.ServiceItemView;
import view.View;
import view.forms.ServiceItemForm;
import controller.CourseController;
import exception.CourseException;

@SuppressWarnings("serial")
public class EditCourseDecorator extends ServiceItemDecorator{

	private Component editCourseBtn;
	private JButton backBtn;
	private Course course;

	/**
	 * Create the frame.
	 * @param courseForm 
	 */
	public EditCourseDecorator(ServiceItemView viewToDecorate) {
		super(viewToDecorate);
	}
	
	@Override
	public void createLabelsAndFields(JFrame viewToDecorate, ServiceItem course) {
		this.frame = viewToDecorate;
		super.createLabelsAndFields(viewToDecorate, course);
		this.course = (Course) course;

		nameField.setBounds(276, 74, 346, 30);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblC = new JLabel("Alterar dados do curso: " + course.getName());
		lblC.setFont(new Font("Dialog", Font.BOLD, 20));
		lblC.setBounds(326, 15, 344, 15);
		frame.getContentPane().add(lblC);
		
		descriptionField.setBounds(284, 306, 446, 105);
		frame.getContentPane().add(descriptionField);
	
			
		fillTheFields(this.course);

	}
	
	private void fillTheFields(Course course) {
		
		nameField.setText(course.getName());
		descriptionField.setText(course.getCourseDescription());
		setEditableAllFields();
		
	}
	
	private void setEditableAllFields() {
		
		nameField.setEditable(true);
		descriptionField.setEditable(true);
		durationField.setEditable(true);
		valueField.setEditable(true);
		
	}

	@Override
	public void createButtons(JFrame frame) {
		editCourseBtn = new JButton("Alterar");
		frame.getContentPane().add(editCourseBtn);
		editCourseBtn.setBounds(379, 455, 117, 25);
		editCourseBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e){			
				String courseName = nameField.getText();
				String courseDescription = descriptionField.getText();
				
				Integer courseDuration;
				Object courseDurationField = durationField.getValue(); 
				if(!(courseDurationField == null)){
					
					String duration = courseDurationField.toString();
					courseDuration = new Integer(duration);
				}else{
					courseDuration = new Integer(0);
				}
				
				Integer courseValue;
				Object courseValueField = valueField.getValue(); 
				if(!(courseValueField == null)){
					
					String value = courseValueField.toString();
					courseValue = new Integer(value);
				}else{
					courseValue = new Integer(0);
				}
			
				CourseController courseController = new CourseController();
				String message = "";
				boolean courseWasUpdated;
				try {
					courseWasUpdated = courseController.updateCourse(
						course.getId(),
						courseName,
						courseDescription,
						courseDuration,
						courseValue
					);
					if(courseWasUpdated){
						message = "Curso alterado com sucesso.";
					}
					else{
						message = "Não foi possível aletar o curso informado. Tente novamente.";
					}
				} 
				catch(CourseException e1){
					message = e1.getMessage();
				}
				showInfoMessage(message);
				dispose();
				ServiceItemView courseFrame = new SearchCourse();
				courseFrame.buildScreen(courseFrame, course);
				courseFrame.setVisible(true);	
			}
		});
		
		backBtn = new JButton("Voltar");
		frame.getContentPane().add(backBtn);
		backBtn.setBounds(579, 455, 117, 25);
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			
				dispose();
				ServiceItemView courseFrame = new ShowCourseDecorator(new ServiceItemForm());
				courseFrame.buildScreen(courseFrame, course);
				courseFrame.setVisible(true);
			}
		});
	}
	
	@Override
	public void createMasks(JFrame frame){
	
		try{
		
			MaskFormatter durationMask = new MaskFormatter("##");
			durationMask.setValidCharacters("0123456789");
			durationMask.setValueContainsLiteralCharacters(false);
			
			durationField = new JFormattedTextField(durationMask);
			durationField.setBounds(276, 143, 132, 25);
			frame.getContentPane().add(durationField);
			durationField.setValue(course.getDuration());
			
			MaskFormatter valueMask = new MaskFormatter("R$ ####,##");
			valueMask.setValidCharacters("0123456789");
			valueMask.setValueContainsLiteralCharacters(false);
			
			String value = course.getValue().toString();
			int length = value.length();
			switch(length){
				case 1:
					value = "00000" + value;
					break;
				case 2:
					value = "0000" + value;
					break;
				case 3:
					value = "000" + value;
					break;
				case 4:
					value = "00" + value;
					break;
				case 5:
					value = "0" + value;
					break;
			}
			
			valueField = new JFormattedTextField(valueMask);
			valueField.setBounds(284, 224, 124, 28);
			frame.getContentPane().add(valueField);
			valueField.setValue(value);
	
		}
		catch (ParseException e){
			e.printStackTrace();
		}
	}

}
