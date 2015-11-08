package view;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import model.Course;
import model.Package;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;
import controller.CourseController;
import controller.EnrollController;
import controller.PackageController;
import controller.StudentController;
import exception.AddressException;
import exception.CPFException;
import exception.CourseException;
import exception.DateException;
import exception.PackageException;
import exception.PaymentException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;

public class EditStudent  extends View{
	
 	
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField cellField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField cepField;
	private JTextField cityField;
	private JTextField emailField;
	private JFormattedTextField birthdateField;
	private JTextField motherField;
	private JTextField fatherField;
	private JLabel firstListLabel;
	private JLabel secondListLabel;
	private JTextField paymentValueField;
	private JTextField paymentInstallmentsField;
	private JLabel paymentTypeLbl;
	private DefaultTableModel courseTableModel;
	private DefaultTableModel packageTableModel;
	private JTextField ddCellField;
	private JTextField ddPhoneField;
	private JTextField issuingInstitutionField;
	private JTextField ufField;
	private JTextField numberField;
	private JTextField complementField;
	private JComboBox<String> paymentForms;
	private JComboBox<String> paymentTypes;
	private JComboBox<String> packages;
	private DefaultComboBoxModel<String> availableCourses;
	private DefaultComboBoxModel<String> availablePackages;
	private ArrayList<String> coursesId = new ArrayList<String>(); 
	private ArrayList<String> coursesName = new ArrayList<String>();
	private ArrayList<String> packagesId = new ArrayList<String>();
	private ArrayList<String> packagesName = new ArrayList<String>();
	private JTable tableOfAddedCourses;
	private JTable tableOfAddedPackages;
	private ArrayList<String> addedCoursesId = new ArrayList<String>(); 
	private ArrayList<String> addedPackagesId = new ArrayList<String>();
	private JButton editlBtn;
	
