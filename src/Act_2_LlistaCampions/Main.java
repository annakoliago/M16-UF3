package Act_2_LlistaCampions;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
	/*
	 * Variables globals.
	 */
	private static String USER = "root";
	private static String PASSW = "";
	private static String bd = "lol";
	private static String tableName = "champions";
	private static Connection connexio;
	private static String valor = "";
	private static String comparar = "";
	private static String nomCampio = "";
	private static String titolCampio = "";
	private static String loreCampio = "";
	private static String tagsCampio = "";

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		connexio = connection.connectar(bd, USER, PASSW);

		/*
		 * Aui es morta el menu amb les seves opcions.
		 */

		String opcio;
		do {
			System.out.println("---------------------- MENU ----------------------");
			System.out.println("1. Llistar Campions");
			System.out.println("2. Veure Campió");
			System.out.println("3. Afegir Campió");
			System.out.println("4. Sortir");
			System.out.print("Seleccioni una opció:");
			opcio = scanner.next();

			switch (opcio) {
			/*
			 * Cridem al mètode de llistarCampions i aquest només ens mostrarà fins a 15.
			 */
			
			case "1":
				Campio.llistarCampions(connexio, bd, tableName);
				System.out.println();
				break;
			/*
			 * Aquí podem escollir per ID i per nom, també per lletres varies.
			 */
				
			case "2":
				System.out.println("Escull una opció per cercar al campió");
				System.out.println("1.ID" + "\n" + "2.Nom");
				opcio = scanner.next();
				switch (opcio) {

				case "1":
					valor = "id";
					System.out.print("Introdueix la ID del campió:");
					comparar = scanner.next();
					Campio.veureCampions(connexio, tableName, valor, comparar);
					break;

				case "2":
					valor = "name";
					System.out.print("Introdueix el nom del campió:");
					comparar = scanner.next();
					Campio.veureCampions(connexio, tableName, valor, comparar);
					break;
				default:
					System.out.println("Error, intenta-ho de nou");
					break;
				}
				break;

			/*
			 * Cridem al mètode per a inserir un nou campió.
			 */
				
			case "3":
				scanner.nextLine();
				System.out.println("Insereix el nom del campió: ");
				nomCampio = scanner.nextLine();
				System.out.println("Insereix el titol del campió: ");
				titolCampio = scanner.nextLine();
				System.out.println("Insereix la descrepció del campió: ");
				loreCampio = scanner.nextLine();
				System.out.println("Insereix les etiquetes del campió: ");
				tagsCampio = scanner.nextLine();
				Campio.afegir_campio(connexio, tableName, nomCampio, titolCampio, loreCampio, tagsCampio);
				break;

			case "4":
				System.out.println("Adeu");
				connection.tancarConnexio(connexio);
				break;
			default:
				System.out.println("Error opció invalida.");
				break;
			}
			System.out.println();
		} while (opcio != "4");

		scanner.close();
	}
}
