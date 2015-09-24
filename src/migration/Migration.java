package migration;

import java.sql.SQLException;

import dao.DAO;

public abstract class Migration extends DAO{
	
	protected String query;
	protected int version;
	
	protected void run() throws SQLException{
		
		execute(this.query);
		
		try{
			updateMigrationVersion();
		}catch(SQLException e){
			createMigrationTable();
			updateMigrationVersion();
		}
	}
	
	private void createMigrationTable() throws SQLException{
		
		String query = "";
		
		query+= "CREATE TABLE IF NOT EXISTS migration (";
		query+= "version int(11) NOT NULL ";
		query+= ")";
		
		execute(query);
		
		query = "";
		query+= "INSERT INTO migration(version) VALUES('0')";
		execute(query);
	}
	
	private void updateMigrationVersion() throws SQLException{
		String query = "";
		
		query+= "UPDATE `migration` SET `version`=" + this.version + " WHERE 1";
		
		execute(query);
	}
	
	protected void setVersion(int version){
		this.version = version;
	}
	
	protected void setQuery(String query){
		this.query = query;
	}
}
