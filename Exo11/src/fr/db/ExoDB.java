package fr.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ExoDB {


	public static void main(String[] args) {
		Properties mesProperties = new Properties();

		try (InputStream is = ClassLoader.getSystemResourceAsStream("mesPreferences.properties")) {
			mesProperties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//		File file = new File("C:/Users/chris/git/java/Exo11/src/mesPreferences.properties");
		//		if (file.exists() && file.canRead()) {
		//
		//			try (FileReader fr = new FileReader(file)) { // a partir de java 7,
		//				// traite le finally
		//				// tout seul
		//				mesProperties.load(fr);
		//			} catch (IOException e) {
		//				e.printStackTrace();
		//			}
		//		}
		// else {
		// System.err.println("Fichier '" + file + "' pas trouve");
		// }

		// Nom du driver pour acceder a la base de donnees
		// lire la doc associee a sa base de donnees pour le connaitre
		final String dbDriver = mesProperties.getProperty("db.driver");
		final String dbUrl = mesProperties.getProperty("db.url");
		final String dbLogin = mesProperties.getProperty("db.login");
		final String dbPwd = mesProperties.getProperty("db.password");

		System.out.println("dbDriver : " + dbDriver);

		// on peut faire
		mesProperties.setProperty("une.nouvelle.cle", "bonjour");
		// on peut le sauvegarder
		// mesProperties.store()

		// on peut recuperer les properties du systeme
		Properties ps = System.getProperties();
		Iterator<Map.Entry<Object, Object>> iter = ps.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Object, Object> entry = iter.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}

		String nomDuDriver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(nomDuDriver); // on charge le driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(-1); // on sort si on ne peut pas acceder au driver
		}

		String login = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/banque";

		PreparedStatement ste = null;
		ResultSet resultat = null;
		Connection connexion = null;

		try {
			connexion = DriverManager.getConnection(url, login, password);

			String requete = "select nom, prenom from utilisateur";
			ste = connexion.prepareStatement(requete);
			// ste.setString(1, "Breda"); // on ne s'occupe pas du formatage
			// ste.setString(1, "B%"); // on ne s'occupe pas du formatage
			//// pratique pour les dates car ca depend
			// pas de la bdd
			// ste.setInt(2, 4000);

			// Liste des utilisateurs

			resultat = ste.executeQuery();
			while (resultat.next()) {

				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");

				System.out.println("Nom : " + nom + " Prenom : " + prenom);
			}

			// pour faire une nouvelle requete

			resultat.close();
			ste.close();

			// 2. Executer une seconde requête qui ira chercher
			// les comptes associés à un utilisateur donné et affichera leur ID

			requete = "select * from compte where utilisateurId = ?";
			ste = connexion.prepareStatement(requete);
			ste.setInt(1, 1);
			resultat = ste.executeQuery();
			while (resultat.next()) {
				int idCompte = resultat.getInt("id");

				String libelle = resultat.getString("libelle");

				System.out.println("Id compte de l'utilisateur 1 : " + idCompte + " Libelle : " + libelle);
			}

			// 3. Executer une troisième requête qui ira chercher
			// les opérations associées à un compte donné et affichera leur ID.

			requete = "select * from operation where compteId = ?";
			ste = connexion.prepareStatement(requete);
			ste.setInt(1, 12);
			resultat = ste.executeQuery();
			while (resultat.next()) {
				int idOperation = resultat.getInt("id");

				String libelle = resultat.getString("libelle");

				System.out.println("Id compte 12 : " + idOperation + " Libelle : " + libelle);
			}

		} catch (SQLException e)

		{
			e.printStackTrace();
		} finally {
			// fermer les elements dans l'ordre inverse on les a ouverts

			try {
				if (resultat != null) {
					resultat.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (ste != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