	public EditStudent() {
		
		JLabel editStudentLbl = new JLabel("Editar Aluno");
		getContentPane().add(editStudentLbl);
		editStudentLbl.setBounds(399, 12, 275, 31);
		editStudentLbl.setFont(new Font("Dialog", Font.BOLD, 20));
		getContentPane().add(editStudentLbl);
		
		JLabel dataOfStudentLbl = new JLabel("DADOS DO ALUNO");
        dataOfStudentLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        dataOfStudentLbl.setBounds(189, 36, 150, 17);
        getContentPane().add(dataOfStudentLbl);
        
        JLabel nameLbl = new JLabel("Nome");
        nameLbl.setBounds(40, 60, 70, 17);
        getContentPane().add(nameLbl);
        
        nameField = new JTextField();
        nameField.setBounds(85, 55, 434, 27);
        getContentPane().add(nameField);
        nameField.setColumns(10);
        
        JLabel birthdateLabel = new JLabel("Data de Nascimento");
        birthdateLabel.setBounds(30, 171, 200, 17);
        getContentPane().add(birthdateLabel);
        
        MaskFormatter birthdateMask;
		try{
			birthdateMask = new MaskFormatter("##/##/####");
			birthdateMask.setValidCharacters("0123456789");
			birthdateMask.setValueContainsLiteralCharacters(true);
	        birthdateField = new JFormattedTextField(birthdateMask);
		}
		catch(ParseException e2){
			e2.printStackTrace();
		}

        birthdateField.setBounds(30, 195, 190, 27);
        birthdateField.setColumns(10);
        getContentPane().add(birthdateField);
        
        JLabel cellLabel = new JLabel("Celular");
        cellLabel.setBounds(285, 171, 70, 17);
        getContentPane().add(cellLabel);
               
        JLabel phoneLabel = new JLabel("Telefone");
        phoneLabel.setBounds(285, 205, 70, 17);
        getContentPane().add(phoneLabel);
        
        ddCellField = new JTextField();
        ddCellField.setBounds(359, 166, 40, 27);
        ddCellField.setColumns(10);
        getContentPane().add(ddCellField);
        
        cellField = new JTextField();
        cellField.setBounds(399, 166, 100, 27);
        cellField.setColumns(10);
        getContentPane().add(cellField);
        
        ddPhoneField = new JTextField();
        ddPhoneField.setBounds(359, 200, 40, 27);
        ddPhoneField.setColumns(10);
        getContentPane().add(ddPhoneField);
        
        phoneField = new JTextField();
        phoneField.setBounds(399, 200, 100, 27);
        phoneField.setColumns(10);
        getContentPane().add(phoneField);
        
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(30, 248, 70, 17);
        getContentPane().add(emailLabel);
        
        emailField = new JTextField();
        emailField.setBounds(85, 243, 334, 27);
        getContentPane().add(emailField);
		
        JLabel addressLabel = new JLabel("Endereço");
        addressLabel.setBounds(30, 282, 70, 17);
        getContentPane().add(addressLabel);
        
        addressField = new JTextField();
        addressField.setBounds(105, 277, 344, 27);
        getContentPane().add(addressField);

        JLabel cepLabel = new JLabel("CEP");
        cepLabel.setBounds(416, 321, 33, 17);
        getContentPane().add(cepLabel);
        
        cepField = new JTextField();
        cepField.setBounds(455, 316, 84, 27);
        getContentPane().add(cepField);

        JLabel cityLabel = new JLabel("Cidade");
        cityLabel.setBounds(266, 321, 70, 17);
        getContentPane().add(cityLabel);
        
        cityField = new JTextField();
        cityField.setBounds(326, 316, 85, 27);
        getContentPane().add(cityField);

        JLabel motherLabel = new JLabel("Nome da mãe");
        motherLabel.setBounds(30, 369, 95, 17);
        getContentPane().add(motherLabel);
        
        motherField = new JTextField();
        motherField.setBounds(137, 364, 402, 27);
        getContentPane().add(motherField);

        JLabel fatherLabel = new JLabel("Nome do pai");
        fatherLabel.setBounds(30, 409, 95, 17);
        getContentPane().add(fatherLabel);
        
        fatherField = new JTextField();
        fatherField.setBounds(137, 404, 402, 27);
        getContentPane().add(fatherField);
        
        firstListLabel = new JLabel();
        firstListLabel.setBounds(562, 73, 120, 17);
        getContentPane().add(firstListLabel);
        		
		JLabel coursesLabel = new JLabel("Cursos");
		coursesLabel.setBounds(546, 73, 70, 17);
		getContentPane().add(coursesLabel);
		
		secondListLabel = new JLabel("Pacotes");
        secondListLabel.setBounds(546, 308, 70, 17);
        getContentPane().add(secondListLabel);
        
        JLabel dataOfPaymentLbl = new JLabel("DADOS DO PAGAMENTO");
        dataOfPaymentLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        dataOfPaymentLbl.setBounds(189, 443, 200, 17);
        getContentPane().add(dataOfPaymentLbl);
        
        JLabel paymentForm = new JLabel("Forma de pagamento");
        paymentForm.setBounds(42, 520, 171, 17);
        getContentPane().add(paymentForm);
        
        paymentTypeLbl = new JLabel("Tipo de pagamento");
        paymentTypeLbl.setBounds(42, 472, 150, 17);
        getContentPane().add(paymentTypeLbl);

        JLabel paymentValue = new JLabel("Valor total");
        paymentValue.setBounds(30, 586, 200, 17);
        getContentPane().add(paymentValue);
                
        paymentValueField = new JTextField();
        paymentValueField.setBounds(105, 580, 120, 27);
        getContentPane().add(paymentValueField);
        
        paymentInstallmentsField = new JTextField();
        paymentInstallmentsField.setBounds(412, 581, 27, 27);
        getContentPane().add(paymentInstallmentsField);
        
        JLabel paymentInstallments = new JLabel("Quantidade de Parcelas");
        paymentInstallments.setBounds(229, 580, 190, 17);
        getContentPane().add(paymentInstallments);
        
        paymentTypes = new JComboBox<String>();
        paymentTypes.setBounds(221, 472, 151, 24);
        getContentPane().add(paymentTypes);
        
        DefaultComboBoxModel<String> paymentTypesModel = new DefaultComboBoxModel<String>();
        paymentTypesModel.addElement("À vista");
        paymentTypesModel.addElement("Parcelado");
        
        paymentTypes.setModel(paymentTypesModel);
        
        paymentForms = new JComboBox<String>();
        paymentForms.setBounds(221, 516, 151, 24);
        getContentPane().add(paymentForms);
        
        DefaultComboBoxModel<String> paymentFormsModel = new DefaultComboBoxModel<String>();
        paymentFormsModel.addElement("Dinheiro");
        paymentFormsModel.addElement("Cartão");
        paymentFormsModel.addElement("Cheque");
        
        paymentForms.setModel(paymentFormsModel);
        
        packages = new JComboBox<String>();
        packages.setBounds(553, 97, 251, 31);
        getContentPane().add(packages);
        availableCourses = new DefaultComboBoxModel<String>();
		
        packages.setModel(availableCourses);
        
		JScrollPane scrollPaneAddedCourses = new JScrollPane();
		scrollPaneAddedCourses.setBounds(553, 130, 251, 169);
		getContentPane().add(scrollPaneAddedCourses);
		scrollPaneAddedCourses.setBackground(Color.WHITE);
		
        packages = new JComboBox<String>();
		packages.setBounds(553, 337, 251, 31);
		getContentPane().add(packages);
        
		availablePackages = new DefaultComboBoxModel<String>();
        
        packages.setModel(availablePackages);
		
		JScrollPane scrollPaneAddedPackages = new JScrollPane();
		scrollPaneAddedPackages.setBounds(553, 370, 251, 169);
		getContentPane().add(scrollPaneAddedPackages);
		scrollPaneAddedPackages.setBackground(Color.WHITE);
					
		String [] columnsAddedCourses = {"Cursos adicionados", "ID"};
		
		courseTableModel = new DefaultTableModel(null, columnsAddedCourses);			

		tableOfAddedCourses = new JTable(courseTableModel);
		scrollPaneAddedCourses.setViewportView(tableOfAddedCourses);
		disposeColumns(tableOfAddedCourses);
			
		String [] columnsAddedPackages = {"Pacotes adicionados", "ID"};
		
		packageTableModel = new DefaultTableModel(null, columnsAddedPackages);			

		tableOfAddedPackages = new JTable(packageTableModel);
		scrollPaneAddedPackages.setViewportView(tableOfAddedPackages);
		disposeColumns(tableOfAddedPackages);
	}

