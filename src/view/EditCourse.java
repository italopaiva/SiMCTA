package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.CourseController;
import exception.CourseException;

@SuppressWarnings("serial")
public class EditCourse extends View{
	
	private String courseName;
	private String courseDescription;
	private Integer courseDuration;
	private Integer courseValue;
	private Integer courseId;
	
	private JPanel contentPane;
	private JTextField courseNameField;
	private JFormattedTextField durationField;
	private JFormattedTextField valueField;
	private JTextArea descriptionField;

	/**
	 * Create the frame.
	 */
	public EditCourse(Integer courseId, String courseName, String description, Integer courseDuration, Integer courseValue){
		
		super();
		
		setCourseId(courseId);
		setCourseName(courseName);
		setCourseDescription(description);
		setCourseDuration(courseDuration);
		setCourseValue(courseValue);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		courseNameField = new JTextField();
		courseNameField.setBounds(276, 74, 346, 30);
		contentPane.add(courseNameField);
		courseNameField.setColumns(10);
		courseNameField.setText(this.courseName);
		courseNameField.setEditable(false);
		
		JLabel courseNameLabel = new JLabel("Nome do curso");
		courseNameLabel.setBounds(284, 45, 124, 17);
		contentPane.add(courseNameLabel);
		
		JLabel lblC = new JLabel("Alterar dados do curso");
		lblC.setFont(new Font("Dialog", Font.BOLD, 20));
		lblC.setBounds(348, 12, 298, 15);
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
			
			MaskFormatter durationMask = new MaskFormatter("##");
			durationMask.setValidCharacters("0123456789");
			durationMask.setValueContainsLiteralCharacters(false);
			
			durationField = new JFormattedTextField(durationMask);
			durationField.setBounds(276, 143, 132, 25);
			contentPane.add(durationField);
			durationField.setValue(this.courseDuration);
			
			MaskFormatter valueMask = new MaskFormatter("R$ ####,##");
			valueMask.setValidCharacters("0123456789");
			valueMask.setValueContainsLiteralCharacters(false);
			
			String value = this.courseValue.toString();
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
			contentPane.add(valueField);
			valueField.setValue(value);
			
			descriptionField = new JTextArea();
			descriptionField.setBounds(284, 306, 446, 105);
			contentPane.add(descriptionField);
			descriptionField.setText(this.courseDescription);
			
			JButton registerCourseButton = new JButton("Alterar");
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
						
						boolean courseWasUpdated = courseController.updateCourse(
							EditCourse.this.getCourseId(),
							courseName,
							courseDescription,
							courseDuration,
							courseValue
						);
						
						String message = "";
						if(courseWasUpdated){
							message = "Curso alterado com sucesso.";
						}
						else{
							message = "Não foi possível aletar o curso informado. Tente novamente.";
						}
						
						showInfoMessage(message);
						
						dispose();
						
						SearchCourse searchCourse = new SearchCourse();
						searchCourse.setVisible(true);
						
					}catch(CourseException caughtException){
						
						showInfoMessage(caughtException.getMessage());
					}catch(SQLException caughtException){
						
						caughtException.printStackTrace();
					}
				}
			});
			registerCourseButton.setBounds(363, 455, 114, 25);
			contentPane.add(registerCourseButton);
			
			JButton backBtn = new JButton("Voltar");
			backBtn.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent arg0){
					
					SearchCourse searchCourseFrame;
					try {
						
						searchCourseFrame = new SearchCourse();
						searchCourseFrame.setVisible(true);
					}
					catch(SQLException e){
						
						e.printStackTrace();
					}
					catch(CourseException e){

					}
				}
			});
			backBtn.setBounds(524, 455, 117, 25);
			contentPane.add(backBtn);
			
		}catch (ParseException e){
			e.printStackTrace();
		}
	}

	private void setCourseName(String courseName){
		this.courseName = courseName;
	}

	private void setCourseDescription(String courseDescription){
		this.courseDescription = courseDescription;
	}

	private void setCourseDuration(Integer courseDuration){
		this.courseDuration = courseDuration;
	}

	private void setCourseValue(Integer courseValue){
		this.courseValue = courseValue;
	}

	private Integer getCourseId(){
		return courseId;
	}

	private void setCourseId(Integer courseId){
		this.courseId = courseId;
	}
}
