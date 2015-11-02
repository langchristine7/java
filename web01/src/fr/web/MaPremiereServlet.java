package fr.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MaPremiereServlet
 */
@WebServlet(description = "Ceci est ma premiere Servlet", urlPatterns = { "/MaPremiereServlet" }, loadOnStartup = 99)
public class MaPremiereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaPremiereServlet() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("-- Passage dans Init --");
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("-- Passage dans dans Destroy --");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuffer buff = new StringBuffer();
		buff.append("<html>\n");
		buff.append("<head>\n");
		buff.append("<title>\n");
		buff.append("   Ma page web\n");
		buff.append("</title>\n");
		buff.append("</head>\n");
		buff.append("<body>\n");
		buff.append("	<h1>\n");
		buff.append("		Bonjour toto\n");
		buff.append("	</h1>\n");
		buff.append("	Le serveur porte le nom : ");
		buff.append(request.getServerName()).append("<br>\n");

		buff.append("	Le context root est : ");
		buff.append(request.getContextPath()).append("<br>\n");

		buff.append("	Votre adresse ip est : ");
		buff.append(request.getRemoteAddr()).append("<br>\n");

		buff.append("	Votre protocole est : ");
		buff.append(request.getScheme()).append("<br>\n");

		for (int i = 0; i < 10; i++) {
			buff.append("   Val=").append(i).append("<br/>");

		}

		buff.append("</body>\n");
		buff.append("</html>\n");

		out.print(buff);
	}
}
