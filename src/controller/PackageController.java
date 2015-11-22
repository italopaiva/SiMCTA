package controller;

import java.util.ArrayList;

import model.Course;
import model.Package;
import dao.PackageDAO;
import exception.CourseException;
import exception.PackageException;

public class PackageController {

	private PackageDAO packageDAO;
	private CourseController courseController;
	private static final String CANT_UPDATE_STATUS = "Não foi possível alterar o status do pacote";
	
	public PackageController(){
		packageDAO = new PackageDAO();
		courseController = new CourseController();
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
	public Package newPackage(String packageName, Integer packageValue, ArrayList<String> coursesId) throws PackageException{
		
		Package newPackage;
		try{
			Integer packageId = packageDAO.getTheLastId() + 1; 
			
			newPackage = new Package(packageId, packageName, packageValue);
					
			for(String courseId : coursesId){
				
				Integer id = new Integer(courseId);
				Course course = courseController.get(id);
				
				if(course != null){
					newPackage.addServiceItem(course);
				}
				else{
					// Nothing to do because the course is invalid
				}
			}
			
			packageDAO.save(newPackage);
		}
		catch(PackageException e){
			newPackage = null;
			throw new PackageException(e.getMessage());
		}
	
		return newPackage;

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
	public Package updatePackage(Integer packageId, String packageName, Integer packageValue, ArrayList<String> packageCourses)
		throws PackageException{
	
		Package newPackage = new Package(packageId, packageName, packageValue);
	
		try{
			for(String courseId : packageCourses){
				Integer id = new Integer(courseId);
				Course course = courseController.get(id);
				
				if(course != null){
					newPackage.addServiceItem(course);
				}
				else{
					// Nothing to do because the course is invalid
				}
			}
			
			packageDAO.update(newPackage);
		}
		catch(PackageException e){
			newPackage = null;
			throw new PackageException(e.getMessage());
		}
		
		return newPackage;
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
				searchedPackages = packageDAO.get(name);
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
			
		Package foundPackage;

		foundPackage = packageDAO.get(idPackage);

		return foundPackage;
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

	public boolean alterStatusOfThePackage(Package packageToShow, int status) throws PackageException {

		int packageId = packageToShow.getId();
		boolean wasAltered = false;
		
		Package newPackage;
		try {
			packageDAO.update(packageId, status);
			wasAltered = true;
		} 
		catch(PackageException e){
			throw new PackageException(CANT_UPDATE_STATUS);
		}
		
		
		return wasAltered;
	}
}
