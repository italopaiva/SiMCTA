package modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import model.Course;
import model.Package;

import org.junit.Before;
import org.junit.Test;

import controller.CourseController;
import exception.CourseException;
import exception.PackageException;

public class PackageTest {
	private static final int MAX_DURATION = 99;
	private static final int MIN_DURATION = 1;
	
	private static final int MAX_VALUE = 999999;
	private static final int MIN_VALUE = 1;
	
	Package packageInstance;
	
	private void newPackage(Integer packageId, String name, Integer packageValue) 
							throws PackageException{
		
		packageInstance = new Package(packageId, name, packageValue);
	}
	
	/** Test of valid entries */
	@Test
	public void testValidPackageName(){

		try{
			
			newPackage(1, "Instalação de acessórios", 150000);
			
			assertEquals("Instalação de acessórios", packageInstance.getName());
			
		}
		catch (PackageException e){
			
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testAllAttributesValidWithMaxValues(){

		try{
		
			newPackage(1, "Instalação de acessórios", MAX_VALUE);
			
		}
		catch (PackageException e){
			
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testAllAttributesValidWithMinValues(){

		try{
			newPackage(1, "Instalação de acessórios", MIN_VALUE);
			
			assertEquals("Instalação de acessórios", packageInstance.getName());
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

			newPackage(1, "", 150000);
	}
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithNameNull() throws PackageException{

			newPackage(1, null, 150000);
	}
	
	/** Tests with package value */
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithValueZero() throws PackageException{

			newPackage(1, "Pacote 1", 0);
	}
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithValueGreatherThanMax() throws PackageException{

			newPackage(1, "Pacote 1", MAX_VALUE + 1);
	}
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithValueLessThanMin() throws PackageException{

			newPackage(1, "Pacote 1", MIN_VALUE - 2);
	}
			
	/** Tests with package id */
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithIdLessThanZero() throws PackageException{
		newPackage(-1, "Pacote 1", 150000);
	}
	
	@Test(expected = PackageException.class)
	public void testIfCreatesAPackageWithIdNull() throws PackageException{

		newPackage(null, "Pacote 1", 150000);
	}
	/** End of invalid entries */
}
