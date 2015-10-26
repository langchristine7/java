package fr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import fr.banque.Client;

public class Db {

	private String nomDuDriver = "com.mysql.jdbc.Driver";
	private String login = "root";
	private String password = "root";
	private String url = "jdbc:mysql://localhost:3306/banque";
	private Connection laConnexion = null;

	public Db() throws ClassNotFoundException {
		System.out.println("creation classe connexion ");
		Class.forName(this.nomDuDriver); // on charge le driver
	}

	public void seConnecter(String unLogin, String unPassword, String unUrl) throws SQLException {
		this.login = unLogin;
		this.password = unPassword;
		this.url = unUrl;
		this.seConnecter();
	}

	public void seConnecter() throws SQLException {
		if (this.laConnexion != null) {
			this.laConnexion = DriverManager.getConnection(this.url, this.login, this.password);
		}
	}

	public void seDeconnecter() {
		if (this.laConnexion == null) {
			return;
		}
		try {
			this.laConnexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Client recupererClient(int id) {

		PreparedStatement ste = null;
		ResultSet resultat = null;
		Client client = new Client();

		if (this.laConnexion == null) {
			throw new RuntimeException("Connect to db before");
		}

		String requete;
		try {
			requete = "select * from client where id = ?";
			ste = this.laConnexion.prepareStatement(requete);
			ste.setInt(1, id);
			resultat = ste.executeQuery();
			while (resultat.next()) {
				client.setNo(resultat.getInt("id"));
				client.setNom(resultat.getString("nom"));
				client.setPrenom(resultat.getString("prenom"));
				Date dateNaiss = new Date(resultat.getDate("dateDeNaissance"));

				// System.out.println("Id compte de l'utilisateur " + id + " : "
				// + idCompte + " Libelle : " + libelle);

			}

		} catch (SQLException e) {
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
		return client;
	}



}
