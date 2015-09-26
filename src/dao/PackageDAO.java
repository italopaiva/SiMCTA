package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.PackageException;
import model.Package;

public class PackageDAO extends DAO{
	
	private static final String PACKAGE_COURSES_WASNT_SAVED = "Não foi possível associar os cursos ao pacote. Tente novamente.";
	
	private static final String TABLE_NAME = "Package";
	private static final String NAME_COLUMN = "name";
	private static final String VALUE_COLUMN = "value";
	private static final String DURATION_COLUMN = "duration";
	private static final String ID_COLUMN = "id_package";
	private static final String TABLE_ASSOCIATION_NAME = "PackageCourse";
	private static final String ID_COURSE_COLUMN = "id_course";

	public PackageDAO(){ }
	
	/**
	 * Saves the data of package without the courses
	 * @param packageInstance - a Package object with the package information to be saved
	 * @return TRUE if the package was saved or FALSE if it does not
	 * @throws PackageException 
	 */
	public boolean save(Package packageInstance) throws PackageException {
		
		String packageName = packageInstance.getPackageName();
		Integer packageValue = packageInstance.getPackageValue();
		Integer packageDuration = packageInstance.getPackageDuration();
		
		String query = "INSERT INTO "+ TABLE_NAME + "(" + NAME_COLUMN + ", "
								+ VALUE_COLUMN +  ", " + DURATION_COLUMN + ")";
		
		query += "VALUES('" + packageName + "','" + packageValue + "','" + packageDuration + "')";
		
		boolean packageWasSaved = false;
		
		try{
			
			this.execute(query);
			packageWasSaved = true;
			
			try{
				
				saveDataOfPackageCourse(packageInstance);
			}catch(SQLException caughtException){
				
				throw new PackageException(PACKAGE_COURSES_WASNT_SAVED);
			}
			
		}catch(SQLException caughtException){
			
			packageWasSaved = false;
		}
		
		
		return packageWasSaved;
	}
	
	/**
	 * Saves the data of the association between package and courses
	 * @param packageInstance - a Package object with the association between package and courses
	 * information to be saved
	 * @throws SQLException 
	 */
	private void saveDataOfPackageCourse(Package packageInstance) throws SQLException {

		int indexOfCourses = 0;
		int quantityOfCourses = packageInstance.getCourses().size();
		
		Integer packageId = packageInstance.getPackageId();
		Integer courseId = 0;
		String courseIdString = null;
		String query = null;
		ArrayList<String> coursesId = new ArrayList<String>();
		
		coursesId = packageInstance.getCourses();

		try{
			while(indexOfCourses < quantityOfCourses){
				
				query = "INSERT INTO "+ TABLE_ASSOCIATION_NAME + "(" + ID_COLUMN + ", "
						+ ID_COURSE_COLUMN + ")";
				
				courseIdString = coursesId.get(indexOfCourses);
				
				courseId = Integer.parseInt(courseIdString);

				query += "VALUES('" + packageId + "','" + courseId + "')";

				this.execute(query);
				indexOfCourses++;
			}						
		}
		catch(SQLException caughtException){
			
			throw caughtException;
		}
	}

	

	/**
	 * Gets the last package id from database
	 * @return the last ID
	 * @throws SQLException
	 */
	public int getTheLastId() throws SQLException {
		
		int lastId = 0;
		String query = "SELECT " + ID_COLUMN + " FROM " + TABLE_NAME + " ORDER BY ";
		query += ID_COLUMN + " DESC LIMIT 1 ";
		ResultSet result;
		
		try{
			result = this.search(query);
		}catch(SQLException caughtException){
			result = null;
		}
		
		while(result.next()){
			lastId = Integer.parseInt(result.getString(ID_COLUMN));
		}
			
		return lastId;
	}
	
	public boolean update(Integer packageId, Package newPackage){
		
		String newPackageName = newPackage.getPackageName();
		Integer newPackageValue = newPackage.getPackageValue();
		
		String query = "UPDATE "+ TABLE_NAME + " SET "
				   + NAME_COLUMN + "='" + newPackageName + "', "
				   + VALUE_COLUMN + "='" + newPackageValue + "' "
				   + "WHERE " + ID_COLUMN + "='" + packageId + "'";
	
		boolean wasUpdated = false;
		
		try{
			
			this.execute(query);
			wasUpdated  = true;
		}catch(SQLException caughtException){
			
			wasUpdated = false;
		}
		
		return wasUpdated;
	}
}
