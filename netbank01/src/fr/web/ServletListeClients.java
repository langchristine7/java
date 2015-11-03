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
import fr.db.Db;

/**
 * Servlet implementation class ServletClient
 */
@WebServlet(description = "Affiche les clients", urlPatterns = { "/ServletListeClients" })
public class ServletListeClients extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Db db = null;
	private Properties mesProperties = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListeClients() {
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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			List<Client> listClt = this.db.listerClients();
			request.setAttribute("listeClients", listClt);
			// Passer la main a 'MaJSP02.jsp'
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListeClients.jsp");
			// on laisse la main à une JSP
			dispatcher.forward(request, response);
			// attention le code ici sera execute à la suite

		} catch (RuntimeException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
