package fr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.banque.BanqueException;
import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.FactoryCompte;

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
		if (this.laConnexion == null) {
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

		String requete = null;
		try {
			requete = "select * from utilisateur where id = ?";
			ste = this.laConnexion.prepareStatement(requete);
			ste.setInt(1, id);
			resultat = ste.executeQuery();
			while (resultat.next()) {
				client.setNo(resultat.getInt("id"));
				client.setNom(resultat.getString("nom"));
				client.setPrenom(resultat.getString("prenom"));
				Date today = new Date();
				Date birthday = new Date(resultat.getDate("dateDeNaissance").getTime());
				int age = today.getYear() - birthday.getYear();
				client.setAge(age);

				List<Compte> listCpte = new ArrayList<Compte>();
				listCpte = this.listerComptes(id);

				for (Compte cpt : listCpte) {
					try {
						client.ajouterCompte(cpt);
					} catch (BanqueException e) {
						e.printStackTrace();
					}
				}
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

	public List<Compte> listerComptes(int userId) {

		PreparedStatement ste = null;
		ResultSet resultat = null;
		List<Compte> listeCpt = new ArrayList<Compte>();

		if (this.laConnexion == null) {
			throw new RuntimeException("Connect to db before");
		}

		String requete = null;
		try {
			requete = "select * from compte where utilisateurId = ?";
			ste = this.laConnexion.prepareStatement(requete);
			ste.setInt(1, userId);
			resultat = ste.executeQuery();
			while (resultat.next()) {
				Compte cpte = FactoryCompte.getInstance().creerCompte();
				cpte.setNo(resultat.getInt("id"));
				cpte.setLibelle(resultat.getString("libelle"));
				cpte.setSolde(resultat.getDouble("solde"));
				listeCpt.add(cpte);
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
		return listeCpt;
	}

}