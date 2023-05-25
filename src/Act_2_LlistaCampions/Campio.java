package Act_2_LlistaCampions;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;

public class Campio {

	private static int contador = 0;
	public Scanner scanner;

	/*
	 * Aquest mètode llista nomès 15 campions.
	 */

	public static void llistarCampions(Connection connexio, String db, String table_name) {
		System.out.println(" ----------------------Lllistat de Campions ----------------------");
		try {
			String Query = "SELECT * FROM " + table_name;
			Statement st = connexio.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);
			while (resultSet.next() && contador < 15) {
				System.out.println("ID: " + resultSet.getString("id") + " " + "Nom: " + resultSet.getString("name")
						+ " " + "Titol:" + resultSet.getString("title") + " " + "Descripció: "
						+ resultSet.getString("lore") + " " + "Etiquetes: " + resultSet.getString("tags") + " ");
				contador++;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}
		contador = 0;
	}

	/*
	 * Aquí podem veure els campions per nom, ID o bé posarem lletres i ens donarà
	 * el resultat d'aquells.
	 */
	
	public static void veureCampions(Connection conexio, String taula, String valor, String comparacio) {

		String Query = "";
		try {
			if (valor == "name") {
				Query = "SELECT * FROM " + taula + " WHERE " + valor + " LIKE '%" + comparacio + "%';";
			} else if (valor == "id") {
				Query = "SELECT * FROM " + taula + " WHERE " + valor + "=" + comparacio + ";";
			}
			Statement st = conexio.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);

			while (resultSet.next() && contador < 15) {
				System.out.println("ID: " + resultSet.getString("id") + " " + "Nom: " + resultSet.getString("name")
						+ " " + "Titol:" + resultSet.getString("title") + " " + "Descripció: "
						+ resultSet.getString("lore") + " " + "Etiquetes: " + resultSet.getString("tags") + " ");
				contador++;
			}
			System.out.println();
		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
			System.out.println("Error no es correste.");
		}
		contador = 0;
	}

	/*
	 * Afegirem un campió amb les seves dades.
	 */

	public static void afegir_campio(Connection conexio, String taula, String name, String titol, String lore,
			String tags) {
		boolean no_repetit = true;
		try {
			String Query = "SELECT * FROM " + taula + ";";
			Statement st = conexio.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);
			while (resultSet.next()) {
				if (resultSet.getString("name").equals(name)) {
					no_repetit = false;
				}
			}
			if (no_repetit) {
				Query = "INSERT INTO " + taula + "(name, title, lore, tags) VALUE(" + "\"" + name + "\", " + "\""
						+ titol + "\", " + "\"" + lore + "\", " + "\"" + tags + "\"); ";
				st = conexio.createStatement();
				st.executeUpdate(Query);
				System.out.println("Dades emmagamatzemades correctament.");
				;
			} else {

				System.out.println("Aquest campió ja existeix, intenta-ho de nou.");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en l'emmagatzament.");
		}

	}
}