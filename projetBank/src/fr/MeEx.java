package fr;

import java.io.File;

public class MeEx {

	public static void main(String[] args) {
		File f = new File("c:/temp");
		if (f.exists() && f.canRead()) {

			File[] lesFichiers = f.listFiles();
			for (int i = 0; i < lesFichiers.length; i++) {
				System.out.println(lesFichiers[i]);
			}
		} else {
			System.out.println("Le repertoire n'est pas lisible");
		}
	}
}
