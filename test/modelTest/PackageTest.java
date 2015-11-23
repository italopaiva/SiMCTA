package modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import model.Course;
import model.Package;
import model.ServiceItem;

import org.junit.Before;
import org.junit.Test;

import controller.CourseController;
import exception.CourseException;
import exception.PackageException;

public class PackageTest {
	private static final Integer MAX_DURATION = 99;
	private static final Integer MIN_DURATION = 1;
	
	private static final Integer MAX_VALUE = 999999;
	private static final Integer MIN_VALUE = 1;
	
	private static final Integer ACTIVE_PACKAGE_STATUS = 1;
	private static final Integer DISABLED_PACKAGE_STATUS = 0;
	
	private Package packageInstance;
	private Course item;
	private Course item2;
	
	private void loadItens() throws CourseException{
		item = new Course(1, "Instalação de som", "Instala sons", 3, 205000);
		item2 = new Course(2, "Instalação de porta", "Instala portas", 5, 150000);
	}
	
	private void newPackage(Integer packageId, String name, Integer packageValue) 
							throws PackageException{
		
		packageInstance = new Package(packageId, name, packageValue);
	}
	
	private void newPackage(Integer packageId, String name, Integer packageValue, Integer packageStatus) 
			throws PackageException{

		packageInstance = new Package(packageId, name, packageValue, packageStatus);
	}
	
	private void newPackage(Integer packageId, String name, Integer packageValue, Integer packageDuration, Integer packageStatus) 
			throws PackageException{

		packageInstance = new Package(packageId, name, packageValue, packageDuration, packageStatus);
	}
	
	private void newPackage(Integer packageId, String name, Integer packageValue, Integer packageDuration, Integer packageStatus, ArrayList<ServiceItem> items) 
			throws PackageException{

		packageInstance = new Package(packageId, name, packageValue, packageDuration, packageStatus, items);
	}
	
/** Test of valid entries */
	
	/** Tests of package name **/
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
	
