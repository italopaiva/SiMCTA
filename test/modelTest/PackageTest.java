package modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import model.Package;

import org.junit.Before;
import org.junit.Test;

import exception.PackageException;

public class PackageTest {
	private static final int MAX_DURATION = 99;
	private static final int MIN_DURATION = 1;
	
	private static final int MAX_VALUE = 999999;
	private static final int MIN_VALUE = 1;
	
	private ArrayList<String> coursesID;
	Package packageInstance;
	
	@Before
	public void setUp(){
		coursesID = new ArrayList<String>();
		coursesID.add("1");
		coursesID.add("2");
	}
	
	private void newPackage(int packageId, String name, Integer packageDuration, Integer packageValue, ArrayList<String> courses) 
							throws PackageException{
		
		packageInstance = new Package(packageId, name, packageValue, packageDuration, courses);
	}
	
	/** Test of valid entries */
	@Test
	public void testAllAttributesValid(){

		try{
			
			newPackage(1, "Instalação de acessórios",3, 150000, coursesID);
			
			assertEquals("Instalação de acessórios", packageInstance.getPackageName());		
			assertEquals("1", coursesID.get(0));
			
		}
		catch (PackageException e){
			
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testAllAttributesValidWithMaxValues(){

		try{
		
			newPackage(1, "Instalação de acessórios",MAX_DURATION, MAX_VALUE, coursesID);
			
			assertEquals("Instalação de acessórios", packageInstance.getPackageName());		
			assertEquals("1", coursesID.get(0));
			
		}
		catch (PackageException e){
			
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testAllAttributesValidWithMinValues(){

		try{
			newPackage(1, "Instalação de acessórios",MIN_DURATION, MIN_VALUE, coursesID);
			
			assertEquals("Instalação de acessórios", packageInstance.getPackageName());		
			assertEquals("1", coursesID.get(0));
			
		}
		catch (PackageException e){
			
			fail("Should not throw exception.");
		}
	}
	
	/** End of valid entries */
	
	/** Tests of invalid entries 
	 * @throws PackageException */
	
	/** Tests with package name **/
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithNameBlank() throws PackageException{

			newPackage(1, "",3, 150000, coursesID);
	}
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithNameNull() throws PackageException{

			newPackage(1, null ,3, 150000, coursesID);
	}
	
	/** Tests with package value */
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithValueZero() throws PackageException{

			newPackage(1, "Pacote 1",3, 0, coursesID);
	}
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithValueGreatherThanMax() throws PackageException{

			newPackage(1, "Pacote 1",3, MAX_VALUE + 1 , coursesID);
	}
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithValueLessThanMin() throws PackageException{

			newPackage(1, "Pacote 1",3, MIN_VALUE - 2, coursesID);
	}
	
	/** Tests with package duration */
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithDurationZero() throws PackageException{

			newPackage(1, "Pacote 1", 0 , 150000, coursesID);
	}
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithDurationGreatherThanMax() throws PackageException{

			newPackage(1, "Pacote 1", MAX_DURATION + 1, 150000 , coursesID);
	}
	
	/** Tests with package courses */
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithNoCourses() throws PackageException{

			coursesID.clear();
			newPackage(1, "Pacote 1", MAX_DURATION + 3, 150000 , coursesID);
	}
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithCoursesNull() throws PackageException{

			newPackage(1, "Pacote 1", 3 , 150000, null);
	}
	
	/** End of invalid entries */

}
