package Act_1_ConnexioBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Act_1_ConnexioBD {

	private static Connection connexio;
	private static String USER = "root";
	private static String PASSW = "";
	private static String bd = "pelicules";
	private static String nom_Taula = "pelicules";

	public static void main(String[] ares) {
		connectar(bd, USER, PASSW);
		registres(nom_Taula);
	}

	// Mètode per connectra-se a la Base de dades..
	private static void connectar(String USER, String PASSW, String bd) {

		try {
			connexio = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/" + USER, PASSW, bd);
			System.out.println("Servidor connectat");

		} catch (SQLException e) {
			System.out.println("No s'ha pogut connectar a la base de dades");
			e.printStackTrace();
		}

	}

	// Mètode per a que retorni els registres de la taula que li pasem per
	// parametre.
	private static void registres(String nom_Taula) {

		try {
			String Query = "SELECT * FROM " + nom_Taula;
			java.sql.Statement st = connexio.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);
			while (resultSet.next()) {
				switch (nom_Taula) {
				case "pelicules":
					System.out.println("ID " + resultSet.getString("id_peli") + "\t" + "Nom: "
							+ resultSet.getString("nom") + "\t" + "Dierctor: " + resultSet.getString("director") + "\t"
							+ "Any producció: " + resultSet.getString("any_prod") + "\t" + "Llogada: "
							+ resultSet.getString("llogada"));
					break;
				case "lloguers":
					System.out.println("ID Lloguer: " + resultSet.getString("id_lloguer") + "\t" + "ID Peli: "
							+ resultSet.getString("id_peli") + "\t" + "ID Client: " + resultSet.getString("id_client"));
					break;
				case "clients":
					System.out.println("ID Client: " + resultSet.getString("id_client") + "\t" + "Usuari: "
							+ resultSet.getString("usuari") + "\t" + "Contrasenya: "
							+ resultSet.getString("contrasenya"));
					break;
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error");
		}
	}

	private static void setNom_Taula(String nom_Taula) {
		Act_1_ConnexioBD.nom_Taula = nom_Taula;
	}

	// Mètode per tancar la connexio amb la base de dades.
	private static void tancarConnexio() {

		try {
			connexio.close();
			JOptionPane.showMessageDialog(null, "S'ha tancat la conneció amb el servidor");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static String getPASSW() {
		return PASSW;
	}

	private static void setPASSW(String PASSW) {
		Act_1_ConnexioBD.PASSW = PASSW;
	}

	private static Connection getConnexio() {
		return connexio;
	}

	private static void setConnexio(Connection connexio) {
		Act_1_ConnexioBD.connexio = connexio;

	}
}
