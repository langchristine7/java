package com.exo.jms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class JmsConsumer
 */
@WebServlet({ "/JmsConsumer", "/recevoir" })
public class JmsConsumer extends AbstractJmsServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JmsConsumer.LOG.debug("passage dans service servlet recevoir");

	}

}
