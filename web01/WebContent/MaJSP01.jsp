<%@page import="java.text.SimpleDateFormat,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% for (int i = 0 ; i<10 ; i++){ %>
	Bonjour <% out.write(String.valueOf(i)); %> <br>
	hello <%= i %> <br/>
	
<%} %>
	Mon adresse IP : <%= request.getRemoteAddr() %> <br/>
	La date : <%= new Date() %> <br>
	<%-- commentaire JSP (reste cote serveur) --%>

<% SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); %>
Date formatée : <%= sdf.format(new Date()) %> <br>
<!--  commentaire html -->
</body>
</html>
