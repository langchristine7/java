<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Envoyer</title>
</head>
<body>

<form action="<c:url value="/envoyer"/>" method="post">
	<div>
		Message : 
		<textarea name="textMessage" id="textMessage" cols="80" rows="6"></textarea>
	</div>
	<div><input type="submit" value="Envoyer"></div>
</form>

<a href="#" >Recevoir les messages</a></br>

<a href="<c:url value="localhost:8161"/>">URL d'administration ActiveMQ(admin/admin)</a>


</body>
</html>