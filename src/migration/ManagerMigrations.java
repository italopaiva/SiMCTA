package migration;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ManagerMigrations{

	public static void main(String[] args) {
		
		String message = "";
		
		/**
		 * Call your migration class here
		 */
		M003_CreatingPackageTable m = new M003_CreatingPackageTable();
		
		try{
			m.up();
			message = "Executado com sucesso!";
		}catch(SQLException e){
			message = e.getMessage();
		}finally{
			JOptionPane.showMessageDialog(null, message);
		}
	}
}
