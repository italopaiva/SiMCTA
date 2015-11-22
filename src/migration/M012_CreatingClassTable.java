package migration;

import java.sql.SQLException;

public class M012_CreatingClassTable extends Migration {
	
	private static final int MIGRATION_VERSION = 12;
	
	public M012_CreatingClassTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String classTableQuery = "";
		
		classTableQuery+= "CREATE TABLE IF NOT EXISTS Class (";
		classTableQuery+= "id_class varchar(20) NOT NULL PRIMARY KEY,";
		classTableQuery+= "id_course int(11) NOT NULL,";
		classTableQuery+= "teacher_cpf varchar(11) NOT NULL,";
		classTableQuery+= "startDate Date NOT NULL,";
		classTableQuery+= "endDate Date NOT NULL,";
		classTableQuery+= "shift varchar(2) NOT NULL,";
		classTableQuery+= "FOREIGN KEY (id_course) REFERENCES Course (id_course),";
		classTableQuery+= "FOREIGN KEY (teacher_cpf) REFERENCES Teacher (cpf)";
		classTableQuery+= ")";
				
		setQuery(classTableQuery);
		run();
		
		String studentClassTableQuery = "";
		
		studentClassTableQuery+= "CREATE TABLE IF NOT EXISTS StudentClass (";
		studentClassTableQuery+= "id_class varchar(20) NOT NULL,";
		studentClassTableQuery+= "cpf varchar(11) NOT NULL,";
		studentClassTableQuery+= "FOREIGN KEY (id_class) REFERENCES Class (id_class),";
		studentClassTableQuery+= "FOREIGN KEY (cpf) REFERENCES Student (cpf)";
		studentClassTableQuery+= ")";
		
		setQuery(studentClassTableQuery);
		run();
	}
}