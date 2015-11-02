package view.decorator;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Person;
import model.Teacher;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;
import view.SearchTeacher;
import view.PersonView;
import view.forms.TeacherForm;

public class ShowTeacherDecorator extends PersonDecorator {

	private JButton editTeacherBtn;
	private JButton backBtn;
	Teacher teacher;

	public ShowTeacherDecorator(PersonView viewToDecorate) {
		super(viewToDecorate);
	}

	@Override
	public void createLabelsAndFields(JFrame viewToDecorate, Person teacher) {
		this.frame = viewToDecorate;
		super.createLabelsAndFields(viewToDecorate, teacher);
		this.teacher = (Teacher) teacher;

		registerTeacherLbl.setText(teacher.getName());
		registerTeacherLbl.setBounds(407, 12, 475, 31);
		registerTeacherLbl.setFont(new Font("Dialog", Font.BOLD, 20));
		frame.getContentPane().add(registerTeacherLbl);
		
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
		
		String birthdate = teacher.getBirthdate().getSlashFormattedDate();
		birthdateField = new JTextField(birthdate);
		birthdateField.setBounds(70, 195, 190, 27);
		frame.getContentPane().add(birthdateField);

		String cpf = teacher.getCpf().getFormattedCpf();
		cpfField = new JTextField(cpf);
		cpfField.setBounds(102, 97, 129, 27);
		frame.getContentPane().add(cpfField);

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
		String teacherCpf  = cpf.getFormattedCpf();
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
		
		setNonEditableAllFields();
		
	}
	
	private void setNonEditableAllFields() {
		nameField.setEditable(false);
        rgField.setEditable(false);
        cellField.setEditable(false);
        phoneField.setEditable(false);
        addressField.setEditable(false);
        cepField.setEditable(false);
        cityField.setEditable(false);
        emailField.setEditable(false);
        motherField.setEditable(false);
        fatherField.setEditable(false);
        dddCellField.setEditable(false);
        dddPhoneField.setEditable(false);
        issuingInstitutionField.setEditable(false);
        ufField.setEditable(false);
        numberField.setEditable(false);
        complementField.setEditable(false);
        qualificationField.setEditable(false);
		cpfField.setEditable(false);
		birthdateField.setEditable(false);
	}



	@Override
	public void createButtons(JFrame frame) {
		editTeacherBtn = new JButton("Editar");
		frame.getContentPane().add(editTeacherBtn);
		editTeacherBtn.setBounds(599, 55, 117, 25);
		editTeacherBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			
				PersonView teacherFrame = new EditTeacherDecorator(new TeacherForm());
				dispose();
				teacherFrame.buildScreen(teacherFrame,teacher);
				teacherFrame.setVisible(true);
			}
		});
		
		backBtn = new JButton("Voltar");
		frame.getContentPane().add(backBtn);
		backBtn.setBounds(799, 55, 117, 25);
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			
				dispose();
				PersonView teacherFrame = new SearchTeacher();
				teacherFrame.buildScreen(teacherFrame,null);
				teacherFrame.setVisible(true);
			}
		});
	}

	@Override
	public void createMasks(JFrame frame) {
		
	}
}
