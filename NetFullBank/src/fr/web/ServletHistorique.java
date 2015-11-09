package fr.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Db db = null;
		List<Operation> lstOperation = null;
		int noCompte = 0;

		try {
			Client client = (Client) request.getSession(true).getAttribute("client");

			if (client == null) {
				request.setAttribute("error", "Merci de vous connecter");
				this.retourneAuLogin(request, response);
				return;
			}

			db = this.getConnexion(request, response);

			if (db == null) {
				return;
			}

			noCompte = Integer.parseInt(request.getParameter("noCompte"));
			request.setAttribute("noCompte", noCompte);

			String inDateDebut = request.getParameter("inDateDebut");
			Date dateDebutDate = null;
			String inDateFin = request.getParameter("inDateFin");
			Date dateFinDate = null;

			if ((inDateDebut != null) && !inDateDebut.equals("")) {
				Calendar dateDebutCal = Calendar.getInstance();
				dateDebutCal.set(Integer.valueOf(inDateDebut.split("/")[2]), Integer.valueOf(inDateDebut.split("/")[1]) - 1,
						Integer.valueOf(inDateDebut.split("/")[0]));
				dateDebutDate = new Date(dateDebutCal.getTimeInMillis());
			}

			if ((inDateFin != null) && !inDateFin.equals("")) {
				Calendar dateFinCal = Calendar.getInstance();
				dateFinCal.set(Integer.valueOf(inDateFin.split("/")[2]), Integer.valueOf(inDateFin.split("/")[1]) - 1,
						Integer.valueOf(inDateFin.split("/")[0]));
				dateFinDate = new Date(dateFinCal.getTimeInMillis());
			}

			lstOperation = db.rechercherOperation(noCompte, dateDebutDate, dateFinDate, null);

			if (lstOperation == null) {
				ServletHistorique.LOG.debug("historique : liste operations retourne null");
				request.setAttribute("error", "Probleme d'acces a la liste des operations");
				ServletHistorique.LOG.debug("historique retourne null noCompte = " + noCompte);
				request.setAttribute("noCompte", noCompte);

				lstOperation = new ArrayList<Operation>();
			}

			request.setAttribute("lstOperation", lstOperation);
			ServletHistorique.LOG.debug("noCompte = ", noCompte);
			request.setAttribute("noCompte", noCompte);

		} catch (Exception e) {
			ServletHistorique.LOG.debug("historique compte no " + noCompte + "execption :  " + e.getMessage());
			request.setAttribute("error", "Probleme d'acces a la liste des operations");
			lstOperation = new ArrayList<Operation>();
		}
		finally {
			this.close(db);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(this.pageHistorique);
		dispatcher.forward(request, response);
	}
}
