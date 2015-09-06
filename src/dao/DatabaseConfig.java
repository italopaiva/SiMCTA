package dao;

public class DatabaseConfig {
	
	private final String local = "jdbc:mysql://localhost/simcta";
	private final String user = "root";
	private final String password = "";
	
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
