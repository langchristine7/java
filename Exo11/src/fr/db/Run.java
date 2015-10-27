package fr.db;

import java.sql.SQLException;
import java.util.List;

import fr.banque.Client;
import fr.banque.Compte;

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

			// recherche du client id = 1
			System.out.println("\nrecupererClient --- Recherche du client no 1");

			Client client = db.recupererClient(1);
			System.out.println(client);

			List<Compte> listCpt = db.listerComptes(2);
			System.out.println(listCpt);

		} catch (SQLException e) {
			System.out.println("Probleme de connexion : ");
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();

		}


	}

}
