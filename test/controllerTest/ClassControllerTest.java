package controllerTest;

import static org.junit.Assert.*;
import model.datatype.CPF;
import model.datatype.Date;

import org.junit.Before;
import org.junit.Test;

import controller.ClassController;
import exception.CPFException;
import exception.ClassException;
import exception.DateException;

public class ClassControllerTest {

	private ClassController classController;
	
	@Before
	public void setUp(){
		classController = new ClassController();
	}
	
	// Test assuming the class "APLICAÇÃO - MA 10/2" and the teacher "85988316972" exists 
	@Test
	public void testIfUpdatesAClass() throws DateException, CPFException{
		
		CPF teacherCpf = new CPF("85988316972");
		Date startDate = new Date(7, 8, 2016);
		
		try {
			classController.updateClass("APLICAÇÃO - MA 10/2", teacherCpf , "VE", startDate);
		}
		catch(ClassException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}

}
