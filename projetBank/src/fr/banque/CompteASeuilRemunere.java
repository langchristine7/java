package fr.banque;

public class CompteASeuilRemunere extends CompteASeuil implements ICompteASeuilRemunere {

	private double taux;

	CompteASeuilRemunere() {
		this(0d, 0d);
	}

	CompteASeuilRemunere(double seuil, double taux) {
		super(seuil);
		this.setTaux(taux);
	}

	@Override
	public double getTaux() {
		return this.taux;
	}

	@Override
	public void setTaux(double taux) {
		if ((taux < 0) || (taux > 1)) {
			System.out.println("Le taux doit être compris entre 0 et 1");
		} else {
			this.taux = taux;
		}
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
