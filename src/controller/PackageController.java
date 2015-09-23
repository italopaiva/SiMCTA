package controller;

import dao.PackageDAO;
import exception.PackageException;
import model.Package;

public class PackageController {

	/**
	 * Create a new course with the given information
	 * @param packageName - the name of the Package
	 * @param packageValue - the price of the Package
	 * @return - TRUE if the package was created or FALSE if it does not
	 * @throws PackageException
	 */
	public boolean newPackage(String packageName, Integer packageValue) throws PackageException{
		
		boolean packageCreated = false;
		
		Package packageInstance = new Package(packageName, packageValue);
		PackageDAO packageDao = new PackageDAO();
		
		packageCreated = packageDao.save(packageInstance);
		
		return packageCreated;
		
	}
	
	
}
