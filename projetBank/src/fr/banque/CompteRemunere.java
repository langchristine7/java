package fr.banque;

public class CompteRemunere extends Compte

{
	/*
	 * pourcentage de remuneration doit etre compris entre 0 et 1
	 */
	private double taux;

	/**
	 * @return the taux
	 */
	public double getTaux() {
		return this.taux;
	}

	/**
	 * @param taux
	 *            the taux to set
	 */
	public void setTaux(double taux) {
		this.taux = taux;
	}

	public CompteRemunere() {
	}

	/**
	 * @param taux
	 */
	public CompteRemunere(double taux) {
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
	public double calculerInterets() {
		return this.getTaux() * this.getSolde();
	}

	public void verserInterets(double uneValeur) {
		this.ajouter(this.calculerInterets());
	}

}
