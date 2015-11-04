<%@page import="java.util.List,fr.banque.*"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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
<c:if test="${not empty erreur}">
		Erreur : <c:out value="${erreur}"/>		
</c:if>
<c:if test="${empty erreur}">

		<h1>
			Liste des comptes du client <c:out value="${noClient}"/><br>
			<c:out value="${nomclient}"/>
		</h1>

		<table> 
		<thead> 
		<tr> 
		<td> No </td>
		<td> Libelle </td>
		<td> Solde </td>
		<td> Seuil </td>
		<td> Taux </td>
		</tr> 
		</thead> 
		<tbody>
		 
	<c:forEach var="c" items="${lstBeanCompte}" >

			<tr> 
			<td> <c:out value="${c.no}"/></td>
			<td> <c:out value="${c.libelle}"/> </td>
			<td> <c:out value="${c.solde}"/>&nbsp;&euro;</td>
			<td>
			<c:if test="${c.hasSeuil}">
				<c:out value="${c.seuil}"/>&nbsp;&euro;
			</c:if>
			<c:if test="${not c.hasSeuil}">
				<c:out value="pas de seuil"/>
			</c:if>
			</td>
			<td>
			<c:if test="${c.hasTaux}"> 
			    <c:out value="${c.taux}"/>&nbsp;%
			</c:if>
			<c:if test="${not c.hasTaux}"> 
			    <c:out value="pas de taux"/>
			</c:if>
			 </td>
			<td>
			</td>
			</tr> 
	</c:forEach>
		</tbody> 
		</table> 
</c:if>
</div>
</div>
</body>
</html>