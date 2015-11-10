package fr.web.connexion;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.banque.Client;

public final class WebConnexion {
	private static final Logger LOG = LogManager.getLogger(WebConnexion.class);
	private static Map<Integer, Client> mapClients = new HashMap<Integer, Client>();

	public static void addConnexion(HttpSession session) {
		WebConnexion.LOG.debug("addConnexion  ");
		if (session == null) {
			WebConnexion.LOG.debug("addConnexion, session est nulle");
			return;
		}
		Client client = (Client) session.getAttribute("client");
		if (client == null) {
			WebConnexion.LOG.debug("addConnexion : pas de client connecte");
			return;
		}
		WebConnexion.LOG.debug("addConnexion client : " + client.getNom());
		ServletContext context = session.getServletContext();

		Map<Integer, Client> mapClients = (Map<Integer, Client>) context.getAttribute("mapClients");
		if (mapClients == null) {
			mapClients = new HashMap<Integer, Client>();
		}
		mapClients.put(client.getNo(), client);
		context.setAttribute("mapClients", mapClients);
		context.setAttribute("nbConnexions", mapClients.size());
		WebConnexion.LOG.debug("addConnexion nb connexions : " + mapClients.size());
	}

	public static int getNombreConnexions(ServletContext context) {
		Map<Integer, Client> mapClients = (Map<Integer, Client>) context.getAttribute("mapClients");
		if (mapClients == null) {
			return 0;
		}
		return mapClients.size();
	}

	public static void removeConnexion(HttpSession session) {
		Client client = (Client) session.getAttribute("client");
		ServletContext context = session.getServletContext();

		WebConnexion.LOG.debug("removeConnexion");

		if (client == null) {
			WebConnexion.LOG.debug("removeConnexion : client non connecte");
			return;
		}
		WebConnexion.LOG.debug("removeConnexion retrait client : " + client.getNom());

		Map<Integer, Client> mapClients = (Map<Integer, Client>) context.getAttribute("mapClients");
		if (mapClients != null) {
			mapClients.remove(client.getNo());
		}
		context.setAttribute("mapClients", mapClients);
		context.setAttribute("nbConnexions", WebConnexion.getNombreConnexions(context));
		WebConnexion.LOG
		.debug("removeConnexion, nb de connexions restantes : " + WebConnexion.getNombreConnexions(context));
		session.invalidate();
	}
}

