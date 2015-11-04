<%@page import="java.util.List,fr.banque.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des Clients</title>
</head>
<body>

<%
			List<Client> listClt = (List<Client>)request.getAttribute("listeClients");
%>
		<div class="container">
		<h1>
			Liste des clients
			</h1>

			<table> 
			<thead> 
			<tr> 
			<td> Nom </td>
			<td> Prenom </td>
			<td> Age </td>
			<td>  </td>
			</tr> 
			</thead> 
			<tbody> 
		<% for (Client c : listClt) { %>

				<tr> 
				<td>  <%=  c.getNom() %> </td>
				<td>  <%=  c.getPrenom() %>  </td>
				<td>  <%=  c.getAge() %> </td>
				<td>
					<form method='post' action="ServletListeComptes">
					<input type="hidden" name="no" value="<%=c.getNo()%>">
					<input type='submit' value='Comptes'/>
					</form>
				</td> 
				</td> 
				</tr> 
		<% } %>
			</tbody> 
			</table> 
		</div>
		</body>
		</html>

</body>
</html>
