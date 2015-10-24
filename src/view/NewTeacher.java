package view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

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

public class NewTeacher extends View {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField cpfField;
	private JTextField rgField;
	private JTextField cellField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField cepField;
	private JTextField cityField;
	private JTextField emailField;
	private JTextField birthdateField;
	private JTextField motherField;
	private JTextField fatherField;
	private JTextField dddCellField;
	private JTextField dddPhoneField;
	private JButton registerTeacherBtn;
	private JTextField issuingInstitutionField;
	private JTextField ufField;
	private JTextField numberField;
	private JTextField complementField;
	private JTextField qualificationField;
	
	public NewTeacher(){
		buildScreen();
	}
	
	@Override
	protected void createLabelsAndFields(){
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel registerTeacherLbl = new JLabel("Cadastrar novo professor");
		registerTeacherLbl.setBounds(407, 12, 475, 31);
		registerTeacherLbl.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(registerTeacherLbl);
 
        JLabel nameLbl = new JLabel("Nome");
        nameLbl.setBounds(70, 60, 70, 17);
        contentPane.add(nameLbl);
        
        nameField = new JTextField();
        nameField.setBounds(115, 55, 434, 27);
        contentPane.add(nameField);
        nameField.setColumns(10);
        
        JLabel cpfLabel = new JLabel("CPF");
        cpfLabel.setBounds(70, 102, 70, 17);
        contentPane.add(cpfLabel);
        
        JLabel rgLabel = new JLabel("Número RG");
        rgLabel.setBounds(243, 97, 85, 17);
        contentPane.add(rgLabel);
                
        rgField = new JTextField();
        rgField.setBounds(327, 92, 100, 27);
        contentPane.add(rgField);
        rgField.setColumns(10);
        
        issuingInstitutionField = new JTextField();
		issuingInstitutionField.setColumns(10);
		issuingInstitutionField.setBounds(203, 131, 85, 27);
		contentPane.add(issuingInstitutionField);
		
		JLabel issuingInstitutionLbl = new JLabel("Órgão expedidor");
		issuingInstitutionLbl.setBounds(70, 137, 129, 15);
		contentPane.add(issuingInstitutionLbl);
		
		ufField = new JTextField();
		ufField.setColumns(10);
		ufField.setBounds(417, 132, 100, 27);
		contentPane.add(ufField);
		
		JLabel ufLbl = new JLabel("UF");
		ufLbl.setBounds(306, 132, 27, 17);
		contentPane.add(ufLbl);

        JLabel birthdateLabel = new JLabel("Data de Nascimento");
        birthdateLabel.setBounds(70, 171, 200, 17);
        contentPane.add(birthdateLabel);
 
        JLabel cellLabel = new JLabel("Celular");
        cellLabel.setBounds(305, 171, 70, 17);
        contentPane.add(cellLabel);
               
        JLabel phoneLabel = new JLabel("Telefone");
        phoneLabel.setBounds(305, 205, 70, 17);
        contentPane.add(phoneLabel);
        
        dddCellField = new JTextField();
        dddCellField.setBounds(379, 166, 40, 27);
        contentPane.add(dddCellField);
        dddCellField.setColumns(10);
        
        cellField = new JTextField();
        cellField.setBounds(429, 166, 100, 27);
        contentPane.add(cellField);
        cellField.setColumns(10);
        
        dddPhoneField = new JTextField();
        dddPhoneField.setBounds(379, 200, 40, 27);
        contentPane.add(dddPhoneField);
        dddPhoneField.setColumns(10);
        
        phoneField = new JTextField();
        phoneField.setBounds(429, 200, 100, 27);
        contentPane.add(phoneField);
        phoneField.setColumns(10);
        
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(70, 248, 70, 17);
        contentPane.add(emailLabel);
        
        emailField = new JTextField();
        emailField.setBounds(115, 243, 334, 27);
        contentPane.add(emailField);

        JLabel addressLabel = new JLabel("Endereço");
        addressLabel.setBounds(70, 282, 70, 17);
        contentPane.add(addressLabel);
        
        addressField = new JTextField();
        addressField.setBounds(145, 277, 344, 27);
        contentPane.add(addressField);

        JLabel cepLabel = new JLabel("CEP");
        cepLabel.setBounds(495, 321, 33, 17);
        contentPane.add(cepLabel);
        
        cepField = new JTextField();
        cepField.setBounds(545, 316, 84, 27);
        contentPane.add(cepField);

        JLabel cityLabel = new JLabel("Cidade");
        cityLabel.setBounds(317, 321, 70, 17);
        contentPane.add(cityLabel);
        
        cityField = new JTextField();
        cityField.setBounds(385, 316, 105, 27);
        contentPane.add(cityField);

		JLabel numberLbl = new JLabel("Nº");
		numberLbl.setBounds(495, 282, 33, 17);
		contentPane.add(numberLbl);
		
		numberField = new JTextField();
		numberField.setBounds(522, 277, 57, 27);
		contentPane.add(numberField);
		
		complementField = new JTextField();
		complementField.setBounds(177, 316, 122, 27);
		contentPane.add(complementField);
		
		JLabel complementLbl = new JLabel("Complemento");
		complementLbl.setBounds(70, 321, 105, 17);
		contentPane.add(complementLbl);
        
        JLabel motherLabel = new JLabel("Nome da mãe");
        motherLabel.setBounds(70, 369, 95, 17);
        contentPane.add(motherLabel);
        
        motherField = new JTextField();
        motherField.setBounds(177, 364, 402, 27);
        contentPane.add(motherField);

        JLabel fatherLabel = new JLabel("Nome do pai");
        fatherLabel.setBounds(70, 409, 95, 17);
        contentPane.add(fatherLabel);
        
        fatherField = new JTextField();
        fatherField.setBounds(177, 404, 402, 27);
        contentPane.add(fatherField);
        
        JLabel qualificationLabel = new JLabel("Qualificação");
        qualificationLabel.setBounds(70, 444, 105, 17);
        contentPane.add(qualificationLabel);
        
        qualificationField = new JTextField();
        qualificationField.setBounds(177, 444, 402, 127);
        contentPane.add(qualificationField);		

	}
	
	@Override
	protected void createMasks(){
	       
        MaskFormatter birthdateMask = null;
        MaskFormatter cpfMask = null;
		try{
	        // Mask for cpf
	        cpfMask = new MaskFormatter("###########");
	        cpfMask.setValidCharacters("0123456789");
	        cpfMask.setValueContainsLiteralCharacters(false);

	        cpfField = new JFormattedTextField(cpfMask);
	        cpfField.setBounds(102, 97, 129, 27);
	        contentPane.add(cpfField);
	        cpfField.setColumns(10);
	        
			// Mask for birthdate
			birthdateMask = new MaskFormatter("##/##/####");
			birthdateMask.setValidCharacters("0123456789");
			birthdateMask.setValueContainsLiteralCharacters(true);
	        
	        birthdateField = new JFormattedTextField(birthdateMask);
	        birthdateField.setBounds(70, 195, 190, 27);
	        contentPane.add(birthdateField);
	        birthdateField.setColumns(10);

		}
		catch(ParseException e2){
			e2.printStackTrace();
		}
	}
	
	@Override
	protected void createButtons(){
		registerTeacherBtn = new JButton("Cadastrar");
		contentPane.add(registerTeacherBtn);
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
