package view.decorator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import model.Course;
import view.CourseView;
import view.SimCta;
import controller.CourseController;
import exception.CourseException;

@SuppressWarnings("serial")
public class NewCourseDecorator extends CourseDecorator{
	

	public NewCourseDecorator(CourseView viewToDecorate) {
		super(viewToDecorate);
	}
	
	@Override
	public void createLabelsAndFields(JFrame frame, Course course) {
		super.createLabelsAndFields(frame, course);
		courseNameField.setBounds(276, 74, 346, 30);
		frame.getContentPane().add(courseNameField);
		courseNameField.setColumns(10);

		
		JLabel lblC = new JLabel("Novo curso");
		lblC.setFont(new Font("Dialog", Font.BOLD, 20));
		lblC.setBounds(426, 15, 144, 15);
		frame.getContentPane().add(lblC);
		
		descriptionField.setBounds(284, 306, 446, 105);
		frame.getContentPane().add(descriptionField);
	}

	@Override
	public void createMasks(JFrame frame) {
		super.createMasks(frame);		
		try{
			
			MaskFormatter durationMask = new MaskFormatter("## semanas");
			durationMask.setValidCharacters("0123456789");
			durationMask.setValueContainsLiteralCharacters(false);
			
			durationField = new JFormattedTextField(durationMask);
			durationField.setBounds(276, 143, 132, 25);
			frame.getContentPane().add(durationField);
			
			MaskFormatter valueMask = new MaskFormatter("R$ ####,##");
			valueMask.setValidCharacters("0123456789");
			valueMask.setValueContainsLiteralCharacters(false);
			
			valueField = new JFormattedTextField(valueMask);
			valueField.setBounds(284, 224,124, 28);
			frame.getContentPane().add(valueField);
			
		}catch (ParseException e){
			e.printStackTrace();
		}
	}

	@Override
	public void createButtons(JFrame frame) {
		super.createButtons(frame);		
		
		JButton registerCourseButton = new JButton("Cadastrar");
		registerCourseButton.setBackground(Color.WHITE);
		registerCourseButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				String courseName = courseNameField.getText();
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
				
				try{
											
					CourseController courseController = new CourseController();
					
					boolean courseWasSaved = courseController.newCourse(
						courseName,
						courseDescription,
						courseDuration,
						courseValue
					);
					
					String message = "";
					if(courseWasSaved){
						message = "Curso cadastrado com sucesso.";
					}else{
						message = "Não foi possível cadastrar o curso informado. Tente novamente.";
					}
					
					showInfoMessage(message);
					
					dispose();
					
					SimCta mainPage = new SimCta();
					mainPage.setVisible(true);
					
				}catch(CourseException caughtException){
					
					showInfoMessage(caughtException.getMessage());
				}
			}
		});
		registerCourseButton.setBounds(456, 462, 114, 25);
		frame.getContentPane().add(registerCourseButton);
	}

}
