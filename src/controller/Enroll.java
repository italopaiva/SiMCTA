package controller;

import exception.ServiceException;
import exception.StudentException;

public abstract class Enroll{
	
	protected void enroll() throws StudentException, ServiceException{
		
		saveStudent();
		saveService();
	}
	
	protected abstract void saveStudent() throws StudentException;
	
	protected abstract void saveService() throws ServiceException;
}
