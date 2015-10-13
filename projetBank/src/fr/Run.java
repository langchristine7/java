package fr;
import fr.banque.Client;
import fr.banque.Compte;
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

		c1.retirer(50);
		c1.afficher();

		Client client1 = new Client();
		client1.afficher();

		Client client2 = new Client("Durand", "Albert", 25);
		client2.afficher();
		client2.ajouterCompte(c1);
		client2.afficher();

		c2.ajouter(250);
		client2.ajouterCompte(c2);
		c2.afficher();

		client2.afficher();

		// recherche du compte no 3

		int cptRecherche = 3;
		Compte cpt3 = client2.getCompte(cptRecherche);

		if (cpt3 == null) {
			System.out.print("Client ");
			System.out.print(client2.getNom());
			System.out.println(" ne contient pas le compte no " + cptRecherche);
		} else {
			cpt3.afficher();
		}
	}
}
