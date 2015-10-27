package fr.banque;

public class Compte implements Comparable<Compte> {
	private int no;
	private String libelle;
	private double solde;
	// private static int dernierNo = 0;

	/**
	 * Utiliser la methode FactoryCompte.creerCompte pour creer un compte
	 * Creation compte par defaut solde = 0
	 */
	protected Compte() {
		this(0);
	}

	/**
	 * Utiliser la methode FactoryCompte.creerCompte pour creer un compte
	 *
	 * @param solde
	 */
	protected Compte(double solde) {
		super();
		this.solde = solde;
		// this.no = ++Compte.dernierNo;
	}

	public void setNo(int no) {
		this.no = no;
	}


	public double getSolde() {
		return this.solde;
	}


	public void setSolde(double solde) {
		this.solde = solde;
	}


	public int getNo() {
		return this.no;
	}


	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
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

	public void retirer(double montant) throws BanqueException {
		this.setSolde(this.getSolde() - montant);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof Client) {
			Client c = (Client) obj;
			return this.getNo() == c.getNo();
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (this.getNo() == 0) {
			return super.hashCode();
		}
		return (this.getClass().getName() + "_" + this.getNo()).hashCode();
	}

	@Override
	public int compareTo(Compte o) {
		if (this.getNo() == o.getNo()) {
			return 0;
		} else if (this.getNo() < o.getNo()) {
			return -1;
		} else {
			return 1;
		}
	}

}
