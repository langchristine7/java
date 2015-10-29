
public class Stagiaire {
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String codePostal;
	private String ville;
	private String email;

	private static int lastId = 0; // numero du dernier Stagiaire

	public Stagiaire(String nom, String prenom, String email) {
		super();
		this.id = ++Stagiaire.lastId;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass());
		builder.append(" [getId()=");
		builder.append(this.getId());
		builder.append(", getNom()=");
		builder.append(this.getNom());
		builder.append(", getPrenom()=");
		builder.append(this.getPrenom());
		builder.append(", getAdresse()=");
		builder.append(this.getAdresse());
		builder.append(", getCodePostal()=");
		builder.append(this.getCodePostal());
		builder.append(", getVille()=");
		builder.append(this.getVille());
		builder.append(", getEmail()=");
		builder.append(this.getEmail());
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return this.adresse;
	}

	/**
	 * @param adresse
	 *            the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return this.codePostal;
	}

	/**
	 * @param codePostal
	 *            the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return this.ville;
	}

	/**
	 * @param ville
	 *            the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



}
