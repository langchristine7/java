package fr.banque;

public final class FactoryCompte {
	private static FactoryCompte instance;
	private int dernierNo;

	private FactoryCompte() {
		this.dernierNo = -1;
	}

	public static FactoryCompte getInstance() {
		if (FactoryCompte.instance == null) {
			synchronized (FactoryCompte.class) {
				if (FactoryCompte.instance == null) {
					FactoryCompte.instance = new FactoryCompte();
				}
			}
		}
		return FactoryCompte.instance;
	}

	public Compte creerCompte() {
		return this.creerCompte(0);
	}

	public Compte creerCompte(int solde) {
		Compte unCompte = new Compte();
		unCompte.setNo(++this.dernierNo);
		unCompte.setSolde(solde);
		return unCompte;
	}
}

