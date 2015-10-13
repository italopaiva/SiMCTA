package view;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;
import controller.StudentController;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PhoneException;
import exception.RGException;
import exception.StudentException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class EnrollStudent extends View {
	private JTextField studentNameField;

	public EnrollStudent(){
		
		super();
		getContentPane().setLayout(null);
		
		JLabel enrollStudentLbl = new JLabel("Matricular novo aluno");
		enrollStudentLbl.setBounds(407, 12, 275, 31);
		enrollStudentLbl.setFont(new Font("Dialog", Font.BOLD, 20));
		getContentPane().add(enrollStudentLbl);
		
		JLabel studentNameLbl = new JLabel("Nome do aluno");
		studentNameLbl.setBounds(62, 57, 137, 15);
		getContentPane().add(studentNameLbl);
		
		studentNameField = new JTextField();
		studentNameField.setBounds(62, 73, 399, 24);
		getContentPane().add(studentNameField);
		studentNameField.setColumns(10);
		
		JButton enrollBtn = new JButton("Matricular");
		enrollBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				
				String message = "";
				try{
					
					String studentName = "Mário Oliveira";
					CPF studentCpf = new CPF("00240137140");
					RG studentRg = new RG("18480543", "SSP", "BA");
					Date birthdate = new Date(20, 8, 1999);
					String email = "mariooliv@gmail.com";
					Address address = new Address("QR 507 Conjunto 9", "14", "", "72331009", "Samambaia");
					Phone principalPhone = new Phone("61", "85678934");
					Phone secondaryPhone = new Phone("61", "88677935");
					String motherName = "Maria Julia Azevedo";
					String fatherName = "Paulo Julio Aragão";
					
					ArrayList<String> courses = new ArrayList<String>();
					courses.add("1");
					courses.add("2");
					ArrayList<String> packages = new ArrayList<String>();
					packages.add("7");
					packages.add("8");
					
					int paymentType = 1;
					int paymentForm = 3;
					Integer installments = new Integer(0);
					
					StudentController studentController = new StudentController();
					studentController.newStudent(studentName, studentCpf, studentRg, birthdate, email, address,
												 principalPhone, secondaryPhone, motherName, fatherName, courses, packages, paymentType, paymentForm, installments);
					
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
					// TODO Auto-generated catch block
					message = e1.getMessage();
				}
				finally{
					showInfoMessage(message);
				}
				
			}
		});
		enrollBtn.setBounds(683, 157, 117, 25);
		getContentPane().add(enrollBtn);
		
		
	}
}
