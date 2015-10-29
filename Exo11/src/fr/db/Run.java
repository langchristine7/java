package fr.db;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.Operation;

public class Run {

	public Run() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		Properties mesProperties = new Properties();
		File file = new File("C:/Users/chris/git/java/Exo11/src/mesPreferences.properties");
		if (file.exists() && file.canRead()) {

			try (FileReader fr = new FileReader(file)) { // a partir de java 7,
				// traite le finally
				// tout seul
				mesProperties.load(fr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Fichier '" + file + "' pas trouve");
		}

		// Nom du driver pour acceder a la base de donnees
		// lire la doc associee a sa base de donnees pour le connaitre
		final String dbDriver = mesProperties.getProperty("db.driver");
		final String dbUrl = mesProperties.getProperty("db.url");
		final String dbLogin = mesProperties.getProperty("db.login");
		final String dbPwd = mesProperties.getProperty("db.password");

		Db db = null;
		try {
			// db = new Db();
			db = new Db(dbDriver, dbUrl, dbLogin, dbPwd);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			System.exit(-1);
		}
		try {
			db.seConnecter();

			String login = "dj";
			String pwd = "dj";

			System.out.println("test authentification ");
			Client clt1 = db.authentifier(login, pwd);
			if (clt1 == null) {
				System.out.println("User non authentifie");
			} else {
				System.out.println("nom user : " + clt1.getNom());
				System.out.println("age user : " + clt1.getAge());
			}

			// recherche du client id = 1
			System.out.println("\nrecupererClient --- Recherche du client no 1");

			Client client = db.recupererClient(1);
			System.out.println(client);

			List<Compte> listCpt = db.listerComptes(2);
			System.out.println(listCpt);

			List<Operation> listeOpe = db.rechercherOperation(12, null, null, null);
			System.out.println("Liste des operations");
			System.out.println(listeOpe);

			Compte cpteRech = db.rechercherCompte(15);
			System.out.println("compte no 15 : ");
			System.out.println(cpteRech);


		} catch (SQLException e) {
			System.out.println("Probleme de connexion : ");
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();

		}


	}

}
