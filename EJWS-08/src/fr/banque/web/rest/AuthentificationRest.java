package fr.banque.web.rest;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.banque.Client;
import fr.bd.AccesBD;
import fr.web.IServlet;
import net.sf.json.JSONObject;

@Path("/authentication")
public class AuthentificationRest {
	private static final Logger LOG = LogManager.getLogger();


	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response authentifierPut(@QueryParam("login") String login, @QueryParam("password") String pwd) {

		AuthentificationRest.LOG.debug("service Authentification");
		AccesBD bd = null;
		JSONObject objJson = new JSONObject();

		try {
			bd = new AccesBD(IServlet.DB_DRIVER);
			bd.seConnecter(IServlet.DB_URL, IServlet.DB_LOGIN, IServlet.DB_PWD);

			if ((login == null) || (pwd == null) || login.trim().isEmpty() || pwd.trim().isEmpty()) {
				objJson.put("message", "Login et/ou password vides");
				return Response.status(Status.BAD_REQUEST).entity(objJson.toString()).build();
			}
			// On verifie que le tout est ok
			int userId = bd.authentifier(login, pwd);
			if (userId > 0) {
				Client client = bd.selectUtilisateur(userId);
				if (client != null) {
					objJson.put("numero", Integer.valueOf(userId));
					objJson.put("nom", client.getNom());
					objJson.put("prenom", client.getPrenom());
					objJson.put("age", client.getAge());
					return Response.status(Status.OK).entity(objJson.toString()).build();
				} else {
					AuthentificationRest.LOG.debug("pb acces client : client is null" + userId);
					objJson.put("message", "Problème d'accès aux données du client");
					return Response.status(Status.BAD_REQUEST).entity(objJson.toString()).build();
				}

			} else {
				AuthentificationRest.LOG.debug("authentifier : login/pwd incorrects");
				objJson.put("message", "Login/password incorrects");
				return Response.status(Status.BAD_REQUEST).entity(objJson.toString()).build();
			}


		} catch (SQLException e) {
			AuthentificationRest.LOG.debug("SQLException : pb acces bdd : " + e.getMessage());
			objJson.put("message", "Problème d'accès à la base de données");
			return Response.status(Status.BAD_REQUEST).entity(objJson.toString()).build();
		} finally {
			if (bd != null) {
				bd.seDeconnecter();
			}
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String authentifierPost(@FormParam("login") String login, @FormParam("password") String pwd) {

		AuthentificationRest.LOG.debug("service Authentification");
		AccesBD bd = null;
		JSONObject objJson = new JSONObject();

		try {
			bd = new AccesBD(IServlet.DB_DRIVER);
			bd.seConnecter(IServlet.DB_URL, IServlet.DB_LOGIN, IServlet.DB_PWD);

			if ((login == null) || (pwd == null) || login.trim().isEmpty() || pwd.trim().isEmpty()) {
				objJson.put("message", "Login et/ou password vides");
				return objJson.toString();
			}
			// On verifie que le tout est ok
			int userId = bd.authentifier(login, pwd);
			if (userId > 0) {
				Client client = bd.selectUtilisateur(userId);
				if (client != null) {
					objJson.put("numero", Integer.valueOf(userId));
					objJson.put("nom", client.getNom());
					objJson.put("prenom", client.getPrenom());
					objJson.put("age", client.getAge());
					return objJson.toString();
				} else {
					AuthentificationRest.LOG.debug("pb acces client : client is null" + userId);
					objJson.put("message", "Problème d'accès aux données du client");
					return objJson.toString();
				}

			} else {
				AuthentificationRest.LOG.debug("authentifier : login/pwd incorrects");
				objJson.put("message", "Login/password incorrects");
				return objJson.toString();
			}

		} catch (SQLException e) {
			AuthentificationRest.LOG.debug("SQLException : pb acces bdd : " + e.getMessage());
			objJson.put("message", "Problème d'accès à la base de données");
			return objJson.toString();
		} finally {
			if (bd != null) {
				bd.seDeconnecter();
			}
		}
	}

	@GET
	@Path("{login}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String authentifierGet(@PathParam("login") String login, @PathParam("password") String pwd,
			@Context HttpServletRequest request) {

		AuthentificationRest.LOG.debug("service Authentification");
		AccesBD bd = null;
		JSONObject objJson = new JSONObject();

		try {
			bd = new AccesBD(IServlet.DB_DRIVER);
			bd.seConnecter(IServlet.DB_URL, IServlet.DB_LOGIN, IServlet.DB_PWD);

			if ((login == null) || (pwd == null) || login.trim().isEmpty() || pwd.trim().isEmpty()) {
				objJson.put("message", "Login et/ou password vides");
				return objJson.toString();
			}
			// On verifie que le tout est ok
			int userId = bd.authentifier(login, pwd);
			if (userId > 0) {
				Client client = bd.selectUtilisateur(userId);
				if (client != null) {
					objJson.put("numero", Integer.valueOf(userId));
					objJson.put("nom", client.getNom());
					objJson.put("prenom", client.getPrenom());
					objJson.put("age", client.getAge());
					return objJson.toString();
				} else {
					AuthentificationRest.LOG.debug("pb acces client : client is null" + userId);
					objJson.put("message", "Problème d'accès aux données du client");
					return objJson.toString();
				}

			} else {
				AuthentificationRest.LOG.debug("authentifier : login/pwd incorrects");
				objJson.put("message", "Login/password incorrects");
				return objJson.toString();
			}

		} catch (SQLException e) {
			AuthentificationRest.LOG.debug("SQLException : pb acces bdd : " + e.getMessage());
			objJson.put("message", "Problème d'accès à la base de données");
			return objJson.toString();
		} finally {
			if (bd != null) {
				bd.seDeconnecter();
			}
		}
	}

}