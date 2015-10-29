package fr.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NomPrenomReader {

	File fNom;
	File fPrenom;


	public NomPrenomReader() {
		this("", "");
	}

	/**
	 * @param fichierNom
	 * @param fichierPrenom
	 */
	public NomPrenomReader(String fichierNom, String fichierPrenom) {
		super();
		this.fNom = new File(fichierNom);
		this.fPrenom = new File(fichierNom);
	}

	public List<String> readNom() throws IOException {
		List<String> res = new ArrayList<String>();

		// new File("C:/data/sources/chris/exo16/src/nom.txt");
		if (this.fNom.exists() && this.fNom.canRead()) {

			try (BufferedReader reader = new BufferedReader(new FileReader(this.fNom))) {
				String ligne = null;
				while ((ligne = reader.readLine()) != null) {
					res.add(ligne);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Fichier '" + this.fNom + "' pas trouve");
		}
		return res;
	}

	public List<String> readPrenom() throws IOException {
		List<String> res = new ArrayList<String>();

		if (this.fPrenom.exists() && this.fPrenom.canRead()) {

			try (BufferedReader reader = new BufferedReader(new FileReader(this.fPrenom))) {
				String ligne = null;
				while ((ligne = reader.readLine()) != null) {
					res.add(ligne);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Fichier '" + this.fPrenom + "' pas trouve");
		}
		return res;
	}


}
