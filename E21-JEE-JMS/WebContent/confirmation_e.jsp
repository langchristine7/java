<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation</title>


</head>
<body>
Le message a bien été envoyé :
<h1>Confirmation : </h1>
<div name="message" id="message"><c:out value="${message}"/>"</div>
<div></br>
</br><a href="<c:url value="/recevoir"/>">Voir les messages</a></div>
<div></div>
</body>
</html>
