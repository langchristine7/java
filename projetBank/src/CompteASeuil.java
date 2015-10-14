import fr.banque.Compte;

public class CompteASeuil extends Compte {
	private double seuil;

	/**
	 * @return the seuil
	 */
	public double getSeuil() {
		return this.seuil;
	}

	/**
	 * @param seuil
	 *            the seuil to set
	 */
	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}

	/**
	 * @param seuil
	 */
	public CompteASeuil(double seuil) {
		super();
		this.setSeuil(seuil);
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

	@Override
	public void retirer (double uneValeur)
	{
		double nouveauSolde = this.getSolde()-uneValeur ;
		if (nouveauSolde < this.getSeuil()) {
			System.out.print("Retrait non autorisé, le seuil est de : ");
			System.out.print(this.getSeuil());
			return;
		}
		else {
			this.setSolde(nouveauSolde);
		}
	}
}