	private void disposeColumns(JTable table) {
		
		TableColumnModel tableModel = table.getColumnModel();
		
		tableModel.getColumn(1).setMinWidth(0);     
		tableModel.getColumn(1).setPreferredWidth(0);  
		tableModel.getColumn(1).setMaxWidth(0);    
	}

/**
 * Method used to get the available packages
 * @throws CourseException 
 * @throws PackageException 
 */
	@SuppressWarnings("unused")
	private void getAllPackagesToSelect() throws CourseException, PackageException  {
	
		PackageController packageController = new PackageController();
		ArrayList<Package> packages = packageController.getPackages();		
		int indexOfPackages = 0;
		
		while(indexOfPackages < packages.size()){
			
			Package currentPackage = packages.get(indexOfPackages);
			Integer packageId = currentPackage.getId();
			String packageName = (currentPackage.getName());
	
			packagesId.add(packageId.toString());
			packagesName.add(packageName);
			
			availablePackages.addElement(packageName);
			
			indexOfPackages++;
		}
	}

/**
 *  Method used to show all available courses 
 * @throws SQLException
 * @throws CourseException 
 */
	@SuppressWarnings("unused")
	private void getAllCoursesToSelect() throws CourseException {
		
		CourseController courseController = new CourseController();
		ArrayList<Course> courses = courseController.showCourse();		
		int indexOfCourses = 0;
		
		while(indexOfCourses < courses.size()){
			
			Course course = courses.get(indexOfCourses);
			Integer courseId = course.getId();
			String courseName = (course.getName());
	
			coursesId.add(courseId.toString());
			coursesName.add(courseName);
			
			availableCourses.addElement(courseName);
			
			indexOfCourses++;
			
		}
	
	
		JButton registerStudentButton = new JButton("Alterar");
		registerStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		registerStudentButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				
				String studentName = nameField.getText();
				Date birthdate = (Date) birthdateField.getValue();
				String email = emailField.getText();
				Address address = (Address) ((JFormattedTextField) addressField).getValue();
				Phone principalPhone = (Phone) ((JFormattedTextField)  cellField).getValue();
				Phone secondaryPhone = (Phone) ((JFormattedTextField) phoneField).getValue();
				String motherName = motherField.getText();
				String fatherName = fatherField.getText();
				
				try{
											
					StudentController studentController = new StudentController();
					
					boolean studentWasUpdated = studentController.updateStudent(
						studentName,
						birthdate,
						email,
						address,
						principalPhone,
						secondaryPhone,
						motherName,
						fatherName
					);
					
					String message = "";
					if(studentWasUpdated){
						message = "Aluno alterado com sucesso.";
					}
					else{
						message = "Não foi possível alterar o aluno informado. Tente novamente.";
					}
					
					showInfoMessage(message);
					
					dispose();
					
					SearchStudent searchStudent = new SearchStudent();
					searchStudent.setVisible(true);
					
				}catch(Exception caughtException){
					
					showInfoMessage(caughtException.getMessage());
				}
			}
		});
		registerStudentButton.setBounds(422, 631, 117, 25);
		getContentPane().add(registerStudentButton);
	
	JButton addCourseBtn = new JButton("Adicionar Curso");
	addCourseBtn.setBounds(835, 97, 151, 31);
	contentPane.add(addCourseBtn);
	addCourseBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			int indexOfSelectedCourse = packages.getSelectedIndex();
			addCourseToAddedCourses(indexOfSelectedCourse);
			availableCourses.removeElementAt(indexOfSelectedCourse);
			addedCoursesId.add(coursesId.get(indexOfSelectedCourse));
			coursesId.remove(indexOfSelectedCourse);
			coursesName.remove(indexOfSelectedCourse);
		}
		
		private void addCourseToAddedCourses(int indexOfSelectedCourse) {
			
			String courseId = coursesId.get(indexOfSelectedCourse);
			String courseName = coursesName.get(indexOfSelectedCourse);

			String[] allCourses = new String[2];
	
			allCourses[0] = (courseName);
			allCourses[1] = (courseId.toString());
			
			courseTableModel.addRow(allCourses);
		}
	});
	
	JButton removeCourseBtn = new JButton("Remover Curso");
	removeCourseBtn.setBounds(835, 137, 151, 31);
	contentPane.add(removeCourseBtn);
	removeCourseBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			int selectedRow = tableOfAddedCourses.getSelectedRow();

			String courseName = (String) courseTableModel.getValueAt(selectedRow, 0);
			String courseId = (String) courseTableModel.getValueAt(selectedRow, 1);
							
			coursesId.add(courseId);
			coursesName.add(courseName);
			
			addedCoursesId.remove(selectedRow);
			
			availableCourses.addElement(courseName);
			
			courseTableModel.removeRow(selectedRow);
		}
		
	});
	
	JButton addPackageBtn = new JButton("Adicionar Pacote");
	addPackageBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			int indexOfSelectedPackage = packages.getSelectedIndex();
			
			addPackageToAddedCourses(indexOfSelectedPackage);
			
			availablePackages.removeElementAt(indexOfSelectedPackage);
			addedPackagesId.add(packagesId.get(indexOfSelectedPackage));
			packagesId.remove(indexOfSelectedPackage);
			packagesName.remove(indexOfSelectedPackage);
		}
		
		private void addPackageToAddedCourses(int indexOfSelectedPackage){
			
			String packageId = packagesId.get(indexOfSelectedPackage);
			String packageName = packagesName.get(indexOfSelectedPackage);

			String[] allPackages = new String[2];
	
			allPackages[0] = (packageName);
			allPackages[1] = (packageId.toString());
			
			packageTableModel.addRow(allPackages);
		}
	});
	addPackageBtn.setBounds(835, 337, 151, 31);
	contentPane.add(addPackageBtn);
	
	JButton removePackageBtn = new JButton("Remover Pacote");
	removePackageBtn.setBounds(835, 377, 151, 31);
	removePackageBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			int selectedRow = tableOfAddedPackages.getSelectedRow();

			String packageName = (String) packageTableModel.getValueAt(selectedRow, 0);
			String packageId = (String) packageTableModel.getValueAt(selectedRow, 1);
							
			packagesId.add(packageId);
			packagesName.add(packageName);
			
			addedPackagesId.remove(selectedRow);
			
			availablePackages.addElement(packageName);
				
			packageTableModel.removeRow(selectedRow);
		}
		
	});
	contentPane.add(removePackageBtn);
	
	}

}
