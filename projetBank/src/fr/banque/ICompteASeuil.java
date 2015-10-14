package fr.banque;

public interface ICompteASeuil {

	/**
	 * @return the seuil
	 */
	double getSeuil();

	/**
	 * @param seuil
	 *            the seuil to set
	 */
	void setSeuil(double seuil);

	void retirer(double uneValeur);

}