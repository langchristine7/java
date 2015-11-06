package fr.banque;

import java.io.Serializable;

public class CompteRemunere extends Compte implements ICompteRemunere, Serializable

{
	/*
	 * pourcentage de remuneration doit etre compris entre 0 et 1
	 */
	private double taux;

	/* (non-Javadoc)
	 * @see fr.banque.ICompteRemunere#getTaux()
	 */
	@Override
	public double getTaux() {
		return this.taux;
	}

	/* (non-Javadoc)
	 * @see fr.banque.ICompteRemunere#setTaux(double)
	 */
	@Override
	public void setTaux(double taux) {
		if ((taux < 0) || (taux > 1)) {
			System.out.println("Le taux doit être compris entre 0 et 1");
		} else {
			this.taux = taux;
		}
	}

	protected CompteRemunere() {
	}

	/**
	 * @param taux
	 */
	protected CompteRemunere(double taux) {
		super();
		this.taux = taux;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.delete(builder.length() - 1, builder.length());
		builder.append(", taux : ");
		builder.append(this.taux);
		builder.append("]");
		return builder.toString();
	}

	/*
	 * Calcule les interets en fonction du taux et du Solde
	 */
	/* (non-Javadoc)
	 * @see fr.banque.ICompteRemunere#calculerInterets()
	 */
	@Override
	public double calculerInterets() {
		return this.getTaux() * this.getSolde();
	}

	/* (non-Javadoc)
	 * @see fr.banque.ICompteRemunere#verserInterets()
	 */
	@Override
	public void verserInterets() {
		this.ajouter(this.calculerInterets());
	}

}
