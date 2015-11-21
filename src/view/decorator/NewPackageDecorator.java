package view.decorator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import model.Course;
import model.ServiceItem;
import view.ServiceItemView;
import view.forms.ServiceItemForm;
import controller.CourseController;
import controller.PackageController;
import exception.CourseException;
import exception.PackageException;

public class NewPackageDecorator extends ServiceItemDecorator{
	
	// The "-1" means that doesn't have a row selected
	private static final int NONE_ROW_SELECTED = -1;
	
	// The max length of a value (0000.00)
	private static final int MAX_VALUE_LENGTH = 6;
	
	private DefaultTableModel tableModel;
	private ArrayList <String> coursesName;
	private ArrayList <String> coursesId;
	private ArrayList <String> coursesDuration;
	private ArrayList <String> coursesValue;
	private DefaultTableModel tableSecondModel;
	private JTable tableOfCourses;
	private JTable tableOfAddedCourses;
	private JButton addCourseButton;
	private JButton removeAddedCourseButton;
	private ServiceItem newPackage;

	
	public NewPackageDecorator(ServiceItemView viewToDecorate) {
		super(viewToDecorate);
	}
	
	@Override
	public void createLabelsAndFields(JFrame frame, ServiceItem newPackage) {
		
		super.createLabelsAndFields(frame, newPackage);
		this.frame = frame;
		this.newPackage = newPackage;
		
		JLabel requiredFieldsLbl = new JLabel("Os campos com * são obrigatórios");
		requiredFieldsLbl.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC,12));
		requiredFieldsLbl.setBounds(284, 40, 370, 17);
        frame.getContentPane().add(requiredFieldsLbl);
				
		nameField.setBounds(276, 94, 346, 30);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);

		
		JLabel lblC = new JLabel("Novo pacote");
		lblC.setFont(new Font("Dialog", Font.BOLD, 20));
		lblC.setBounds(426, 12, 144, 35);
		frame.getContentPane().add(lblC);
				
		JLabel coursesLabel = new JLabel("Cursos:");
		coursesLabel.setBounds(76, 272, 144, 15);
		frame.getContentPane().add(coursesLabel);
	
		final JTextArea listAddedCourses = new JTextArea();
		listAddedCourses.setBounds(276, 353, 294, -55);
		frame.getContentPane().add(listAddedCourses);

		JLabel label = new JLabel("Adicionados:");
		label.setBounds(673, 272, 144, 15);
		frame.getContentPane().add(label);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 301, 353, 169);
		frame.getContentPane().add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		
		JScrollPane scrollPaneAddedCourses = new JScrollPane();
		scrollPaneAddedCourses.setBounds(576, 301, 353, 169);
		frame.getContentPane().add(scrollPaneAddedCourses);
		scrollPaneAddedCourses.setBackground(Color.WHITE);
		
		String [] columns = {"Cursos disponíveis", "ID", "Duração", "Valor"};
		
		tableModel = new DefaultTableModel(null, columns);			

		tableOfCourses = new JTable(tableModel);
		
		disposeColumns(tableOfCourses);
		tableOfCourses.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableOfCourses);
		
		String [] columnsAddedCourses = {"Cursos adicionados", "ID", "Duração", "Valor"};
		
		tableSecondModel = new DefaultTableModel(null, columnsAddedCourses);			

		tableOfAddedCourses = new JTable(tableSecondModel);
		scrollPaneAddedCourses.setViewportView(tableOfAddedCourses);
		disposeColumns(tableOfAddedCourses);

		
		
		try {
			getAllCoursesToSelect();
		} 
		catch(CourseException | SQLException e){
			
		}
	}

	@Override
	public void createMasks(JFrame frame) {
		super.createMasks(frame);		
		MaskFormatter durationMask = null;
		MaskFormatter valueMask = null;
		try {
			durationMask = new MaskFormatter("## semanas");
			durationMask.setValidCharacters("0123456789");
			durationMask.setPlaceholder("00");
			durationMask.setValueContainsLiteralCharacters(false);
			
			durationField = new JFormattedTextField(durationMask);
			durationField.setBounds(276, 163, 132, 25);
			durationField.setEditable(false);
			frame.getContentPane().add(durationField);
			
			JLabel psMessageLbl = new JLabel("Adicione os cursos para ver a duração");
			psMessageLbl.setFont(new Font("DejaVu Sans Condensed", Font.ITALIC,12));
			psMessageLbl.setBounds(276, 188, 370, 17);
	        frame.getContentPane().add(psMessageLbl);
			
			valueMask = new MaskFormatter("R$ ####,##");
			valueMask.setValidCharacters("0123456789");
			valueMask.setValueContainsLiteralCharacters(false);
			
			valueField = new JFormattedTextField(valueMask);
			valueField.setBounds(276, 244,124, 28);
			frame.getContentPane().add(valueField);
			
			JLabel ps2MessageLbl = new JLabel("Adicione os cursos para ver o valor atual");
			ps2MessageLbl.setFont(new Font("DejaVu Sans Condensed", Font.ITALIC,12));
			ps2MessageLbl.setBounds(276, 272, 370, 17);
	        frame.getContentPane().add(ps2MessageLbl);
		} 
		catch (ParseException e) {
			
		}
	}

	@Override
	public void createButtons(JFrame frame) {
		super.createButtons(frame);		
		
		JButton registerPackageButton = new JButton("Cadastrar");
		registerPackageButton.setBackground(Color.WHITE);
		registerPackageButton.setBounds(456, 525, 114, 25);
		frame.getContentPane().add(registerPackageButton);
		
		registerPackageButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				String packageName = nameField.getText();
				String packageDuration = calculateDuration(coursesValue);
				Integer packageValue;
				Object packageValueField = valueField.getValue(); 
				
				if(packageValueField != null){
					
					String value = packageValueField.toString();
					packageValue = new Integer(value);
				}
				else{
					packageValue = new Integer(0);
				}
				
				try{
											
					PackageController packageController = new PackageController();
					
					newPackage = packageController.newPackage(packageName, packageValue, coursesId);
					
					if(newPackage != null){
						showInfoMessage("Pacote cadastrado com sucesso.");
						dispose();
						ServiceItemView showPackageFrame = new ShowPackageDecorator(new ServiceItemForm());
						showPackageFrame.buildScreen(showPackageFrame, newPackage);
						showPackageFrame.setVisible(true);
					}
					else{
						showInfoMessage("Não foi possível cadastrar o pacote.");
					}
				
				}
				catch(PackageException caughtException){
					
					showInfoMessage(caughtException.getMessage());
				} 
			}

		});
		
		addCourseButton = new JButton("Adicionar");
		addCourseButton.setBackground(Color.WHITE);
		addCourseButton.setBounds(452, 333, 114, 25);
		frame.getContentPane().add(addCourseButton);
		
		removeAddedCourseButton = new JButton("Remover");
		removeAddedCourseButton.setBackground(Color.WHITE);
		removeAddedCourseButton.setBounds(452, 383, 114, 25);
		frame.getContentPane().add(removeAddedCourseButton);
		
		
		addCourse(tableOfCourses, tableOfAddedCourses);

	}

	/**
	 * Creates all labels and fields on frame
	 */
	public void createLabelsAndFields() {
		nameField = new JTextField();
		nameField.setBounds(276, 74, 346, 30);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JLabel packageNameLabel = new JLabel("Nome do pacote");
		packageNameLabel.setBounds(276, 50, 124, 17);
		contentPane.add(packageNameLabel);
		
		JLabel packageDescriptionLabel = new JLabel("Duração");
		packageDescriptionLabel.setBounds(550, 120, 70, 15);
		contentPane.add(packageDescriptionLabel);
		
		JLabel lblC = new JLabel("Novo pacote");
		lblC.setFont(new Font("Dialog", Font.BOLD, 20));
		lblC.setBounds(426, 12, 144, 28);
		contentPane.add(lblC);
		
		JLabel valueLabel = new JLabel("Valor");
		valueLabel.setBounds(278, 120, 70, 15);
		contentPane.add(valueLabel);
		
	
	}

	/**
	 * Adds the "Adicionar" button to table
	 * @param tableOfCourses
	 * @param tableOfAddedCourses 
	 */
	private void addCourse(final JTable tableOfCourses, final JTable tableOfAddedCourses) {
		
		coursesName = new ArrayList<String>(); 
		coursesId = new ArrayList<String>(); 
		coursesDuration = new ArrayList<String>(); 
		coursesValue = new ArrayList<String>();
					
		
		addCourseButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				int currentRow = tableOfCourses.getSelectedRow();
							    
				if(currentRow != NONE_ROW_SELECTED){
					
					// Gets the name of the selected course 
					String addedCourse = (String) tableOfCourses.getValueAt(currentRow, 0);
					coursesName.add(addedCourse);
					
					// Gets the id of the selected course
					String addedCourseId = (String) tableOfCourses.getValueAt(currentRow, 1);
					coursesId.add(addedCourseId);
					
					// Gets the duration of the selected course
					String addedCourseDuration = (String) tableOfCourses.getValueAt(currentRow, 2);
					coursesDuration.add(addedCourseDuration);
					

					// Gets the value of the selected course
					String addedCourseValue = (String) tableOfCourses.getValueAt(currentRow, 3);
					coursesValue.add(addedCourseValue);
				    
					// Remove the course from available courses table
					tableModel.removeRow(currentRow);
					
				    // Show added courses
					String [] allCourses = new String[4];
					allCourses[0] = addedCourse;
					allCourses[1] = addedCourseId;
					allCourses[2] = addedCourseDuration;
					allCourses[3] = addedCourseValue;
					tableSecondModel.addRow(allCourses);
	
				    //Show the current duration
				    String duration = calculateDuration(coursesDuration);
				    durationField.setText(duration);
				    
				    //Show the current value
				    String value = calculateValue(coursesValue);
				    valueField.setText(value);
				}	
				else{
					showInfoMessage("Selecione um curso da lista de cursos disponíveis");
				}
			}


		});
		
		removeAddedCourseButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				int currentRow = tableOfAddedCourses.getSelectedRow();
			
				if(currentRow != NONE_ROW_SELECTED){
				
					// Gets the id of the selected course
					String removeCourseId = (String) tableOfAddedCourses.getValueAt(currentRow, 1);
					
					// Search for the values to remove
					int index = 0;
					boolean indexFound = false;
					while(index < coursesId.size() && !indexFound){
						
						if(coursesId.get(index).equals(removeCourseId)){
							indexFound = true;
						}
						else{
							index++;
						}
						
					}
					
					String [] courseAvailable = new String[4];
					courseAvailable[0] = coursesName.get(index);
					courseAvailable[1] = coursesId.get(index);
					courseAvailable[2] = coursesDuration.get(index);
					courseAvailable[3] = coursesValue.get(index);
					
					// Add the course to available courses table
					tableModel.addRow(courseAvailable);
					
					// Remove the course from added courses list
					coursesDuration.remove(index);
					coursesName.remove(index);
					coursesId.remove(index);
					coursesValue.remove(index);
					
					// Remove the course from added courses table
					tableSecondModel.removeRow(currentRow);
					
	
				    //Show the current duration
				    String duration = calculateDuration(coursesDuration);
				    durationField.setText(duration.toString());

				    //Show the current value
				    String value = calculateValue(coursesValue);
				    valueField.setText(value.toString());
				}
				else{
					showInfoMessage("Selecione um curso da lista de cursos adicionados");
				}
			}

		});

	}

	/**
	 * Calculate the duration of package based on courses duration
	 * @param coursesDuration - the duration of the courses
	 * @return the duration of the package
	 */
	private String calculateDuration(ArrayList<String> coursesDuration) {
		
		Integer packageDuration = 0;
		
		for(int i = 0; i < coursesDuration.size(); i++){
			packageDuration += Integer.parseInt((coursesDuration.get(i)));
		}
		
		return packageDuration.toString();
		
	}
	
	/**
	 * Calculate the value of package based on courses duration
	 * @param coursesValue - the values of the courses
	 * @return packageValue
	 */
	private String calculateValue(ArrayList<String> coursesValue) {
		
		Integer packageValue = 0;
		String value = "";
		
		for(int i = 0; i < coursesValue.size(); i++){

			String courseValue = coursesValue.get(i);
			packageValue += Integer.parseInt((courseValue));
		}
		
		value = packageValue.toString();
		if(value.length() != MAX_VALUE_LENGTH){
			value = "0" + value;
		}
		else{
			// Nothing to do
		}		
		
		return value;
	
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
	
		tableModel.getColumn(2).setMinWidth(0);     
		tableModel.getColumn(2).setPreferredWidth(0);  
		tableModel.getColumn(2).setMaxWidth(0);
		
		tableModel.getColumn(3).setMinWidth(0);     
		tableModel.getColumn(3).setPreferredWidth(0);  
		tableModel.getColumn(3).setMaxWidth(0);
					
	}

	/**
	 *  Method used to show all available courses 
	 * @throws SQLException
	 * @throws CourseException 
	 */
	public boolean getAllCoursesToSelect() throws SQLException, CourseException {
		
		CourseController courseController = new CourseController();
		ArrayList<Course> courses = courseController.showCourse();		
		int indexOfCourses = 0;
		boolean isEmpty = courses.isEmpty();
		while(indexOfCourses < courses.size()){
			
			Course course = courses.get(indexOfCourses);
			Integer courseId = course.getId();
			Integer courseDuration = course.getDuration();
			Integer courseValue = course.getValue();

			String[] allCourses = new String[4];
	
			allCourses[0] = (course.getName());
			allCourses[1] = (courseId.toString());
			allCourses[2] = (courseDuration.toString());
			allCourses[3] = (courseValue.toString());
			
			tableModel.addRow(allCourses);
			
			indexOfCourses++;
			
		}
		return isEmpty;
	}
}
