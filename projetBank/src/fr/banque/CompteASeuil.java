package fr.banque;

public class CompteASeuil extends Compte implements ICompteASeuil {
	private double seuil;

	public CompteASeuil() {
		this(0.0);
	}

	/**
	 * @param seuil
	 */
	public CompteASeuil(double seuil) {
		super();
		this.setSeuil(seuil);
	}

	/* (non-Javadoc)
	 * @see fr.banque.ICompteASeuil#getSeuil()
	 */
	@Override
	public double getSeuil() {
		return this.seuil;
	}

	/* (non-Javadoc)
	 * @see fr.banque.ICompteASeuil#setSeuil(double)
	 */
	@Override
	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.delete(builder.length() - 1, builder.length());
		builder.append(", seuil : ");
		builder.append(this.seuil);
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see fr.banque.ICompteASeuil#retirer(double)
	 */

	@Override
	public void retirer (double uneValeur)
	{
		double nouveauSolde = this.getSolde()-uneValeur ;
		if (nouveauSolde < this.getSeuil()) {
			System.out.print("Retrait non autorisé, le seuil est de : ");
			System.out.println(this.getSeuil());
			return;
		}
		else {
			this.setSolde(nouveauSolde);
		}
	}
}
