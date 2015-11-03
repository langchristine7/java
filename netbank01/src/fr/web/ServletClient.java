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
import fr.banque.Operation;
import fr.db.Db;

/**
 * Servlet implementation class ServletClient
 */
@WebServlet(description = "Affiche les clients", urlPatterns = { "/ServletClient" })
public class ServletClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Db db = null;
	private Properties mesProperties = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletClient() {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			List<Client> listClt = this.db.listerClients();
			PrintWriter out = response.getWriter();
			StringBuffer buff = new StringBuffer();
			buff.append("<html>\n");
			buff.append("<head>\n");
			buff.append("<title>\n");
			buff.append("   Banque Poec\n");
			buff.append("</title>\n");
			buff.append("<link rel=\"stylesheet\" href=\"css/normalize.css\"/>");
			buff.append("<link rel=\"stylesheet\" href=\"css/skeleton.css\"/>");

			buff.append("</head>\n");
			buff.append("<body>\n");
			buff.append("	<h1>\n");
			buff.append("		Liste des clients\n");
			buff.append("	</h1>\n");

			buff.append("	<table> \n");
			buff.append("	<thead> \n");
			buff.append("	<tr> \n");
			buff.append("	<td> Nom </td>\n");
			buff.append("	<td> Prenom </td>\n");
			buff.append("	<td> Age </td>\n");
			buff.append("	<td>  </td>\n");
			buff.append("	</tr> \n");
			buff.append("	</thead> \n");
			buff.append("	<tbody> \n");
			for (Client c : listClt) {

				buff.append("	<tr> \n");
				buff.append("	<td> " + c.getNom() + "</td>\n");
				buff.append("	<td> " + c.getPrenom() + " </td>\n");
				buff.append("	<td> " + c.getAge() + "</td>\n");

				buff.append("	<td>\n");
				buff.append("		<form method='post' action='./ServletCompte?no=" + c.getNo() + "'>");
				buff.append("		<input type='submit' value='Comptes'/>");
				buff.append("		</form>\n");
				buff.append("	</td> \n");
				buff.append("	</td> \n");
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
