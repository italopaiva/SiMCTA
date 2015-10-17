package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import exception.AuthenticationException;
import exception.CourseException;
import exception.PackageException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class View extends JFrame {
	
	protected JMenuBar menuBar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected void instantiateMenuBar(){
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu courseMenu = new JMenu("Cursos");
		menuBar.add(courseMenu);
		
		JMenuItem registerCourse = new JMenuItem("Cadastrar Curso");
		registerCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean permissionToAccess = false;
				
				permissionToAccess = getPermissionToAccess();
				if(permissionToAccess == true){
					dispose();
					NewCourse newCourseFrame = new NewCourse();
					newCourseFrame.setVisible(true);
				}
				else{
					View frame = new View();
					frame.setVisible(true);
				}
			}
		});
		courseMenu.add(registerCourse);
		
		JMenuItem searchCourse = new JMenuItem("Visualizar Curso");
		searchCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();			
				try {
					SearchCourse searchCourseFrame = new SearchCourse();
					searchCourseFrame.setVisible(true);
				} 
				catch(SQLException e){
					e.printStackTrace();
				}
				catch(CourseException e){
					
				}
			}
		});
		courseMenu.add(searchCourse);
		
		JMenu packageMenu = new JMenu("Pacotes");
		menuBar.add(packageMenu);
		
		JMenuItem registerPackage = new JMenuItem("Cadastrar Pacote");
		registerPackage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean permissionToAccess = false;
				
				permissionToAccess = getPermissionToAccess();
				if(permissionToAccess == true){
					dispose();

					try {
						NewPackage newPackageFrame = new NewPackage();
						newPackageFrame.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				else{
					View frame = new View();
					frame.setVisible(true);
				}
			}
		});
		packageMenu.add(registerPackage);
		
		JMenuItem searchPackage = new JMenuItem("Visualizar Pacote");
		searchPackage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();

				try {
					SearchPackage newPackageFrame = new SearchPackage();
					newPackageFrame.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PackageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		packageMenu.add(searchPackage);
		
		JMenu studentMenu = new JMenu("Alunos");
		menuBar.add(studentMenu);
		
		JMenuItem newStudent = new JMenuItem("Cadastrar aluno");
		newStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				EnrollStudent newStudentFrame = new EnrollStudent();
				newStudentFrame.setVisible(true);
			}
		});
		studentMenu.add(newStudent);

		JMenuItem searchStudent = new JMenuItem("Visualizar Aluno");
		searchStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				SearchStudent searchStudentFrame = new SearchStudent();
				searchStudentFrame.setVisible(true);
			}
		});
		studentMenu.add(searchStudent);
	}
	
	protected boolean getPermissionToAccess(){
		
		boolean canAccess = false;
		String messageToUser = "";
		String enteredPassword = "senha digitada";
		final Object [] inputPassword;
		final JPasswordField enteredPasswordField;
		final JLabel passwordLabel;
							
		passwordLabel = new JLabel("Digite a senha:");
		enteredPasswordField = new JPasswordField();
		inputPassword = new Object[]{passwordLabel, enteredPasswordField};
		
		while(canAccess == false){
			AuthenticationView authenticationFrame = new AuthenticationView();
			int verify = authenticationFrame.showConfirmDialog(null, inputPassword, "Senha:",
											  authenticationFrame.OK_CANCEL_OPTION,
											  authenticationFrame.PLAIN_MESSAGE);
			enteredPassword = enteredPasswordField.getText();
			if (verify == JOptionPane.OK_OPTION) {
				if(enteredPassword != null){
					try {
						canAccess = authenticationFrame.authenticateUser(enteredPassword);
					} 
					catch(AuthenticationException | SQLException e){	
						messageToUser = e.toString();
						int indexToSepare = messageToUser.indexOf(":");
						messageToUser = messageToUser.substring(indexToSepare + 2);
						authenticationFrame.showMessageDialog(null, messageToUser);
					}
				}
			}
			else if (verify == JOptionPane.CANCEL_OPTION) {
				break;
			}
		}

		return canAccess;
	}
	
	/**
	 * Create the frame.
	 */
	public View(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 1248);
		
		instantiateMenuBar();
	}
	
	protected void showInfoMessage(String message){
		
		JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
	}
}
