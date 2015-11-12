package fr.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.banque.BanqueException;
import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.CompteASeuil;
import fr.banque.CompteASeuilRemunere;
import fr.banque.CompteRemunere;
import fr.banque.FactoryCompte;
import fr.banque.Operation;

public class Db {

	private DataSource dataSource = null;

	// private String nomDuDriver = "com.mysql.jdbc.Driver";
	// private String login = "root";
	// private String password = "root";
	// private String url = "jdbc:mysql://localhost:3306/banque";
	private Connection laConnexion = null;
	private final static Logger LOG = LogManager.getLogger(Db.class);
	private String theJndiName = "jdbc/NetFullBankPool";

	public Db() throws SQLException, NamingException {
		javax.naming.Context context = new InitialContext();
		this.dataSource = (DataSource) context.lookup("java:comp/env/" + theJndiName);
	}

	public Connection getCxn() {
		if (laConnexion == null) {
			LOG.debug("getCxn : n'est pas connecte");
			return null;
		}

		return this.laConnexion;
	}

	public void seConnecter() throws SQLException {
		if (this.laConnexion == null) {
			this.laConnexion = dataSource.getConnection();
		}
	}

	public void seDeconnecter() {
		if (this.laConnexion == null) {
			LOG.debug("seDeconnecter : n'etait pas connecte");
			return;
		}
		try {
			this.laConnexion.close();
		} catch (SQLException e) {
			LOG.debug("seDeconnecter : pb pour fermer la connexion db" + e.getMessage());
			e.printStackTrace();
		}
	}

