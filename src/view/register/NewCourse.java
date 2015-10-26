package view.register;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import view.SimCta;
import view.View;
import controller.CourseController;
import exception.CourseException;

import java.awt.Color;

@SuppressWarnings("serial")
public class NewCourse extends View {

	private JPanel contentPane;
	private JTextField courseNameField;
	private JFormattedTextField durationField;
	private JFormattedTextField valueField;
	private JTextArea descriptionField;
	
	/**
	 * Create the frame.
	 */
	public NewCourse() {
		
		super();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		courseNameField = new JTextField();
		courseNameField.setBounds(276, 74, 346, 30);
		contentPane.add(courseNameField);
		courseNameField.setColumns(10);
		
		JLabel courseNameLabel = new JLabel("Nome do curso");
		courseNameLabel.setBounds(284, 45, 124, 17);
		contentPane.add(courseNameLabel);
		
		JLabel lblC = new JLabel("Novo curso");
		lblC.setFont(new Font("Dialog", Font.BOLD, 20));
		lblC.setBounds(426, 12, 144, 15);
		contentPane.add(lblC);
		
		JLabel durationLabel = new JLabel("Duração");
		durationLabel.setBounds(286, 116, 70, 15);
		contentPane.add(durationLabel);
		
		JLabel valueLabel = new JLabel("Valor");
		valueLabel.setBounds(286, 197, 70, 15);
		contentPane.add(valueLabel);
		
		JLabel descriptionLabel = new JLabel("Descrição do curso");
		descriptionLabel.setBounds(284, 264, 144, 15);
		contentPane.add(descriptionLabel);
		
		try{
			
			MaskFormatter durationMask = new MaskFormatter("## semanas");
			durationMask.setValidCharacters("0123456789");
			durationMask.setValueContainsLiteralCharacters(false);
			
			durationField = new JFormattedTextField(durationMask);
			durationField.setBounds(276, 143, 132, 25);
			contentPane.add(durationField);
			
			MaskFormatter valueMask = new MaskFormatter("R$ ####,##");
			valueMask.setValidCharacters("0123456789");
			valueMask.setValueContainsLiteralCharacters(false);
			
			valueField = new JFormattedTextField(valueMask);
			valueField.setBounds(284, 224, 124, 28);
			contentPane.add(valueField);
			
			descriptionField = new JTextArea();
			descriptionField.setBounds(284, 306, 446, 105);
			contentPane.add(descriptionField);
			
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
			contentPane.add(registerCourseButton);
			
		}catch (ParseException e){
			e.printStackTrace();
		}
	}
}
