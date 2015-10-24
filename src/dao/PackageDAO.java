package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import exception.PackageException;
import model.Package;

public class PackageDAO extends DAO{
	
	private static final String PACKAGE_COURSES_WASNT_SAVED = "Não foi possível associar os cursos ao pacote. Tente novamente.";
	private static final String COULD_NOT_DISASSOCIATE_PACKAGE_COURSES = "Não foi possível desassociar os cursos do pacote.";
	private static final String PACKAGE_WASNT_SAVED = "Não foi possível cadastrar o pacote. Tente novamente";
	
	private static final String TABLE_NAME = "Package";
	private static final String NAME_COLUMN = "name";
	private static final String VALUE_COLUMN = "value";
	private static final String DURATION_COLUMN = "duration";
	private static final String ID_COLUMN = "id_package";
	private static final String ASSOCIATION_TABLE_NAME = "PackageCourse";
	private static final String ID_COURSE_COLUMN = "id_course";
	private static final String COURSE_TABLE = "Course";
	private static final String COURSE_NAME_COLUMN = "course_name";
	private static final String STATUS_COLUMN = "status";
	private static final String COULDNT_GET_PACKAGE_COURSES = "Não foi possível pegar os dados dos cursos do pacote.";
	
	public PackageDAO(){ }
	
