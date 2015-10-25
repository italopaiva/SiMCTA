package view.register;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.Teacher;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;
import view.TeacherView;
import view.decorator.TeacherDecorator;
import controller.TeacherController;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.TeacherException;

public class NewTeacherDecorator extends TeacherDecorator {
	
    protected JButton registerTeacherBtn;
	
	public NewTeacherDecorator(TeacherView viewToDecorate) {
		super(viewToDecorate);
	}


	@Override
	public void createLabelsAndFields(JFrame frame, int fieldStatus, Teacher teacher) {
		super.createLabelsAndFields(frame, fieldStatus,teacher);
	}
	
	@Override
	public void createMasks(JFrame frame, int fieldStatus){
	       
       
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
		
		try{
			String teacherName = super.nameField.getText();
			
			System.out.println("Teacher: '" + teacherName+ "'");
			
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

}
