package fr.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import fr.web.Connect;

/**
 * Servlet Filter implementation class AuthentifieFilter
 */
@WebFilter("/AuthentifieFilter")
public class AuthentifieFilter implements Filter {
	private final static Logger LOG = LogManager.getLogger(AuthentifieFilter.class);

	/**
	 * Default constructor.
	 */
	public AuthentifieFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain) 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		AuthentifieFilter.LOG.debug("url + " + httpRequest.getRequestURL());
		System.out.println("authentifie = " + chain);
		System.out.println("authentifie url : " + httpRequest.getRequestURL());

		if (httpRequest.getSession().getAttribute("client") == null) {
			AuthentifieFilter.LOG.debug("client est null");
			System.out.println("client est null");
			httpRequest.setAttribute("error", "Veuillez vous connecter");
			Connect.retourneAuLogin(httpRequest, (HttpServletResponse) response);
			return;
		}
		chain.doFilter(request, response);
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
