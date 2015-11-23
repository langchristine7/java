package com.exo.jms.servlet;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class Envoyer
 */
@WebServlet(description = "Envoi du message", urlPatterns = { "/envoyer" })
public class JmsProducer extends AbstractJmsServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		try {
			connection = this.getJmsFactory().createConnection();
			connection.start();
			javax.jms.Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		} catch (JMSException e) {
			JmsProducer.LOG.debug("Erreur connexion : " + e.getMessage());
			request.setAttribute("erreur", "Erreur connexion : " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("erreur_e.jsp");
			dispatcher.forward(request, response);
		}

		// request.setAttribute("message", );
	}

}
