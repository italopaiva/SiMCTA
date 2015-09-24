package migration;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ManagerMigrations{

	public static void main(String[] args) {
		
		String message = "";
		
		/**
		 * Call your migration class here
		 */
		M001_CreatingCourseTable m001 = new M001_CreatingCourseTable();
		
		try{
			m001.up();
			message = "Executado com sucesso!";
		}catch(SQLException e){
			message = e.getMessage();
		}finally{
			JOptionPane.showMessageDialog(null, message);
		}
	}
}
