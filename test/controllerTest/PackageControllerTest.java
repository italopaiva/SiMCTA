package controllerTest;

import static org.junit.Assert.*;

import java.awt.ItemSelectable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Course;
import model.Package;
import model.ServiceItem;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.mockito.*;

import com.mysql.jdbc.Connection;

import controller.PackageController;
import exception.CourseException;
import exception.PackageException;
import controller.CourseController;
import dao.PackageDAO;

public class PackageControllerTest {
	
	private PackageController packageController;
	private Package packageInstance;
	private ArrayList<ServiceItem> courses;
	private ArrayList<String> coursesID;
	
	@Mock
	private PackageDAO packageDAOMock;
	private CourseController courseControllerMock;

	@Before
	public void setUp() throws PackageException, SQLException, CourseException{
		
		MockitoAnnotations.initMocks(this);
		packageDAOMock = mock(PackageDAO.class);
		
		courses = new ArrayList<ServiceItem>();
		Course course1 = new Course(1, "Aplicação de película", "Curso bom", 3, 500000);
		courses.add(course1);
		Course course2 = new Course(2, "Instalação de Som", "Curso bom", 3, 500000);
		courses.add(course2);
		
		packageController = new PackageController();
		
		// Insert a course to associate with a package
		courseControllerMock = mock(CourseController.class);
		//courseControllerMock.newCourse();
		//courseControllerMock.newCourse();
	}
	/*
	@Test
	public void testNewPackageMethodWithValidPackage() throws PackageException, SQLException{
		

		when(packageDAOMock.getTheLastId()).thenReturn(2);
		packageController.setPackageDAO(packageDAOMock);
		packageInstance = new Package(packageDAOMock.getTheLastId() + 1,"PelSom", 500000, 3, coursesID);
		
		when(packageDAOMock.save(packageInstance)).thenReturn(true);
		packageController.setPackageDAO(packageDAOMock);
		
		boolean wasSaved;
		wasSaved = packageController.newPackage("PelSom", 500000, 3, coursesID); 
		
		assertTrue("Should create the given package", wasSaved);
	}
	
	@Test(expected= PackageException.class)
	public void testNewPackageMethodWithInvalidPackage() throws PackageException, SQLException{
		
		packageController.setPackageDAO(packageDAOMock);
		coursesID.clear();
		boolean wasSaved = packageController.newPackage(" ", 0, 0, coursesID);

	}
	
	@Test(expected= PackageException.class)
	public void testNewPackageMethodWithPackageWithInexistingCourse() throws PackageException, SQLException{
		coursesID.add("3");
		Package package1 = new Package(5,"PelSom", 500000, 3, coursesID);
		when(packageDAOMock.save(package1)).thenThrow(new PackageException("Não foi possível associar os cursos ao pacote. Tente novamente."));
		packageController.setPackageDAO(packageDAOMock);		
		
		boolean wasSaved = packageController.newPackage("PelSom", 500000, 3, coursesID);

	}
	*/
	@Test
	public void testSearchPackageByNamePackageNotFound() throws PackageException{
		
		ArrayList<Package> array = new ArrayList<Package>();
		when(packageDAOMock.searchPackageByName("txt")).thenReturn(array);
		packageController.setPackageDAO(packageDAOMock);
		
		ArrayList<Package> array1 = new ArrayList<Package>();
		array1 = packageController.searchPackageByName("txt");
		
		assertNull(array1);
	}
	
