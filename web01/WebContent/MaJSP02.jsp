<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ma JSP 02</title>
</head>
<body>
	<%  // les clefs sont forcement des string
	Integer valeur = (Integer) request.getAttribute("maClef"); 
	 Integer uneClefSession = (Integer) session.getAttribute("uneClefSession"); 
	 Integer valeurApplication = (Integer) application.getAttribute("encoreUneClef"); 
	 Enumeration<String> en=request.getAttributeNames();
	 while(en.hasMoreElements()){
		 String clef = en.nextElement();
	%> 
	 
	 	Clef=<%=clef %>, valeur=<%=request.getAttribute(clef) %><br>
	 <% }%>
	
	Resultat = <%=valeur%>
</body>
</html>
