package fr.banque;

public final class FactoryElem <T> {

	private static FactoryElem<T> instance;
	private int dernierNo;

	private FactoryElem() {
		this.dernierNo = -1;
	}

	public static FactoryElem getInstance() {
		if (FactoryElem.instance == null) {
			synchronized (FactoryCompte.class) {
				if (FactoryElem.instance == null) {
					FactoryElem.instance = new FactoryElem();
				}
			}
		}
		return FactoryElem.instance;
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


}
