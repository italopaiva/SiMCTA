
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.DatabaseConfig;

public class FactoryConnection {
	
	private static FactoryConnection instance;
	
	/**
	 * Establish the connection with the database
	 * @return the established connection
	 * @throws SQLException
	 */
	public static Connection establishConnection() throws SQLException{
		
		Connection connection = getInstance().getConnection();
		
		return connection;
	}
	
	/**
	 * Get the current instance of the class
	 * @return the current instance, if it exists, or creates a new one and then return it
	 */
	private static FactoryConnection getInstance(){
		
		if(instance == null){
			
            instance = new FactoryConnection();
		}
		else{
            // Nothing to do because the instance already exists
        }
		
		return instance;
	}
	
	/**
	 * Try to connect with the database
	 * @return the established connection (Connection class object)
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException{
		
		// Try to connect with the data specified on the DatabaseConfig class
		DatabaseConfig database = new DatabaseConfig(); 
		
		Connection connection = null;
		connection = DriverManager.getConnection(
			database.local(),
			database.user(),
			database.password()
		);
		
		return connection;
	}
}

