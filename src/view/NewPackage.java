package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.CourseController;
import controller.PackageController;
import exception.CourseException;
import exception.PackageException;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;

import util.ButtonColumn;

public class NewPackage extends View{
	
	private JPanel contentPane;
	private JTextField packageNameField;
	private JFormattedTextField valueField;
	private DefaultTableModel tableModel;
	private int quantityOfCourses;
	private JLabel coursesOfPackage;
	private String addedCourses;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public NewPackage() throws SQLException {
		
		super();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		packageNameField = new JTextField();
		packageNameField.setBounds(276, 74, 346, 30);
		contentPane.add(packageNameField);
		packageNameField.setColumns(10);
		
		JLabel packageNameLabel = new JLabel("Nome do pacote");
		packageNameLabel.setBounds(276, 50, 124, 17);
		contentPane.add(packageNameLabel);
		
		JLabel lblC = new JLabel("Novo pacote");
		lblC.setFont(new Font("Dialog", Font.BOLD, 20));
		lblC.setBounds(426, 12, 144, 28);
		contentPane.add(lblC);
		
		JLabel valueLabel = new JLabel("Valor");
		valueLabel.setBounds(278, 120, 70, 15);
		contentPane.add(valueLabel);
		
		JLabel coursesLabel = new JLabel("Cursos");
		coursesLabel.setBounds(276, 202, 144, 15);
		contentPane.add(coursesLabel);
		
		try{
			
			MaskFormatter durationMask = new MaskFormatter("## semanas");
			durationMask.setValidCharacters("0123456789");
			durationMask.setValueContainsLiteralCharacters(false);
			
			MaskFormatter valueMask = new MaskFormatter("R$ ####,##");
			valueMask.setValidCharacters("0123456789");
			valueMask.setValueContainsLiteralCharacters(false);
			
			valueField = new JFormattedTextField(valueMask);
			valueField.setBounds(276, 147, 124, 28);
			contentPane.add(valueField);
			
			JButton registerPackageButton = new JButton("Cadastrar");
			registerPackageButton.setBackground(Color.WHITE);
			registerPackageButton.addMouseListener(new MouseAdapter(){
				
				@Override
				public void mouseClicked(MouseEvent e){		
					
					String packageName = packageNameField.getText();					
					Integer packageDuration;
					Integer packageValue;
					Object packageValueField = valueField.getValue(); 
					if(!(packageValueField == null)){
						
						String value = packageValueField.toString();
						packageValue = new Integer(value);
					}else{
						packageValue = new Integer(0);
					}
					
					try{
												
						PackageController packageController = new PackageController();
						
						boolean packageWasSaved = packageController.newPackage(
							packageName,
							packageValue
						);
						
						String message = "";
						if(packageWasSaved){
							message = "Pacote cadastrado com sucesso.";
						}else{
							message = "Não foi possível cadastrar o pacote informado. Tente novamente.";
						}
						
						showInfoMessage(message);
						
						dispose();
						
						SimCta mainPage = new SimCta();
						mainPage.setVisible(true);
						
					}catch(PackageException caughtException){
						
						showInfoMessage(caughtException.getMessage());
					}
				}
			});
			registerPackageButton.setBounds(456, 525, 114, 25);
			contentPane.add(registerPackageButton);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(276, 231, 353, 169);
			contentPane.add(scrollPane);
			scrollPane.setBackground(Color.WHITE);
			
			String [] columns = {"Curso", ""};
			
			tableModel = new DefaultTableModel(null, columns);			

			final JTable tableOfCourses = new JTable(tableModel);
			
			tableOfCourses.setBackground(Color.WHITE);
			scrollPane.setViewportView(tableOfCourses);
			
			CourseController course = new CourseController();

			getAllCoursesToSelect(course);
			
			final ArrayList <String> courses = new ArrayList<String>(); 
			final JTextArea listAddedCourses = new JTextArea();
			listAddedCourses.setBounds(276, 283, 294, -55);
			contentPane.add(listAddedCourses);

			JLabel label = new JLabel("Adicionados:");
			label.setBounds(673, 202, 144, 15);
			contentPane.add(label);
			
			coursesOfPackage = new JLabel();
			coursesOfPackage.setBounds(673, 218, 140, 311);
			contentPane.add(coursesOfPackage);
			
			addedCourses = coursesOfPackage.getText();
			
			quantityOfCourses = 0;
			Action addCourses = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					
					int currentRow = tableOfCourses.getSelectedRow();
					
					String addedCourse = (String) tableOfCourses.getValueAt(currentRow, 0);
					courses.add(addedCourse);
					
					// Erases the selected row 
					int modelRow = Integer.valueOf( e.getActionCommand() );
					
				    ((DefaultTableModel)tableOfCourses.getModel()).removeRow(modelRow);
				    
				    addedCourses += "\n" + courses.get(quantityOfCourses);
				    coursesOfPackage.setText(addedCourses);
				    System.out.print(addedCourses);
				    quantityOfCourses++;
				}
			};

			ButtonColumn buttonColumn2 = new ButtonColumn(tableOfCourses, addCourses, 1);
			
			PackageController packageController = new PackageController();
			//packageController.newPackage(packageName, packageValue);

		}catch(ParseException e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  Method used to show all existing courses
	 * @param courses - Receives an instance of the class CourseController 
	 * @throws SQLException
	 */
	private void getAllCoursesToSelect(CourseController courses) throws SQLException {
		
		ResultSet resultOfTheSelect = courses.showCourse();		
		
		while(resultOfTheSelect.next()){
			String[] allCourses = new String[2];
			allCourses[0] = (resultOfTheSelect.getString("course_name"));
			allCourses[1] = ("Adicionar");
			tableModel.addRow(allCourses);
		}	
		
	}
}
