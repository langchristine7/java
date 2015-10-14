package com.exo.entite;

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
		d1.setAge(25);

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

	}

}
