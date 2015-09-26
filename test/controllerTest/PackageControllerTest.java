package controllerTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Package;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;

import controller.PackageController;
import exception.CourseException;
import exception.PackageException;
import controller.CourseController;

public class PackageControllerTest {
	
	private PackageController packageController;
	private Package packageInstance;
	private ArrayList<String> coursesID;

	@Before
	public void setUp() throws PackageException, SQLException, CourseException{
		
		packageController = new PackageController();
		coursesID = new ArrayList<String>();
		coursesID.add("1");
		coursesID.add("2");

		// Insert a course to associate with a package
		CourseController course = new CourseController();
		course.newCourse(1, "Aplicação de película", "Curso bom", 3, 500000);
		course.newCourse(2, "Instalação de Som", "Curso bom", 3, 500000);
	}

	@Test
	public void testNewPackageMethodWithValidPackage() throws PackageException, SQLException{
		
		boolean wasSaved = packageController.newPackage("PelSom", 500000, 3, coursesID);
		
		assertTrue("Should create the given package", wasSaved);
	}
	
	@Test(expected= PackageException.class)
	public void testNewPackageMethodWithInvalidPackage() throws PackageException, SQLException{
		
		coursesID.clear();
		boolean wasSaved = packageController.newPackage(" ", 0, 0, coursesID);

	}
	
	@Test(expected= PackageException.class)
	public void testNewPackageMethodWithPackageWithInexistingCourse() throws PackageException, SQLException{
		coursesID.add("3");
		boolean wasSaved = packageController.newPackage("PelSom", 500000, 3, coursesID);

	}
}
