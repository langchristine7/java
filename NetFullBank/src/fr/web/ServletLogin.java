package fr.web;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
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
import fr.db.Db;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger(ServletLogin.class);
	private String propertiesFileName = "mesPreferences.properties";
	private String login;
	private String password;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLogin() {
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.retourneAuLogin(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.login = request.getParameter("inLogin");
		this.password = request.getParameter("inPass");
		// on remet le login et le password dans la request en cas d'erreur pour
		// retourner a la page login

		request.setAttribute("login", this.login);
		request.setAttribute("password", this.password);

		if (this.login.isEmpty()) {
			request.setAttribute("error", "Renseignez le login");
			this.retourneAuLogin(request, response);
			return;
		}
		if (this.password.isEmpty()) {
			request.setAttribute("error", "Renseignez le mot de passe");
			this.retourneAuLogin(request, response);
			return;
		}

		// login et password sont renseignes

		Properties mesProperties = new Properties();
		// chemin a partir du src
		try (InputStream is = ServletLogin.class.getClassLoader().getResourceAsStream("mesPreferences.properties")) {
			mesProperties.load(is);
		} catch (IOException e) {
			request.setAttribute("error", "Problème de connexion, merci de contacter l'administrateur.");
			ServletLogin.LOG.error("Impossible de lire le fichier properties : "+ this.propertiesFileName + " : " + e.getMessage());
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

		// test si le user/mot de passe  est correct est correct
		//TODO
		Client clt = null;
		try {
			clt = db.authentifier(this.login, this.password);

		} catch (Exception e) {
			request.setAttribute("error", "Problème de connexion, merci de contacter l'administrateur");
			ServletLogin.LOG.debug("Pb authentifier " + e.getMessage());
			this.retourneAuLogin(request, response);
			return;
		}
		if (clt == null) {
			request.setAttribute("error", "Le nom d'utilisateur ou le mot de passe ne sont pas corrects");
			ServletLogin.LOG.info("user/mdp incorrect");
			this.retourneAuLogin(request, response);
			return;
		}

		db.seDeconnecter();

		// connexion is ok
		// request.getSession(true).setAttribute("db", db);
		request.getSession(true).setAttribute("client", clt);
		request.setAttribute("password", null);
		RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
		dispatcher.forward(request, response);


	}

	private void retourneAuLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
		return;
	}
}
