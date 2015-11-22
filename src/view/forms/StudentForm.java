package view.forms;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Person;

public class StudentForm extends PersonForm {
	public StudentForm() {
	}
	
	protected JLabel paymentTypeLbl;
	protected JComboBox<String> paymentTypes;
	protected JComboBox<String> packages;
	protected JLabel firstListLabel;
	protected DefaultComboBoxModel<String> availableCourses;
	protected DefaultComboBoxModel<String> availablePackages;

	@Override
	public void createLabelsAndFields(JFrame frame, Person teacher) {
		super.createLabelsAndFields(frame, teacher);
	
		JLabel coursesLabel = new JLabel("*Cursos");
		coursesLabel.setBounds(581, 73, 70, 17);
		 frame.getContentPane().add(coursesLabel);
		
		JLabel packagesLabel = new JLabel("*Pacotes");
        packagesLabel.setBounds(581, 348, 70, 17);
        frame.getContentPane().add(packagesLabel);
        
        JLabel dataOfPaymentLbl = new JLabel("DADOS DO PAGAMENTO");
        dataOfPaymentLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        dataOfPaymentLbl.setBounds(219, 443, 200, 17);
        frame.getContentPane().add(dataOfPaymentLbl);

	}

	@Override
	public void createMasks(JFrame frame) {
		
		
	}

	@Override
	public void createButtons(JFrame frame) {


	}
	
}
