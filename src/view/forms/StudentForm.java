package view.forms;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Person;

public class StudentForm extends PersonForm {
	
	protected JLabel paymentTypeLbl;
	protected JComboBox<String> paymentTypes;
	protected JComboBox<String> packages;
	protected JLabel firstListLabel;
	protected DefaultComboBoxModel<String> availableCourses;
	protected DefaultComboBoxModel<String> availablePackages;

	@Override
	public void createLabelsAndFields(JFrame frame, Person teacher) {
		super.createLabelsAndFields(frame, teacher);
	
		JLabel coursesLabel = new JLabel("Cursos");
		coursesLabel.setBounds(586, 73, 70, 17);
		 frame.getContentPane().add(coursesLabel);
		
		JLabel packagesLabel = new JLabel("Pacotes");
        packagesLabel.setBounds(586, 348, 70, 17);
        frame.getContentPane().add(packagesLabel);
        
        JLabel dataOfPaymentLbl = new JLabel("DADOS DO PAGAMENTO");
        dataOfPaymentLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        dataOfPaymentLbl.setBounds(219, 443, 200, 17);
        frame.getContentPane().add(dataOfPaymentLbl);
        
        JLabel paymentForm = new JLabel("Forma de pagamento");
        paymentForm.setBounds(72, 520, 171, 17);
        frame.getContentPane().add(paymentForm);
        
        paymentTypeLbl = new JLabel("Tipo de pagamento");
        paymentTypeLbl.setBounds(72, 472, 150, 17);
        frame.getContentPane().add(paymentTypeLbl);

        JLabel paymentValue = new JLabel("Valor total");
        paymentValue.setBounds(30, 586, 200, 17);
        frame.getContentPane().add(paymentValue);
                       
        JLabel paymentInstallments = new JLabel("Quantidade de Parcelas");
        paymentInstallments.setBounds(229, 580, 190, 17);
        frame.getContentPane().add(paymentInstallments);
        
      

	}

	@Override
	public void createMasks(JFrame frame) {
		
		
	}

	@Override
	public void createButtons(JFrame frame) {


	}
	
}
