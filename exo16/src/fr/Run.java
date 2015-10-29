package fr;

import java.io.IOException;
import java.util.List;

import fr.io.NomPrenomReader;
import fr.io.UtilisateurWriter;

public class Run {

	public static void main(String[] args) {

		String fnom = "C:/data/sources/chris/exo16/src/nom.txt";
		String fprenom = "C:/data/sources/chris/exo16/src/prenom.txt";
		String fresultat = "c:/temp/resultat.csv";
		int combien = 50;
		NomPrenomReader npr = new NomPrenomReader(fnom, fprenom);

		List<String> listeNom, listePrenom;
		try {

			listeNom = npr.readNom();
			listePrenom = npr.readPrenom();
			//System.out.println(listeNom);
			UtilisateurWriter uw = new UtilisateurWriter(fresultat);
			uw.writeUtilisateur(listeNom, listePrenom, combien);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
