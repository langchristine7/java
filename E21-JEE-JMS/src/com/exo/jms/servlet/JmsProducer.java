package com.exo.jms.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = null;
		javax.jms.Session session = null;
		javax.jms.MessageProducer producer = null;

		try {
			connection = super.getJmsFactory().createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			javax.jms.Destination destination = session.createQueue("jmsj2ee.queue");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			String text = request.getParameter("textMessage") + " [" + sdf.format(new Date()) + "]";
			javax.jms.TextMessage message = session.createTextMessage(text);
			JmsProducer.LOG.debug(message);
			producer.send(message);

			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("erreur_e.jsp");
			dispatcher.forward(request, response);

		} catch (JMSException e) {
			JmsProducer.LOG.debug("Erreur connexion : " + e.getMessage());
			request.setAttribute("erreur", "Erreur connexion : " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("erreur_e.jsp");
			dispatcher.forward(request, response);

		} finally {
			try {
				if (producer != null) {
					producer.close();
				}
			} catch (JMSException e) {
				JmsProducer.LOG.debug("Cannot close producer");
			}

			try {
				if (session != null) {
					session.close();
				}
			} catch (JMSException e) {
				JmsProducer.LOG.debug("Cannot close session");
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (JMSException e) {
				JmsProducer.LOG.debug("Cannot close connection");
			}
		}
	}
}