	/**
	 * Saves the data of package without the courses
	 * @param packageInstance - a Package object with the package information to be saved
	 * @return TRUE if the package was saved or FALSE if it does not
	 * @throws PackageException 
	 */
	public void save(Package packageInstance) throws PackageException {
		
		String packageName = packageInstance.getPackageName();
		Integer packageValue = packageInstance.getPackageValue();
		Integer packageDuration = packageInstance.getPackageDuration();
		
		String query = "INSERT INTO "+ TABLE_NAME + "(" + NAME_COLUMN + ", "
								+ VALUE_COLUMN +  ", " + DURATION_COLUMN + ")";
		
		query += "VALUES('" + packageName + "','" + packageValue + "','" + packageDuration + "')";
				
		try{
			
			this.execute(query);
			
			try{
				saveDataOfPackageCourse(packageInstance);
			}
			catch(SQLException caughtException){
				
				throw new PackageException(PACKAGE_COURSES_WASNT_SAVED);
			}
			
		}
		catch(SQLException caughtException){
			
			throw new PackageException(PACKAGE_WASNT_SAVED);
		}
		
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
				
				query = "INSERT INTO "+ ASSOCIATION_TABLE_NAME + "(" + ID_COLUMN + ", "
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
	public int getTheLastId(){
		
		int lastId = 0;
		String query = "SELECT " + ID_COLUMN + " FROM " + TABLE_NAME + " ORDER BY ";
		query += ID_COLUMN + " DESC LIMIT 1 ";
		ResultSet result;
		
		try{
			result = this.search(query);
		}catch(SQLException caughtException){
			result = null;
		}
		
		try {
			while(result.next()){
				lastId = Integer.parseInt(result.getString(ID_COLUMN));
			}
		} 
		catch (NumberFormatException | SQLException e) {
		} 
			
		return lastId;
	}
	
	/**
	 * Update a package on the database 
	 * @param packageId - Id of the package to be updated
	 * @param newPackage - Package object with the data of the new package
	 * @return TRUE if the package was updated or FALSE if it does not
	 * @throws PackageException
	 */
	public boolean update(Integer packageId, Package newPackage) throws PackageException{
		
		String newPackageName = newPackage.getPackageName();
		Integer newPackageValue = newPackage.getPackageValue();
		Integer newPackageDuration = newPackage.getPackageDuration();
		ArrayList<String> newPackageCourses = newPackage.getCourses(); 
		
		String query = "UPDATE "+ TABLE_NAME + " SET "
				   + NAME_COLUMN + "='" + newPackageName + "', "
				   + VALUE_COLUMN + "='" + newPackageValue + "', "
				   + DURATION_COLUMN + "='" + newPackageDuration + "' "
				   + "WHERE " + ID_COLUMN + "='" + packageId + "'";
	
		boolean wasUpdated = false;
		
		try{
			
			this.execute(query);
			wasUpdated  = true;
			
			try{
				
				updatePackageCourses(packageId, newPackageCourses);
			}catch(SQLException caughtException){
				
				throw new PackageException(PACKAGE_COURSES_WASNT_SAVED);
			}catch(PackageException caughtException){
				
				throw caughtException;
			}
			
		}catch(SQLException caughtException){
			
			wasUpdated = false;
		}
		
		return wasUpdated;
	}
	
	/**
	 * Update the courses of a package on the database
	 * @param packageId - Id of the package to get the courses updated
	 * @param packageCourses - Array with the courses to be associated with the package
	 * @throws SQLException
	 * @throws PackageException
	 */
	private void updatePackageCourses(Integer packageId, ArrayList<String> packageCourses) throws SQLException, PackageException{
		
		boolean wasDisassociated = disassociateAllCoursesOfPackage(packageId);
		
		if(wasDisassociated){
			
			int i = 0;
			for(i=0; i < packageCourses.size(); i++){
				
				String currentCourse = packageCourses.get(i);
				
				Integer courseId = Integer.parseInt(currentCourse);
				
				String associateCoursesToPackage = "INSERT INTO "+ ASSOCIATION_TABLE_NAME + "(" + ID_COLUMN + ", "+ ID_COURSE_COLUMN + ")";
				associateCoursesToPackage += "VALUES('" + packageId + "','" + courseId + "')";
				
				this.execute(associateCoursesToPackage);
			}
		}else{
			throw new PackageException(COULD_NOT_DISASSOCIATE_PACKAGE_COURSES);
		}
	}
	
	/**
	 * Disassociate all the courses of a package
	 * @param packageId - The package to disassociate all courses
	 * @return
	 */
	private boolean disassociateAllCoursesOfPackage(Integer packageId){
		
		String deleteAllPreviousAssociations = "DELETE FROM "+ ASSOCIATION_TABLE_NAME + " WHERE "+ ID_COLUMN +"= " + packageId;
		
		boolean disassociated = false;
		
		try{
			this.execute(deleteAllPreviousAssociations);
			disassociated = true;
		}catch(SQLException caughtException){
			disassociated = false;
		}
		
		return disassociated;
	}
	
	public Package get(int packageId){
		
		String query = "SELECT "+ TABLE_NAME +".* , " + ASSOCIATION_TABLE_NAME + ". " + ID_COURSE_COLUMN;
			   query += " FROM " + TABLE_NAME + " JOIN " + ASSOCIATION_TABLE_NAME;
			   query += " ON " + TABLE_NAME + "." + ID_COLUMN +" = " + ASSOCIATION_TABLE_NAME + "." + ID_COLUMN;
			   query += " WHERE " + TABLE_NAME + "." + ID_COLUMN + "=" + packageId;
				
		Package foundPackage = null;
		try{
			
			ResultSet result = this.search(query);
			
			if(result.first()){
				
				Integer idPackage = result.getInt(ID_COLUMN);
				String packageName = result.getString(NAME_COLUMN);
				Integer packageDuration = result.getInt(DURATION_COLUMN);
				Integer packageValue = result.getInt(VALUE_COLUMN);
				ArrayList<String> packageCourses = new ArrayList<String>();
				
				// Get back to first tuple and add each id_course to packageCourses 
				result.first();
				while(result.next()){
					packageCourses.add(result.getString(ID_COURSE_COLUMN));
				}
				
				foundPackage = new Package(idPackage, packageName, packageValue, packageDuration, packageCourses);
			}
			else{
				foundPackage = null;
			}
		}
		catch(SQLException e){
			foundPackage = null;
		}catch(PackageException e){
			foundPackage = null;
		}
		
		return foundPackage;
	}
	
	public ArrayList<Package> get() throws PackageException{
		
		String query = "SELECT * FROM " + TABLE_NAME;
		
		ArrayList<Package> packages = new ArrayList<Package>();
		
		try{
			
			ResultSet result = this.search(query);
			
			while(result.next()){
				
				Integer packageId = result.getInt(ID_COLUMN);
				String packageName = result.getString(NAME_COLUMN);
				Integer packageValue = result.getInt(VALUE_COLUMN);
				Integer packageDuration = result.getInt(DURATION_COLUMN);
				
				ArrayList<String> packageCourses = getPackageCourses(packageId);
				
				Package currentPackage = new Package(packageId, packageName, packageValue,
													 packageDuration, packageCourses);
				
				packages.add(currentPackage);
			}
		}
		catch(SQLException e){
			throw new PackageException(COULDNT_GET_PACKAGE_COURSES);
		}
		
		return packages;
	}
	
	private ArrayList<String> getPackageCourses(Integer packageId) throws SQLException{
		
		String query = "SELECT * FROM "+ ASSOCIATION_TABLE_NAME 
					 + " WHERE "+ ID_COLUMN +" = "+ packageId;
		
		ResultSet result = this.search(query);
		
		ArrayList<String> packageCourses = new ArrayList<String>();
		
		while(result.next()){
			
			String course = result.getString(ID_COURSE_COLUMN);
		
			packageCourses.add(course);
		}
		
		return packageCourses;
	}
	
	/**
	 * Search and get package(s) name that contains the string parameter package_name
	 * @param package_name
	 * @return ArrayList<Package> of founded packages or null if were not founded packages
	 * @throws PackageException
	 */
	public ArrayList<Package> searchPackageByName(String package_name) throws PackageException{
		
		ResultSet resultSet;
		String query = "SELECT * FROM " + TABLE_NAME 
				+ " WHERE " + NAME_COLUMN 
				+ " LIKE \"%" + package_name + "%\"";
		
		try{
			
			resultSet = this.search(query);
			
			//test if there was return to resultSet
			//if (!resultSet.isBeforeFirst() ) { 
				
				//return null;
				
			//} else {
				
				ArrayList<Package> arrayListPackage = new ArrayList<Package>();
				
				while (resultSet.next()) {              
			       arrayListPackage.add(returnAPackageOfResultSet(resultSet));
				}
				
				return arrayListPackage;
				
			//}

		
		}catch(SQLException caughtException){
			
			caughtException.printStackTrace();
			return null;
		}
		
	}
	/**
	 * Get a package by a idPackage informed
	 * @param idPackage
	 * @return a Package that was founded by idPackage or null if doesn't exist a package to idPackage informed
	 */
	public Package showPackage(int idPackage){
		
		ResultSet resultSet;
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = " + idPackage;
		
		try{
			resultSet = search(query);
			
			if(!resultSet.isBeforeFirst()){ 
				
				return null;
				
			}else{
				
				resultSet.next();
				return returnAPackageOfResultSet(resultSet);
			}
			
		} catch (SQLException | PackageException caughtException){
			
			caughtException.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Get names of courses registered in package informed by idPackage
	 * @param idPackage
	 * @return ArrayList<String> of name of courses registered in package informed or null if doesn't exist courses to idPackage informed
	 */
	public ArrayList<String> getNameCoursesInPackages(int idPackage) {

		ResultSet resultSet;
		String query = "SELECT " + COURSE_NAME_COLUMN + " FROM "
				+ COURSE_TABLE + " INNER JOIN "
				+ ASSOCIATION_TABLE_NAME + " ON " + COURSE_TABLE
				+ "." + ID_COURSE_COLUMN + " = " + ASSOCIATION_TABLE_NAME + "."
				+ ID_COURSE_COLUMN + " WHERE " + ID_COLUMN	+ " = " + idPackage;

		try {
			
			resultSet = search(query);
			
			if (!resultSet.isBeforeFirst()) {

				return null;

			} else {

				ArrayList<String> arrayListNameCourse = new ArrayList<String>();

				while (resultSet.next()) {
					arrayListNameCourse.add(resultSet.getString(1));
				}

				return arrayListNameCourse;

			}

		} catch (SQLException caughtException) {

			caughtException.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns a package to the resulSet received without a ArrayList<String> of courses' name
	 * @param resultSet
	 * @return new package from a resultSet received
	 * @throws PackageException
	 * @throws SQLException
	 */
	private Package returnAPackageOfResultSet(ResultSet resultSet) throws PackageException, SQLException{
		
		/**
		 * GET THE PACKAGE COURSES TO SET ON THIS ARRAY LIST
		 */
		ArrayList<String> packageCourses = new ArrayList<String>();
		/**
		 * THIS IS A RANDOM COURSE
		 * TAKE THIS OUT OF HERE
		 */
		packageCourses.add("5");
		
		Package newPackage = new Package(
			resultSet.getInt(ID_COLUMN), 
			resultSet.getString(NAME_COLUMN), 
			resultSet.getInt(VALUE_COLUMN), 
			resultSet.getInt(DURATION_COLUMN),
			resultSet.getInt(STATUS_COLUMN),
		
			/**
			 * GET THE PACKAGE COURSES TO SET ON THIS ARRAY LIST
			 */
			packageCourses
		);
		
		return newPackage;
	}
	
	/**
	 * Returns a package to the resulSet received with a ArrayList<String> of courses' name
	 * @param resultSet
	 * @return new package from a resultSet received
	 * @throws PackageException
	 * @throws SQLException
	 */

	public Package returnACompletePackageOfResultSet(ResultSet resultSet) throws PackageException, SQLException{
		
		ArrayList<String> arraylist;
		arraylist = getNameCoursesInPackages(resultSet.getInt(1));
		
		Package newPackage = new Package(
				resultSet.getInt(1), 
				resultSet.getString(2), 
				resultSet.getInt(3), 
				resultSet.getInt(4), 
				resultSet.getInt(5),
				arraylist);
		
		return newPackage;
	}

	/**
	 * Gets the courses id that form the package
	 * @param idPackage
	 * @return
	 */
	public ArrayList<String> getIdCourses(int idPackage) {
		
		ResultSet resultSet;
		String query = "SELECT " + ID_COURSE_COLUMN + " FROM "
				+ ASSOCIATION_TABLE_NAME + " WHERE " + ID_COLUMN	+ " = " + idPackage;
		
		
		try {
			resultSet = search(query);
			if (!resultSet.isBeforeFirst()) {

				return null;

			} 
			else {
				ArrayList<String> coursesId = new ArrayList<String>();
				while (resultSet.next()) {
					Integer idCourse = resultSet.getInt(ID_COURSE_COLUMN);
					String addedCourse = idCourse.toString();
					coursesId.add(addedCourse);
				}
				return coursesId;
			}
		} 
		catch (SQLException caughtException) {

			caughtException.printStackTrace();
			return null;
		}
	}

}
