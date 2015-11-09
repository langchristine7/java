package fr.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletMenu
 */
@WebServlet("/menu")
public class ServletMenu extends Connect {
	private static final long serialVersionUID = 1L;
	private String pageMenu = "menu.jsp";

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

		RequestDispatcher dispatcher = request.getRequestDispatcher(this.pageMenu);
		dispatcher.forward(request, response);
	}

}
