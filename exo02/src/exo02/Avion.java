package exo02;

public class Avion<T> {

	private T[] soute;
	private int taille;
	/**
	 * taille par defaut 25 cases dans la soute
	 *
	 */
	// on la met public pour que le developpeur puisse acceder a la valeur par
	// defaut
	// par le code
	public final static int TAILLE_DEFAULT = 25;

	/**
	 * Constructeur sans parametre <br>
	 * 25 places par defaut
	 *
	 */

	public Avion() {
		this(Avion.TAILLE_DEFAULT);
	}

	public Avion(int taille) {
		this.taille = taille;
	}

	public void charger(int place, T unElement) {
		if (this.soute == null) {
			this.soute = (T[]) new Object[this.taille];
		}
		if ((place < this.taille) && (place >= 0)) {
			this.soute[place] = unElement;
		}
	}

	public T decharger(int place) {
		if (this.soute == null) {
			return null;
		}
		return this.soute[place];
	}
}
