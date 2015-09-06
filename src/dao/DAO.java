package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DAO {
	
	public void execute(String query) throws SQLException{
		
		Connection connection = this.connectToDB(); 
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	public ResultSet search(String query) throws SQLException{
		
		Connection connection = this.connectToDB(); 
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		ResultSet queryResult = preparedStatement.executeQuery();
		preparedStatement.close();
		connection.close();
		
		return queryResult;
	}
	
	private Connection connectToDB() throws SQLException{
		
		FactoryConnection factoryConnection = new FactoryConnection();
		Connection connection = factoryConnection.establishConnection();
		
		return connection;
	}
}
