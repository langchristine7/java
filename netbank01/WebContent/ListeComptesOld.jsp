<%@page import="java.util.List,fr.banque.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Liste des Clients</title>
	<link rel="stylesheet" href="css/normalize.css"/>
	<link rel="stylesheet" href="css/skeleton.css"/>

</head>
<body>
<%
	String erreur = (String) request.getAttribute("erreur");
	if (erreur == null) {
%>
		Erreur : <%=erreur %>		

<%
	} else
	{
	int noClient = (int) request.getAttribute("noClient");
	List<Compte> listCpt = (List<Compte>) request.getAttribute("listeComptes");
	
%>
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
<% } %>
</body>
</html>