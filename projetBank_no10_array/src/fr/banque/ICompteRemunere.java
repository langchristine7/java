package fr.banque;

public interface ICompteRemunere {

	/**
	 * @return the taux
	 */
	double getTaux();

	/**
	 * @param taux
	 *            the taux to set
	 */
	void setTaux(double taux);

	/*
	 * Calcule les interets en fonction du taux et du Solde
	 */
	double calculerInterets();

	void verserInterets();

}