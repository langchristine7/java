package fr.web;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
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

import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.Operation;
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

			String login = "dj";
			String pwd = "dj";

			System.out.println("test authentification ");
			Client clt1 = this.db.authentifier(login, pwd);
			if (clt1 == null) {
				System.out.println("User non authentifie");
			} else {
				System.out.println("nom user : " + clt1.getNom());
				System.out.println("age user : " + clt1.getAge());
			}

			// recherche du client id = 1
			System.out.println("\nrecupererClient --- Recherche du client no 1");

			Client client = this.db.recupererClient(1);
			System.out.println(client);

			List<Compte> listCpt = this.db.listerComptes(2);
			System.out.println(listCpt);

			List<Operation> listeOpe = this.db.rechercherOperation(12, null, null, null);
			System.out.println("Liste des operations");
			System.out.println(listeOpe);

			Compte cpteRech = this.db.rechercherCompte(15);
			System.out.println("compte no 15 : ");
			System.out.println(cpteRech);

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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int noClient;
			try {
				noClient = Integer.parseInt(request.getParameter("no"));
			} catch (RuntimeException e) {
				request.setAttribute("erreur", "Le numero de client n'est pas un entier ");
				noClient = 0;
			}

			List<Compte> listCpt = this.db.listerComptes(noClient);

			request.setAttribute("noClient", noClient);
			request.setAttribute("listeComptes", listCpt);

			RequestDispatcher dispatcher = request.getRequestDispatcher("ListeComptes.jsp");
			dispatcher.forward(request, response);

		} catch (RuntimeException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
