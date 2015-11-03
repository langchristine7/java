package fr.web;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.ICompteASeuil;
import fr.banque.ICompteRemunere;
import fr.banque.Operation;
import fr.db.Db;

/**
 * Servlet implementation class ServletCompte
 */
@WebServlet(description = "Liste des comptes", urlPatterns = { "/ServletCompte" })
public class ServletCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Db db = null;
	private Properties mesProperties = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCompte() {
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
			PrintWriter out = response.getWriter();
			int noClient;
			try {
				noClient = Integer.parseInt(request.getParameter("no"));
			} catch (RuntimeException e) {
				out.print("Le client no " + request.getParameter("no") + " n'existe pas");
				return;
			}

			List<Compte> listCpt = this.db.listerComptes(noClient);

			StringBuffer buff = new StringBuffer();
			buff.append("<html>\n");
			buff.append("<head>\n");
			buff.append("<title>\n");
			buff.append("   Banque Poec\n");
			buff.append("</title>\n");
			buff.append("</head>\n");
			buff.append("<body>\n");
			buff.append("	<h1>\n");
			buff.append("		Liste des comptes du client " + noClient + "\n");
			buff.append("	</h1>\n");

			buff.append("	<table> \n");
			buff.append("	<thead> \n");
			buff.append("	<tr> \n");
			buff.append("	<td> No </td>\n");
			buff.append("	<td> Libelle </td>\n");
			buff.append("	<td> Solde </td>\n");
			buff.append("	<td> Taux </td>\n");
			buff.append("	<td> Seuil </td>\n");
			buff.append("	</tr> \n");
			buff.append("	</thead> \n");
			buff.append("	<tbody> \n");
			for (Compte c : listCpt) {

				buff.append("	<tr> \n");
				buff.append("	<td> " + c.getNo() + "</td>\n");
				buff.append("	<td> " + c.getLibelle() + "</td>\n");
				buff.append("	<td> " + c.getSolde() + " </td>\n");
				buff.append("	<td> ");
				if (c instanceof ICompteASeuil) {
					buff.append(((ICompteASeuil) c).getSeuil());
				}
				buff.append("	</td>\n");
				buff.append("	<td> ");
				if (c instanceof ICompteRemunere) {
					buff.append(((ICompteRemunere) c).getTaux());
				}
				buff.append("	</td>\n");

				buff.append("	</tr> \n");
			}
			buff.append("	</tbody> \n");
			buff.append("	</table> \n");

			buff.append("</body>\n");
			buff.append("</html>\n");

			out.print(buff);

		} catch (RuntimeException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
