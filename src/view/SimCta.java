package view;

import java.util.*;

import controller.StudentController;

public class SimCta {
	
	private static Scanner input(){
		
		Scanner input = new Scanner(System.in);
		
		return input; 
	}
	
	public static void main(String[] args) {
		
		String studentName = "";
		
		System.out.println("Informe o nome do aluno:");
		studentName = input().nextLine();
		
		StudentController student = new StudentController();
		String saveMessage = student.newStudent(studentName);
		
		System.out.println();
		System.out.println(saveMessage);
	}
}
