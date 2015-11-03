package fr.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MaDeuxiemeServlet
 */
@WebServlet("/MaDeuxiemeServlet")
public class MaDeuxiemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaDeuxiemeServlet() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// je fais qqc de compliqué
		int a = 50 + 12;
		// je place l'information a disposition de la JSP

		// scope session
		request.setAttribute("maClef", Integer.valueOf(a));

		// scope session
		HttpSession session = request.getSession(true);
		session.setAttribute("uneClefSession", Integer.valueOf(a));

		// scope application
		ServletContext sc = request.getServletContext();
		sc.setAttribute("encoreUneClef", Integer.valueOf(a));

		// Passer la main a 'MaJSP02.jsp'
		RequestDispatcher dispatcher = request.getRequestDispatcher("MaJSP02.jsp");
		// on laisse la main à une JSP
		dispatcher.forward(request, response);
		// attention le code ici sera execute à la suite
		return;

		// si on fait

		// on perd ce qu'on a mis dans request
		// response.sendRedirect("MaJSP02.jsp");

	}

}
