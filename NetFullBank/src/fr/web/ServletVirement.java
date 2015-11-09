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
 * Servlet implementation class ServletVirement
 */
@WebServlet("/virement")
public class ServletVirement extends Connect {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger(ServletVirement.class);

	public ServletVirement() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Db db = null;

		Client client = (Client) request.getSession(true).getAttribute("client");

		if (client == null) {
			request.setAttribute("error", "Merci de vous connecter");
			Connect.retourneAuLogin(request, response);
			return;
		}

		int noClient = client.getNo();
		request.setAttribute("noClient", noClient);
		List<BeanCompte> lstBeanCompte = null;

		try {

			db = this.getConnexion(request, response);

			if (db == null) {
				return;
			}

			List<Compte> listCpt = db.listerComptes(noClient);

			if (listCpt == null) {
				request.setAttribute("error", "Probleme d'acces a la liste de vos comptes");
				ServletVirement.LOG.debug("listerComptes retourne null idClient = " + noClient);
				listCpt = new ArrayList<Compte>();
			}

			lstBeanCompte = new ArrayList<BeanCompte>();
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

		} catch (RuntimeException e) {
			request.setAttribute("error", "Probleme d'acces a la liste de vos comptes");
			ServletVirement.LOG.debug("listerComptes retourne null idClient = " + noClient);

		} finally {
			this.close(db);
		}

		request.setAttribute("lstBeanCompte", lstBeanCompte);

		RequestDispatcher dispatcher = request.getRequestDispatcher(this.pageVirement);
		dispatcher.forward(request, response);
	}

}
