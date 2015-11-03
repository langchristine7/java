<%@page import="java.io.IOException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.List,java.text.SimpleDateFormat,java.util.Date,fr.db.*,fr.banque.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des Clients</title>
</head>
<body>

<%!
	Db db = null;
	Properties mesProperties = null;
%>

<%! public void jspInit() {
	mesProperties = new Properties();
	File file = new File("C:/Users/chris/git/java/Exo11/src/mesPreferences.properties");
	if (file.exists() && file.canRead()) {
		try (FileReader fr = new FileReader(file)) { // a partir de java 7,
			// traite le finally
			// tout seul
			mesProperties.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} else {
		System.err.println("Fichier '" + file + "' pas trouve");
	}

	// Nom du driver pour acceder a la base de donnees
	// lire la doc associee a sa base de donnees pour le connaitre
	final String dbDriver = mesProperties.getProperty("db.driver");
	final String dbUrl = mesProperties.getProperty("db.url");
	final String dbLogin = mesProperties.getProperty("db.login");
	final String dbPwd = mesProperties.getProperty("db.password");

	db = null;
	try {
		// db = new Db();
		db = new Db(dbDriver, dbUrl, dbLogin, dbPwd);
	} catch (ClassNotFoundException e) {

		e.printStackTrace();
		System.exit(-1);
	}
	try {
		db.seConnecter();

	
	} catch (SQLException e) {
		System.out.println("Probleme de connexion : ");
		e.printStackTrace();
	} catch (RuntimeException e) {
		e.printStackTrace();

	}
}
%>

<%! 
public void jspDestroy() {
	if (db != null) {
		db.seDeconnecter();
	}
}
%>

<%
try {
	int noClient;
	try {
		noClient = Integer.parseInt(request.getParameter("no"));
	} catch (RuntimeException e) {
		out.print("Le client no " + request.getParameter("no") + " n'existe pas");
		return;
	}

	List<Compte> listCpt = db.listerComptes(noClient);
%>

	<html>
	<head>
	<title>
	   Banque Poec
	</title>
	</head>
	<body>
		<h1>
			Liste des comptes du client <%=noClient%>
		</h1>

		<table> 
		<thead> 
		<tr> 
		<td> No </td>
		<td> Libelle </td>
		<td> Solde </td>
		<td> Taux </td>
		<td> Seuil </td>
		</tr> 
		</thead> 
		<tbody> 
	<% for (Compte c : listCpt) { %>

			<tr> 
			<td> <%=c.getNo()%></td>
			<td> <%= c.getLibelle() %> </td>
			<td> <%= c.getSolde() %></td>
			<td>
		<% if (c instanceof ICompteASeuil) { %>
			<%=((ICompteASeuil) c).getSeuil()%>
		<% } %>
			</td>
			<td>
		<% if (c instanceof ICompteRemunere) { %>
			<%=((ICompteRemunere) c).getTaux()%>
		<% } %>
			</td>

			</tr> 
	<% } %>
		</tbody> 
		</table> 

	</body>
	</html>

<%
} catch (RuntimeException e) {
	e.printStackTrace();

} catch (Exception e) {
	e.printStackTrace();
}
%>

</body>
</html>
