package fr.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

		int noClient = client.getNo();
		List<Compte> listCpt = db.listerComptes(noClient);

		if (listCpt == null) {
			// TODO ajouter log4j et mettre une erreur
			request.setAttribute("error", "Probleme d'acces a la liste de vos comptes");
			ServletHistorique.LOG.debug("listerComptes retourne null idClient = " + noClient);
			listCpt = new ArrayList<Compte>();
		}

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

		this.close(db);

		RequestDispatcher dispatcher = request.getRequestDispatcher(this.pageHistorique);
		dispatcher.forward(request, response);
	}

}
