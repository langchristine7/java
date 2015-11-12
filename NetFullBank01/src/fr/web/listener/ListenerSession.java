package fr.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.web.connexion.WebConnexion;

public class ListenerSession implements HttpSessionListener {
	final static Logger LOG = LogManager.getLogger(ListenerSession.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		ListenerSession.LOG.debug("Creation du listener session : " + this.getClass());
		ListenerSession.LOG.debug(event.getSource());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		ListenerSession.LOG.debug("Destruction de session : " + this.getClass());
		HttpSession session = event.getSession();
		WebConnexion.removeConnexion(session);
	}

}
