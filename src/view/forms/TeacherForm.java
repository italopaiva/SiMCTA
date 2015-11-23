package view.forms;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

import model.Person;

public class TeacherForm extends PersonForm {

	@Override
	public void createLabelsAndFields(JFrame frame, Person teacher) {
		super.createLabelsAndFields(frame, teacher);
		
        JLabel qualificationLabel = new JLabel("*Qualificação");
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
