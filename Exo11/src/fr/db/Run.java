package fr.db;

import java.sql.SQLException;
import java.util.List;

import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.Operation;

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
