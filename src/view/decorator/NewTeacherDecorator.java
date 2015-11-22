package view.decorator;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.Person;
import model.Teacher;
import view.PersonView;
import view.forms.TeacherForm;
import controller.TeacherController;
import datatype.Address;
import datatype.CPF;
import datatype.Date;
import datatype.Phone;
import datatype.RG;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.TeacherException;

public class NewTeacherDecorator extends PersonDecorator {
	
    private JButton registerTeacherBtn;

	public NewTeacherDecorator(PersonView viewToDecorate) {
		super(viewToDecorate);
	}


	@Override
	public void createLabelsAndFields(JFrame frame, Person teacher) {
		
		super.createLabelsAndFields(frame,teacher);
        
		JLabel requiredFieldsLbl = new JLabel("Os campos com * são obrigatórios");
		requiredFieldsLbl.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC,12));
		requiredFieldsLbl.setBounds(115, 30, 370, 17);
        frame.getContentPane().add(requiredFieldsLbl);
				
		registerPersonLbl.setText("Cadastrar novo professor");
		registerPersonLbl.setBounds(407, 12, 475, 31);
		registerPersonLbl.setFont(new Font("Dialog", Font.BOLD, 20));
		frame.getContentPane().add(registerPersonLbl);
		
        nameField.setBounds(115, 55, 434, 27);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);
        
        rgField.setBounds(327, 92, 100, 27);
        frame.getContentPane().add(rgField);
        rgField.setColumns(10);
        
		issuingInstitutionField.setColumns(10);
		issuingInstitutionField.setBounds(203, 131, 85, 27);
		frame.getContentPane().add(issuingInstitutionField);
		
		ufField.setColumns(10);
		ufField.setBounds(417, 132, 100, 27);
		frame.getContentPane().add(ufField);
		
        dddCellField.setBounds(379, 166, 40, 27);
        frame.getContentPane().add(dddCellField);
        dddCellField.setColumns(10);
        
        cellField.setBounds(429, 166, 100, 27);
        frame.getContentPane().add(cellField);
        cellField.setColumns(10);
        
        dddPhoneField.setBounds(379, 200, 40, 27);
        frame.getContentPane().add(dddPhoneField);
        dddPhoneField.setColumns(10);
        
        phoneField.setBounds(429, 200, 100, 27);
        frame.getContentPane().add(phoneField);
        phoneField.setColumns(10);
               
        emailField.setBounds(115, 243, 334, 27);
        frame.getContentPane().add(emailField);

        addressField.setBounds(145, 277, 344, 27);
        frame.getContentPane().add(addressField);

        cepField.setBounds(545, 316, 84, 27);
        frame.getContentPane().add(cepField);
        
        cityField.setBounds(385, 316, 105, 27);
        frame.getContentPane().add(cityField);

		numberField.setBounds(522, 277, 57, 27);
		frame.getContentPane().add(numberField);
		
		complementField.setBounds(177, 316, 122, 27);
		frame.getContentPane().add(complementField);
		        
        motherField.setBounds(177, 364, 402, 27);
        frame.getContentPane().add(motherField);

        fatherField.setBounds(177, 404, 402, 27);
        frame.getContentPane().add(fatherField);
        
        qualificationField.setBounds(177, 444, 402, 127);
        frame.getContentPane().add(qualificationField);	
	}
	
	@Override
	public void createMasks(JFrame frame){
		
		MaskFormatter birthdateMask = null;
        MaskFormatter cpfMask = null;
		try{
	        // Mask for cpf
	        cpfMask = new MaskFormatter("###.###.###-##");
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
	public void createButtons(JFrame frame){
		registerTeacherBtn = new JButton("Cadastrar");
		frame.getContentPane().add(registerTeacherBtn);
		registerTeacherBtn.setBounds(422, 581, 117, 25);
		registerTeacherBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){			
				try {
					newTeacher();
				} 
				catch (TeacherException e1) {

				}
			}
				
		});
				
	}
	
	private void newTeacher() throws TeacherException {
		String message = "";
		
		Teacher teacher = null;
		try{
			String teacherName = nameField.getText();
						
			String cpf = cpfField.getText();
			cpf = cpf.replace(".", "");
			cpf = cpf.replace("-", "");
			CPF teacherCpf = new CPF(cpf);

			String rgNumber = rgField.getText();
			String rgIssuingInstitution = issuingInstitutionField.getText();
			String rgUf = ufField.getText();
		
			RG teacherRg = new RG(rgNumber, rgIssuingInstitution, rgUf);					
		
			String date = birthdateField.getText();
			String day = date.substring(0, 2);
			String month = date.substring(3, 5);
			String year = date.substring(6, 10);
			
			Date birthdate = new Date(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
			
			String email = emailField.getText();
			
			String addressInfo = addressField.getText();
			String addressNumber = numberField.getText();
			String addressComplement = complementField.getText();
			String addressCity = cityField.getText();
			String addressCep = cepField.getText();
			
			Address address = new Address(addressInfo, addressNumber, addressComplement, addressCep, addressCity);
			
			String ddCell = dddCellField.getText();
			String cellNumber = cellField.getText();
								
			String ddPhone = dddPhoneField.getText();
			String phoneNumber = phoneField.getText();
			
			Phone principalPhone;
			Phone secondaryPhone;
			if(!phoneNumber.isEmpty() && !ddPhone.isEmpty()){
				
				principalPhone = new Phone(ddCell, cellNumber);
				secondaryPhone = new Phone(ddPhone, phoneNumber);
			}
			else{
				principalPhone = new Phone(ddCell, cellNumber);
				secondaryPhone = null;
			}
			
			String motherName = motherField.getText();
			String fatherName = fatherField.getText();
			
			String qualification = qualificationField.getText();
			
			TeacherController teacherController = new TeacherController();
			teacher = teacherController.newTeacher(teacherName, teacherCpf, teacherRg, birthdate, email, address,
					 					 principalPhone, secondaryPhone, motherName, fatherName, qualification);
			
			message = "Professor cadastrado com sucesso.";
		}
		catch(DateException | PhoneException | CPFException | RGException | AddressException | PersonException e2){
			message = e2.getMessage();
		} 					
		finally{
			showInfoMessage(message);
			if(teacher != null){
				dispose();
				PersonView showTeacherFrame = new ShowTeacherDecorator(new TeacherForm());
				showTeacherFrame.buildScreen(showTeacherFrame, teacher);
				showTeacherFrame.setVisible(true);
			}
		}
		
	}

}
