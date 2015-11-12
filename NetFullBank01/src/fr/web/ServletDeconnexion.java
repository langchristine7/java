package fr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.web.connexion.WebConnexion;

/**
 * Servlet implementation class deconnexion
 */
@WebServlet("/deconnexion")
public class ServletDeconnexion extends Connect {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger();

	public ServletDeconnexion() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletDeconnexion.LOG.debug("Deconnexion client");
		HttpSession session = request.getSession();
		WebConnexion.removeConnexion(session);
		Connect.retourneAuLogin(request, response);
	}



}
