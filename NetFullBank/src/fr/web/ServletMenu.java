package fr.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.banque.Client;
import fr.db.Db;

/**
 * Servlet implementation class ServletMenu
 */
@WebServlet("/menu")
public class ServletMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletMenu() {
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Db db = (Db)  request.getSession(true).getAttribute("db");
		Client client = (Client) request.getSession(true).getAttribute("client");
		if (db == null) {
			request.setAttribute("error", "Merci de vous connecter");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.asp");
			dispatcher.forward(request, response);
		}


	}

}
