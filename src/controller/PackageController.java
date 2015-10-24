package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PackageDAO;
import exception.CourseException;
import exception.PackageException;
import model.Course;
import model.Package;

public class PackageController {

	private PackageDAO packageDAO;
	
	public PackageController(){
		packageDAO = new PackageDAO();
	}

	public void setPackageDAO(PackageDAO packageDAO) {
		this.packageDAO = packageDAO;
	}

	/**
	 * Create a new course with the given information
	 * @param packageName - the name of the Package
	 * @param packageValue - the price of the Package
	 * @param coursesId - the Id of the courses contained in the package
	 * @return - TRUE if the package was created or FALSE if it does not
	 * @throws PackageException
	 */
	public void newPackage(String packageName, Integer packageValue, ArrayList<String> coursesId) throws PackageException{
		
		Integer packageId = packageDAO.getTheLastId() + 1; 
		
		Package packageInstance = new Package(packageId, packageName, packageValue);
		
		CourseController courseController = new CourseController();
		
		for(String courseId : coursesId){
			
			Integer id = new Integer(courseId);
			Course course = courseController.get(id);
			
			if(course != null){
				packageInstance.addServiceItem(course);
			}
			else{
				// Nothing to do because the course is invalid
			}
		}
		
		packageDAO.save(packageInstance);

	}
	
	/**
	 * Update the data of a package with the given information
	 * @param packageId - Id of the package to be updated
	 * @param packageName - New name of the package
	 * @param packageValue - New value of the package
	 * @param packageDuration - New duration of the package
	 * @param packageCourses - New courses associated with the package
	 * @return TRUE if the package was updated or FALSE if it does not
	 * @throws PackageException
	 */
	public boolean updatePackage(Integer packageId, String packageName, Integer packageValue, Integer packageDuration, ArrayList<String> packageCourses)
		throws PackageException{
		
		Package newPackage = new Package(packageId, packageName, packageValue, packageDuration, packageCourses);
		
		boolean wasUpdated = packageDAO.update(packageId, newPackage);
		
		return wasUpdated;
	}
	
	public Package getPackage(int packageId){
		
		Package foundPackage = packageDAO.get(packageId);
		
		return foundPackage;
	}
	
	/**
	 * Search a packages that have part or all string name parameter 
	 * @param name - name of course that will be searched
	 * @return  ArrayList<Package> of packages found or null if not were found packages 
	 */
	public ArrayList<Package> searchPackageByName(String name) {
		
		ArrayList<Package> searchedPackages = null; 
		
		try{
				searchedPackages = packageDAO.searchPackageByName(name);
				if (searchedPackages.isEmpty()){
					return null;
				} else {
					return searchedPackages;
				}
			} catch (PackageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} 
					
	}
	
	/**
	 * Get a package by idPackage to show
	 * @param idPackage
	 * @return a package that was founded by idPackage
	 * @throws PackageException
	 */
	public Package showPackage(int idPackage) throws PackageException{
		
		Package packageAux;
		Package packageToShow;
		
		ArrayList<String> coursesName;
		
		coursesName = packageDAO.getNameCoursesInPackages(idPackage);
		
		packageAux = packageDAO.showPackage(idPackage);
		
		packageToShow = new Package(packageAux.getId(), packageAux.getName(),
				packageAux.getValue(), packageAux.getDuration(), packageAux.getStatus(),
				coursesName);
		
		return packageToShow;

	}

	/**
	 * Get a package by idPackage to show
	 * @param idPackage
	 * @return a package that was founded by idPackage
	 * @throws PackageException
	 */
	public Package searchCoursesOfAPackage(int idPackage) throws PackageException{
		
		Package packageAux;
		Package packageToShow;
		
		ArrayList<String> coursesId;
		
		coursesId = packageDAO.getIdCourses(idPackage);

		packageAux = packageDAO.showPackage(idPackage);
		
		packageToShow = new Package(packageAux.getPackageId(), packageAux.getPackageName(),
				packageAux.getPackageValue(), packageAux.getPackageDuration(), packageAux.getPackageStatus(),
				coursesId);
		
		return packageToShow;

	}
	
	/**
	 * Get all registered packages
	 * @return An ArrayList with the packages found
	 * @throws CourseException 
	 * @throws PackageException 
	 */
	public ArrayList<Package> getPackages() throws CourseException, PackageException{
		
		ArrayList<Package> packages = packageDAO.get();
		
		return packages;
	}
}
