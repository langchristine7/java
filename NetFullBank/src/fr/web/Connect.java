package fr.web;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.db.Db;

public abstract class Connect extends HttpServlet {
	private String pageLogin = "login.jsp";
	private static final long serialVersionUID = 1L;
	private String propertiesFileName = "mesPreferences.properties";
	private final static Logger LOG = LogManager.getLogger(Connect.class);

	protected void retourneAuLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(this.pageLogin);
		dispatcher.forward(request, response);
		return;
	}

	protected Db getConnexion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Db db = null;
		Properties mesProperties = new Properties();

		try (InputStream is = ServletLogin.class.getClassLoader().getResourceAsStream("mesPreferences.properties")) {
			mesProperties.load(is);

		} catch (IOException e) {
			request.setAttribute("error", "Problème de connexion, merci de contacter l'administrateur.");
			Connect.LOG.error(
					"Impossible de lire le fichier properties : " + this.propertiesFileName + " : " + e.getMessage());
			this.retourneAuLogin(request, response);
			return null;
		}

		String dbDriver = mesProperties.getProperty("db.driver");
		String dbUrl = mesProperties.getProperty("db.url");
		String dbLogin = mesProperties.getProperty("db.login");
		String dbPwd = mesProperties.getProperty("db.password");

		try {
			db = new Db(dbDriver, dbUrl, dbLogin, dbPwd);
		} catch (ClassNotFoundException e) {
			request.setAttribute("error", "Problème de connexion, merci de contacter l'administrateur.");
			Connect.LOG.error("Impossible de creer objet DB : " + e.getMessage());
			this.retourneAuLogin(request, response);
			return null;
		}

		try {
			db.seConnecter();

		} catch (SQLException e) {
			request.setAttribute("error", "Problème de connexion, merci de contacter l'administrateur.");
			Connect.LOG.error("erreur seConnecter() : " + e.getMessage());
			this.retourneAuLogin(request, response);
			return null;

		} catch (RuntimeException e) {
			request.setAttribute("error", "Problème de connexion, merci de contacter l'administrateur.");
			Connect.LOG.error("erreur seConnecter() : " + e.getMessage());
			this.retourneAuLogin(request, response);
			return null;
		}

		return db;
	}

	protected void close(Db db) {
		if (db != null) {
			db.seDeconnecter();
		}
	}
}
