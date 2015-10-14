package fr.banque;

public final class FactoryCompte {
	private static FactoryCompte instance;
	private int dernierNo;

	private FactoryCompte() {
		this.dernierNo = 0;
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
		unCompte.setNo(this.creerNumero());
		unCompte.setSolde(solde);
		return unCompte;
	}

	private int creerNumero() {
		return ++this.dernierNo;
	}

	public CompteRemunere creerCompteRemunere () {
		CompteRemunere unCompte = new CompteRemunere();
		unCompte.setNo(this.creerNumero());
		return unCompte;
	}

	public CompteASeuil creerCompteASeuil() {
		CompteASeuil unCompte = new CompteASeuil();
		unCompte.setNo(this.creerNumero());
		return unCompte;
	}

	public CompteASeuilRemunere creerCompteASeuilRemunere() {
		CompteASeuilRemunere unCompte = new CompteASeuilRemunere();
		unCompte.setNo(this.creerNumero());
		return unCompte;
	}
}

