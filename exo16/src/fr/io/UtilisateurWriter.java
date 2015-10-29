package fr.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UtilisateurWriter {
	private File fResultat;

	/**
	 *
	 * @param nomFichierRes
	 */
	public UtilisateurWriter(String fResultat) {
		this.fResultat = new File(fResultat);
	}


	public void writeUtilisateur(List<String> listeNom, List<String> listePrenom, int combien) throws IOException {
		Random r = new Random ();
		String[] mm = {"Mr", "Mme"};
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(this.fResultat));

			int lgNom = listeNom.size();
			int lgPrenom = listePrenom.size();

			Collections.shuffle(listeNom);
			Collections.shuffle(listePrenom);

			for (int i = 0; i < combien; i++) {
				String ligne = mm[r.nextInt(mm.length)] + ";" + listeNom.get(r.nextInt(lgNom))

				+ ";" + listePrenom.get(r.nextInt(lgPrenom));
				writer.write(ligne + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
