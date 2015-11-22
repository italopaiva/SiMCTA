package view.decorator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Course;
import model.ServiceItem;
import view.ServiceItemView;
import view.SearchCourse;
import view.SearchTeacher;
import view.PersonView;
import view.forms.ServiceItemForm;
import controller.CourseController;
import exception.CourseException;

public class ShowCourseDecorator extends ServiceItemDecorator {
	
	private JButton editTeacherBtn;
	private JButton backBtn;
	private JTextField durationField;
	private JTextField valueField;
	Course course;
	private JButton btnActiveOrDeactive;

	public ShowCourseDecorator(ServiceItemView viewToDecorate) {
		super(viewToDecorate);
	}
		
	@Override
	public void createLabelsAndFields(JFrame viewToDecorate, ServiceItem course) {
		this.frame = viewToDecorate;
		super.createLabelsAndFields(viewToDecorate, course);
		this.course = (Course) course;
		
        
        JLabel descriptionLabel = new JLabel("* Descrição do curso");
        descriptionLabel.setBounds(276, 284, 144, 15);
        frame.getContentPane().add(descriptionLabel);

		nameField.setBounds(276, 94, 346, 30);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblC = new JLabel(course.getName());
		lblC.setFont(new Font("Dialog", Font.BOLD, 20));
		lblC.setBounds(400, 12, 344, 25);
		frame.getContentPane().add(lblC);
		
		descriptionField.setBounds(276, 326, 446, 105);
		frame.getContentPane().add(descriptionField);
		
		durationField =  new JTextField();
		durationField.setBounds(276, 163, 132, 25);
		frame.getContentPane().add(durationField);
			
		valueField = new JTextField();
		valueField.setBounds(276, 244,124, 28);
		frame.getContentPane().add(valueField);
		
		fillTheFields(this.course);

	}
	
	private void fillTheFields(Course course) {
		
		nameField.setText(course.getName());
		descriptionField.setText(course.getCourseDescription());
		durationField.setText(course.getDuration().toString() + " semanas");
		
		Integer value = course.getValue();
		valueField.setText("R$ " + course.getFormattedValue(value));
		
		setNonEditableAllFields();
		
	}
	
	private void setNonEditableAllFields() {
		
		nameField.setEditable(false);
		descriptionField.setEditable(false);
		durationField.setEditable(false);
		valueField.setEditable(false);
		
	}

	@Override
	public void createButtons(JFrame frame) {
		editTeacherBtn = new JButton("Editar");
		frame.getContentPane().add(editTeacherBtn);
		editTeacherBtn.setBounds(379, 455, 117, 25);
		editTeacherBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			
				ServiceItemView courseFrame = new EditCourseDecorator(new ServiceItemForm());
				dispose();
				courseFrame.buildScreen(courseFrame,course);
				courseFrame.setVisible(true);
			}
		});
		
		backBtn = new JButton("Voltar");
		frame.getContentPane().add(backBtn);
		backBtn.setBounds(479, 535, 117, 25);
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			
				dispose();
				ServiceItemView courseFrame;
				courseFrame = new SearchCourse();
				courseFrame.buildScreen(courseFrame,null);
				courseFrame.setVisible(true);
		
			}
		});
		
		btnActiveOrDeactive = new JButton("New Button");
		btnActiveOrDeactive.setBounds(579, 455, 117, 25);
		frame.getContentPane().add(btnActiveOrDeactive);
		btnActiveOrDeactive.setText(showsActiveOrDeactive(course.getStatus()));
		
		btnActiveOrDeactive.addActionListener(new ActionListener() {
			
			private int courseStatus = course.getStatus();

			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = 0;				
				String action =  showsActiveOrDeactive(courseStatus);
				confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja " + action + " este curso?", "Atenção!!!", JOptionPane.YES_NO_OPTION);
				
				if (confirm == JOptionPane.YES_OPTION) {
					
					CourseController courseController = new CourseController();
					try {
						int courseID = course.getId();
						boolean wasChanged = courseController.alterStatusCourse(courseID);
						if(wasChanged){
							changeStatus();
							String currentStatus = showActiveOrInactive(courseStatus);
							showInfoMessage("Curso " + currentStatus + " com sucesso!");
							btnActiveOrDeactive.setText(showsActiveOrDeactive(courseStatus));
						}
						else{
							showInfoMessage("Não foi possível" + action + "o curso!");	
						}
					} 
					catch(CourseException e1){
						e1.printStackTrace();
					}	
				}
				
			}

			private void changeStatus() {
				if(courseStatus == SERVICE_ACTIVE){
					courseStatus = 0;
				}
				else{
					courseStatus = 1;
				}
				
			}

		});
	}

	private String showsActiveOrDeactive(int status){
		String statusToShow = null;
		
		switch(status){
			case 0:
				statusToShow = "Ativar";
				break;
			case 1:
				statusToShow = "Desativar";
		}
		
		return statusToShow;
	}
	
	private String showActiveOrInactive(int status){
		
		String statusToShow = null;
		
		switch(status){
			case 0:
				statusToShow = "Desativado";
				break;
			case 1:
				statusToShow = "Ativado";
		}
		
		return statusToShow;
	}
}
