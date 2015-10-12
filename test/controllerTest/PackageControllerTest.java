package controllerTest;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Course;
import model.Package;

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
	private ArrayList<String> coursesID;
	
	@Mock
	private PackageDAO packageDAOMock;
	private CourseController courseControllerMock;

	@Before
	public void setUp() throws PackageException, SQLException, CourseException{
		
		MockitoAnnotations.initMocks(this);
		packageDAOMock = mock(PackageDAO.class);
		
		coursesID = new ArrayList<String>();
		coursesID.add("1");
		coursesID.add("2");
		
		packageController = new PackageController();
		
		// Insert a course to associate with a package
		courseControllerMock = mock(CourseController.class);
		//courseControllerMock.newCourse(1, "Aplicação de película", "Curso bom", 3, 500000);
		//courseControllerMock.newCourse(2, "Instalação de Som", "Curso bom", 3, 500000);
	}

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
	public void testSearchPackageByNamePackageFound() throws PackageException{
		
		ArrayList<Package> packagesArray = new ArrayList<Package>();
		ArrayList<String> courses = new ArrayList<String>();
		
		courses.add("Curso1");
		courses.add("Curso2");
		
		Package pacote1 = new Package(1, "Pacote 1", 200000, 2, 1, courses);
		courses.add("Curso3");
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
	public void testShowPackage() throws PackageException{
		
		ArrayList<String> coursesNames = new ArrayList<String>();
		coursesNames.add("Instalacao de Pelicula");
		coursesNames.add("Instalacao de Som");
		
		Package packageTest = new Package(2, "Pacote 2", 250000, 4, 1);
				
		when(packageDAOMock.getNameCoursesInPackages(2)).thenReturn(coursesNames);
		when(packageDAOMock.showPackage(2)).thenReturn(packageTest);
		packageController.setPackageDAO(packageDAOMock);
		
		Package expectedPackage = new Package(packageTest.getPackageId(),
												packageTest.getPackageName(),
												packageTest.getPackageValue(),
												packageTest.getPackageDuration(),
												packageTest.getPackageStatus(),
												coursesNames);
		
		Package receivedPackage = new Package();
		receivedPackage = packageController.showPackage(2);
		
		assertEquals(expectedPackage.getPackageId(), receivedPackage.getPackageId());
		assertEquals(expectedPackage.getPackageName(), receivedPackage.getPackageName());
		assertEquals(expectedPackage.getPackageValue(), receivedPackage.getPackageValue());
		assertEquals(expectedPackage.getPackageDuration(), receivedPackage.getPackageDuration());
		assertEquals(expectedPackage.getPackageStatus(), receivedPackage.getPackageStatus());
		assertEquals(expectedPackage.getCourses(), receivedPackage.getCourses());
		
	}
	
	@Test
	public void testUpdatePackageMethodWithValidCourse() throws PackageException, SQLException{
		
		try{
			
			Package packageModel = new Package(packageDAOMock.getTheLastId() + 1,"PelSom", 500000, 3, coursesID); 		
			when(packageDAOMock.update(packageDAOMock.getTheLastId(), packageModel)).thenReturn(true);
			packageController.setPackageDAO(packageDAOMock);
			
			packageController.updatePackage(packageDAOMock.getTheLastId() + 1,"SomPel", 500000, 3, coursesID);
		
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
