package fr.banque;

public class Compte {
	private int no;
	private double solde;
	// private static int dernierNo = 0;

	/**
	 * Creation compte par defaut solde = 0
	 */
	Compte() {
		this(0);
	}

	Compte(double solde) {
		super();
		this.solde = solde;
		// this.no = ++Compte.dernierNo;
	}

	void setNo(int no) {
		this.no = no;
	}

	/**
	 * @return the solde
	 */
	public double getSolde() {
		return this.solde;
	}

	/**
	 * @param solde
	 *            the solde to set
	 */
	protected void setSolde(double solde) {
		this.solde = solde;
	}

	/**
	 * @return the no
	 */
	public int getNo() {
		return this.no;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName()).append("[getSolde()=");
		builder.append(this.getSolde());
		builder.append(", getNo()=");
		builder.append(this.getNo());
		builder.append("]");
		return builder.toString();
	}

	public void afficher() {
		System.out.println(this.toString());

	}

	public void ajouter(double montant) {
		this.setSolde(this.getSolde() + montant);
	}

	public void retirer(double montant) {
		this.setSolde(this.getSolde() - montant);
	}

	@Override
	public boolean equals(Object obj) {
		return this.no == ((Compte) obj).getNo();
	}

}
