package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
	private JTextField paymentValueField;
	private JTextField paymentInstallmentsField;
	private JLabel paymentTypeLbl;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableSecondModel;
	private DefaultTableModel tableModelPackages;
	private DefaultTableModel tableSecondModelPackages;
	private JTextField ddCellField;
	private JTextField ddPhoneField;
	private JTextField textField;

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
        rgLabel.setBounds(319, 104, 70, 17);
        contentPane.add(rgLabel);
        
        cpfField = new JTextField();
        cpfField.setBounds(85, 123, 140, 27);
        contentPane.add(cpfField);
        cpfField.setColumns(10);
        
        rgField = new JTextField();
        rgField.setBounds(359, 99, 140, 27);
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
        
        ddCellField = new JTextField();
        ddCellField.setBounds(359, 166, 40, 27);
        contentPane.add(ddCellField);
        ddCellField.setColumns(10);
        
        cellField = new JTextField();
        cellField.setBounds(399, 166, 100, 27);
        contentPane.add(cellField);
        cellField.setColumns(10);
        
        ddPhoneField = new JTextField();
        ddPhoneField.setBounds(359, 200, 40, 27);
        contentPane.add(ddPhoneField);
        ddPhoneField.setColumns(10);
        
        phoneField = new JTextField();
        phoneField.setBounds(399, 200, 100, 27);
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
        firstListLabel.setBounds(562, 73, 120, 17);
        contentPane.add(firstListLabel);
        		
		JLabel coursesLabel = new JLabel("Cursos");
		coursesLabel.setBounds(546, 73, 70, 17);
		contentPane.add(coursesLabel);
		
		secondListLabel = new JLabel("Pacotes");
        secondListLabel.setBounds(546, 308, 70, 17);
        contentPane.add(secondListLabel);
        
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
        
        JComboBox<String> courses = new JComboBox<String>();
        courses.setBounds(553, 97, 251, 31);
        contentPane.add(courses);
        DefaultComboBoxModel<String> coursesSelectModel = new DefaultComboBoxModel<String>();
		
        courses.setModel(coursesSelectModel);
        
		JScrollPane scrollPaneAddedCourses = new JScrollPane();
		scrollPaneAddedCourses.setBounds(553, 130, 251, 169);
		contentPane.add(scrollPaneAddedCourses);
		scrollPaneAddedCourses.setBackground(Color.WHITE);
		
        JComboBox<String> packages = new JComboBox<String>();
		packages.setBounds(553, 337, 251, 31);
		contentPane.add(packages);
		
		JScrollPane scrollPaneAddedPackages = new JScrollPane();
		scrollPaneAddedPackages.setBounds(553, 370, 251, 169);
		contentPane.add(scrollPaneAddedPackages);
		scrollPaneAddedPackages.setBackground(Color.WHITE);
					
		String [] columnsAddedCourses = {"Cursos adicionados", "ID"};
		
		tableSecondModel = new DefaultTableModel(null, columnsAddedCourses);			

		final JTable tableOfAddedCourses = new JTable(tableSecondModel);
		scrollPaneAddedCourses.setViewportView(tableOfAddedCourses);
		disposeColumns(tableOfAddedCourses);
			
		String [] columnsAddedPackages = {"Pacotes adicionados", "ID"};
		
		tableSecondModelPackages = new DefaultTableModel(null, columnsAddedPackages);			

		final JTable tableOfAddedPackages = new JTable(tableSecondModelPackages);
		scrollPaneAddedPackages.setViewportView(tableOfAddedPackages);
		disposeColumns(tableOfAddedPackages);
        
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
		
		JButton addCourseBtn = new JButton("Adicionar Curso");
		addCourseBtn.setBounds(835, 97, 151, 31);
		contentPane.add(addCourseBtn);
		
		JButton removeCourseBtn = new JButton("Remover Curso");
		removeCourseBtn.setBounds(835, 137, 151, 31);
		contentPane.add(removeCourseBtn);
		
		JButton addPackageBtn = new JButton("Adicionar Pacote");
		addPackageBtn.setBounds(835, 337, 151, 31);
		contentPane.add(addPackageBtn);
		
		JButton removePackageBtn = new JButton("Remover Pacote");
		removePackageBtn.setBounds(835, 377, 151, 31);
		contentPane.add(removePackageBtn);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(359, 123, 140, 27);
		contentPane.add(textField);

	}


	/**
	 * Dispose the id and duration columns
	 * @param table - Receives the table to dispose columns
	 */
	private void disposeColumns(JTable table) {
		
		TableColumnModel tableModel = table.getColumnModel();
		
		tableModel.getColumn(1).setMinWidth(0);     
		tableModel.getColumn(1).setPreferredWidth(0);  
		tableModel.getColumn(1).setMaxWidth(0);    
	}
}