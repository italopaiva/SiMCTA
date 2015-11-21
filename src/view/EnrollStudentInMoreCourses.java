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
import model.Student;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;
import controller.CourseController;
import controller.PackageController;
import controller.ServiceController;
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

public class EnrollStudentInMoreCourses extends View {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField cpfField;
	private JLabel firstListLabel;
	private JLabel secondListLabel;
	private JTextField paymentValueField;
	private JTextField paymentInstallmentsField;
	private JLabel paymentTypeLbl;
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
	private CPF studentCpf;

	public EnrollStudentInMoreCourses(CPF studentCpf, String studentName) {
		
		super();
		
		setStudentCpf(studentCpf);

		getContentPane().setLayout(null);

		createLabelsAndFields();

		try {
			showInformationOfStudent(this.studentCpf, studentName);
			
			getAllCoursesToSelect();

			getAllPackagesToSelect();
			
		} catch (CourseException | PackageException e) {
			showInfoMessage(e.getMessage());
		}
	}
	
	private void setStudentCpf(CPF studentCpf_){
		this.studentCpf = studentCpf_;
	}

	/**
	 * Creates the labels and fields of the frame
	 */
	protected void createLabelsAndFields() {

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel enrollStudentLbl = new JLabel("Matricular Aluno em Mais Cursos");
		enrollStudentLbl.setBounds(80, 12, 419, 31);
		enrollStudentLbl.setFont(new Font("Dialog", Font.BOLD, 20));
		contentPane.add(enrollStudentLbl);

		JLabel dataOfStudentLbl = new JLabel("DADOS DO ALUNO");
		dataOfStudentLbl.setFont(new Font("Dialog", Font.BOLD, 12));
		dataOfStudentLbl.setBounds(189, 36, 150, 17);
		contentPane.add(dataOfStudentLbl);

		JLabel nameLbl = new JLabel("Nome");
		nameLbl.setBounds(40, 60, 70, 17);
		contentPane.add(nameLbl);

		nameField = new JTextField();
		nameField.setBounds(85, 55, 434, 27);
		contentPane.add(nameField);
		nameField.setColumns(10);

		JLabel cpfLabel = new JLabel("CPF");
		cpfLabel.setBounds(579, 60, 70, 17);
		contentPane.add(cpfLabel);

		cpfField = new JTextField();
		cpfField.setBounds(611, 55, 129, 27);
		contentPane.add(cpfField);
		cpfField.setColumns(10);

		firstListLabel = new JLabel();
		firstListLabel.setBounds(105, 112, 120, 17);
		contentPane.add(firstListLabel);

		JLabel coursesLabel = new JLabel("Cursos");
		coursesLabel.setBounds(40, 142, 70, 17);
		contentPane.add(coursesLabel);

		secondListLabel = new JLabel("Pacotes");
		secondListLabel.setBounds(572, 142, 70, 17);
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

		paymentTypes = new JComboBox<String>();
		paymentTypes.setBounds(221, 472, 151, 24);
		contentPane.add(paymentTypes);

		DefaultComboBoxModel<String> paymentTypesModel = new DefaultComboBoxModel<String>();
		paymentTypesModel.addElement("À vista");
		paymentTypesModel.addElement("Parcelado");

		paymentTypes.setModel(paymentTypesModel);

		paymentForms = new JComboBox<String>();
		paymentForms.setBounds(221, 516, 151, 24);
		contentPane.add(paymentForms);

		DefaultComboBoxModel<String> paymentFormsModel = new DefaultComboBoxModel<String>();
		paymentFormsModel.addElement("Dinheiro");
		paymentFormsModel.addElement("Cartão");
		paymentFormsModel.addElement("Cheque");

		paymentForms.setModel(paymentFormsModel);

		packages = new JComboBox<String>();
		packages.setBounds(86, 166, 251, 31);
		contentPane.add(packages);
		availableCourses = new DefaultComboBoxModel<String>();

		packages.setModel(availableCourses);

		JScrollPane scrollPaneAddedCourses = new JScrollPane();
		scrollPaneAddedCourses.setBounds(86, 199, 251, 169);
		contentPane.add(scrollPaneAddedCourses);
		scrollPaneAddedCourses.setBackground(Color.WHITE);

		packages = new JComboBox<String>();
		packages.setBounds(579, 171, 251, 31);
		contentPane.add(packages);

		availablePackages = new DefaultComboBoxModel<String>();

		packages.setModel(availablePackages);

		JScrollPane scrollPaneAddedPackages = new JScrollPane();
		scrollPaneAddedPackages.setBounds(579, 204, 251, 169);
		contentPane.add(scrollPaneAddedPackages);
		scrollPaneAddedPackages.setBackground(Color.WHITE);

		String[] columnsAddedCourses = { "Cursos adicionados", "ID" };

		courseTableModel = new DefaultTableModel(null, columnsAddedCourses);

		tableOfAddedCourses = new JTable(courseTableModel);
		scrollPaneAddedCourses.setViewportView(tableOfAddedCourses);
		disposeColumns(tableOfAddedCourses);

		String[] columnsAddedPackages = { "Pacotes adicionados", "ID" };

		packageTableModel = new DefaultTableModel(null, columnsAddedPackages);

		tableOfAddedPackages = new JTable(packageTableModel);
		scrollPaneAddedPackages.setViewportView(tableOfAddedPackages);
		disposeColumns(tableOfAddedPackages);

		JButton enrollBtn = new JButton("Matricular");
		enrollBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				boolean sucess = prepareData();
				if (sucess){
					dispose();
					SearchStudent searchStudent = new SearchStudent();
					searchStudent.setVisible(true);
				}

			}

		});
		enrollBtn.setBounds(514, 582, 117, 25);
		contentPane.add(enrollBtn);

		JButton addCourseBtn = new JButton("Adicionar Curso");
		addCourseBtn.setBounds(368, 166, 151, 31);
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
		removeCourseBtn.setBounds(368, 206, 151, 31);
		contentPane.add(removeCourseBtn);
		removeCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int selectedRow = tableOfAddedCourses.getSelectedRow();

				String courseName = (String) courseTableModel.getValueAt(
						selectedRow, 0);
				String courseId = (String) courseTableModel.getValueAt(
						selectedRow, 1);

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

			private void addPackageToAddedCourses(int indexOfSelectedPackage) {

				String packageId = packagesId.get(indexOfSelectedPackage);
				String packageName = packagesName.get(indexOfSelectedPackage);

				String[] allPackages = new String[2];

				allPackages[0] = (packageName);
				allPackages[1] = (packageId.toString());

				packageTableModel.addRow(allPackages);
			}
		});
		addPackageBtn.setBounds(861, 171, 151, 31);
		contentPane.add(addPackageBtn);

		JButton removePackageBtn = new JButton("Remover Pacote");
		removePackageBtn.setBounds(861, 211, 151, 31);
		removePackageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int selectedRow = tableOfAddedPackages.getSelectedRow();

				String packageName = (String) packageTableModel.getValueAt(
						selectedRow, 0);
				String packageId = (String) packageTableModel.getValueAt(
						selectedRow, 1);

				packagesId.add(packageId);
				packagesName.add(packageName);

				addedPackagesId.remove(selectedRow);

				availablePackages.addElement(packageName);

				packageTableModel.removeRow(selectedRow);
			}

		});
		contentPane.add(removePackageBtn);

	}

	/**
	 * Dispose the id and duration columns
	 * 
	 * @param table
	 *            - Receives the table to dispose columns
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
	private void getAllPackagesToSelect() throws CourseException, PackageException {

		PackageController packageController = new PackageController();
		ArrayList<Package> packages = packageController.getPackages();
		int indexOfPackages = 0;

		while (indexOfPackages < packages.size()) {

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
	 * Method used to show all available courses
	 * 
	 * @throws SQLException
	 * @throws CourseException
	 */
	private void getAllCoursesToSelect() throws CourseException {

		CourseController courseController = new CourseController();
		ArrayList<Course> courses = courseController.showCourse();
		int indexOfCourses = 0;

		while (indexOfCourses < courses.size()) {

			Course course = courses.get(indexOfCourses);
			Integer courseId = course.getId();
			String courseName = (course.getName());

			coursesId.add(courseId.toString());
			coursesName.add(courseName);

			availableCourses.addElement(courseName);

			indexOfCourses++;

		}
	}
	
	private void showInformationOfStudent(CPF studentCpf_, String studentName) {
		cpfField.setText(studentCpf_.getCpf());
		cpfField.setEditable(false);
		nameField.setText(studentName);
		nameField.setEditable(false);
	}

	private boolean prepareData() {

		String message = "";
		boolean sucess = false;
		try {

			String studentName = nameField.getText();

			String cpf = cpfField.getText();
			CPF studentCpf = new CPF(cpf);

			int paymentType = paymentTypes.getSelectedIndex();

			switch (paymentType) {
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

			switch (paymentForm) {
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
			if (!paymentInstallments.isEmpty()) {
				installments = new Integer(paymentInstallments);
			} else {
				installments = 0;
			}
			
			ServiceController serviceController = new ServiceController();
			serviceController.newService(studentCpf, studentName, addedCoursesId,
					addedPackagesId, paymentType, paymentForm, installments);

			message = "Novos cursos/servicos adicionados criados com sucesso.";
			sucess = true;
		} catch (CPFException e1) {
			message = e1.getMessage();
		} catch (StudentException e1) {
			message = e1.getMessage();
		} catch (ServiceException e1) {
			message = e1.getMessage();
		} catch (PaymentException e1) {
			message = e1.getMessage();
		} catch (PersonException e1) {
			message = e1.getMessage();
		} catch (PhoneException e) {
			message = e.getMessage();
			e.printStackTrace();
		} catch (DateException e) {
			message = e.getMessage();
			e.printStackTrace();
		} catch (AddressException e) {
			message = e.getMessage();
			e.printStackTrace();
		} catch (RGException e) {
			message = e.getMessage();
			e.printStackTrace();
		} finally {
			showInfoMessage(message);
		}
		return sucess;
	}
	
}