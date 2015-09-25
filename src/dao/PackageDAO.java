package dao;

import java.sql.SQLException;

import model.Package;

public class PackageDAO extends DAO{
	
	private static final String TABLE_NAME = "Package";
	private static final String NAME_COLUMN = "packageName";
	private static final String VALUE_COLUMN = "packageValue";

	public PackageDAO(){ }
	
	public boolean save(Package packageInstance){
		
		String packageName = packageInstance.getPackageName();
		Integer packageValue = packageInstance.getPackageValue();
		
		String query = "INSERT INTO "+ TABLE_NAME + "(" + NAME_COLUMN + ", "
						+ VALUE_COLUMN +")";
		
		query += "VALUES('" + packageName + "','" + packageValue + "')";
	
		boolean wasSaved = false;
		
		try{
			
			this.execute(query);
			wasSaved  = true;
		}catch(SQLException caughtException){
			
			wasSaved = false;
		}
		
		return wasSaved;
	}
	
	
}
