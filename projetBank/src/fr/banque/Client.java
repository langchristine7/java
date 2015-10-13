package fr.banque;

import java.util.Arrays;

public class Client {
	private String nom;
	private String prenom;
	private int age;
	private int no;
	Compte[] comptes;
	private int nbComptes = 0;
	public static final int MAX_COMPTES = 5;
	private static int dernierNo = 0;

	public Client() {
		this("", "", 0);
	}

	public Client(String nom, String prenom, int age) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.setNo(++Client.dernierNo);
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return this.prenom;
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

	/**
	 * @return the no
	 */
	public int getNo() {
		return this.no;
	}

	/**
	 * @param no
	 *            the no to set
	 */
	private void setNo(int no) {
		this.no = no;
	}

	/**
	 * @return the comptes
	 */
	public Compte[] getComptes() {
		return this.comptes;
	}

	/**
	 * @param comptes
	 *            the comptes to set
	 */
	public void setComptes(Compte[] comptes) {
		this.comptes = comptes;
	}

	/**
	 * @return the dernierNo
	 */
	public static int getDernierNo() {
		return Client.dernierNo;
	}

	/**
	 * @param dernierNo
	 *            the dernierNo to set
	 */
	private static void setDernierNo(int dernierNo) {
		Client.dernierNo = dernierNo;
	}

	/**
	 * @return the maxComptes
	 */
	public static int getMaxComptes() {
		return Client.MAX_COMPTES;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName());
		builder.append(" [getNom()=");
		builder.append(this.getNom());
		builder.append(", getPrenom()=");
		builder.append(this.getPrenom());
		builder.append(", getAge()=");
		builder.append(this.getAge());
		builder.append(", getNo()=");
		builder.append(this.getNo());
		builder.append(", getComptes()=");
		builder.append(Arrays.toString(this.getComptes()));
		builder.append("]");
		return builder.toString();
	}

	public void afficher() {
		System.out.println(this.toString());
	}

	public void ajouterCompte(Compte compte) {
		if (compte == null) {
			return;
		}
		if (this.nbComptes == Client.MAX_COMPTES) {
			if (this.comptes == null) {
				this.comptes = new Compte[Client.MAX_COMPTES];
			}
		}
		this.comptes[this.nbComptes++] = compte;
	}

	public Compte getCompte(int no) {
		if (this.comptes == null) {
			return null;
		}
		for (int i = 0; i < this.nbComptes; i++) {
			if (this.comptes[i].getNo() == no) {
				return this.comptes[i];
			}
		}
		return null;
	}
}
