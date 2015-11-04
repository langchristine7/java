<%@page import="java.util.List,fr.banque.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des Clients</title>
<link rel="stylesheet" href="<c:url value="css/normalize.css"/>" />
<link rel="stylesheet" href="<c:url value="css/skeleton.css"/>" />
</head>
<body>

<div class="container">
<div class="row">
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
		<c:forEach items="${listeClients}" var="c">

				<tr> 
				<td>  <c:out value="${c.nom}" /> </td>
				<td>  <c:out value="${c.prenom}" /> </td>
				<td>  <c:out value="${c.age}" /> </td>
				<td>
					<form method='post' action="<c:url value="/ServletListeComptes"/>" >
					<input type="hidden" name="no" value="<c:out value="${c.no}" />" />
					<input type='submit' value='Comptes'/>
					</form>
				</td> 
				
				</tr> 
		</c:forEach>
			</tbody> 
			</table> 
			</div>
		</div>
</body>
</html>

