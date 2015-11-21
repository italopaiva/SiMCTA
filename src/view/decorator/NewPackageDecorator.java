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
import view.SimCta;
import controller.CourseController;
import controller.PackageController;
import exception.CourseException;
import exception.PackageException;

public class NewPackageDecorator extends ServiceItemDecorator{
	
	private static final int NOW_ROW_SELECTED = -1;
	
	private DefaultTableModel tableModel;
	private ArrayList <String> coursesName;
	private ArrayList <String> coursesId;
	private ArrayList <String> coursesDuration;
	private DefaultTableModel tableSecondModel;
	private JTable tableOfCourses;
	private JTable tableOfAddedCourses;

	private JButton addCourseButton;

	private JButton removeAddedCourseButton;

	
	public NewPackageDecorator(ServiceItemView viewToDecorate) {
		super(viewToDecorate);
	}
	
	@Override
	public void createLabelsAndFields(JFrame frame, ServiceItem packageInstance) {
		
		super.createLabelsAndFields(frame, packageInstance);
		this.frame = frame;
		
		nameField.setBounds(276, 74, 346, 30);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);

		
		JLabel lblC = new JLabel("Novo pacote");
		lblC.setFont(new Font("Dialog", Font.BOLD, 20));
		lblC.setBounds(426, 15, 144, 15);
		frame.getContentPane().add(lblC);
				
		JLabel coursesLabel = new JLabel("Cursos:");
		coursesLabel.setBounds(76, 252, 144, 15);
		frame.getContentPane().add(coursesLabel);
	
		final JTextArea listAddedCourses = new JTextArea();
		listAddedCourses.setBounds(276, 333, 294, -55);
		frame.getContentPane().add(listAddedCourses);

		JLabel label = new JLabel("Adicionados:");
		label.setBounds(673, 252, 144, 15);
		frame.getContentPane().add(label);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 281, 353, 169);
		frame.getContentPane().add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		
		JScrollPane scrollPaneAddedCourses = new JScrollPane();
		scrollPaneAddedCourses.setBounds(576, 281, 353, 169);
		frame.getContentPane().add(scrollPaneAddedCourses);
		scrollPaneAddedCourses.setBackground(Color.WHITE);
		
		String [] columns = {"Cursos disponíveis", "ID", "Duração"};
		
		tableModel = new DefaultTableModel(null, columns);			

		tableOfCourses = new JTable(tableModel);
		
		disposeColumns(tableOfCourses);
		tableOfCourses.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableOfCourses);
		
		String [] columnsAddedCourses = {"Cursos adicionados", "ID", "Duração"};
		
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
			durationMask.setValueContainsLiteralCharacters(false);
			
			durationField = new JFormattedTextField(durationMask);
			durationField.setBounds(276, 143, 132, 25);
			durationField.setEditable(false);
			frame.getContentPane().add(durationField);
			
			valueMask = new MaskFormatter("R$ ####,##");
			valueMask.setValidCharacters("0123456789");
			valueMask.setValueContainsLiteralCharacters(false);
			
			valueField = new JFormattedTextField(valueMask);
			valueField.setBounds(284, 224,124, 28);
			frame.getContentPane().add(valueField);
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
				Integer packageDuration = calculateDuration(coursesDuration);
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
					
					packageController.newPackage(packageName, packageValue, coursesId);

					showInfoMessage("Pacote cadastrado com sucesso.");
					dispose();
					SimCta mainframe = new SimCta();
					mainframe.setVisible(true);
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
					
		
		addCourseButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				int currentRow = tableOfCourses.getSelectedRow();
							    
				if(currentRow != NOW_ROW_SELECTED){
					
					// Gets the name of the selected course 
					String addedCourse = (String) tableOfCourses.getValueAt(currentRow, 0);
					coursesName.add(addedCourse);
					
					// Gets the id of the selected course
					String addedCourseId = (String) tableOfCourses.getValueAt(currentRow, 1);
					coursesId.add(addedCourseId);
					
					// Gets the duration of the selected course
					String addedCourseDuration = (String) tableOfCourses.getValueAt(currentRow, 2);
					coursesDuration.add(addedCourseDuration);
				    
					// Remove the course from available courses table
					tableModel.removeRow(currentRow);
					
				    // Show added courses
					String [] allCourses = new String[3];
					allCourses[0] = addedCourse;
					allCourses[1] = addedCourseId;
					allCourses[2] = addedCourseDuration;
					tableSecondModel.addRow(allCourses);
	
				    //Show the current duration
				    Integer duration = calculateDuration(coursesDuration);
				    durationField.setText(duration.toString());
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
			
				if(currentRow != NOW_ROW_SELECTED){
				
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
					
					String [] courseAvailable = new String[3];
					courseAvailable[0] = coursesName.get(index);
					courseAvailable[1] = coursesId.get(index);
					courseAvailable[2] = coursesDuration.get(index);
					
					// Add the course to available courses table
					tableModel.addRow(courseAvailable);
					
					// Remove the course from added courses list
					coursesDuration.remove(index);
					coursesName.remove(index);
					coursesId.remove(index);
					
					// Remove the course from added courses table
					tableSecondModel.removeRow(currentRow);
					
	
				    //Show the current duration
				    Integer duration = calculateDuration(coursesDuration);
				    durationField.setText(duration.toString());
				    
				}
				else{
					showInfoMessage("Selecione um curso da lista de cursos adicionados");
				}
			}

		});

	}

	/**
	 * Calculate the duration of package based on courses duration
	 * @param coursesDuration
	 * @return
	 */
	private Integer calculateDuration(ArrayList<String> coursesDuration) {
		
		Integer packageDuration = 0;
		
		for(int i = 0; i < coursesDuration.size(); i++){
			packageDuration += Integer.parseInt((coursesDuration.get(i)));
		}
		
		return packageDuration;
		
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

			String[] allCourses = new String[3];
	
			allCourses[0] = (course.getName());
			allCourses[1] = (courseId.toString());
			allCourses[2] = (courseDuration.toString());
			
			tableModel.addRow(allCourses);
			
			indexOfCourses++;
			
		}
		return isEmpty;
	}
}
