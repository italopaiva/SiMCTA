package migration;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class RunMigration {

	public static void main(String[] args) {
		
		M005_AddingStatusColumnToPackageTable m = new M005_AddingStatusColumnToPackageTable();
		
		String message = "";
		try {
			m.up();
			message = "Migration executada com sucesso.";
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		JOptionPane.showMessageDialog(null, message);
	}

}
