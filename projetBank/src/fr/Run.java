package fr;

import fr.banque.BanqueException;
import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.CompteASeuil;
import fr.banque.CompteASeuilRemunere;
import fr.banque.CompteRemunere;
import fr.banque.FactoryCompte;

public class Run {
	public static void main(String[] args) {

		FactoryCompte facCompte = FactoryCompte.getInstance();

		Compte c1 = facCompte.creerCompte();
		c1.afficher();
		Compte c2 = facCompte.creerCompte();
		c2.afficher();

		c1.ajouter(200);
		c1.afficher();

		try {
			c1.retirer(50);
		} catch (BanqueException e) {
			System.out.println("ERREUR: Impossible de retirer de l'argent : " + e.getMessage());
		}
		c1.afficher();

		Client client1 = new Client();
		client1.afficher();

		Client client2 = new Client("Durand", "Albert", 25);
		client2.afficher();

		try {
			client2.ajouterCompte(c1);
		} catch (BanqueException e) {
			System.out.println("ERREUR : Impossible d'ajouter le compte : " + e.getMessage());
		}

		client2.afficher();
		c2.ajouter(250);

		try {
			client2.ajouterCompte(c2);
		} catch (BanqueException e) {
			System.out.println("ERREUR : Impossible d'ajouter le compte : " + e.getMessage());
		}
		c2.afficher();

		client2.afficher();

		// recherche du compte no cptRecherche

		int cptRecherche = 3;
		Compte cpt3 = client2.getCompte(cptRecherche);

		if (cpt3 == null) {
			System.out.print("Client ");
			System.out.print(client2.getNom());
			System.out.println(" ne contient pas le compte no " + cptRecherche);
		} else {
			cpt3.afficher();
		}

		System.out.println("Nouveaux clients -----------------------------------");

		CompteRemunere cr1 = facCompte.creerCompteRemunere();
		try {
			client1.ajouterCompte(cr1);
		} catch (BanqueException e) {
			System.out.println("ERREUR : Impossible d'ajouter le compte : " + e.getMessage());
		}
		cr1.setSolde(150d);
		cr1.setTaux(0.03);
		System.out.println(" interets : " + cr1.calculerInterets());
		// cr1.verserInterets();
		CompteASeuil cs1 = facCompte.creerCompteASeuil();
		try {
			client1.ajouterCompte(cs1);
		} catch (BanqueException e) {
			System.out.println("ERREUR : Impossible d'ajouter le compte : " + e.getMessage());
		}

		cs1.setSolde(20);
		cs1.setSeuil(-10);
		try {
			cs1.retirer(100);
		} catch (BanqueException e) {
			System.out.println("ERREUR: Impossible de retirer de l'argent : " + e.getMessage());
		}

		client1.afficher();
		client1.verserInteretsTsComptes();
		System.out.println("apres interets");
		client1.afficher();

		CompteRemunere cr3 = facCompte.creerCompteRemunere();
		CompteASeuil cs3 = facCompte.creerCompteASeuil();
		try {
			client2.ajouterCompte(cr3);
			client2.ajouterCompte(cs3);
		} catch (BanqueException e) {
			System.out.println("ERREUR : Impossible d'ajouter le compte : " + e.getMessage());
		}

		client2.afficher();

		System.out.println("CompteASeuilRemunere ---------------------------------------");
		CompteASeuilRemunere csr1 = facCompte.creerCompteASeuilRemunere();
		try {
			client1.ajouterCompte(csr1);
		} catch (BanqueException e)
		{
			System.out.println("ERREUR : Impossible d'ajouter le compte : " + e.getMessage());
		}
		csr1.setTaux(0.02);
		csr1.setSeuil(-20);
		csr1.setSolde(50);
		try {
			csr1.retirer(100);
		} catch (BanqueException e) {
			System.out.println("ERREUR: Impossible de retirer de l'argent : " + e.getMessage());
		}
		client1.verserInteretsTsComptes();

		client1.afficher();

		Client clientTropComptes = new Client("trop", "de comptes", 10);
		for (int i = 0; i < 10; i++) {
			Compte cptTmp = facCompte.creerCompte();
			try {
				clientTropComptes.ajouterCompte(cptTmp);
			} catch (BanqueException e) {
				System.out.println("ERREUR : impossible d'ajouter encore un compte : " + e.getMessage());
			}
		}
	}

}
