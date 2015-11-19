package migration;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class RunMigration {

	public static void main(String[] args) {
		
		M014_AddingStudentClassAttributes m = new M014_AddingStudentClassAttributes();
		
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
