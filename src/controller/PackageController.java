package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CourseDAO;
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
	 * @param packageDuration - the duration of the Package
	 * @param coursesId - the Id of the courses contained in the package
	 * @return - TRUE if the package was created or FALSE if it does not
	 * @throws PackageException
	 * @throws SQLException 
	 */
	public boolean newPackage(String packageName, Integer packageValue, Integer packageDuration, ArrayList<String> coursesId) throws PackageException, SQLException{
		
		boolean packageCreated = false;

		int packageID = packageDAO.getTheLastId() + 1;
		
		Package packageInstance = new Package(packageID, packageName, packageValue,
				                              packageDuration, coursesId);
		
		packageCreated = packageDAO.save(packageInstance);

		return packageCreated;
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
	
public boolean changeStatusPackage(int idPackage) throws PackageException{
		
		boolean statusWasAltered;
		
		PackageDAO packageDao = new PackageDAO();
		int teste = packageDao.returnStatusPackage(idPackage);
	
		
		if (teste == 1){
			statusWasAltered = packageDao.changePackageStatus(idPackage, 0);
		} else {
			statusWasAltered = packageDao.changePackageStatus(idPackage, 1);
		}
		
		return statusWasAltered;
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
		
		packageToShow = new Package(packageAux.getPackageId(), packageAux.getPackageName(),
				packageAux.getPackageValue(), packageAux.getPackageDuration(), packageAux.getPackageStatus(),
				coursesName);
		
		return packageToShow;

	}
	
public ResultSet showPackage(String searchedPackage) throws PackageException{
		
		//PackageDAO packageDao = new PackageDAO();
		Package newPackage = new Package(0, searchedPackage, null, null, null, 0);
		boolean hasId = false;

		ResultSet resultOfSearch = packageDAO.get(newPackage, hasId);
			
		return resultOfSearch;
	}
	
	/**
	 * Show the name and the status of all packages registered
	 * @return the data produced by the given query
	 */
	public ResultSet showPackage(){
		
		ResultSet resultOfTheSelect;
		PackageDAO packageDao = new PackageDAO();		
		resultOfTheSelect = packageDao.getAll();
		
		return resultOfTheSelect;
		
	}
	

	/** 
	 * Show the information of a package searched by user
	 * @param idPackage - The id of package to be searched
	 * @return the data produced by the given query
	 * @throws PackageException
	 */
	public ResultSet showPackage(Integer idPackage) throws PackageException{
		
		PackageDAO packageDao = new PackageDAO();
		Package newPackage = new Package(idPackage, null, idPackage, idPackage, null, idPackage);
		boolean hasId = true;
		ResultSet resultOfSearch = packageDao.get(newPackage, hasId);
				
		return resultOfSearch;
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
}
