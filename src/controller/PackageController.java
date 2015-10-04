package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.PackageDAO;
import exception.PackageException;
import model.Package;

public class PackageController {

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
		PackageDAO packageDao = new PackageDAO();

		int packageID = packageDao.getTheLastId() + 1;
		
		Package packageInstance = new Package(packageID, packageName, packageValue,
				                              packageDuration, coursesId);
		
		packageCreated = packageDao.save(packageInstance);

		return packageCreated;
	}
	
	public boolean updatePackage(Integer packageId, String packageName, Integer packageValue, Integer packageDuration, ArrayList<String> packageCourses)
		throws PackageException{
		
		Package newPackage = new Package(packageId, packageName, packageValue, packageDuration, packageCourses);
		PackageDAO packageDao = new PackageDAO();
		
		boolean wasUpdated = packageDao.update(packageId, newPackage);
		
		return wasUpdated;
	}
	
	public ArrayList<Package> searchPackageByName(String name) {
		
		PackageDAO packageDao = new PackageDAO();
		
		ArrayList<Package> searchedPackages = null; 
		
		try{
				searchedPackages = packageDao.searchPackageByName(name);
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
		
		PackageDAO packageDao = new PackageDAO();
		Package packageAux;
		Package packageToShow;
		
		ArrayList<String> coursesName;
		
		coursesName = packageDao.getNameCoursesInPackages(idPackage);
		
		packageAux = packageDao.showPackage(idPackage);
		
		packageToShow = new Package(packageAux.getPackageId(), packageAux.getPackageName(),
				packageAux.getPackageValue(), packageAux.getPackageDuration(), packageAux.getPackageStatus(),
				coursesName);
		
		return packageToShow;

	}
	
}
