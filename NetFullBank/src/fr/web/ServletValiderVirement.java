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

@WebServlet("/validerVirement")
public class ServletValiderVirement extends Connect {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger(ServletValiderVirement.class);

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		int noCompteSource = Integer.parseInt(request.getParameter("inCmptEme"));
		int noCompteDestination = Integer.parseInt(request.getParameter("inCmptDes"));

		if (noCompteSource == 0) {
			request.setAttribute("messagePasOk", "Choisir un compte source");
			RequestDispatcher dispatcher = request.getRequestDispatcher(this.pageVirement);
			dispatcher.forward(request, response);
		}

		if (noCompteDestination == 0) {
			request.setAttribute("messagePasOk", "Choisir un compte destination");
			RequestDispatcher dispatcher = request.getRequestDispatcher(this.pageVirement);
			dispatcher.forward(request, response);
		}

		double montant = Double.parseDouble(request.getParameter("inMontant"));

		if (montant == 0d) {
			request.setAttribute("messagePasOk", "Indiquer le montant du virement");
			RequestDispatcher dispatcher = request.getRequestDispatcher(this.pageVirement);
			dispatcher.forward(request, response);
		}

		try {

			db = this.getConnexion(request, response);

			if (db == null) {
				return;
			}

			List<Compte> listCpt = db.listerComptes(noClient);

			if (listCpt == null) {
				request.setAttribute("error", "Probleme d'acces a la liste de vos comptes");
				ServletValiderVirement.LOG.debug("listerComptes retourne null idClient = " + noClient);
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

			// TODO faire le virement
			// TODO mettre la liste des comptes en session


			request.setAttribute("messageOk", "Le virement a été réalisé");

		} catch (Exception e) {
			request.setAttribute("messagePasOk", "Problème lors du virement, virement non realisé");
			request.setAttribute("error", "Probleme d'acces a la liste de vos comptes");
			ServletValiderVirement.LOG.debug("listerComptes retourne null idClient = " + noClient);

		} finally {
			this.close(db);
		}

		request.setAttribute("lstBeanCompte", lstBeanCompte);

		RequestDispatcher dispatcher = request.getRequestDispatcher(this.pageVirement);
		dispatcher.forward(request, response);
	}

}
