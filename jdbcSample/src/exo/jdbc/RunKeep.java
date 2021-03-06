package exo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RunKeep {

	public RunKeep() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// 1 - Chargement du driver en memoire
		// --- Trouver le nom du driver ????
		// --- On cherche le nom d'une classe ???

		// preferer le PreparedStatement au Statement, plus puissant,
		// il s'occupe de traiter le type des donn�es

		String nomDuDriver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(nomDuDriver); // on charge le driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(-1); // on sort si on ne peut pas acceder au driver
		}

		// 2 - Recuperer une connexion a la base
		// --- J'ai besoin login/password
		// --- J'ai besoin de l'URL pour acceder a la base

		String login = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/world";
		// depend du jdbc
		// voir
		// https://dev.mysql.com/doc/connector-j/en/connector-j-reference-configuration-properties.html

		PreparedStatement ste = null;
		ResultSet resultat = null;
		Connection connexion = null;

		try {
			connexion = DriverManager.getConnection(url, login, password);

			// 3- Balancer une requete
			// String requete = "select * from city where Name=?";
			String requete = "select * from city where Name like ? and ID > ?";
			ste = connexion.prepareStatement(requete);
			// ste.setString(1, "Breda"); // on ne s'occupe pas du formatage
			ste.setString(1, "B%"); // on ne s'occupe pas du formatage
			//// pratique pour les dates car ca depend
			// pas de la bdd
			ste.setInt(2, 4000);

			// 4- recuperer le resultat
			resultat = ste.executeQuery();
			while (resultat.next()) {
				int id = resultat.getInt("ID");
				// les index en base de donnees commencent a 1
				// preferer les noms de colonnes
				String nom = resultat.getString("Name");
				String countryCode = resultat.getString("CountryCode");
				String district = resultat.getString("District");
				int population = resultat.getInt("Population");
				System.out.println("Id=" + id + ", Nom= " + nom + "CountryCode= " + countryCode + ", District="
						+ district + ", Population=" + population);
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

