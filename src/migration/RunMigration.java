package migration;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class RunMigration {

	public static void main(String[] args) {
		
		M010_CreatingPaymentDescriptionTable m = new M010_CreatingPaymentDescriptionTable();
		
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
