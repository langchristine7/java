package fr.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.db.Db;

public abstract class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected static String pageLogin = "/login.jsp";
	protected String pageMenu = "/menu.jsp";
	protected String pageHistorique = "/comptes/historique.jsp";
	protected String pageVirement = "/comptes/virement.jsp";
	protected String pageListeComptes = "/comptes/listeComptes.jsp";

	// private String propertiesFileName = "mesPreferences.properties";
	private final static Logger LOG = LogManager.getLogger(Connect.class);

	public static void retourneAuLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(Connect.pageLogin);
		dispatcher.forward(request, response);
		return;
	}

	protected Db getConnexion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Db db = null;

		/*
		 * Properties mesProperties = new Properties();
		 * 
		 * try (InputStream is =
		 * ServletLogin.class.getClassLoader().getResourceAsStream(
		 * "mesPreferences.properties")) { mesProperties.load(is);
		 * 
		 * } catch (IOException e) { request.setAttribute("error",
		 * "Probleme de connexion, merci de contacter l'administrateur.");
		 * Connect.LOG.error( "Impossible de lire le fichier properties : " +
		 * this.propertiesFileName + " : " + e.getMessage());
		 * Connect.retourneAuLogin(request, response); return null; }
		 * 
		 * 
		 * String dbDriver = mesProperties.getProperty("db.driver"); String
		 * dbUrl = mesProperties.getProperty("db.url"); String dbLogin =
		 * mesProperties.getProperty("db.login"); String dbPwd =
		 * mesProperties.getProperty("db.password");
		 */
		try {
			db = new Db();

		} catch (SQLException | NamingException e) {
			request.setAttribute("error", "Probleme de connexion, merci de contacter l'administrateur.");
			Connect.LOG.error("Impossible de creer objet DB : " + e.getMessage());
			Connect.retourneAuLogin(request, response);
			return null;
		}

		try {
			db.seConnecter();

		} catch (SQLException e) {
			request.setAttribute("error", "Probleme de connexion, merci de contacter l'administrateur.");
			Connect.LOG.error("erreur seConnecter() : " + e.getMessage());
			Connect.retourneAuLogin(request, response);
			return null;

		} catch (RuntimeException e) {
			request.setAttribute("error", "Probleme de connexion, merci de contacter l'administrateur.");
			Connect.LOG.error("erreur seConnecter() : " + e.getMessage());
			Connect.retourneAuLogin(request, response);
			return null;
		}

		return db;
	}

	protected void close(Db db) {
		if (db != null) {
			db.seDeconnecter();
			LOG.debug("Deconnexion bdd");
		}
	}
}
