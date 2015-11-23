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
import model.ServiceItem;
import view.ServiceItemView;
import view.SimCta;
import view.forms.ServiceItemForm;
import controller.CourseController;
import exception.CourseException;

@SuppressWarnings("serial")
public class NewCourseDecorator extends ServiceItemDecorator{
	

	public NewCourseDecorator(ServiceItemView viewToDecorate) {
		super(viewToDecorate);
	}
	
	@Override
	public void createLabelsAndFields(JFrame frame, ServiceItem course) {
		super.createLabelsAndFields(frame, course);
		nameField.setBounds(276, 94, 346, 30);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel requiredFieldsLbl = new JLabel("Os campos com * são obrigatórios");
		requiredFieldsLbl.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC,12));
		requiredFieldsLbl.setBounds(284, 40, 370, 17);
        frame.getContentPane().add(requiredFieldsLbl);
		        
        JLabel descriptionLabel = new JLabel("* Descrição do curso");
        descriptionLabel.setBounds(276, 284, 144, 15);
        frame.getContentPane().add(descriptionLabel);
		
		JLabel lblC = new JLabel("Novo curso");
		lblC.setFont(new Font("Dialog", Font.BOLD, 20));
		lblC.setBounds(426, 15, 144, 15);
		frame.getContentPane().add(lblC);
		
		descriptionField.setBounds(276, 326, 446, 105);
		frame.getContentPane().add(descriptionField);
	}

	@Override
	public void createMasks(JFrame frame) {
		super.createMasks(frame);		
		try{
			
			MaskFormatter durationMask = new MaskFormatter("## semanas");
			durationMask.setValidCharacters("0123456789");
			durationMask.setPlaceholder("00");
			durationMask.setValueContainsLiteralCharacters(false);
			
			durationField = new JFormattedTextField(durationMask);
			durationField.setBounds(276, 163, 132, 25);
			durationField.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT); 
			frame.getContentPane().add(durationField);
			
			MaskFormatter valueMask = new MaskFormatter("R$ ####,##");
			valueMask.setValidCharacters("0123456789");
			valueMask.setValueContainsLiteralCharacters(false);
			
			valueField = new JFormattedTextField(valueMask);
			valueField.setBounds(276, 244,124, 28);
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
				
				try{
											
					CourseController courseController = new CourseController();
					
					Course course = courseController.newCourse(
						courseName,
						courseDescription,
						courseDuration,
						courseValue
					);
					
					String message = "";
					if(course != null){
						message = "Curso cadastrado com sucesso.";

						dispose();
						
						ServiceItemView showCourseFrame = new ShowCourseDecorator(new ServiceItemForm());
						showCourseFrame.buildScreen(showCourseFrame, course);
						showCourseFrame.setVisible(true);
					}
					else{
						message = "Não foi possível cadastrar o curso informado. Tente novamente.";
					}
					showInfoMessage(message);

					
				}catch(CourseException caughtException){
					
					showInfoMessage(caughtException.getMessage());
				}
			}
		});
		registerCourseButton.setBounds(456, 462, 114, 25);
		frame.getContentPane().add(registerCourseButton);
	}

}
