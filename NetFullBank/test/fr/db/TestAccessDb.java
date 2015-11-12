package fr.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.Operation;

public class TestAccessDb {

	private Db db = null;

	/*
	 * @Test public void testerBonjour() { System.out.println("Ok");
	 * Assert.assertEquals("oui est bien = a oui", "oui", "oui");
	 * System.out.println("Ben non"); }
	 *
	 * @Test public void testerBonjour2() { System.out.println("Ok");
	 * Assert.assertEquals("oui est bien = a oui", "oui", "oui"); Object o =
	 * null; Assert.assertNull("je dois etre null", o); }
	 *
	 * @Test public void testerBonjour3() { System.out.println("Ok");
	 * Assert.assertEquals("oui est bien = a oui", "oui", "oui");
	 *
	 * }
	 *
	 */
	@Before
	public void init() {
		try {
			this.db = new Db();
			this.db.seConnecter();
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}

	}

	@After
	public void after() {
		if (this.db != null) {
			this.db.seDeconnecter();
		}
	}

	/**
	 * on s'authentifie correctement
	 */
	@Test
	public void testerAuthentifier1() {
		Client clt1 = null;
		try {
			clt1 = this.db.authentifier("df", "df");

		} catch (Exception e) {
			Assert.fail("Pas normal (" + e.getMessage() + ")");

		}
		Assert.assertNotNull("Le client ne doit pas etre null", clt1);
	}

	/**
	 * on s'authentifie mal
	 */
	@Test
	public void testerAuthentifierRate() {
		Client clt1 = null;
		try {
			clt1 = this.db.authentifier("toto", "toto");

		} catch (Exception e) {
			Assert.fail("Pas normal (" + e.getMessage() + ")");

		}
		Assert.assertNull("Le client est null", clt1);
	}

	/**
	 * test recupererIdClient existe
	 */
	@Test
	public void testerRecupererClient1() {
		Client client = this.db.recupererClient(1);
		Assert.assertTrue("client 1 existe", client.getNo() == 1);
	}

	/**
	 * test recupererIdClient existe pas
	 */
	@Test
	public void testerRecupererClientExistePas() {
		Client client = this.db.recupererClient(10);
		Assert.assertNull("client 10 ne doit pas exister", client);
	}

	/**
	 * test listeCompte > 0
	 */
	@Test
	public void testerListeCompte1() {
		List<Compte> listCpt = this.db.listerComptes(2);
		Assert.assertTrue("le client 2 a plusieurs comptes", listCpt.size() > 0);
	}

	/**
	 * test listeCompte est vide
	 */
	@Test
	public void testerListeCompteNull() {
		List<Compte> listCpt = this.db.listerComptes(45);
		Assert.assertTrue("liste compte est vide", listCpt.size() == 0);
	}

	/**
	 * test rechercherOperation > 0
	 */
	@Test
	public void testerRechercherOperation1() {
		List<Operation> listOpe = this.db.rechercherOperation(12, null, null, null);
		Assert.assertTrue("le client 12 a plusieurs operations", listOpe.size() > 0);
	}

	/**
	 * test rechercherOperation est null
	 */
	@Test
	public void testerRechercherOperationNulle() {
		List<Operation> listOpe = this.db.rechercherOperation(45, null, null, null);
		Assert.assertTrue("rechercherOperation est vide", listOpe.size() == 0);
	}

	/**
	 * test rechercherOperation > 0
	 */
	@Test
	public void testerRechercherCompte1() {
		int noCpt = 15;
		Compte cpteRech = this.db.rechercherCompte(noCpt);
		Assert.assertTrue("le compte " + noCpt + " a ete trouve", cpteRech.getNo() == noCpt);
	}

	/**
	 * test rechercherCompte ne trouve pas le compte
	 */
	@Test
	public void testerRechercherCompteExistePas() {
		int noCpt = 45;
		Compte cpteRech = this.db.rechercherCompte(noCpt);
		Assert.assertNull("rechercherOperation est vide", cpteRech);
	}

	/**
	 * test faireVirement
	 */
	@Test
	public void testerFaireVirement() {
		Connection c = null;
		// try {
		c = this.db.getCxn();
		int cptSrc = 13;
		int cptDest = 12;
		double montant = 100d;
		// Compte compteSrc = this.db.rechercherCompte(cptSrc);
		double sldSrc = this.db.rechercherCompte(cptSrc).getSolde();
		double sldDest = this.db.rechercherCompte(cptDest).getSolde();
		try {
			this.db.faireVirement(cptSrc, cptDest, montant);
		} catch (SQLException e) {
			Assert.fail("Pas normal (" + e.getMessage() + ")");
			e.printStackTrace();
		}
		List<Operation> lstOpe = this.db.rechercherOperation(cptSrc, null, null, false);

		// resultat
		Assert.assertTrue("Les Montants doivent etre ok ",
				this.db.rechercherCompte(cptSrc).getSolde() == (sldSrc - montant));
		Assert.assertTrue("Les Montants doivent etre ok ",
				this.db.rechercherCompte(cptDest).getSolde() == (sldDest + montant));

		Assert.assertTrue("les operations doit etre enregistrees", lstOpe.size() > 0);
		try {
			this.db.faireVirement(cptDest, cptSrc, montant);
		} catch (SQLException e) {
			Assert.fail("Pas normal (" + e.getMessage() + ")");
			e.printStackTrace();
		}
	}
}