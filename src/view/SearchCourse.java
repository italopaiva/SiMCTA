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
import javax.swing.AbstractButton;
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
	
	private JPanel contentPane;
	private JLabel courseResultLabel;
	private JTextArea descriptionResultLabel;
	private JLabel durationResultLabel;
	private JLabel valueResultLabel;
	final DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JTextField searchedCourseField;
	private JTable tableOfCourses;
	private JInternalFrame internalFrame;
	private JInternalFrame internalFrame_1;
	private JButton backButton;
	private JButton editCourseBtn;

	private Integer courseId;
	private String courseName;
	private String courseDescription;
	private Integer courseDuration;
	private Integer courseValue;
	private Integer courseStatus;

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
		
		searchedCourseField = new JTextField();
		searchedCourseField.setBounds(140, 56, 446, 19);
		contentPane.add(searchedCourseField);
		searchedCourseField.setColumns(10);
		
		JButton btnConsultar = new JButton("Pesquisar");
		btnConsultar.setBounds(598, 53, 117, 25);
		contentPane.add(btnConsultar);
		
		
		internalFrame = new JInternalFrame();
		internalFrame.setEnabled(false);
		internalFrame.setBackground(Color.WHITE);
		internalFrame.setBounds(227, 141, 557, 317);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		editCourseBtn = new JButton("Editar");

		editCourseBtn.setBounds(156, 248, 107, 25);
		internalFrame.getContentPane().add(editCourseBtn);
		
		JLabel courseLabel = new JLabel("Curso:");
		courseLabel.setBounds(12, 12, 70, 15);
		internalFrame.getContentPane().add(courseLabel);
		
		courseResultLabel = new JLabel("a");
		courseResultLabel.setBounds(65, 12, 273, 15);
		internalFrame.getContentPane().add(courseResultLabel);
		
		JLabel descriptionLabel = new JLabel("Descrição:");
		descriptionLabel.setBounds(12, 86, 86, 15);
		internalFrame.getContentPane().add(descriptionLabel);
		
		descriptionResultLabel = new JTextArea("a");
		descriptionResultLabel.setEditable(false);
		descriptionResultLabel.setBounds(12, 113, 523, 123);
		internalFrame.getContentPane().add(descriptionResultLabel);
		
		JLabel durationLabel = new JLabel("Duração:");
		durationLabel.setBounds(12, 43, 70, 15);
		internalFrame.getContentPane().add(durationLabel);
		
		durationResultLabel = new JLabel("a");
		durationResultLabel.setBounds(75, 43, 129, 15);
		internalFrame.getContentPane().add(durationResultLabel);
		
		JLabel valueLabel = new JLabel("Valor:");
		valueLabel.setBounds(337, 43, 70, 15);
		internalFrame.getContentPane().add(valueLabel);
		
		valueResultLabel = new JLabel("a");
		valueResultLabel.setBounds(401, 43, 70, 15);
		internalFrame.getContentPane().add(valueResultLabel);
		
		btnAtivarOrDesativar = new JButton("New Button");
		btnAtivarOrDesativar.setBounds(312, 248, 105, 25);
		internalFrame.getContentPane().add(btnAtivarOrDesativar);
			
		String [] columns = { "Curso", "Status", "Ação", "Id"};
		
		tableModel = new DefaultTableModel(null, columns);
		tableOfCourses = new JTable(tableModel);
		
		tableOfCourses.removeColumn(tableOfCourses.getColumnModel().getColumn(3));
		tableOfCourses.setBackground(Color.WHITE);
			
		Action showCourse = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				JTable table = (JTable)e.getSource();
				int selectedRow = table.getSelectedRow();
				
				int idCourse = Integer.parseInt(table.getModel().getValueAt(selectedRow,3).toString());
				try {
					getCourseForId(idCourse);
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
				}
					
				}
		};
		
		ButtonColumn buttonColumn2 = new ButtonColumn(tableOfCourses, showCourse, 2);
			
		internalFrame_1 = new JInternalFrame();
		internalFrame_1.setEnabled(false);
		internalFrame_1.getContentPane().setLayout(null);
		internalFrame_1.setVisible(true);


		internalFrame_1.setBackground(Color.WHITE);
		internalFrame_1.setBounds(12, 43, 409, 196);
		//contentPane.add(internalFrame_1);
				
		backButton = new JButton("Voltar");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.setVisible(false);
				searchedCourseField.setText("");
				backButton.setVisible(false);
				tableOfCourses.setVisible(true);
			}
		});
		
			scrollPane = new JScrollPane();
			scrollPane.setBounds(227, 141, 557, 317);
			contentPane.add(scrollPane);
			scrollPane.setBackground(Color.WHITE);
			
						
		backButton.setBounds(727, 53, 117, 25);
		contentPane.add(backButton);
		backButton.setVisible(false);
		scrollPane.setViewportView(tableOfCourses);
		final CourseController courseController = new CourseController();			
		getAllCourses(courseController);

		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchedCourse = searchedCourseField.getText();
				
				boolean enteredCourse = !searchedCourse.isEmpty();
				
				if(enteredCourse){
					try {
						buildTableWithSearchedCourse(searchedCourse);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
				else{
					JOptionPane.showMessageDialog(null, "Digite o nome de um curso");
				}
				tableOfCourses.updateUI();
			}
					
		});	
	}
	
	/**
	 * Method used to search a course by name on database
	 * @param idCourse - id of the selected row by the user
	 * @throws SQLException 
	 */
	protected void getCourseForId(int idCourse) throws SQLException {
		
		CourseController courseController = new CourseController();
		
		try {
			ResultSet resultOfTheSearch = courseController.showCourse(idCourse);
			String currentId = "";
			
			while(resultOfTheSearch.next()){
				currentId = resultOfTheSearch.getString("id_course");
				int idToCompare = Integer.parseInt(currentId);
				if(idToCompare == idCourse){
					showDataOfCourse(resultOfTheSearch);
				}
			}
			
		} catch (CourseException e1) {
			e1.printStackTrace();
		}
		
	}

	/**
	 * Method used to search a course by name on database
	 * @param searchedCourse - Receives the course name entered by user
	 * @throws SQLException
	 */
	private void buildTableWithSearchedCourse(String searchedCourse) throws SQLException{
		
		CourseController courseController = new CourseController();
		tableModel.setRowCount(0);
		try {
			ResultSet resultOfTheSearch = courseController.showCourse(searchedCourse);
			
			
			while(resultOfTheSearch.next()){
				String[] allCourses = new String[4];
				allCourses[0] = (resultOfTheSearch.getString("course_name"));
				allCourses[1] = (showsAtivoOrInativo(resultOfTheSearch.getInt("status")));
				allCourses[2] = ("Ver");
				allCourses[3] = (resultOfTheSearch.getString("id_course"));
				tableModel.addRow(allCourses);
			}
						
		} 
		catch(CourseException e1) {
			e1.printStackTrace();
		}
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
	/**
	 * Method used to show the information about the found course
	 * @param resultOfTheSearch - Receives the result of the search from database
	 * @throws SQLException
	 */
	public void showDataOfCourse(final ResultSet resultOfTheSearch) throws SQLException {
		
		internalFrame_1.dispose();
		internalFrame.setVisible(true);
		backButton.setVisible(true);
		tableOfCourses.setVisible(false);
		
		String valueText = "";
		String durationString = "";
		
		courseId = resultOfTheSearch.getInt("id_course");
		courseName = resultOfTheSearch.getString("course_name");
		courseDescription = resultOfTheSearch.getString("description");
		courseValue = resultOfTheSearch.getInt("value");
		courseDuration = resultOfTheSearch.getInt("duration");
		courseStatus = resultOfTheSearch.getInt("status");
		
		valueText = passValueToMonetaryForm(courseValue);
		durationString = courseDuration.toString() + " semanas";
		
		
		courseResultLabel.setText(courseName);
		descriptionResultLabel.setText(courseDescription);
		valueResultLabel.setText(valueText);
		durationResultLabel.setText(durationString);
		
		editCourseBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				
				boolean hasPermission = SearchCourse.this.getPermissionToAccess();
				
				if(hasPermission){
					
					dispose();
										
					EditCourse editCourseFrame = new EditCourse(
						SearchCourse.this.courseId, 
						SearchCourse.this.courseName, 
						SearchCourse.this.courseDescription, 
						SearchCourse.this.courseDuration, 
						SearchCourse.this.courseValue
					);
					
					editCourseFrame.setVisible(true);
				}
				else{
					
				}
			}
		});
		
		btnAtivarOrDesativar.setText(showsAtivarOrDesativar(resultOfTheSearch.getInt("status")));
		
		btnAtivarOrDesativar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = 0;				
				confirm = JOptionPane.showConfirmDialog(tableOfCourses, "Tem certeza que deseja " + showsAtivarOrDesativar(courseStatus) + " este curso?", "Atenção!!!", JOptionPane.YES_NO_OPTION);
				
				if (confirm == JOptionPane.YES_OPTION) {
					
					CourseController courseController = new CourseController();
					try {
						int courseID = courseId;
						courseController.alterStatusCourse(courseID);
						courseStatus = (courseStatus == 0 ? 1 : 0);
						JOptionPane.showMessageDialog(tableOfCourses, "Status do curso alterado! Curso " + showsAtivoOrInativo(courseStatus) + " !");
						SimCta mainPage = new SimCta();
						mainPage.setVisible(true);	
					} catch (CourseException e1) {
						e1.printStackTrace();
					}	
				}
				
			}
		});

	}
	
	/**
	 *  Method used to show all existing courses
	 * @param courses - Receives an instance of the class CourseController 
	 * @throws SQLException
	 */
	public void getAllCourses(CourseController courses) throws SQLException{
					
		ResultSet resultOfTheSelect = courses.showCourse();		

	
		while(resultOfTheSelect.next()){
			String[] allCourses = new String[4];
			allCourses[0] = (resultOfTheSelect.getString("course_name"));
			allCourses[1] = (showsAtivoOrInativo(resultOfTheSelect.getInt("status")));
			allCourses[2] = ("Ver");
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
