package view.forms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import model.ServiceItem;
import view.ServiceItemView;
import java.awt.BorderLayout;
import java.awt.Font;

public class ServiceItemForm extends ServiceItemView {

	@Override
	public void createLabelsAndFields(JFrame frame, ServiceItem serviceItem) {
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel courseNameLabel = new JLabel("* Nome");
		courseNameLabel.setBounds(274, 65, 124, 17);
		frame.getContentPane().add(courseNameLabel);

        JLabel durationLabel = new JLabel("* Duração");
        durationLabel.setBounds(274, 136, 70, 15);
        frame.getContentPane().add(durationLabel);
        
        JLabel valueLabel = new JLabel("* Valor");
        valueLabel.setBounds(274, 217, 70, 15);
        frame.getContentPane().add(valueLabel);

		
	}

	@Override
	public void createMasks(JFrame frame) {
	}

	@Override
	public void createButtons(JFrame frame) {

	}

}
