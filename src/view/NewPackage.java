package view;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.CourseController;
import controller.PackageController;
import exception.CourseException;
import exception.PackageException;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollBar;

import util.ButtonColumn;

public class NewPackage extends View{
	
	private static final int NOW_ROW_SELECTED = -1;
	
	private JPanel contentPane;
	private JTextField packageNameField;
	private JFormattedTextField valueField;
	private DefaultTableModel tableModel;
	private JTextField packageDurationField;
	private ArrayList <String> coursesName;
	private ArrayList <String> coursesId;
	private ArrayList <String> coursesDuration;
	private DefaultTableModel tableSecondModel;

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
		
		coursesName = null;
		coursesId = null;
		coursesDuration = null;
		
		createLabelsAndFields();
		
		try{
			
			createMasks();				
			
			getAllCoursesToSelect();
			
			createAPackage();
						
		}catch(ParseException e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Creates a new package
	 */
	private void createAPackage() {
		JButton registerPackageButton = new JButton("Cadastrar");
		registerPackageButton.setBackground(Color.WHITE);
		registerPackageButton.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){		
				
				String packageName = packageNameField.getText();
				Integer packageDuration = calculateDuration(coursesDuration);
				Integer packageValue;
				Object packageValueField = valueField.getValue(); 
				
				if(!(packageValueField == null)){
					
					String value = packageValueField.toString();
					packageValue = new Integer(value);
				}
				else{
					packageValue = new Integer(0);
				}
				
				try{
											
					PackageController packageController = new PackageController();
					
					boolean packageWasSaved = packageController.newPackage(packageName, packageValue, 
																		   packageDuration, coursesId);
					
					String message = "";
					
					if(packageWasSaved){
						message = "Pacote cadastrado com sucesso.";
					}else{
						message = "Não foi possível cadastrar o pacote informado. Tente novamente.";
					}
					
					showInfoMessage(message);
					
				}
				catch(PackageException caughtException){
					
					showInfoMessage(caughtException.getMessage());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
		registerPackageButton.setBounds(456, 525, 114, 25);
		contentPane.add(registerPackageButton);

		
	}

	/**
	 * Creates the masks of value and duration fields
	 * @throws ParseException
	 */
	private void createMasks() throws ParseException {
		MaskFormatter durationMask = new MaskFormatter("## semanas");
		durationMask.setValidCharacters("0123456789");
		durationMask.setValueContainsLiteralCharacters(false);
		
		MaskFormatter valueMask = new MaskFormatter("R$ ####,##");
		valueMask.setValidCharacters("0123456789");
		valueMask.setValueContainsLiteralCharacters(false);
		
		valueField = new JFormattedTextField(valueMask);
		valueField.setBounds(276, 147, 124, 28);
		contentPane.add(valueField);
		
		packageDurationField = new JFormattedTextField(durationMask);
		packageDurationField.setBounds(550, 147, 124, 28);
		contentPane.add(packageDurationField);
		packageDurationField.setColumns(10);
		packageDurationField.setEditable(false);
		
	}

	/**
	 * Creates all labels and fields on frame
	 */
	private void createLabelsAndFields() {
		packageNameField = new JTextField();
		packageNameField.setBounds(276, 74, 346, 30);
		contentPane.add(packageNameField);
		packageNameField.setColumns(10);
		
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
		
		JLabel coursesLabel = new JLabel("Cursos");
		coursesLabel.setBounds(276, 202, 144, 15);
		contentPane.add(coursesLabel);
	
		final JTextArea listAddedCourses = new JTextArea();
		listAddedCourses.setBounds(276, 283, 294, -55);
		contentPane.add(listAddedCourses);

		JLabel label = new JLabel("Adicionados:");
		label.setBounds(673, 202, 144, 15);
		contentPane.add(label);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 231, 353, 169);
		contentPane.add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		
		JScrollPane scrollPaneAddedCourses = new JScrollPane();
		scrollPaneAddedCourses.setBounds(576, 231, 353, 169);
		contentPane.add(scrollPaneAddedCourses);
		scrollPaneAddedCourses.setBackground(Color.WHITE);
		
		String [] columns = {"Cursos disponíveis", "ID", "Duração"};
		
		tableModel = new DefaultTableModel(null, columns);			

		final JTable tableOfCourses = new JTable(tableModel);
		
		disposeColumns(tableOfCourses);
		tableOfCourses.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableOfCourses);
		
		String [] columnsAddedCourses = {"Cursos adicionados", "ID", "Duração"};
		
		tableSecondModel = new DefaultTableModel(null, columnsAddedCourses);			

		final JTable tableOfAddedCourses = new JTable(tableSecondModel);
		scrollPaneAddedCourses.setViewportView(tableOfAddedCourses);
		disposeColumns(tableOfAddedCourses);

		addCourse(tableOfCourses, tableOfAddedCourses);
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
					
		JButton addCourseButton = new JButton("Adicionar");
		addCourseButton.setBackground(Color.WHITE);
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
				    packageDurationField.setText(duration.toString());
				}	
				else{
					showInfoMessage("Selecione um curso da lista de cursos disponíveis");
				}
			}
			

		});
		addCourseButton.setBounds(452, 231, 114, 25);
		contentPane.add(addCourseButton);
		
		JButton removeAddedCourseButton = new JButton("Remover");
		removeAddedCourseButton.setBackground(Color.WHITE);
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
				    packageDurationField.setText(duration.toString());
				    
				}
				else{
					showInfoMessage("Selecione um curso da lista de cursos adicionados");
				}
			}

		});
		removeAddedCourseButton.setBounds(452, 281, 114, 25);
		contentPane.add(removeAddedCourseButton);
		
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
	 */
	private void getAllCoursesToSelect() throws SQLException {
		
		CourseController courses = new CourseController();
		ResultSet resultOfTheSelect = courses.showCourse();		
		
		while(resultOfTheSelect.next()){
			String[] allCourses = new String[3];
			allCourses[0] = (resultOfTheSelect.getString("course_name"));
			allCourses[1] = (resultOfTheSelect.getString("id_course"));
			allCourses[2] = (resultOfTheSelect.getString("duration"));
			tableModel.addRow(allCourses);
		}	
		
	}
}
