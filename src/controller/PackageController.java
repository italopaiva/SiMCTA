package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.PackageDAO;
import exception.PackageException;
import model.Package;

public class PackageController {

	private PackageDAO packageDAO;
	
	public PackageController(){
		packageDAO = new PackageDAO();
	}
	
	public PackageDAO getPackageDAO() {
		return packageDAO;
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
	
	public boolean updatePackage(Integer packageId, String packageName, Integer packageValue, Integer packageDuration, ArrayList<String> packageCourses)
		throws PackageException{
		
		Package newPackage = new Package(packageId, packageName, packageValue, packageDuration, packageCourses);
		
		boolean wasUpdated = packageDAO.update(packageId, newPackage);
		
		return wasUpdated;
	}
	
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
	
}
