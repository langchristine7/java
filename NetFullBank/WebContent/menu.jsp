<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>NetBanque Menu</title>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <link href="css/banque.css" rel="stylesheet">
</head>

<body  class="elBody" >
    <table border="0" width="100%">
      <tr>
        <td align="center" valign="top">
          <img src="images/image-bienvenue.jpg" border="0" height="98" alt="" />
        </td>
      </tr>
      <tr>
        <td>
          <hr>
        </td>
      </tr>
      <tr>
        <td align="center">
          <table>
            <tr>
              <td>
                <table width="100%" border="1">
                  <tr class="elLigneTableau1">
                    <td width="446" class="elCelluleTableau3">
                      <img src="images/puce.gif" width="13" height="18" alt=""/>&nbsp;
                   <!--     <a href="<c:url value="/comptes/liste.html"/>"> Liste de vos comptes</a> -->
                      <a href="<c:url value="listeComptes"/>"> Liste de vos comptes</a>
                    </td>
                  </tr>
                  <tr class="elLigneTableau2">
                    <td width="446" class="elCelluleTableau3">
                      <img src="images/puce.gif" width="13" height="18" alt="" />&nbsp;
                      <a href="<c:url value="comptes/virement.html"/>" > Virement </a>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
         </table>
      </td>
    </tr>
  </table>
</body>
</html>
