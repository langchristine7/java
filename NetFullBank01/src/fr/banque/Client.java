package fr.banque;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class Client implements Serializable {
	private String nom;
	private String prenom;
	private int age;
	private int no;
	protected Map<Integer, Compte> comptes;
	public static final int MAX_COMPTES = 5;
	private static int dernierNo = 0;

	public Client() {
		this("", "", 0);
	}

	public Client(String nom, String prenom, int age) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.setNo(++Client.dernierNo);
		this.comptes = new Hashtable<Integer, Compte>();
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getNo() {
		return this.no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Compte[] getComptes() {
		if ((this.comptes == null) || (this.comptes.size() == 0)) {
			return null;
		}

		Compte[] tab = new Compte[this.comptes.size()];
		return this.comptes.values().toArray(tab);
	}

	public void setComptes(Compte[] desComptes) {
		if (desComptes == null) {
			System.out.println("param desComptes of setComptes is null");
			return;
		}
		if (this.comptes == null) {
			this.comptes = new Hashtable<Integer, Compte>() ;
		}

		for (Compte c : desComptes) {
			this.comptes.put(new Integer(c.getNo()), c);
		}
	}

	public static int getDernierNo() {
		return Client.dernierNo;
	}

	protected static void setDernierNo(int dernierNo) {
		Client.dernierNo = dernierNo;
	}

	public static int getMaxComptes() {
		return Client.MAX_COMPTES;
	}

	public int getNbComptes() {
		return this.comptes.size();
	}

	public void afficher() {
		System.out.println(this.toString());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName());
		builder.append(" [");
		if (this.getNom() != null) {
			builder.append("getNom()=");
			builder.append(this.getNom());
			builder.append(", ");
		}
		if (this.getPrenom() != null) {
			builder.append("getPrenom()=");
			builder.append(this.getPrenom());
			builder.append(", ");
		}
		builder.append("getAge()=");
		builder.append(this.getAge());
		builder.append(", getNo()=");
		builder.append(this.getNo());
		builder.append(", ");
		if (this.comptes != null) {
			builder.append("comptes=");
			builder.append(this.comptes);
			builder.append(", ");
		}
		builder.append("getNbComptes()=");
		builder.append(this.getNbComptes());
		builder.append(", hashCode()=");
		builder.append(this.hashCode());
		builder.append("]");
		return builder.toString();
	}

	public void ajouterCompte(Compte compte) throws BanqueException {
		if (compte == null) {
			throw new BanqueException("ajouterCompte : compte est null");
		}
		//
		// if (this.nbComptes == Client.MAX_COMPTES) {
		// throw new BanqueException("Le nombre de comptes maximum est atteint :
		// " + Client.MAX_COMPTES);
		// }
		if (this.comptes == null) {
			this.comptes = new Hashtable<Integer, Compte>();
		}
		this.comptes.put(compte.getNo(), compte);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	// @Override
	// public String toString() {
	// StringBuilder builder = new StringBuilder();
	// builder.append("Client [");
	// if (this.comptes != null) {
	// builder.append("comptes=");
	// builder.append(this.comptes);
	// builder.append(", ");
	// }
	// if (this.getNom() != null) {
	// builder.append("getNom()=");
	// builder.append(this.getNom());
	// builder.append(", ");
	// }
	// if (this.getPrenom() != null) {
	// builder.append("getPrenom()=");
	// builder.append(this.getPrenom());
	// builder.append(", ");
	// }
	// builder.append("getAge()=");
	// builder.append(this.getAge());
	// builder.append(", getNo()=");
	// builder.append(this.getNo());
	// builder.append(", getNbComptes()=");
	// builder.append(this.getNbComptes());
	// builder.append(", hashCode()=");
	// builder.append(this.hashCode());
	// builder.append("]");
	// return builder.toString();
	// }

	public Compte getCompte(int no) {
		if (this.comptes == null) {
			return null;
		}
		// for (int i = 0; i < this.nbComptes; i++) {
		// if (this.comptes[i].getNo() == no) {
		// return this.comptes[i];
		// }
		// }
		return (this.comptes.get(new Integer(no)));
	}

	@Override
	public boolean equals (Object obj) {
		if (obj == null){
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

	public void verserInteretsTsComptes () {
		Iterator<Compte> iterValue = this.comptes.values().iterator();
		while (iterValue.hasNext()) {
			Compte cpt = iterValue.next();
			if (cpt instanceof ICompteRemunere) {
				((ICompteRemunere) cpt).verserInterets();
			}
		}
	}
}
