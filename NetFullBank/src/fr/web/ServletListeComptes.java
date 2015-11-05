package fr.web;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.ICompteASeuil;
import fr.banque.ICompteRemunere;
import fr.db.Db;

/**
 * Servlet implementation class ServletCompte
 */
@WebServlet(description = "Liste des comptes", urlPatterns = { "/listeComptes" })
public class ServletListeComptes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger(ServletListeComptes.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListeComptes() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Client client = (Client) request.getSession(true).getAttribute("client");

		if (client == null) {
			request.setAttribute("error", "Merci de vous connecter");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		Properties mesProperties = new Properties();
		// chemin a partir du src
		try (InputStream is = ServletLogin.class.getClassLoader().getResourceAsStream("mesPreferences.properties")) {
			mesProperties.load(is);
		} catch (IOException e) {
			request.setAttribute("error", "Problème de connexion, merci de contacter l'administrateur.");
			ServletLogin.LOG.error(
					"Impossible de lire le fichier properties : " + this.propertiesFileName + " : " + e.getMessage());
			this.retourneAuLogin(request, response);
			return;
		}

		// Nom du driver pour acceder a la base de donnees
		// lire la doc associee a sa base de donnees pour le connaitre
		String dbDriver = mesProperties.getProperty("db.driver");
		String dbUrl = mesProperties.getProperty("db.url");
		String dbLogin = mesProperties.getProperty("db.login");
		String dbPwd = mesProperties.getProperty("db.password");

		Db db = null;
		try {
			db = new Db(dbDriver, dbUrl, dbLogin, dbPwd);
		} catch (ClassNotFoundException e) {
			request.setAttribute("error", "Problème de connexion, merci de contacter l'administrateur.");
			ServletLogin.LOG.error("Impossible de creer objet DB : " + e.getMessage());
			this.retourneAuLogin(request, response);
			return;
		}

		try {
			db.seConnecter();

		} catch (SQLException e) {
			request.setAttribute("error", "Problème de connexion, merci de contacter l'administrateur.");
			ServletLogin.LOG.error("erreur seConnecter() : " + e.getMessage());
			this.retourneAuLogin(request, response);
			return;
		} catch (RuntimeException e) {
			request.setAttribute("error", "Problème de connexion, merci de contacter l'administrateur.");
			ServletLogin.LOG.error("erreur seConnecter() : " + e.getMessage());
			this.retourneAuLogin(request, response);
			return;
		}

		int noClient = client.getNo();
		List<Compte> listCpt = db.listerComptes(noClient);

		if (listCpt == null) {
			//TODO ajouter log4j et mettre une erreur
			request.setAttribute("error", "Probleme d'acces a la liste de vos comptes");
			ServletListeComptes.LOG.debug("listerComptes retourne null idClient = " + noClient);
			listCpt = new ArrayList<Compte>();
		}

		db.seDeconnecter();

		List<BeanCompte> lstBeanCompte = new ArrayList<BeanCompte>();
		for (Compte c : listCpt) {
			BeanCompte beanC = new BeanCompte();
			beanC.setNo(c.getNo());
			beanC.setLibelle(c.getLibelle());
			beanC.setSolde(c.getSolde());
			if (c instanceof ICompteASeuil) {
				beanC.setSeuil(((ICompteASeuil) c).getSeuil());
				beanC.setHasSeuil(true);
			}
			if (c instanceof ICompteRemunere) {
				beanC.setTaux(((ICompteRemunere) c).getTaux());
				beanC.setHasTaux(true);
			}
			lstBeanCompte.add(beanC);
		}


		request.setAttribute("noClient", noClient);
		request.setAttribute("lstBeanCompte", lstBeanCompte);
		request.setAttribute("nomClient", db.recupererClient(noClient).getNom());

		RequestDispatcher dispatcher = request.getRequestDispatcher("/comptes/listeComptes.jsp");
		dispatcher.forward(request, response);
	}

	private void retourneAuLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
