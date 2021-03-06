package fr.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.banque.Client;
import fr.db.Db;
import fr.web.connexion.WebConnexion;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/login")
public class ServletLogin extends Connect {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger(ServletLogin.class);
	private String login;
	private String password;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connect.retourneAuLogin(request, response);
	}

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
			Connect.retourneAuLogin(request, response);
			return;
		}
		if (this.password.isEmpty()) {
			request.setAttribute("error", "Renseignez le mot de passe");
			Connect.retourneAuLogin(request, response);
			return;
		}

		// login et password sont renseignes

		Db db = this.getConnexion(request, response);

		if (db == null) {
			return;
		}

		// test si le user/mot de passe  est correct est correct

		Client clt;
		try {
			clt = db.authentifier(this.login, this.password);

		} catch (Exception e) {
			request.setAttribute("error", "Probl�me de connexion, merci de contacter l'administrateur");
			ServletLogin.LOG.debug("Pb authentifier " + e.getMessage());
			Connect.retourneAuLogin(request, response);
			return;
		}
		if (clt == null) {
			request.setAttribute("error", "Le nom d'utilisateur ou le mot de passe ne sont pas corrects");
			ServletLogin.LOG.info("user/mdp incorrect");
			Connect.retourneAuLogin(request, response);
			return;
		}

		this.close(db);

		// connexion is ok

		HttpSession session = request.getSession(true);
		Client ancienClient = (Client) session.getAttribute("client");
		if (ancienClient != null) {
			WebConnexion.removeConnexion(session);
		}

		request.getSession(true).setAttribute("client", clt);
		request.setAttribute("password", null);
		WebConnexion.addConnexion(session);
		RequestDispatcher dispatcher = request.getRequestDispatcher(this.pageMenu);
		dispatcher.forward(request, response);
	}

}