	@Test
	public void testSearchPackageByNamePackageFound() throws PackageException, CourseException{
		
		ArrayList<Package> packagesArray = new ArrayList<Package>();
		ArrayList<ServiceItem> courses = new ArrayList<ServiceItem>();
		
		ServiceItem serviceItem1 = new Course(1, "Aplicação de película", "Curso bom", 3, 500000);
		ServiceItem serviceItem2 = new Course(2, "Instalação de Som", "Curso bom", 3, 500000);
				
		courses.add(serviceItem1);
		courses.add(serviceItem2);
		
		Package pacote1 = new Package(1, "Pacote 1", 200000, 2, 1, courses);
		ServiceItem serviceItem3 = new Course(3, "Instalação de Alarme", "Curso muito bom", 3, 510000);
		courses.add(serviceItem3);
		Package pacote2 = new Package(2, "Pacote 2", 250000, 4, 1, courses);
		
		packagesArray.add(pacote1);
		packagesArray.add(pacote2);
		when(packageDAOMock.searchPackageByName("pacote")).thenReturn(packagesArray);
		packageController.setPackageDAO(packageDAOMock);
		
		ArrayList<Package> array1 = new ArrayList<Package>();
		array1 = packageController.searchPackageByName("pacote");
		
		assertEquals(packagesArray, array1);
	}
	
	@Test
	public void testShowPackage() throws PackageException, CourseException{
		
		ArrayList<String> coursesNames = new ArrayList<String>();
		coursesNames.add("Instalacao de Pelicula");
		coursesNames.add("Instalacao de Som");
		
		ArrayList<ServiceItem> courses = new ArrayList<ServiceItem>();
		Course course1 = new Course(1, "Aplicação de película", "Curso bom", 3, 500000);
		Course course2 = new Course(2, "Instalação de Som", "Curso bom", 3, 500000);
		courses.add(course1);
		courses.add(course2);
		
		Package packageTest = new Package(2, "Pacote 2", 250000, 4, 1, courses);
				
		when(packageDAOMock.get(2)).thenReturn(packageTest);
		packageController.setPackageDAO(packageDAOMock);
		
		Package expectedPackage = new Package(packageTest.getId(),
												packageTest.getName(),
												packageTest.getValue(),
												packageTest.getDuration(),
												packageTest.getStatus(),
												courses);
		
		Package receivedPackage = new Package();
		receivedPackage = packageController.showPackage(2);
		
		assertEquals(expectedPackage.getId(), receivedPackage.getId());
		assertEquals(expectedPackage.getName(), receivedPackage.getName());
		assertEquals(expectedPackage.getValue(), receivedPackage.getValue());
		assertEquals(expectedPackage.getDuration(), receivedPackage.getDuration());
		assertEquals(expectedPackage.getStatus(), receivedPackage.getStatus());
		assertEquals(expectedPackage.getCourses(), receivedPackage.getCourses());
		
	}
	
	@Test
	public void testUpdatePackageMethodWithValidCourse() throws PackageException, SQLException{
		
		try{
			
			Package packageModel = new Package(packageDAOMock.getTheLastId() + 1,"PelSom", 500000, 3, 1, courses); 		
			//doReturn(packageDAOMock.update(packageModel)).thenReturn(2);
			packageController.setPackageDAO(packageDAOMock);
			
			packageController.updatePackage(packageDAOMock.getTheLastId() + 1,"SomPel", 500000, courses);
		
		}catch(PackageException caughtException){
			
			fail("Should not throw exception");
		}
	}
	
	@Test(expected = PackageException.class)
	public void testUpdatePackageMethodWithInvalidName() throws CourseException, PackageException, SQLException{
		
		packageController.updatePackage(packageDAOMock.getTheLastId() + 1,"", 500000, 3, coursesID);
	}

	@Test(expected = PackageException.class)
	public void testUpdatePackageMethodWithInvalidDuration() throws CourseException, PackageException, SQLException{
		
		packageController.updatePackage(packageDAOMock.getTheLastId() + 1,"Pelsom", 500000, null, coursesID);
	}
	
	@Test(expected = PackageException.class)
	public void testUpdatePackageMethodWithInvalidValue() throws CourseException, PackageException, SQLException{
		
		packageController.updatePackage(packageDAOMock.getTheLastId() + 1,"PelSom", null, 3, coursesID);
	}
	
	@Test(expected = PackageException.class)
	public void testUpdatePackageMethodWithInvalidCourses() throws CourseException, PackageException, SQLException{
		coursesID.clear();
		packageController.updatePackage(packageDAOMock.getTheLastId() + 1,"PelSom", 500000, 3, coursesID);
	}
	
	
}
