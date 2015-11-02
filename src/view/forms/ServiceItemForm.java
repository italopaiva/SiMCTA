package view.forms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import model.ServiceItem;
import view.ServiceItemView;

public class ServiceItemForm extends ServiceItemView {

	@Override
	public void createLabelsAndFields(JFrame frame, ServiceItem serviceItem) {
		
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

		
	}

	@Override
	public void createMasks(JFrame frame) {
	}

	@Override
	public void createButtons(JFrame frame) {

	}

}
