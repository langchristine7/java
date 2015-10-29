package fr.banque;

import java.io.Serializable;
import java.util.Date;

public class Operation implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private transient String libelle;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Operation [getId()=");
		builder.append(this.getId());
		builder.append(", ");
		if (this.getLibelle() != null) {
			builder.append("getLibelle()=");
			builder.append(this.getLibelle());
			builder.append(", ");
		}
		if (this.getMontant() != null) {
			builder.append("getMontant()=");
			builder.append(this.getMontant());
			builder.append(", ");
		}
		if (this.getDate() != null) {
			builder.append("getDate()=");
			builder.append(this.getDate());
			builder.append(", ");
		}
		builder.append("getCompteId()=");
		builder.append(this.getCompteId());
		builder.append("]");
		return builder.toString();
	}

}
