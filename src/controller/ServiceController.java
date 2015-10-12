package controller;

import java.util.ArrayList;

import exception.ServiceException;
import model.Service;
import model.Student;

public class ServiceController {
	
	public void newService(Student student, ArrayList<String> courses, ArrayList<String> packages){
		
		try{
			Service service = new Service(student, courses, packages);
		}
		catch(ServiceException e){
			
		}
	}
}
