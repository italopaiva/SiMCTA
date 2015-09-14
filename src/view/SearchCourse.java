package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import util.ButtonColumn;
import controller.CourseController;
import exception.CourseException;

@SuppressWarnings("serial")
public class SearchCourse extends View {

	private final static int NUMBER_OF_COLUMNS = 0;
	
	private JPanel contentPane;
	private JLabel courseResultLabel;
	private JTextArea descriptionResultLabel;
	private JLabel durationResultLabel;
	private JLabel valueResultLabel;
	final DefaultTableModel tableModel;
	private JScrollPane scrollPane;

	private Integer courseId;
	private String courseName;
	private String courseDescription;
	private Integer courseDuration;
	private Integer courseValue;
	
	private JButton btnAtivarOrDesativar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchCourse frame = new SearchCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SearchCourse() throws SQLException{
		
		super();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextField searchedCourseField = new JTextField();
		searchedCourseField.setBounds(29, 12, 161, 19);
		contentPane.add(searchedCourseField);
		searchedCourseField.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(202, 9, 117, 25);
		contentPane.add(btnConsultar);
		
		
		final JInternalFrame internalFrame = new JInternalFrame();
		internalFrame.setEnabled(false);
		internalFrame.setBackground(Color.WHITE);
		internalFrame.setBounds(12, 43, 409, 196);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JButton editCourseBtn = new JButton("Editar");
		editCourseBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				
				boolean hasPermission = SearchCourse.this.getPermissionToAccess();
				
				if(hasPermission){
					
					dispose();
										
					EditCourse editCourseFrame = new EditCourse(SearchCourse.this.courseId, SearchCourse.this.courseName, SearchCourse.this.courseDescription, SearchCourse.this.courseDuration, SearchCourse.this.courseValue);
					editCourseFrame.setVisible(true);
				}
				else{
					
				}
			}
		});
		editCourseBtn.setBounds(268, 127, 117, 25);
		internalFrame.getContentPane().add(editCourseBtn);
		
		JLabel courseLabel = new JLabel("Curso:");
		courseLabel.setBounds(12, 12, 70, 15);
		internalFrame.getContentPane().add(courseLabel);
		
		courseResultLabel = new JLabel("a");
		courseResultLabel.setBounds(65, 12, 273, 15);
		internalFrame.getContentPane().add(courseResultLabel);
		
		JLabel descriptionLabel = new JLabel("Descrição:");
		descriptionLabel.setBounds(12, 70, 86, 15);
		internalFrame.getContentPane().add(descriptionLabel);
		
		descriptionResultLabel = new JTextArea("a");
		descriptionResultLabel.setEditable(false);
		descriptionResultLabel.setBounds(22, 86, 255, 66);
		internalFrame.getContentPane().add(descriptionResultLabel);
		
		JLabel durationLabel = new JLabel("Duração:");
		durationLabel.setBounds(12, 43, 70, 15);
		internalFrame.getContentPane().add(durationLabel);
		
		durationResultLabel = new JLabel("a");
		durationResultLabel.setBounds(75, 43, 129, 15);
		internalFrame.getContentPane().add(durationResultLabel);
		
		JLabel valueLabel = new JLabel("Valor:");
		valueLabel.setBounds(216, 43, 70, 15);
		internalFrame.getContentPane().add(valueLabel);
		
		valueResultLabel = new JLabel("a");
		valueResultLabel.setBounds(268, 43, 70, 15);
		internalFrame.getContentPane().add(valueResultLabel);
		
		btnAtivarOrDesativar = new JButton("New Button");
		btnAtivarOrDesativar.setBounds(282, 127, 105, 25);
		internalFrame.getContentPane().add(btnAtivarOrDesativar);
		
		final JInternalFrame internalFrame_1 = new JInternalFrame();
		internalFrame_1.setEnabled(false);
		internalFrame_1.getContentPane().setLayout(null);
		internalFrame_1.setVisible(true);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 43, 409, 196);
		scrollPane.setBackground(Color.WHITE);
		contentPane.add(scrollPane);

		String [] columns = { "Curso", "Status", "Ação", "Id"};
		
		tableModel = new DefaultTableModel(null, columns);
		final JTable tableOfCourses = new JTable(tableModel);

		tableOfCourses.removeColumn(tableOfCourses.getColumnModel().getColumn(3));
		
		Action alterStatus = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				JTable table = (JTable)e.getSource();
				int idCourse = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),3).toString());
				String statusCourse = table.getModel().getValueAt(table.getSelectedRow(),1).toString();
				int statusCourseInt = (statusCourse == "Ativo" ? 1 : 0);
				
				int confirm = 0;
				
				confirm = JOptionPane.showConfirmDialog(tableOfCourses, "Tem certeza que deseja " + showsAtivarOrDesativar(statusCourseInt) + " este curso?", "Atenção!!!", JOptionPane.YES_NO_OPTION);
				
				if (confirm == JOptionPane.YES_OPTION) {
					
					CourseController courseController = new CourseController();
					try {
						courseController.alterStatusCourse(idCourse);
						statusCourseInt = (statusCourseInt == 0 ? 1 : 0);
						JOptionPane.showMessageDialog(tableOfCourses, "Status do curso alterado! Curso " + showsAtivoOrInativo(statusCourseInt) + " !");
						int modelRow = Integer.valueOf( e.getActionCommand() );
					    ((DefaultTableModel)table.getModel()).removeRow(modelRow);
					} catch (CourseException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
	};
		
		ButtonColumn buttonColumn2 = new ButtonColumn(tableOfCourses, alterStatus, 2);

		tableOfCourses.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableOfCourses);

		internalFrame_1.setBackground(Color.WHITE);
		internalFrame_1.setBounds(12, 43, 409, 196);
		//contentPane.add(internalFrame_1);
				
		final JButton backButton = new JButton("Voltar");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setVisible(false);
				searchedCourseField.setText("");
				backButton.setVisible(false);
			}
		});
		backButton.setBounds(321, 9, 117, 25);
		contentPane.add(backButton);
		backButton.setVisible(false);
		
		final CourseController courseController = new CourseController();			
		getAllCourses(courseController);

		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchedCourse = searchedCourseField.getText();
				int tableRow = tableOfCourses.getSelectedRow();
				
				boolean enteredCourse = !searchedCourse.isEmpty();
				boolean selectedCourse = tableRow != -1;
				
				if(enteredCourse == true || selectedCourse == true){
					try {
						if(enteredCourse == false){
							searchedCourse = (String) tableOfCourses.getValueAt(tableRow, NUMBER_OF_COLUMNS);
						}
						showCourses(searchedCourse);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
				else{
					JOptionPane.showMessageDialog(null, "Digite ou selecione um curso");
				}
			}

			/**
			 * Method used to search a course by name on database
			 * @param searchedCourse - Receives the course name entered by user
			 * @throws SQLException
			 */
			private void showCourses(String searchedCourse) throws SQLException{
				
				try {
					ResultSet resultOfTheSearch = courseController.showCourse(searchedCourse);
					boolean courseFound = false;
					String current_name = "";
					
					while(resultOfTheSearch.next()){
						current_name = resultOfTheSearch.getString("course_name");
						if(current_name.equals(searchedCourse)){
							courseFound = true;
							break;
						}
						else{
							
						}
					}
					if(courseFound == true){
						internalFrame_1.dispose();
						internalFrame.setVisible(true);
						backButton.setVisible(true);
						showDataOfCourse(resultOfTheSearch);	
					}
					else{
						JOptionPane.showMessageDialog(searchedCourseField, "Curso não encontrado!");
						searchedCourseField.setText("");
					}
					
				} catch (CourseException e1) {
					e1.printStackTrace();
				}
			}

			/**
			 * Method used to show the information about the found course
			 * @param resultOfTheSearch - Receives the result of the search from database
			 * @throws SQLException
			 */
			private void showDataOfCourse(final ResultSet resultOfTheSearch) throws SQLException {
				
				String courseName = "";
				String description = "";
				Integer value = 0;
				Integer duration = 0;
				Integer courseId = 0;
				String valueText = "";
				String durationString = "";
				
				courseId = resultOfTheSearch.getInt("id_course");
				courseName = resultOfTheSearch.getString("course_name");
				description = resultOfTheSearch.getString("description");
				value = resultOfTheSearch.getInt("value");
				duration = resultOfTheSearch.getInt("duration");
				
				SearchCourse.this.courseId = courseId;
				SearchCourse.this.courseName = courseName;
				SearchCourse.this.courseDescription = description;
				SearchCourse.this.courseDuration = duration;
				SearchCourse.this.courseValue = value;
				
				valueText = passValueToMonetaryForm(value);
				durationString = duration.toString() + " semanas";
				
				btnAtivarOrDesativar.setText(showsAtivarOrDesativar(resultOfTheSearch.getInt("status")));
				
				btnAtivarOrDesativar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int idCourse = 0;
						int statusCourseInt = 0;
						try {
							idCourse = resultOfTheSearch.getInt("id_course");
							statusCourseInt = resultOfTheSearch.getInt("status");
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						
						int confirm = 0;
						
						confirm = JOptionPane.showConfirmDialog(tableOfCourses, "Tem certeza que deseja " + showsAtivarOrDesativar(statusCourseInt) + " este curso?", "Atenção!!!", JOptionPane.YES_NO_OPTION);
						
						if (confirm == JOptionPane.YES_OPTION) {
							
							CourseController courseController = new CourseController();
							try {
								courseController.alterStatusCourse(idCourse);
								statusCourseInt = (statusCourseInt == 0 ? 1 : 0);
								JOptionPane.showMessageDialog(tableOfCourses, "Status do curso alterado! Curso " + showsAtivoOrInativo(statusCourseInt) + " !");
								SimCta mainPage = new SimCta();
								mainPage.setVisible(true);	
							} catch (CourseException e1) {
								e1.printStackTrace();
							}	
						}
						
					}
				});
				
				courseResultLabel.setText(courseName);
				descriptionResultLabel.setText(description);
				valueResultLabel.setText(valueText);
				durationResultLabel.setText(durationString);
			}

			/**
			 * Method used to pass the course value to monetary form
			 * @param value - Receives the course value on entire form
			 * @return - Return the course value on monetary form (R$)
			 */
			private String passValueToMonetaryForm(Integer value) {
				
				String valueText= "";
				String entireValue = "";
				String decimalValue = "";
				int lengthOfValue = 0;
				
				valueText = value.toString();
				lengthOfValue = valueText.length();
				entireValue = valueText.substring(0, (lengthOfValue - 2));
				decimalValue = valueText.substring((lengthOfValue - 2), lengthOfValue);
				valueText = entireValue + "," + decimalValue;
				
				return valueText;
			}
		});	
	}

	/**
	 *  Method used to show all existing courses
	 * @param courses - Receives an instance of the class CourseController 
	 * @throws SQLException
	 */
	private void getAllCourses(CourseController courses) throws SQLException{
					
		ResultSet resultOfTheSelect = courses.showCourse();		

	
		while(resultOfTheSelect.next()){
			String[] allCourses = new String[4];
			allCourses[0] = (resultOfTheSelect.getString("course_name"));
			allCourses[1] = (showsAtivoOrInativo(resultOfTheSelect.getInt("status")));
			allCourses[2] = (showsAtivarOrDesativar(resultOfTheSelect.getInt("status")));
			allCourses[3] = (resultOfTheSelect.getString("id_course"));
			tableModel.addRow(allCourses);
		}				
	}

	private String showsAtivarOrDesativar(int status){
		return ((status==1) ? "Desativar":"Ativar");
	}
	
	private String showsAtivoOrInativo(int status){
		return ((status==0) ? "Desativado":"Ativo");
	}
}
