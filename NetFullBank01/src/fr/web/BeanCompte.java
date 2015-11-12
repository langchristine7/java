package fr.web;

import java.io.Serializable;

public class BeanCompte implements Serializable {

	private static final long serialVersionUID = 1L;
	private int no;
	private String libelle;
	private double solde;
	private double taux;
	private double seuil;
	private boolean hasSeuil;
	private boolean hasTaux;

	/**
	 * @param no
	 * @param libelle
	 * @param solde
	 * @param taux
	 * @param seuil
	 */
	public BeanCompte() {
		this(0, "", 0d, 0d, 0d);
	}

	public BeanCompte(int no, String libelle, double solde, double taux, double seuil) {
		super();
		this.no = no;
		this.libelle = libelle;
		this.solde = solde;
		this.taux = taux;
		this.seuil = seuil;
		this.hasTaux = false;
		this.hasSeuil = false;
	}

	public int getNo() {
		return this.no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getSolde() {
		return this.solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public double getTaux() {
		return this.taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public double getSeuil() {
		return this.seuil;
	}

	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}

	public boolean isHasSeuil() {
		return this.hasSeuil;
	}

	public void setHasSeuil(boolean hasSeuil) {
		this.hasSeuil = hasSeuil;
	}

	public boolean isHasTaux() {
		return this.hasTaux;
	}

	public void setHasTaux(boolean hasTaux) {
		this.hasTaux = hasTaux;
	}

}