	public Client recupererClient(int id) {

		PreparedStatement ste = null;
		ResultSet resultat = null;
		Client client = null;

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
				client = new Client();
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
				LOG.debug("recuperer client : pb sql : " + e.getMessage());
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				LOG.debug("recuperer client : pb sql : " + e.getMessage());
			}

		}
		return client;
	}

	public List<Client> listerClients() {

		PreparedStatement ste = null;
		ResultSet resultat = null;
		List<Client> listClt = new ArrayList<Client>();

		if (this.laConnexion == null) {
			throw new RuntimeException("Connect to db before");
		}

		String requete = null;
		try {
			requete = "select * from utilisateur ";
			ste = this.laConnexion.prepareStatement(requete);
			resultat = ste.executeQuery();
			while (resultat.next()) {
				Client client = new Client();
				int no = resultat.getInt("id");
				client.setNo(no);
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
				listCpte = this.listerComptes(no);

				for (Compte cpt : listCpte) {
					try {
						client.ajouterCompte(cpt);
					} catch (BanqueException e) {
						e.printStackTrace();
					}
				}
				listClt.add(client);
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
				LOG.debug("listerClients : pb sql : " + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				LOG.debug("listerClients : pb sql : " + e.getMessage());
				e.printStackTrace();
			}

		}
		return listClt;
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
				Compte cpte = this.remplitCompte(resultat);
				listeCpt.add(cpte);
			}

		} catch (SQLException e) {
			LOG.debug("listerComptes : pb sql : " + e.getMessage());
		} finally {
			// fermer les elements dans l'ordre inverse on les a ouverts
			try {
				if (resultat != null) {
					resultat.close();
				}
			} catch (SQLException e) {
				LOG.debug("listerComptes : pb sql : " + e.getMessage());
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				LOG.debug("listerComptes : pb sql : " + e.getMessage());
			}
		}
		return listeCpt;
	}

	private Compte remplitCompte(ResultSet resultat) throws SQLException {
		Compte cpte = null;

		double seuil = resultat.getDouble("decouvert");
		boolean seuilNull = resultat.wasNull();
		double taux = resultat.getDouble("taux");
		boolean tauxNull = resultat.wasNull();

		if (seuilNull && tauxNull) {
			cpte = FactoryCompte.getInstance().creerCompte();
		} else if (seuilNull && !tauxNull) {
			cpte = FactoryCompte.getInstance().creerCompteRemunere();
			((CompteRemunere) cpte).setTaux(taux);
		} else if (!seuilNull && tauxNull) {
			cpte = FactoryCompte.getInstance().creerCompteASeuil();
			((CompteASeuil) cpte).setSeuil(seuil);
		} else {
			cpte = FactoryCompte.getInstance().creerCompteASeuilRemunere();
			((CompteASeuilRemunere) cpte).setSeuil(seuil);
			((CompteASeuilRemunere) cpte).setTaux(taux);
		}

		cpte.setNo(resultat.getInt("id"));
		cpte.setLibelle(resultat.getString("libelle"));
		cpte.setSolde(resultat.getDouble("solde"));
		return cpte;
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
				LOG.debug("authentifier : pb sql : " + e.getMessage());
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				LOG.debug("authentifier : pb sql : " + e.getMessage());
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

			if (debut != null) {
				requete += " and date > ";
				requete += new java.sql.Date(debut.getTime());
			}

			if (fin != null) {
				requete += " and date < ";
				requete += new java.sql.Date(fin.getTime());
			}

			if (creditDebit != null) {
				if (creditDebit.booleanValue()) {
					requete += " and montant > 0 ";
				} else if (!creditDebit.booleanValue()) {
					requete += " and montant < 0 ";
				}
			}

			resultat = ste.executeQuery();
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String libelle = resultat.getString("libelle");
				Double montant = resultat.getDouble("montant");
				Date dateOpe = new Date(resultat.getDate("date").getTime());
				int compteId = resultat.getInt("compteId");
				Operation ope = new Operation(id, libelle, montant, dateOpe, compteId);
				listeOper.add(ope);
			}

		} catch (SQLException e) {
			LOG.debug("rechercherOperation : pb sql : " + e.getMessage());
		} finally {
			// fermer les elements dans l'ordre inverse on les a ouverts
			try {
				if (resultat != null) {
					resultat.close();
				}
			} catch (SQLException e) {
				LOG.debug("rechercherOperation : pb sql : " + e.getMessage());
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				LOG.debug("rechercherOperation : pb sql : " + e.getMessage());
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
				cpte = this.remplitCompte(resultat);
			}
		} catch (SQLException e) {
			LOG.debug("rechercherCompte : pb sql : " + e.getMessage());
		} finally {
			// fermer les elements dans l'ordre inverse on les a ouverts
			try {
				if (resultat != null) {
					resultat.close();
				}
			} catch (SQLException e) {
				LOG.debug("pb sql : " + e.getMessage());
			}
			try {
				if (ste != null) {
					ste.close();
				}
			} catch (SQLException e) {
				LOG.debug("pb sql : " + e.getMessage());
			}
		}
		return cpte;
	}

	// TODO

	public void faireVirement(Integer cptSrc, Integer cptDest, Double unMontant) throws SQLException {

		if (this.laConnexion == null) {
			throw new SQLException("Connect to db before");
		}
		this.laConnexion.setAutoCommit(false);

		Statement request = null;
		try {
			// Recuperation de tous les clients
			request = this.laConnexion.createStatement();
			request.executeUpdate("update compte set solde=(solde-" + unMontant + ") where id=" + cptSrc);
			request.close();
			request = this.laConnexion.createStatement();
			request.executeUpdate("update compte set solde=(solde+" + unMontant + ") where id=" + cptDest);
			request.close();
			request = this.laConnexion.createStatement();
			request.executeUpdate("insert into operation (libelle, montant, date, compteId) values ('Virement',"
					+ (-unMontant) + ",NOW()," + cptSrc + ")");
			request.close();
			request = this.laConnexion.createStatement();
			request.executeUpdate("insert into operation (libelle, montant, date, compteId) values ('Virement',"
					+ unMontant + ",NOW()," + cptDest + ")");
			this.laConnexion.commit();

		} catch (SQLException sql) {
			this.laConnexion.rollback();
			throw sql;
		} finally {
			if (request != null) {
				try {
					request.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		this.laConnexion.setAutoCommit(true);
	}

}