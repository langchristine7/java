package fr.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.banque.BanqueException;
import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.FactoryCompte;
import fr.banque.Operation;

public class Db {

	private String nomDuDriver = "com.mysql.jdbc.Driver";
	private String login = "root";
	private String password = "root";
	private String url = "jdbc:mysql://localhost:3306/banque";
	private Connection laConnexion = null;

	public Db() throws ClassNotFoundException {
		System.out.println("creation classe connexion ");
		Class.forName(this.nomDuDriver); // on charge le driver
	}

	public void seConnecter(String unLogin, String unPassword, String unUrl) throws SQLException {
		this.login = unLogin;
		this.password = unPassword;
		this.url = unUrl;
		this.seConnecter();
	}

	public void seConnecter() throws SQLException {
		if (this.laConnexion == null) {
			this.laConnexion = DriverManager.getConnection(this.url, this.login, this.password);
		}
	}

	public void seDeconnecter() {
		if (this.laConnexion == null) {
			return;
		}
		try {
			this.laConnexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Client recupererClient(int id) {

		PreparedStatement ste = null;
		ResultSet resultat = null;
		Client client = new Client();

		if (this.laConnexion == null) {
			throw new RuntimeException("Connect to db before");
		}

		String requete = null;
		try {
			requete = "select * from utilisateur where id = ?";
			ste = this.laConnexion.prepareStatement(requete);
			ste.setInt(1, id);
			resultat = ste.executeQuery();
			while (resultat.next()) {
				client.setNo(resultat.getInt("id"));
				client.setNom(resultat.getString("nom"));
				client.setPrenom(resultat.getString("prenom"));
				Date today = new Date();
				java.sql.Date ddn = resultat.getDate("dateDeNaissance");
				if (ddn != null) {
					Date birthday = new Date(ddn.getTime());
					int age = today.getYear() - birthday.getYear();
					client.setAge(age);
				}
				List<Compte> listCpte = new ArrayList<Compte>();
				listCpte = this.listerComptes(id);

				for (Compte cpt : listCpte) {
					try {
						client.ajouterCompte(cpt);
					} catch (BanqueException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fermer les elements dans l'ordre inverse on les a ouverts
			try {
				if (resultat != null) {
					resultat.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return client;
	}

	public List<Compte> listerComptes(int userId) {

		PreparedStatement ste = null;
		ResultSet resultat = null;
		List<Compte> listeCpt = new ArrayList<Compte>();

		if (this.laConnexion == null) {
			throw new RuntimeException("Connect to db before");
		}

		String requete = null;
		try {
			requete = "select * from compte where utilisateurId = ?";
			ste = this.laConnexion.prepareStatement(requete);
			ste.setInt(1, userId);
			resultat = ste.executeQuery();
			while (resultat.next()) {
				Compte cpte = FactoryCompte.getInstance().creerCompte();
				cpte.setNo(resultat.getInt("id"));
				cpte.setLibelle(resultat.getString("libelle"));
				cpte.setSolde(resultat.getDouble("solde"));
				listeCpt.add(cpte);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fermer les elements dans l'ordre inverse on les a ouverts
			try {
				if (resultat != null) {
					resultat.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listeCpt;
	}

	public Client authentifier(String login, String pwd) {

		if ((login == null) || (pwd == null)) {
			return null;
		}

		PreparedStatement ste = null;
		ResultSet resultat = null;
		Client client = null;

		if (this.laConnexion == null) {
			throw new RuntimeException("Connect to db before");
		}

		String requete = null;
		try {
			requete = "select id from utilisateur where login = ? and password = ?";
			ste = this.laConnexion.prepareStatement(requete);
			ste.setString(1, login);
			ste.setString(2, pwd);
			resultat = ste.executeQuery();
			if (resultat.next()) {
				client = this.recupererClient(resultat.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fermer les elements dans l'ordre inverse on les a ouverts
			try {
				if (resultat != null) {
					resultat.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return client;
	}

	public List<Operation> rechercherOperation(int cptId, Date debut, Date fin, Boolean creditDebit) {
		PreparedStatement ste = null;
		ResultSet resultat = null;
		List<Operation> listeOper = new ArrayList<Operation>();

		if (this.laConnexion == null) {
			throw new RuntimeException("Executer seConnecter() avant");
		}

		String requete = null;
		try {
			requete = "select * from operation where compteId = ? ";
			ste = this.laConnexion.prepareStatement(requete);
			ste.setInt(1, cptId);
			if ((debut != null) || (fin != null)) {
				requete += " and date > ? and date < ? ";

				if (debut == null) {
					debut = new Date();
				}
				ste.setDate(2, new java.sql.Date(debut.getTime()));

				if (fin == null) {
					fin = new Date();
				}
				ste.setDate(3, new java.sql.Date(fin.getTime()));
			}
			if (creditDebit != null) {
				if (creditDebit.booleanValue()) {
					requete += " and montant > 0";
				} else if (!creditDebit.booleanValue()) {
					requete += " and montant < 0";
				}
			}

			resultat = ste.executeQuery();
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String libelle = resultat.getString("libelle");
				Double montant = resultat.getDouble("montant");
				Date dateOpe = new Date (resultat.getDate("date").getTime());
				int compteId = resultat.getInt("compteId");
				Operation ope = new Operation(id, libelle, montant, dateOpe, compteId);
				listeOper.add(ope);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fermer les elements dans l'ordre inverse on les a ouverts
			try {
				if (resultat != null) {
					resultat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listeOper;
	}

	public Compte rechercherCompte(int idCpte) {
		PreparedStatement ste = null;
		ResultSet resultat = null;
		Compte cpte = null;

		if (this.laConnexion == null) {
			throw new RuntimeException("Executer seConnecter() avant");
		}

		String requete = null;
		try {
			requete = "select * from compte where id = ? ";
			ste = this.laConnexion.prepareStatement(requete);
			ste.setInt(1, idCpte);

			resultat = ste.executeQuery();
			while (resultat.next()) {
				cpte = FactoryCompte.getInstance().creerCompte();
				cpte.setNo(resultat.getInt("id"));
				cpte.setLibelle(resultat.getString("libelle"));
				cpte.setSolde(resultat.getDouble("solde"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fermer les elements dans l'ordre inverse on les a ouverts
			try {
				if (resultat != null) {
					resultat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cpte;
	}
	/*
	 * TODO public List<Operation> faireVirement(int cpSrcId, int cpDestId,
	 * double som) { PreparedStatement ste = null; ResultSet resultat = null;
	 * List<Operation> listeCpt = new ArrayList<Operation>();
	 * 
	 * if (this.laConnexion == null) { throw new RuntimeException(
	 * "Connect to db before"); } // TODO continuer
	 * this.laConnexion.setAutoCommit(false); // TODO faire le commit qqpart
	 * 
	 * 
	 * Compte cpteSrc = this.rechercherCompte(cpSrcId); Compte cpteDest =
	 * this.rechercherCompte(cpDestId);
	 * 
	 * if ((cpteSrc == null) || (cpteDest == null)) { throw RuntimeException(
	 * "Compte " + cpSrcId + "n'existe pas"); }
	 * 
	 * String requete = null; try { requete =
	 * "select * from compte where utilisateurId = ?"; ste =
	 * this.laConnexion.prepareStatement(requete); ste.setInt(1, userId);
	 * resultat = ste.executeQuery(); while (resultat.next()) { Compte cpte =
	 * FactoryCompte.getInstance().creerCompte();
	 * cpte.setNo(resultat.getInt("id"));
	 * cpte.setLibelle(resultat.getString("libelle"));
	 * cpte.setSolde(resultat.getDouble("solde")); listeCpt.add(cpte); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { // fermer les
	 * elements dans l'ordre inverse on les a ouverts try { if (resultat !=
	 * null) { resultat.close(); } } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } try { if (ste != null)
	 * { ste.close(); } } catch (SQLException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } } return listeCpt; }
	 */
}