/**
 * Class: DAO
 * 
 * This class provides methods to execute queries into the database.
 * All other DAO classes must extend this one to get access to these methods. 
 */

package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DAO {
	
	protected void execute(String query) throws SQLException{
		
		Connection connection = this.connectToDB(); 
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	protected ResultSet search(String query) throws SQLException{
		
		Connection connection = this.connectToDB(); 
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		ResultSet queryResult = preparedStatement.executeQuery();
		
		return queryResult;
	}
	
	protected int getNextId(String table, String idColumn) throws SQLException{
		
		int nextId = 0;
		String query = "SELECT " + idColumn + " FROM " + table + " ORDER BY ";
		query += idColumn + " DESC LIMIT 1 ";
		ResultSet result;
		
		result = this.search(query);
		
		if(result.first()){
			nextId = Integer.parseInt(result.getString(idColumn)) + 1;
		}
		else{
			nextId = 1;
		}
			
		return nextId;
	}
	
	private Connection connectToDB() throws SQLException{
		
		FactoryConnection factoryConnection = new FactoryConnection();
		Connection connection = factoryConnection.establishConnection();
		
		return connection;
	}
}
