package view.forms;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import view.TeacherView;
import model.Teacher;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

public class TeacherForm extends TeacherView {

	@Override
	public void createLabelsAndFields(JFrame frame, Teacher teacher) {
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);
  
		JLabel nameLbl = new JLabel("Nome");
        nameLbl.setBounds(70, 60, 70, 17);
        frame.getContentPane().add(nameLbl);
  
        JLabel cpfLabel = new JLabel("CPF");
        cpfLabel.setBounds(70, 102, 70, 17);
        frame.getContentPane().add(cpfLabel);
        
        JLabel rgLabel = new JLabel("Número RG");
        rgLabel.setBounds(243, 97, 85, 17);
        frame.getContentPane().add(rgLabel);
  
        JLabel issuingInstitutionLbl = new JLabel("Órgão expedidor");
		issuingInstitutionLbl.setBounds(70, 137, 129, 15);
		frame.getContentPane().add(issuingInstitutionLbl);
		
		JLabel ufLbl = new JLabel("UF");
		ufLbl.setBounds(306, 132, 27, 17);
		frame.getContentPane().add(ufLbl);

        JLabel birthdateLabel = new JLabel("Data de Nascimento");
        birthdateLabel.setBounds(70, 171, 200, 17);
        frame.getContentPane().add(birthdateLabel);
 
        JLabel cellLabel = new JLabel("Celular");
        cellLabel.setBounds(305, 171, 70, 17);
        frame.getContentPane().add(cellLabel);
               
        JLabel phoneLabel = new JLabel("Telefone");
        phoneLabel.setBounds(305, 205, 70, 17);
        frame.getContentPane().add(phoneLabel);
        
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(70, 248, 70, 17);
        frame.getContentPane().add(emailLabel);
        
        JLabel addressLabel = new JLabel("Endereço");
        addressLabel.setBounds(70, 282, 70, 17);
        frame.getContentPane().add(addressLabel);

        JLabel cepLabel = new JLabel("CEP");
        cepLabel.setBounds(495, 321, 33, 17);
        frame.getContentPane().add(cepLabel);

        JLabel cityLabel = new JLabel("Cidade");
        cityLabel.setBounds(317, 321, 70, 17);
        frame.getContentPane().add(cityLabel);

		JLabel numberLbl = new JLabel("Nº");
		numberLbl.setBounds(495, 282, 33, 17);
		frame.getContentPane().add(numberLbl);

		JLabel complementLbl = new JLabel("Complemento");
		complementLbl.setBounds(70, 321, 105, 17);
		frame.getContentPane().add(complementLbl);
        
        JLabel motherLabel = new JLabel("Nome da mãe");
        motherLabel.setBounds(70, 369, 95, 17);
        frame.getContentPane().add(motherLabel);
        
        JLabel fatherLabel = new JLabel("Nome do pai");
        fatherLabel.setBounds(70, 409, 95, 17);
        frame.getContentPane().add(fatherLabel);
        
        JLabel qualificationLabel = new JLabel("Qualificação");
        qualificationLabel.setBounds(70, 444, 105, 17);
        frame.getContentPane().add(qualificationLabel);

	}

	@Override
	public void createMasks(JFrame frame) {
		
		MaskFormatter birthdateMask = null;
        MaskFormatter cpfMask = null;
		try{
	        // Mask for cpf
	        cpfMask = new MaskFormatter("###########");
	        cpfMask.setValidCharacters("0123456789");
	        cpfMask.setValueContainsLiteralCharacters(false);

	        cpfField = new JFormattedTextField(cpfMask);
	        cpfField.setBounds(102, 97, 129, 27);
	        frame.getContentPane().add(cpfField);
	        cpfField.setColumns(10);
	        
			// Mask for birthdate
			birthdateMask = new MaskFormatter("##/##/####");
			birthdateMask.setValidCharacters("0123456789");
			birthdateMask.setValueContainsLiteralCharacters(true);
	        
	        birthdateField = new JFormattedTextField(birthdateMask);
	        birthdateField.setBounds(70, 195, 190, 27);
	        frame.getContentPane().add(birthdateField);
	        birthdateField.setColumns(10);

		}
		catch(ParseException e2){
			e2.printStackTrace();
		}
	}

	@Override
	public void createButtons(JFrame frame) {


	}

}
