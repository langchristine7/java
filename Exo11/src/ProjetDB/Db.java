package ProjetDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Db {

	private static String nomDuDriver = "com.mysql.jdbc.Driver";
	private static String login = "root";
	private static String password = "root";
	private static String url = "jdbc:mysql://localhost:3306/banque";
	private static Connection laConnexion = null;
	/**
	 * @return the nomDuDriver
	 */
	public static String getNomDuDriver() {
		return Db.nomDuDriver;
	}

	/**
	 * @param nomDuDriver
	 *            the nomDuDriver to set
	 */
	public static void setNomDuDriver(String nomDuDriver) {
		Db.nomDuDriver = nomDuDriver;
	}

	/**
	 * @return the login
	 */
	public static String getLogin() {
		return Db.login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public static void setLogin(String login) {
		Db.login = login;
	}

	/**
	 * @return the url
	 */
	public static String getUrl() {
		return Db.url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public static void setUrl(String url) {
		Db.url = url;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public static void setPassword(String password) {
		Db.password = password;
	}

	public Db() {
		System.out.println("creation classe connexion ");
		try {
			Class.forName(Db.nomDuDriver); // on charge le driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(-1); // on sort si on ne peut pas acceder au driver
		}
	}

	public void seConnecter(String unLogin, String unPassword, String unUrl) {
		Db.setLogin(unLogin);
		Db.setPassword(unPassword);
		Db.setUrl(unUrl);
		this.seConnecter();
	}

	public boolean seConnecter() {
		if (Db.laConnexion != null) {
			return true;
		}
		try {
			Db.laConnexion = DriverManager.getConnection(Db.url, Db.login, Db.password);
			return true;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public void recupererClient(int id) {

		PreparedStatement ste = null;
		ResultSet resultat = null;

		if (Db.laConnexion == null) {
			this.seConnecter();
		}

		String requete;
		try {
			requete = "select * from compte where utilisateurId = ?";
			ste = Db.laConnexion.prepareStatement(requete);
			ste.setInt(1, id);
			resultat = ste.executeQuery();
			while (resultat.next()) {
				int idCompte = resultat.getInt("id");

				String libelle = resultat.getString("libelle");

				System.out.println("Id compte de l'utilisateur " + id + " : " + idCompte + " Libelle : " + libelle);

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

		}
	}

	public void execute(String[] args) {

		if (Db.laConnexion == null) {
			this.seConnecter();
		}

		PreparedStatement ste = null;
		ResultSet resultat = null;

		try {
			Db.laConnexion = DriverManager.getConnection(Db.url, Db.login, Db.password);

			String requete = "select nom, prenom from utilisateur";
			ste = Db.laConnexion.prepareStatement(requete);

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
			ste = Db.laConnexion.prepareStatement(requete);
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
			ste = Db.laConnexion.prepareStatement(requete);
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

		}
	}

}
