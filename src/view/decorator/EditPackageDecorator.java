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

import controller.CourseController;
import controller.PackageController;
import exception.CourseException;
import exception.PackageException;
import model.Course;
import model.Package;
import model.ServiceItem;
import view.SearchPackage;
import view.ServiceItemView;

public class EditPackageDecorator extends ServiceItemDecorator {

	protected static final int SELECTED_ROW = -1;
	
	ArrayList<String> coursesName = new ArrayList<String>(); 
	ArrayList<String> coursesId = new ArrayList<String>(); 
	ArrayList<String> coursesDuration = new ArrayList<String>();
	private DefaultTableModel tableModel;
	private DefaultTableModel tableSecondModel;
	private Package packageToEdit;

	private JTable tableOfCourses;

	private JTable tableOfAddedCourses;

	
	public EditPackageDecorator(ServiceItemView viewToDecorate) {
		super(viewToDecorate);
	}

	@Override
	public void createLabelsAndFields(JFrame frame, ServiceItem serviceItem) {
		
		super.createLabelsAndFields(frame, serviceItem);
		
		this.packageToEdit = (Package) serviceItem;
		
		nameField = new JTextField();
		nameField.setBounds(276, 74, 346, 30);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel updatePackageLbl = new JLabel("Alterar dados do pacote");
		updatePackageLbl.setFont(new Font("Dialog", Font.BOLD, 20));
		updatePackageLbl.setBounds(346, 12, 310, 28);
		frame.getContentPane().add(updatePackageLbl);
	
		final JTextArea listAddedCourses = new JTextArea();
		listAddedCourses.setBounds(276, 283, 294, -55);
		frame.getContentPane().add(listAddedCourses);

		JLabel label = new JLabel("Adicionados:");
		label.setBounds(673, 202, 144, 15);
		frame.getContentPane().add(label);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 231, 353, 169);
		frame.getContentPane().add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		
		JScrollPane scrollPaneAddedCourses = new JScrollPane();
		scrollPaneAddedCourses.setBounds(576, 231, 353, 169);
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

	}

	@Override
	public void createMasks(JFrame frame) {
		MaskFormatter durationMask = null;
		MaskFormatter valueMask = null;
		try {
			durationMask = new MaskFormatter("## semanas");
			durationMask.setValidCharacters("0123456789");
			durationMask.setValueContainsLiteralCharacters(false);
			
			valueMask = new MaskFormatter("R$ ####,##");
			valueMask.setValidCharacters("0123456789");
			valueMask.setValueContainsLiteralCharacters(false);
		} 
		catch (ParseException e) {
			
		}
	
		valueField = new JFormattedTextField(valueMask);
		valueField.setBounds(276, 147, 124, 28);
		frame.getContentPane().add(valueField);
		
		durationField = new JFormattedTextField(durationMask);
		durationField.setBounds(550, 147, 124, 28);
		frame.getContentPane().add(durationField);
		durationField.setColumns(10);
		durationField.setEditable(false);
		
		try {
			setFieldValues(packageToEdit);
			getAllCoursesToSelect();
		} 
		catch (CourseException | SQLException e) {

		}
	}

	@Override
	public void createButtons(JFrame frame) {
		JButton updatePackageButton = new JButton("Alterar");
		updatePackageButton.setBackground(Color.WHITE);
		updatePackageButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				String packageName = nameField.getText();
				Integer packageId = packageToEdit.getId();
				Integer packageValue;
				Object packageValueField = valueField.getValue(); 
				
				if(packageValueField != null){				
					String value = packageValueField.toString();
					packageValue = new Integer(value);
				}
				else{
					packageValue = packageToEdit.getValue();
				}
				
				try{
											
					if(!coursesId.isEmpty()){
						PackageController packageController = new PackageController();
						packageController.updatePackage(packageId, packageName, packageValue, coursesId);
						
						String message = "Pacote alterado com sucesso.";
						
						showInfoMessage(message);
						dispose();
						ServiceItemView searchPackageFrame = new SearchPackage();
						searchPackageFrame.buildScreen(searchPackageFrame, packageToEdit);
						searchPackageFrame.setVisible(true);
					}
					else{
						showInfoMessage("Não pode haver um pacote sem cursos");
					}
					
				}
				catch(PackageException caughtException){
					showInfoMessage(caughtException.getMessage());
				} 
			}

		});
		updatePackageButton.setBounds(456, 525, 114, 25);
		frame.getContentPane().add(updatePackageButton);

		JButton addCourseButton = new JButton("Adicionar");
		addCourseButton.setBackground(Color.WHITE);
		addCourseButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				int currentRow = tableOfCourses.getSelectedRow();
							    
				if(currentRow != SELECTED_ROW){
					
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
		addCourseButton.setBounds(452, 231, 114, 25);
		frame.getContentPane().add(addCourseButton);
		
		JButton removeAddedCourseButton = new JButton("Remover");
		removeAddedCourseButton.setBackground(Color.WHITE);
		removeAddedCourseButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				int currentRow = tableOfAddedCourses.getSelectedRow();
			
				if(currentRow != SELECTED_ROW){
				
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
		removeAddedCourseButton.setBounds(452, 281, 114, 25);
		frame.getContentPane().add(removeAddedCourseButton);

	}

	/**
	 * Set the data of the package on the fields
	 * @param currentPackage - Package object to edit
	 * @throws CourseException 
	 * @throws SQLException 
	 */
	private void setFieldValues(Package currentPackage) throws CourseException, SQLException {
		
		String packageName = currentPackage.getName();
		String packageDuration = currentPackage.getDuration().toString();
		String packageValue = currentPackage.getValue().toString();
				
		nameField.setText(packageName);
		durationField.setText(packageDuration);
		durationField.setEditable(false);
		valueField.setText(packageValue);
		buildTableAddedCourses(currentPackage);
		
	}

	/**
	 * Show the courses of the package on added courses table
	 * @param currentPackage - Package object to edit
	 * @throws CourseException
	 * @throws SQLException
	 */
	private void buildTableAddedCourses(Package currentPackage) throws CourseException, SQLException {
 

		coursesId = currentPackage.getCourses();
		CourseController courseController = new CourseController();
		
		int index = 0;
		String courseName = null;
		String courseDuration = null;

		while(index < coursesId.size()){
			
			int courseId = Integer.parseInt(coursesId.get(index));
			
			Course dataOfCourse = courseController.showCourse(courseId);
			
			courseName = dataOfCourse.getName();
			Integer duration = dataOfCourse.getDuration();
			courseDuration = duration.toString();

			coursesName.add(courseName);
			coursesDuration.add(courseDuration);
			
			index++;
		}
		
	    // Show added courses
		index = 0;
		while(index < coursesId.size()){
			String [] allCourses = new String[3];
			allCourses[0] = coursesName.get(index);
			allCourses[1] = coursesId.get(index);
			allCourses[2] = coursesDuration.get(index);
			tableSecondModel.addRow(allCourses);
			index++;
		}

					
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
	private void getAllCoursesToSelect() throws SQLException, CourseException {
		
		CourseController courseController = new CourseController();
		ArrayList<Course> courses = courseController.showCourse();		
		
		int indexOfCourses = 0;
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
		
		eliminateAddedCoursesFromAvailableCourses();
	}
	
	/**
	 * Eliminates the added courses to table of available courses
	 */
	private void eliminateAddedCoursesFromAvailableCourses(){
		
		int index = 0;
		while(index < tableOfCourses.getRowCount()){

			String addedCourseId = (String) tableOfCourses.getValueAt(index, 1);
			for(String courseId : coursesId){
				if(addedCourseId.equals(courseId)){
					tableModel.removeRow(index);
				}
			}
			index++;
		}
		
		
		
	}
}