	/** Tests of Package value **/
	@Test
	public void testValidMinValue(){

		try{
			newPackage(1, "Instalação de acessórios", MIN_VALUE);
			
			assertEquals("Instalação de acessórios", packageInstance.getName());
		}
		catch (PackageException e){
			
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testValidRandomValue(){

		try{
			newPackage(1, "Instalação de acessórios", 150000);
			
			assertEquals("Instalação de acessórios", packageInstance.getName());
		}
		catch (PackageException e){
			
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testValidMaxValue(){

		try{
		
			newPackage(1, "Instalação de acessórios", MAX_VALUE);
		}
		catch (PackageException e){
			
			fail("Should not throw exception.");
		}
	}
	
	/** Tests of package status **/
	
	@Test
	public void testIfActiveStatusIsSet(){

		try{
			newPackage(1, "Instalação de acessórios", 150000);
			
			assertEquals(1, packageInstance.getStatus());
		}
		catch (PackageException e){
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testValidActiveStatus(){

		try{
			newPackage(1, "Instalação de acessórios", 150000, ACTIVE_PACKAGE_STATUS);
			
			assertEquals(1, packageInstance.getStatus());
		}
		catch (PackageException e){
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testValidInactiveStatus(){

		try{
			newPackage(1, "Instalação de acessórios", 150000, DISABLED_PACKAGE_STATUS);
			
			assertEquals(0, packageInstance.getStatus());
		}
		catch (PackageException e){
			fail("Should not throw exception.");
		}
	}
	
	/** Tests of package duration**/
	
	@Test
	public void testValidMinDuration(){

		try{
			newPackage(1, "Instalação de acessórios", 150000, MIN_DURATION, ACTIVE_PACKAGE_STATUS);
			
			assertEquals(MIN_DURATION, packageInstance.getDuration());
		}
		catch (PackageException e){
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testValidMaxDuration(){

		try{
			newPackage(1, "Instalação de acessórios", 150000, MAX_DURATION, ACTIVE_PACKAGE_STATUS);
			
			assertEquals(MAX_DURATION, packageInstance.getDuration());
		}
		catch (PackageException e){
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testValidRandomDuration(){

		try{
			newPackage(1, "Instalação de acessórios", 150000, 20, ACTIVE_PACKAGE_STATUS);
			
			assertEquals(new Integer(20), packageInstance.getDuration());
		}
		catch (PackageException e){
			fail("Should not throw exception.");
		}
	}
	
	/** Tests of package items**/
	
	@Test
	public void testIfAddValidItem(){

		try{
			newPackage(1, "Instalação de acessórios", 150000);
			
			loadItens();
			packageInstance.addServiceItem(item);
			
			ServiceItem addedCourse = packageInstance.getServiceItens().get(0);
			
			assertEquals(item, addedCourse);
		}
		catch(PackageException | CourseException e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfAddValidItems(){

		try{
			newPackage(1, "Instalação de acessórios", 150000);
			
			loadItens();
			packageInstance.addServiceItem(item);
			packageInstance.addServiceItem(item2);
			
			ServiceItem addedCourse1 = packageInstance.getServiceItens().get(0);
			ServiceItem addedCourse2 = packageInstance.getServiceItens().get(1);
			
			assertEquals(item, addedCourse1);
			assertEquals(item2, addedCourse2);
		}
		catch(PackageException | CourseException e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfCalculatePackageDuration(){

		try{
			newPackage(1, "Instalação de acessórios", 150000);
			
			loadItens();
			packageInstance.addServiceItem(item);
			packageInstance.addServiceItem(item2);
			
			Integer addedCoursesTotalDuration = item.getDuration() + item2.getDuration();
			assertEquals(addedCoursesTotalDuration, packageInstance.getDuration());
		}
		catch(PackageException | CourseException e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfGetCoursesOfPackage(){

		try{
			newPackage(1, "Instalação de acessórios", 150000);
			
			loadItens();
			packageInstance.addServiceItem(item);
			packageInstance.addServiceItem(item2);
			
			ArrayList<String> addedCoursesIds = new ArrayList<String>();
			addedCoursesIds.add(item.getId().toString());
			addedCoursesIds.add(item2.getId().toString());
			
			assertEquals(addedCoursesIds, packageInstance.getCourses());
		}
		catch(PackageException | CourseException e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}
	
	@Test
	public void testifSetValidItems(){

		try{
			
			loadItens();
			ArrayList<ServiceItem> items = new ArrayList<ServiceItem>();
			items.add(item);
			items.add(item2);
			
			newPackage(1, "Instalação de acessórios", 150000, 3, ACTIVE_PACKAGE_STATUS, items);
			
			assertEquals(items, packageInstance.getServiceItens());
		}
		catch(PackageException | CourseException e){
			fail("Should not throw exception: " + e.getMessage());
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
	
	/** Tests of package value */
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

		newPackage(1, "Pacote 1", MIN_VALUE - 1);
	}
	
	@Test(expected= PackageException.class)
	public void testinvalidNullPackageValue() throws PackageException{

		newPackage(1, "Pacote 1", null);
	}
			
	/** Tests with package id */
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithIdLessThanZero() throws PackageException{
		newPackage(-1, "Pacote 1", 150000);
	}
	
	@Test(expected= PackageException.class)
	public void testIfCreatesAPackageWithIdZero() throws PackageException{
		newPackage(0, "Pacote 1", 150000);
	}
	
	@Test(expected = PackageException.class)
	public void testIfCreatesAPackageWithIdNull() throws PackageException{

		newPackage(null, "Pacote 1", 150000);
	}
	
	/** Tests of package status **/
	@Test(expected = PackageException.class)
	public void testInvalidNegativePackageStatus() throws PackageException{

		newPackage(1, "Pacote 1", 150000, -1);
	}
	
	@Test(expected = PackageException.class)
	public void testInvalidPackageStatusGreaterThanOne() throws PackageException{

		newPackage(1, "Pacote 1", 150000, 2);
	}
	
	@Test(expected = PackageException.class)
	public void testInvalidNullPackageStatus() throws PackageException{

		newPackage(1, "Pacote 1", 150000, null);
	}
	/** Tests of package duration **/
	
	@Test(expected = PackageException.class)
	public void testInvalidPackageDurationLessThanMin() throws PackageException{

		newPackage(1, "Pacote 1", 150000, MIN_DURATION - 1, 1);
	}
	
	@Test(expected = PackageException.class)
	public void testInvalidNegativePackageDuration() throws PackageException{

		newPackage(1, "Pacote 1", 150000, MIN_DURATION - 2, 1);
	}
	
	@Test(expected = PackageException.class)
	public void testInvalidPackageDurationGreaterThanMax() throws PackageException{

		newPackage(1, "Pacote 1", 150000, MAX_DURATION + 1, 1);
	}
	
	@Test(expected = PackageException.class)
	public void testInvalidNullPackageDuration() throws PackageException{

		newPackage(1, "Pacote 1", 150000, null, 1);
	}
	
	/** Tests for package items **/
	
	@Test(expected = PackageException.class)
	public void testIfAddInvalidNullPackageItens() throws PackageException{

		newPackage(1, "Instalação de acessórios", 150000);
		
		packageInstance.addServiceItem(null);
	}
	
/** End of invalid entries */
}
