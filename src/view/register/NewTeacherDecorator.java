package view.register;

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

import view.TeacherView;
import view.decorator.TeacherDecorator;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;
import controller.TeacherController;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.TeacherException;

public class NewTeacherDecorator extends TeacherDecorator {
	
	public NewTeacherDecorator(TeacherView viewToDecorate) {
		super(viewToDecorate);
	}


	@Override
	public void createLabelsAndFields(JFrame frame, int fieldStatus) {
		super.createLabelsAndFields(frame, fieldStatus);
		
	}
	
	@Override
	public void createMasks(JFrame frame, int fieldStatus){
	       
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

				private void newTeacher() throws TeacherException {
					String message = "";
					
					try{
					
						String teacherName = nameField.getText();
					
						String cpf = cpfField.getText();
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
						teacherController.newTeacher(teacherName, teacherCpf, teacherRg, birthdate, email, address,
								 					 principalPhone, secondaryPhone, motherName, fatherName, qualification);
						
						message = "Professor cadastrado com sucesso.";
					}
					catch(DateException | PhoneException | CPFException | RGException | AddressException | PersonException e2){
						message = e2.getMessage();
					} 					
					finally{
						showInfoMessage(message);
					}
					
				}
		});
		
		
	}


}
