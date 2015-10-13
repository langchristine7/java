package com.exo.entite;

/**
 * I am a person
 *
 * @author chris
 *
 *         visibilite package intentionnelle
 */
class Personne {

	/**
	 * my properties
	 */
	private String nom;

	private String prenom;

	private int age = -1;

	private static int compteur;

	Personne() {
		this("", "");
	}

	public Personne(String nom, String prenom) {
		this(nom, prenom, -1);
	}

	public Personne(String nom, String prenom, int age) {
		Personne.compteur++;
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAge(age);
	}


	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("use of finalize method");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	// @Override
	// public String toString() {
	// // TODO Auto-generated method stub
	// StringBuffer buff = new StringBuffer();
	// buff.append(this.getClass().getName());
	// buff.append(" Nom : ");
	// buff.append(this.getNom());
	// buff.append(" Prenom : ");
	// buff.append(this.getPrenom());
	// buff.append(" Age : ");
	// buff.append(this.getAge());
	// return buff.toString();
	// }


	/**
	 * @return the compteur
	 */
	public static int getCompteur() {
		return Personne.compteur;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName()).append(" [");
		if (this.nom != null) {
			builder.append("nom=");
			builder.append(this.nom);
			builder.append(", ");
		}
		if (this.prenom != null) {
			builder.append("prenom=");
			builder.append(this.prenom);
			builder.append(", ");
		}
		builder.append("age=");
		builder.append(this.age);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false; // this ne peut pas etre egale a nulle
		}

		if (obj == this) {
			return true; // ce sont les memes pointeurs
		}

		if (obj instanceof Personne) { // marche meme si heritage
			//if (obj.getClass() == Personne.class) {
			Personne objPers = (Personne) obj;
			return ( (this.getNom() == objPers.getNom()) || (this.getNom().equals(objPers.getNom())
					&& ((this.getPrenom() == objPers.getPrenom())
							|| this.getPrenom().equals(objPers.getPrenom()))
					&& (this.getAge() == objPers.getAge())));
		} // l'objet n'est pas de la classe Personne

		return false;

	}

	/**
	 * @param compteur
	 *            the compteur to set
	 */
	private static void setCompteur(int compteur) {
		Personne.compteur = compteur;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return this.prenom == null ? "" : this.prenom;
	}

	/**
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	public String getNom() {
		return this.nom == null ? "" : this.nom;
	}

	public void setNom(String unNom) {
		this.nom = unNom;
	}

	public void afficher() {
		System.out.println(this.getNom() + " " + this.getPrenom() + " " + this.getAge());
	}

	public void inverser(Personne a) {
		a.setAge(105);

	}

	@Override
	public int hashCode() {
		// identifie l'objet de maniere unique pour etre utilise pour une
		// comparaison
		// on peut mettre ce que l'on veut
		String b = this.getClass().getName() + "_" + this.toString();
		return b.hashCode();
	}

}
