package view;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.Course;
import model.Teacher;

public class CourseForm extends CourseView {

	@Override
	public void createLabelsAndFields(JFrame frame, Course course) {
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel courseNameLabel = new JLabel("Nome do curso");
		courseNameLabel.setBounds(284, 45, 124, 17);
		frame.getContentPane().add(courseNameLabel);

        JLabel durationLabel = new JLabel("Duração");
        durationLabel.setBounds(286, 116, 70, 15);
        frame.getContentPane().add(durationLabel);
        
        JLabel valueLabel = new JLabel("Valor");
        valueLabel.setBounds(286, 197, 70, 15);
        frame.getContentPane().add(valueLabel);
        
        JLabel descriptionLabel = new JLabel("Descrição do curso");
        descriptionLabel.setBounds(284, 264, 144, 15);
        frame.getContentPane().add(descriptionLabel);
		
	}

	@Override
	public void createMasks(JFrame frame) {
	}

	@Override
	public void createButtons(JFrame frame) {

	}

}
