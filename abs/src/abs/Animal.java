package abs;

public abstract class Animal {
	private String nom;
	private int age;

	public Animal() {
		// pas obligatoire, utile plus tard
		// il vaut mieux en faire une
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	// pas de code il faudra la redefinir
	/**
	 * il faut expliquer dans le commentaire ce que la methode doit faire
	 *
	 */
	public abstract void crier();

	public void faireDuBruit() {
		this.crier();
		this.crier();
		this.crier();
		this.crier();
		this.crier();
	}
}
