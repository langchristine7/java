package fr.banque;

public class CompteASeuilRemunere extends CompteASeuil implements ICompteRemunere {

	private double taux;

	CompteASeuilRemunere() {
		this(0d, 0d);
	}

	CompteASeuilRemunere(double seuil, double taux) {
		super(seuil);
		this.setTaux(taux);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getTaux() {
		// TODO Auto-generated method stub
		return this.taux;
	}

	@Override
	public void setTaux(double taux) {
		// TODO Auto-generated method stub
		this.taux = taux;
	}

	/*
	 * Calcule les interets en fonction du taux et du Solde
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see fr.banque.ICompteRemunere#calculerInterets()
	 */
	@Override
	public double calculerInterets() {
		return this.getTaux() * this.getSolde();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.banque.ICompteRemunere#verserInterets()
	 */
	@Override
	public void verserInterets() {
		this.ajouter(this.calculerInterets());
	}

}
