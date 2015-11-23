package view.decorator;


import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

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
import model.Person;
import model.Teacher;
import view.SearchTeacher;
import view.PersonView;
import view.forms.TeacherForm;

public class EditTeacherDecorator extends PersonDecorator {

	private Component alterTeacherBtn;
	private JButton backBtn;
	private Teacher teacher;
	private PersonView teacherFrame;

	public EditTeacherDecorator(PersonView viewToDecorate) {
		super(viewToDecorate);
	}

	@Override
	public void createLabelsAndFields(JFrame viewToDecorate, Person teacher) {
		this.frame = viewToDecorate;
		super.createLabelsAndFields(viewToDecorate, teacher);
		this.teacher = (Teacher) teacher;
		
		JLabel requiredFieldsLbl = new JLabel("Os campos com * são obrigatórios");
		requiredFieldsLbl.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC,12));
		requiredFieldsLbl.setBounds(115, 30, 370, 17);
        frame.getContentPane().add(requiredFieldsLbl);
				
		registerPersonLbl.setText(teacher.getName());
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
		
        fillTheFields(this.teacher);

	}
	
	private void fillTheFields(Teacher teacher) {
		
		String teacherName = teacher.getName();		
		String email = teacher.getEmail();
		String motherName = teacher.getMotherName();
		String fatherName = teacher.getFatherName();

		nameField.setText(teacherName);
		emailField.setText(email);
		motherField.setText(motherName);
		fatherField.setText(fatherName);

		//CPF
		CPF cpf = teacher.getCpf();
		String teacherCpf  = cpf.getCpf();
		cpfField.setText(teacherCpf);
		
		//RG
		
		RG rg = teacher.getRg();
		rgField.setText(rg.getRgNumber());
		ufField.setText(rg.getUf());
		issuingInstitutionField.setText(rg.getIssuingInstitution());
		
		// Birthdate
		Date date = teacher.getBirthdate();
		String birthdate = date.getSlashFormattedDate();
		birthdateField.setText(birthdate);
		
		//Address
		Address address = teacher.getAddress();
		String city = address.getCity();
		String cep = address.getCep();
		
		addressField.setText(address.getAddressInfo());
		cepField.setText(cep);
		cityField.setText(city);
		numberField.setText(address.getNumber());
		complementField.setText(address.getComplement());
		
		//Phones
		Phone principalPhone = teacher.getPrincipalPhone();
		Phone secondaryPhone = teacher.getSecondaryPhone();

		dddCellField.setText(principalPhone.getDDD());
		cellField.setText(principalPhone.getNumber());
		
		if(secondaryPhone != null){
			dddPhoneField.setText(secondaryPhone.getDDD());
			phoneField.setText(secondaryPhone.getNumber());
		}
		else{
			dddPhoneField.setText("");
			phoneField.setText("");
		}
		
		String qualification = teacher.getQualification();
		qualificationField.setText(qualification);
		
		setEditableAllFields();
	}
	
	private void setEditableAllFields() {
        /** 
         * RG and CPF can't be edit
         */
		rgField.setEditable(false);
    	cpfField.setEditable(false);
		nameField.setEditable(true);
        cellField.setEditable(true);
        phoneField.setEditable(true);
        addressField.setEditable(true);
        cepField.setEditable(true);
        cityField.setEditable(true);
        emailField.setEditable(true);
        motherField.setEditable(true);
        fatherField.setEditable(true);
        dddCellField.setEditable(true);
        dddPhoneField.setEditable(true);
        issuingInstitutionField.setEditable(false);
        ufField.setEditable(false);
        numberField.setEditable(true);
        complementField.setEditable(true);
        qualificationField.setEditable(true);
		birthdateField.setEditable(true);
		
	}


	@Override
	public void createButtons(JFrame frame) {	
		
		
		alterTeacherBtn = new JButton("Alterar");
		frame.getContentPane().add(alterTeacherBtn);
		alterTeacherBtn.setBounds(399, 610, 117, 25);
		alterTeacherBtn.addMouseListener(new MouseAdapter() {
			private TeacherController teacherController;

			@Override
			public void mouseClicked(MouseEvent e){			
				teacherController = new TeacherController();
				
					try {
						editTeacher();
						dispose();
						teacherFrame = new ShowTeacherDecorator(new TeacherForm());
						teacherFrame.buildScreen(teacherFrame, teacher);
						teacherFrame.setVisible(true);
					
					} 
					catch(PersonException | TeacherException e1){
						
					}

			}

			private void editTeacher() throws PersonException, TeacherException {
				String message = "";
				try{
					String teacherName = nameField.getText();
					
					CPF teacherCpf = teacher.getCpf();
	
					String rgNumber = rgField.getText();
					String rgIssuingInstitution = issuingInstitutionField.getText();
					String rgUf = ufField.getText();
				
					RG teacherRg = new RG(rgNumber, rgIssuingInstitution, rgUf);					
				
					String date = birthdateField.getText();
					String dates [] = date.split("/");
					String day = dates[0];
					String month = dates[1];
					String year = dates[2];
					
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
					
					teacher = teacherController.updateTeacher(teacherName, teacherCpf, teacherRg, birthdate, email, address,
		 					 principalPhone, secondaryPhone, motherName, fatherName, qualification);
					
					message = "Cadastro do professor alterado com sucesso!";
				}
				catch(DateException | PhoneException | RGException | AddressException e2){
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
		});
		
		backBtn = new JButton("Voltar");
		frame.getContentPane().add(backBtn);
		backBtn.setBounds(520, 610, 117, 25);
		backBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e){			
				dispose();
				teacherFrame = new ShowTeacherDecorator(new TeacherForm());
				teacherFrame.buildScreen(teacherFrame,teacher);
				teacherFrame.setVisible(true);
			}
		});
	}

	@Override
	public void createMasks(JFrame frame) {
        
	
		// Mask for birthdate
		MaskFormatter birthdateMask;
		try {
			birthdateMask = new MaskFormatter("##/##/####");
			birthdateMask.setValidCharacters("0123456789");
			birthdateMask.setValueContainsLiteralCharacters(true);

	        birthdateField = new JFormattedTextField(birthdateMask);
	        birthdateField.setBounds(70, 195, 190, 27);
			String birthdate = teacher.getBirthdate().getWholeDate();
			birthdateField.setText(birthdate);
	        frame.getContentPane().add(birthdateField);
	        birthdateField.setColumns(10);
	        
	        // Mask for cpf
	        MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
	        cpfMask.setValidCharacters("0123456789");
	        cpfMask.setValueContainsLiteralCharacters(false);

			String cpf = teacher.getCpf().getCpf();
			cpfField = new JFormattedTextField(cpfMask);
			cpfField.setText(cpf);
			cpfField.setBounds(102, 97, 129, 27);
			cpfField.setEditable(false);
			frame.getContentPane().add(cpfField);
			
		} 
		catch(ParseException e){

		}

	}
}
