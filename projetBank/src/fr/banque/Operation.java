package fr.banque;

import java.util.Date;

public class Operation {

	private int id;
	private String libelle;
	private Double montant;
	private Date date;
	private int compteId;

	/**
	 * @param id
	 * @param libelle
	 * @param montant
	 * @param date
	 * @param compteId
	 */
	public Operation(int id, String libelle, Double montant, Date date, int compteId) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.montant = montant;
		this.date = date;
		this.compteId = compteId;
	}

	public Operation(int idC) {
		this(0, "no label", 0.0, new Date(), idC);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Double getMontant() {
		return this.montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCompteId() {
		return this.compteId;
	}

	public void setCompteId(int compteId) {
		this.compteId = compteId;
	}

}
