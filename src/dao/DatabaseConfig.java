/**
 * Class: DatabaseConfig
 * 
 * This class is used to set the needed information to connect to the database
 */

package dao;

public class DatabaseConfig {
	
	private final String local = "jdbc:mysql://localhost/simcta";
	private final String user = "root";
	private final String password = "root";
	
	public DatabaseConfig(){}
	
	public String local(){
		return local;
	}
	
	public String user(){
		return user;
	}

	public String password(){
		return password;
	}
}