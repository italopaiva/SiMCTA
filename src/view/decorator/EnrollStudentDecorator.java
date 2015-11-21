package view.decorator;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import view.PersonView;
import view.View;
import model.Course;
import model.Package;
import model.Person;
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

public class EnrollStudentDecorator extends PersonDecorator {
	
	private JTextField paymentValueField;
	private JTextField paymentInstallmentsField;
	private DefaultTableModel courseTableModel;
	private DefaultTableModel packageTableModel;
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
	private JLabel paymentTypeLbl; 
	
	public EnrollStudentDecorator(PersonView viewToDecorate) {
		super(viewToDecorate);
	}

	@Override
	public void createLabelsAndFields(JFrame frame, Person student){
		
		super.createLabelsAndFields(frame,student);
        
		registerPersonLbl.setText("Matricular novo aluno");
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

        cepField.setBounds(535, 316, 84, 27);
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

        JLabel paymentValue = new JLabel("Valor total");
        paymentValue.setBounds(30, 580, 200, 17);
        frame.getContentPane().add(paymentValue);
                       
        JLabel paymentInstallments = new JLabel("Quantidade de Parcelas");
        paymentInstallments.setBounds(229, 580, 190, 17);
        frame.getContentPane().add(paymentInstallments);
        
        paymentValueField = new JTextField();
        paymentValueField.setBounds(105, 580, 120, 27);
        frame.getContentPane().add(paymentValueField);
        
        paymentInstallmentsField = new JTextField();
        paymentInstallmentsField.setBounds(412, 581, 27, 27);
        frame.getContentPane().add(paymentInstallmentsField);
  
        packages = new JComboBox<String>();
        packages.setBounds(583, 97, 251, 31);
        frame.getContentPane().add(packages);
        availableCourses = new DefaultComboBoxModel<String>();
		
        packages.setModel(availableCourses);
        
		JScrollPane scrollPaneAddedCourses = new JScrollPane();
		scrollPaneAddedCourses.setBounds(583, 130, 251, 169);
		frame.getContentPane().add(scrollPaneAddedCourses);
		scrollPaneAddedCourses.setBackground(Color.WHITE);
		
        packages = new JComboBox<String>();
		packages.setBounds(583, 377, 251, 31);
		frame.getContentPane().add(packages);
        
		availablePackages = new DefaultComboBoxModel<String>();
        
        packages.setModel(availablePackages);
        
        JLabel paymentForm = new JLabel("Forma de pagamento");
        paymentForm.setBounds(72, 520, 171, 17);
        frame.getContentPane().add(paymentForm);
        
        paymentTypeLbl = new JLabel("Tipo de pagamento");
        paymentTypeLbl.setBounds(72, 472, 150, 17);
        frame.getContentPane().add(paymentTypeLbl);
        
        paymentTypes = new JComboBox<String>();
        paymentTypes.setBounds(221, 472, 151, 24);
        frame.getContentPane().add(paymentTypes);
        
        DefaultComboBoxModel<String> paymentTypesModel = new DefaultComboBoxModel<String>();
        paymentTypesModel.addElement("À vista");
        paymentTypesModel.addElement("Parcelado");
        
        paymentTypes.setModel(paymentTypesModel);
        
        paymentForms = new JComboBox<String>();
        paymentForms.setBounds(221, 516, 151, 24);
        frame.getContentPane().add(paymentForms);
        
        DefaultComboBoxModel<String> paymentFormsModel = new DefaultComboBoxModel<String>();
        paymentFormsModel.addElement("Dinheiro");
        paymentFormsModel.addElement("Cartão");
        paymentFormsModel.addElement("Cheque");
        
        paymentForms.setModel(paymentFormsModel);
		
		JScrollPane scrollPaneAddedPackages = new JScrollPane();
		scrollPaneAddedPackages.setBounds(583, 410, 251, 169);
		frame.getContentPane().add(scrollPaneAddedPackages);
		scrollPaneAddedPackages.setBackground(Color.WHITE);
					
		String [] columnsAddedCourses = {"Cursos adicionados", "ID"};
		
		courseTableModel = new DefaultTableModel(null, columnsAddedCourses);			

		tableOfAddedCourses = new JTable(courseTableModel);
		scrollPaneAddedCourses.setViewportView(tableOfAddedCourses);
			
		String [] columnsAddedPackages = {"Pacotes adicionados", "ID"};
		
		packageTableModel = new DefaultTableModel(null, columnsAddedPackages);			

		tableOfAddedPackages = new JTable(packageTableModel);
		scrollPaneAddedPackages.setViewportView(tableOfAddedPackages);
		
		disposeColumns(tableOfAddedCourses);

		disposeColumns(tableOfAddedPackages);
 
	
		try {
			getAllCoursesToSelect();
			getAllPackagesToSelect();
		} catch (CourseException | PackageException e1) {
			e1.printStackTrace();
		}
		
	}
	
