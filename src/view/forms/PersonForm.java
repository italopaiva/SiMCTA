package view.forms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Person;
import view.PersonView;

public class PersonForm extends PersonView {

	@Override
	public void createLabelsAndFields(JFrame frame, Person person) {
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);
  
		JLabel nameLbl = new JLabel("* Nome");
        nameLbl.setBounds(63, 60, 70, 17);
        frame.getContentPane().add(nameLbl);
  
        JLabel cpfLabel = new JLabel("*CPF");
        cpfLabel.setBounds(63, 102, 70, 17);
        frame.getContentPane().add(cpfLabel);
        
        JLabel rgLabel = new JLabel("*Número RG");
        rgLabel.setBounds(243, 97, 85, 17);
        frame.getContentPane().add(rgLabel);
  
        JLabel issuingInstitutionLbl = new JLabel("*Órgão expedidor");
		issuingInstitutionLbl.setBounds(63, 137, 129, 15);
		frame.getContentPane().add(issuingInstitutionLbl);
		
		JLabel ufLbl = new JLabel("*UF");
		ufLbl.setBounds(306, 132, 27, 17);
		frame.getContentPane().add(ufLbl);

        JLabel birthdateLabel = new JLabel("*Data de Nascimento");
        birthdateLabel.setBounds(63, 171, 200, 17);
        frame.getContentPane().add(birthdateLabel);
 
        JLabel cellLabel = new JLabel("*Celular");
        cellLabel.setBounds(305, 171, 70, 17);
        frame.getContentPane().add(cellLabel);
               
        JLabel phoneLabel = new JLabel("Telefone");
        phoneLabel.setBounds(305, 205, 70, 17);
        frame.getContentPane().add(phoneLabel);
        
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(63, 248, 70, 17);
        frame.getContentPane().add(emailLabel);
        
        JLabel addressLabel = new JLabel("*Endereço");
        addressLabel.setBounds(63, 282, 90, 17);
        frame.getContentPane().add(addressLabel);

        JLabel cepLabel = new JLabel("*CEP");
        cepLabel.setBounds(495, 321, 43, 17);
        frame.getContentPane().add(cepLabel);

        JLabel cityLabel = new JLabel("*Cidade");
        cityLabel.setBounds(317, 321, 70, 17);
        frame.getContentPane().add(cityLabel);

		JLabel numberLbl = new JLabel("*Nº");
		numberLbl.setBounds(495, 282, 33, 17);
		frame.getContentPane().add(numberLbl);

		JLabel complementLbl = new JLabel("Complemento");
		complementLbl.setBounds(63, 321, 105, 17);
		frame.getContentPane().add(complementLbl);
        
        JLabel motherLabel = new JLabel("*Nome da mãe");
        motherLabel.setBounds(61, 369, 110, 17);
        frame.getContentPane().add(motherLabel);
        
        JLabel fatherLabel = new JLabel("Nome do pai");
        fatherLabel.setBounds(63, 409, 95, 17);
        frame.getContentPane().add(fatherLabel);
        		
	}

	@Override
	public void createMasks(JFrame frame) {
		
	}

	@Override
	public void createButtons(JFrame frame) {
		
	}

}
