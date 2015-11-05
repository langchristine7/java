package fr.web;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.banque.Compte;
import fr.banque.ICompteASeuil;
import fr.banque.ICompteRemunere;
import fr.db.Db;

/**
 * Servlet implementation class ServletCompte
 */
@WebServlet(description = "Liste des comptes", urlPatterns = { "/ServletListeComptes" })
public class ServletListeComptes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Db db = null;
	private Properties mesProperties = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListeComptes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {

		this.mesProperties = new Properties();
		File file = new File("C:/Users/chris/git/java/Exo11/src/mesPreferences.properties");
		if (file.exists() && file.canRead()) {

			try (FileReader fr = new FileReader(file)) { // a partir de java 7,
				// traite le finally
				// tout seul
				this.mesProperties.load(fr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Fichier '" + file + "' pas trouve");
		}

		// Nom du driver pour acceder a la base de donnees
		// lire la doc associee a sa base de donnees pour le connaitre
		final String dbDriver = this.mesProperties.getProperty("db.driver");
		final String dbUrl = this.mesProperties.getProperty("db.url");
		final String dbLogin = this.mesProperties.getProperty("db.login");
		final String dbPwd = this.mesProperties.getProperty("db.password");

		this.db = null;
		try {
			// db = new Db();
			this.db = new Db(dbDriver, dbUrl, dbLogin, dbPwd);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			System.exit(-1);
		}
		try {
			this.db.seConnecter();

		} catch (SQLException e) {
			System.out.println("Probleme de connexion : ");
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		if (this.db != null) {
			this.db.seDeconnecter();
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int noClient;
			try {
				noClient = Integer.parseInt(request.getParameter("no"));
			} catch (RuntimeException e) {
				request.setAttribute("erreur", "Le numero de client n'est pas un entier ");
				noClient = 0;
			}

			List<Compte> listCpt = this.db.listerComptes(noClient);

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
			request.setAttribute("nomClient", this.db.recupererClient(noClient).getNom());

			RequestDispatcher dispatcher = request.getRequestDispatcher("ListeComptes.jsp");
			dispatcher.forward(request, response);

		} catch (RuntimeException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
