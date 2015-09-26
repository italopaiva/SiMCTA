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
		PackageDAO packageDao = new PackageDAO();
		
		boolean wasUpdated = packageDao.update(packageId, newPackage);
		
		return wasUpdated;
	}
}
