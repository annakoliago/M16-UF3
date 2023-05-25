package Act_2_LlistaCampions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class connection {

	/*
	 * Mètode per connectra-se a la base de dades.
	 */

	public static Connection connectar(String USER, String PASSW, String bd) {
		Connection connexio;
		try {
			connexio = ((Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/" + USER, PASSW, bd));
			System.out.println("Servidor connectat");
			return connexio;

		} catch (SQLException e) {
			System.out.println("No s'ha pogut connectar a la base de dades");
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Mètode per a tancar la connexió amb la base de dades.
	 */
	
	public static void tancarConnexio(Connection connexio) {
		try {
			connexio.close();
			JOptionPane.showMessageDialog(null, "S'ha tancat la connexió amb el servidor");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
