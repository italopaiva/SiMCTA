package migration;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class RunMigration {

	public static void main(String[] args) {
		
		M013_AddingClassStatusColumn m = new M013_AddingClassStatusColumn();
		
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
