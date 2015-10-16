package com.exo.entite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Run {

	public static void main(String[] args) {
		//		Personne p1 = new Personne();
		//		Personne p2 = new Personne();
		//		Personne p3 = new Personne("Pépin", "Arnold", 8);
		//
		//		p1.afficher();
		//		p1.setNom("Dupont");
		//		p1.setPrenom("Albert");
		//		p1.afficher();
		//
		//		p2.afficher();
		//		p2.setNom("Durand");
		//		p2.setPrenom("John");
		//		p2.afficher();
		//
		//		p1.inverser(p2);
		//		p1.afficher();
		//		p2.afficher();
		//		p3.afficher();
		//
		//		int x = 25;
		//		int y = 55;
		//
		//		System.out.println("x = " + x + "  y = " + y);
		//
		//		System.out.println(Personne.getCompteur() + " personnes créées");
		//
		//		System.out.println(p2);
		//		System.out.println(p2.getClass().getName());
		//		System.out.println(Personne.class);
		//
		//		Class classeDeP1 = p1.getClass();
		//
		//		if (Personne.class.equals(classeDeP1)) {
		//			;
		//		}
		//		long start = System.currentTimeMillis();
		//		for (int i = 0; i < 50000; i++) {
		//			Personne pLocal = new Personne();
		//			System.out.println("mon instance " + pLocal);
		//		}
		//
		//		long end = System.currentTimeMillis();
		//		System.out.println("mon temps : " + (end - start));
		//


		IPersonne p1 = new Personne ("Dupont", "Albert", 25);
		IPersonne p2 = new Personne("Dupont", "Albert", 25);

		if (p1==p2){
			System.out.println("ok");

		}
		System.out.println("equals ? " + p1.equals(p2)); // true

		System.out.println("toto".equals(p1)); // false

		Directeur d1 = new Directeur();
		d1.setNom("Dupont");
		d1.setPrenom("Albert");
		try {
			System.out.println("-- A --");

			d1.setAge(25);
			System.out.println("-- B --"); // va dans le catch si l'exception
			// est levee
		} catch (MonException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		} finally
		{
			System.out.println("on passe ici dans tous les cas");
			System.out.println("pour fermer des flux et fichiers");
		}

		System.out.println(d1.equals(p1));
		System.out.println(p1.equals(d1));

		System.out.println(p1.hashCode());
		System.out.println(d1.hashCode());

		String a = "Bonjour";
		String b = "toto";
		String c = "Bonjour";

		System.out.println("a=" + a.hashCode());
		System.out.println("b=" + b.hashCode());
		System.out.println("c=" + c.hashCode());

		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println(d1.hashCode());

		Directeur d3 = new Directeur("Dupont", "Albert", 25);

		IMonInterface im = new Personne();
		im.calculerUnNombre(15);

		IPersonne p3 = new Gens();

		List<String> maListe = new ArrayList<String>();
		maListe.add("ZZZ");
		maListe.add("10Toto4");
		maListe.add("1Toto");
		maListe.add("Toto2");

		System.out.println(maListe);
		Collections.sort(maListe, new MonComparator());
		System.out.println(maListe);

		Set<String> monSet = new HashSet<String>();
		monSet.add("Toto");
		monSet.add("Toto"); // n'insert pas 2 fois le meme element
		monSet.add("Titi");
		monSet.add("Tata");
		System.out.println(monSet);

		Map<Integer, String> maMap = new Hashtable<Integer, String>();
		Map<Integer, List<String>> maMap2 = new Hashtable<Integer, List<String>>();
		maMap.put(4, "toto");
		maMap.put(1, "Titi");
		maMap.put(10, "tata");
		maMap.put(1, "tutu");
		System.out.println(maMap);

		Iterator<Integer> iterClef = maMap.keySet().iterator();
		while (iterClef.hasNext()) {
			Integer uneClef = iterClef.next();
			System.out.println(uneClef + "=" + maMap.get(uneClef));
		}
		// sur les valeurs
		Iterator<String> iterValeur = maMap.values().iterator();
		while (iterValeur.hasNext()) {
			String uneValeur = iterValeur.next();
			System.out.println(uneValeur);
		}
		Iterator<Map.Entry<Integer, String>> iterClefValeur = maMap.entrySet().iterator();
		while (iterClefValeur.hasNext()) {
			Map.Entry<Integer, String> uneClefValeur = iterClefValeur.next();

			System.out.println(uneClefValeur.getKey() + "=" + uneClefValeur.getValue());
		}
	}

}