	@Override
	public void createMasks(JFrame frame){
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
	       
        JButton enrollBtn = new JButton("Matricular");
		enrollBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e){
				
				prepareData();
				
			}

		});
		enrollBtn.setBounds(422, 631, 117, 25);
		frame.getContentPane().add(enrollBtn);
		
		JButton addCourseBtn = new JButton("Adicionar Curso");
		addCourseBtn.setBounds(835, 97, 151, 31);
		frame.getContentPane().add(addCourseBtn);
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
		frame.getContentPane().add(removeCourseBtn);
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
		addPackageBtn.setBounds(835, 410, 151, 31);
		frame.getContentPane().add(addPackageBtn);
		
		JButton removePackageBtn = new JButton("Remover Pacote");
		removePackageBtn.setBounds(835, 450, 151, 31);
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
		frame.getContentPane().add(removePackageBtn);
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
	
	/**
	 * Method used to get the available packages
	 * @throws CourseException 
	 * @throws PackageException 
	 */
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
	}
	
	private void prepareData(){
		
		String message = "";
		try{
			
			String studentName = nameField.getText();
			
			String cpf = cpfField.getText();
			CPF studentCpf = new CPF(cpf);
			
			String rgNumber = rgField.getText();
			String rgIssuingInstitution = issuingInstitutionField.getText();
			String rgUf = ufField.getText();
			
			RG studentRg = new RG(rgNumber, rgIssuingInstitution, rgUf);					
			
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
			
			int paymentType = paymentTypes.getSelectedIndex();
			
			switch(paymentType){
				case 0:
					paymentType = 1;
					break;
				case 1:
					paymentType = 2;
					break;
				default:
					showInfoMessage("Tipo de pagamento inválido.");
					break;
			}
			
			int paymentForm = paymentForms.getSelectedIndex();
			
			switch(paymentForm){
				case 0:
					paymentForm = 1;
					break;
				case 1:
					paymentForm = 2;
					break;
				case 2:
					paymentForm = 3;
					break;
				default:
					showInfoMessage("Forma de pagamento inválida.");
					break;
			}
			
			
			String paymentInstallments = paymentInstallmentsField.getText(); 
			
			Integer installments;
			if(!paymentInstallments.isEmpty()){
				installments = new Integer(paymentInstallments);
			}
			else{
				installments = 0;
			}
			
			EnrollController enroll = new EnrollController();
			enroll.enrollStudent(studentName, studentCpf, studentRg, birthdate, email, address,
											 principalPhone, secondaryPhone, motherName, fatherName,
											 addedCoursesId, addedPackagesId, paymentType, paymentForm, installments);
			
			message = "Aluno matriculado com sucesso.";
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
			message = e1.getMessage();
		}
		catch(ServiceException e1){
			message = e1.getMessage();
		}
		catch(PaymentException e1){
			message = e1.getMessage();
		}
		finally{
			showInfoMessage(message);
		}
	}
}