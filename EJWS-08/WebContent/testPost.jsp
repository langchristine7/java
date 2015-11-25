<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>test Post Authentification</title>
</head>
<body>

<form action="rest/authentication" method="post">
  <input type="text" name="login" /><br/>
  <input type="password" name="password" /><br/>
  
  <input type="submit" value="Valider" />
</form>

</body>
</html>
