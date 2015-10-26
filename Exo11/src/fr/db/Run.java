package fr.db;

import java.sql.SQLException;

import fr.banque.Client;

public class Run {

	public Run() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Db db = null;
		try {
			db = new Db();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			System.exit(-1);
		}
		try {
			db.seConnecter();
		} catch (SQLException e) {
			System.out.println("Probleme de connexion : ");
			e.printStackTrace();
		}

		// recherche du client id = 1
		System.out.println("\nrecupererClient --- Recherche du client no 1");

		Client client = db.recupererClient(1);
	}

}
