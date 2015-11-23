<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Erreur</title>


</head>
<body>
<h1>Erreur : </h1>
Erreur :
<div name="erreur" id="erreur"><c:out value="${erreur}"/>"</div>
<div><a href="<c:url value="/envoyer"/>">Réessayer</a></div>
</body>
</html>