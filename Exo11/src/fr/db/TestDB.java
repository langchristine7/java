package fr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {


	public static void main(String[] args) {

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

