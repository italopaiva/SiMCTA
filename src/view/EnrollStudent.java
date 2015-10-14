package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;
import controller.StudentController;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PhoneException;
import exception.RGException;
import exception.StudentException;

public class EnrollStudent extends View {
	
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
	private JLabel firstListLabel;
	private JLabel secondListLabel;
	private JList<String> firstList;
	private JList<String> secondList;
	private JTextField paymentValueField;
	private JTextField paymentInstallmentsField;
	private JLabel paymentTypeLbl;

	public EnrollStudent(){
		
		super();
		
		getContentPane().setLayout(null);
		
		createLabelsAndFields();
	}
	
	/**
	 * Creates the labels and fields of the frame
	 */
	private void createLabelsAndFields(){
		
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel enrollStudentLbl = new JLabel("Matricular novo aluno");
		enrollStudentLbl.setBounds(407, 12, 275, 31);
		enrollStudentLbl.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(enrollStudentLbl);
        
        JLabel dataOfStudentLbl = new JLabel("DADOS DO ALUNO");
        dataOfStudentLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        dataOfStudentLbl.setBounds(189, 39, 150, 17);
        contentPane.add(dataOfStudentLbl);
        
        JLabel nameLbl = new JLabel("Nome");
        nameLbl.setBounds(30, 73, 70, 17);
        contentPane.add(nameLbl);
        
        nameField = new JTextField();
        nameField.setBounds(85, 68, 434, 27);
        contentPane.add(nameField);
        nameField.setColumns(10);
        
        JLabel cpfLabel = new JLabel("CPF");
        cpfLabel.setBounds(30, 128, 70, 17);
        contentPane.add(cpfLabel);
        
        JLabel rgLabel = new JLabel("RG");
        rgLabel.setBounds(319, 128, 70, 17);
        contentPane.add(rgLabel);
        
        cpfField = new JTextField();
        cpfField.setBounds(85, 123, 140, 27);
        contentPane.add(cpfField);
        cpfField.setColumns(10);
        
        rgField = new JTextField();
        rgField.setBounds(359, 123, 140, 27);
        contentPane.add(rgField);
        rgField.setColumns(10);

        JLabel birthdateLabel = new JLabel("Data de Nascimento");
        birthdateLabel.setBounds(30, 171, 200, 17);
        contentPane.add(birthdateLabel);
        
        birthdateField = new JTextField();
        birthdateField.setBounds(30, 195, 190, 27);
        contentPane.add(birthdateField);
        birthdateField.setColumns(10);
        
        JLabel cellLabel = new JLabel("Celular");
        cellLabel.setBounds(285, 171, 70, 17);
        contentPane.add(cellLabel);
        
        JLabel phoneLabel = new JLabel("Telefone");
        phoneLabel.setBounds(285, 205, 70, 17);
        contentPane.add(phoneLabel);
        
        cellField = new JTextField();
        cellField.setBounds(359, 166, 140, 27);
        contentPane.add(cellField);
        cellField.setColumns(10);
        
        phoneField = new JTextField();
        phoneField.setBounds(359, 200, 140, 27);
        contentPane.add(phoneField);
        phoneField.setColumns(10);
        
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(30, 248, 70, 17);
        contentPane.add(emailLabel);
        
        emailField = new JTextField();
        emailField.setBounds(85, 243, 334, 27);
        contentPane.add(emailField);

        JLabel addressLabel = new JLabel("Endereço");
        addressLabel.setBounds(30, 282, 70, 17);
        contentPane.add(addressLabel);
        
        addressField = new JTextField();
        addressField.setBounds(105, 277, 434, 27);
        contentPane.add(addressField);

        JLabel cepLabel = new JLabel("CEP");
        cepLabel.setBounds(319, 321, 70, 17);
        contentPane.add(cepLabel);
        
        cepField = new JTextField();
        cepField.setBounds(354, 316, 105, 27);
        contentPane.add(cepField);

        JLabel cityLabel = new JLabel("Cidade");
        cityLabel.setBounds(30, 321, 70, 17);
        contentPane.add(cityLabel);
        
        cityField = new JTextField();
        cityField.setBounds(85, 321, 106, 27);
        contentPane.add(cityField);

        JLabel motherLabel = new JLabel("Nome da mãe");
        motherLabel.setBounds(30, 369, 95, 17);
        contentPane.add(motherLabel);
        
        motherField = new JTextField();
        motherField.setBounds(137, 364, 402, 27);
        contentPane.add(motherField);

        JLabel fatherLabel = new JLabel("Nome do pai");
        fatherLabel.setBounds(30, 409, 95, 17);
        contentPane.add(fatherLabel);
        
        fatherField = new JTextField();
        fatherField.setBounds(137, 404, 402, 27);
        contentPane.add(fatherField);
        
        firstListLabel = new JLabel();
        firstListLabel.setBounds(576, 73, 120, 17);
        contentPane.add(firstListLabel);
        
        secondListLabel = new JLabel("Pacotes");
        secondListLabel.setBounds(576, 308, 70, 17);
        contentPane.add(secondListLabel);
        
        firstList = new JList<String>();
        firstList.setBackground(Color.WHITE);
        firstList.setBounds(574, 112, 240, 153);
        contentPane.add(firstList);
        
        secondList = new JList<String>();
        secondList.setBackground(Color.WHITE);
        secondList.setBounds(576, 337, 238, 153);
        contentPane.add(secondList);
        
        JLabel dataOfPaymentLbl = new JLabel("DADOS DO PAGAMENTO");
        dataOfPaymentLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        dataOfPaymentLbl.setBounds(189, 443, 200, 17);
        contentPane.add(dataOfPaymentLbl);
        
        JLabel paymentForm = new JLabel("Forma de pagamento");
        paymentForm.setBounds(42, 520, 171, 17);
        contentPane.add(paymentForm);
        
        paymentTypeLbl = new JLabel("Tipo de pagamento");
        paymentTypeLbl.setBounds(42, 472, 150, 17);
        contentPane.add(paymentTypeLbl);

        JLabel paymentValue = new JLabel("Valor total");
        paymentValue.setBounds(30, 586, 200, 17);
        contentPane.add(paymentValue);
                
        paymentValueField = new JTextField();
        paymentValueField.setBounds(105, 580, 120, 27);
        contentPane.add(paymentValueField);
        
        paymentInstallmentsField = new JTextField();
        paymentInstallmentsField.setBounds(412, 581, 27, 27);
        contentPane.add(paymentInstallmentsField);
        
        JLabel paymentInstallments = new JLabel("Quantidade de Parcelas");
        paymentInstallments.setBounds(229, 580, 190, 17);
        contentPane.add(paymentInstallments);
        
        JComboBox<String> paymentTypes = new JComboBox<String>();
        paymentTypes.setBounds(221, 472, 151, 24);
        contentPane.add(paymentTypes);
        
        DefaultComboBoxModel<String> paymentTypesModel = new DefaultComboBoxModel<String>();
        paymentTypesModel.addElement("À vista");
        paymentTypesModel.addElement("Parcelado");
        
        paymentTypes.setModel(paymentTypesModel);
        
        JComboBox<String> paymentForms = new JComboBox<String>();
        paymentForms.setBounds(221, 516, 151, 24);
        contentPane.add(paymentForms);
        
        DefaultComboBoxModel<String> paymentFormsModel = new DefaultComboBoxModel<String>();
        paymentFormsModel.addElement("Dinheiro");
        paymentFormsModel.addElement("Cartão");
        paymentFormsModel.addElement("Cheque");
        
        paymentForms.setModel(paymentFormsModel);
        
        JButton enrollBtn = new JButton("Matricular");
		enrollBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				
				String message = "";
				try{
					
					String studentName = "Emilie Morais";
					CPF studentCpf = new CPF("59418933535");
					RG studentRg = new RG("13454343", "SSP", "BA");
					Date birthdate = new Date(15, 10, 1998);
					String email = "italo@gmail.com";
					Address address = new Address("QS 122 Conjunto 9", "22", "", "72331009", "Samambaia");
					Phone principalPhone = new Phone("61", "85675434");
					Phone secondaryPhone = new Phone("61", "88672335");
					String motherName = "Vaneide Paiva";
					String fatherName = "ALtamir Batista";
					
					ArrayList<String> courses = new ArrayList<String>();
					courses.add("1");
					ArrayList<String> packages = new ArrayList<String>();
					packages.add("7");
					
					int paymentType = 1;
					int paymentForm = 1;
					Integer installments = new Integer(1);
					
					StudentController studentController = new StudentController();
					boolean wasSaved = studentController.newStudent(studentName, studentCpf, studentRg, birthdate, email, address,
																	principalPhone, secondaryPhone, motherName, fatherName, courses, packages, paymentType, paymentForm, installments);
					
					if(wasSaved){
						message = "Aluno matriculado com sucesso.";
					}
					else{
						message = "Não foi possível matricular o aluno informado.";
					}
				}
				catch(CPFException e1){
					message = e1.getMessage();
				}
				catch(RGException e1){
					message = e1.getMessage();
				}
				catch(DateException e1){
					message = e1.getMessage();
				}
				catch(AddressException e1){
					message = e1.getMessage();
				}
				catch(PhoneException e1){
					message = e1.getMessage();
				} catch (StudentException e1) {
					// TODO Auto-generated catch block
					message = e1.getMessage();
				}
				finally{
					showInfoMessage(message);
				}
				
			}
		});
		enrollBtn.setBounds(422, 631, 117, 25);
		contentPane.add(enrollBtn);
		
		JLabel coursesLabel = new JLabel("Cursos");
		coursesLabel.setBounds(574, 83, 70, 17);
		contentPane.add(coursesLabel);
	}
}
