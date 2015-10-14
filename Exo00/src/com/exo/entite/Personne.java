package com.exo.entite;

import java.io.Serializable;

/**
 * I am a person
 *
 * @author chris
 *
 *         visibilite package intentionnelle
 */
class Personne implements IMonInterface, Cloneable, Serializable, Comparable<Personne>, IPersonne {

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


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Personne o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.exo.entite.IMonInterface#faireQQChose()
	 */
	@Override
	public void faireQQChose() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.exo.entite.IMonInterface#calculerUnNombre(int)
	 */
	@Override
	public int calculerUnNombre(int unA) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
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
			IPersonne objPers = (IPersonne) obj;
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

	/* (non-Javadoc)
	 * @see com.exo.entite.IPersonne#getPrenom()
	 */
	@Override
	public String getPrenom() {
		return this.prenom == null ? "" : this.prenom;
	}

	/* (non-Javadoc)
	 * @see com.exo.entite.IPersonne#setPrenom(java.lang.String)
	 */
	@Override
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/* (non-Javadoc)
	 * @see com.exo.entite.IPersonne#getAge()
	 */
	@Override
	public int getAge() {
		return this.age;
	}

	/* (non-Javadoc)
	 * @see com.exo.entite.IPersonne#setAge(int)
	 */
	@Override
	public void setAge(int age) {
		this.age = age;
	}

	/* (non-Javadoc)
	 * @see com.exo.entite.IPersonne#getNom()
	 */
	@Override
	public String getNom() {
		return this.nom == null ? "" : this.nom;
	}

	/* (non-Javadoc)
	 * @see com.exo.entite.IPersonne#setNom(java.lang.String)
	 */
	@Override
	public void setNom(String unNom) {
		this.nom = unNom;
	}

	public void afficher() {
		System.out.println(this.getNom() + " " + this.getPrenom() + " " + this.getAge());
	}

	/* (non-Javadoc)
	 * @see com.exo.entite.IPersonne#inverser(com.exo.entite.IPersonne)
	 */
	@Override
	public void inverser(IPersonne a) {
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
