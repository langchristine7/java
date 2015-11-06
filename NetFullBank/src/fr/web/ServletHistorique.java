package fr.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.banque.Client;
import fr.banque.Operation;
import fr.db.Db;

/**
 * Servlet implementation class ServletHistorique
 */
@WebServlet("/historique")
public class ServletHistorique extends Connect {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger(ServletHistorique.class);
	private String pageHistorique = "/comptes/historique.jsp";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Client client = (Client) request.getSession(true).getAttribute("client");

		if (client == null) {
			request.setAttribute("error", "Merci de vous connecter");
			this.retourneAuLogin(request, response);
			return;
		}

		Db db = this.getConnexion(request, response);

		if (db == null) {
			return;
		}

		int noCompte = Integer.valueOf(request.getParameter("noCompte"));
		String inDateDebut = request.getParameter("inDateDebut");

		Calendar dateDebut = Calendar.getInstance();
		dateDebut.
		List<Operation> lstOperation = db.rechercherOperation(noCompte, null, null, true);

		if (lstOperation == null) {
			ServletHistorique.LOG.debug("historique : liste operations retourne null");
			request.setAttribute("error", "Probleme d'acces a la liste de vos comptes");
			ServletHistorique.LOG.debug("historique retourne null noCompte = " + noCompte);
			lstOperation = new ArrayList<Operation>();
		}

		request.setAttribute("lstOperation", lstOperation);
		ServletHistorique.LOG.debug("noCompte = ", noCompte);
		request.setAttribute("noCompte", noCompte);

		this.close(db);

		RequestDispatcher dispatcher = request.getRequestDispatcher(this.pageHistorique);
		dispatcher.forward(request, response);
	}
}
