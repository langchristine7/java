<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Liste de vos comptes.</title>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <link href="<c:url value="/css/banque.css"/>" rel="stylesheet" type="text/css">
</head>

<body class="elBody">
<!--  jmeter:pagelistecomptes -->

<form id="frmListeCompte" name="frmListeCompte" action="<c:url value="/historique"/>" method="post">
<input type="hidden" name="noCompte" id ="noCompte"/>
  <table border="0" width="100%">
    <tr>
      <td align="center" valign="top">
        <img src="<c:url value="/images/titre.jpg"/>" border="0" height="98" alt="" >
      </td>
    </tr>
    <tr>
      <td><hr></td>
    </tr>
    <tr>
      <td align="center" >
        <p class="elTitre2" >Liste de vos comptes sur Net Banque</p>
        <table border="1" width="60%">
          <tr bgcolor="white">
            <td class="elLibelleTableau">Num&eacute;ro</td>
            <td class="elLibelleTableau">D&eacute;signation</td>
			<td class="elLibelleTableau">Taux</td>
            <td class="elLibelleTableau">D&eacute;couvert autoris&eacute;</td>
            <td class="elLibelleTableau">Solde</td>
          </tr>
          <c:forEach var="c" items="${lstBeanCompte}" >
          
          <tr class="elLigneTableau1">
            <td class="elLibelleTableau">
             <a href="javascript:setNoCompte(<c:out value="${c.no}"/>);"><c:out value="${c.no}"/></a>
            </td>
			<td class="elLibelleTableau"><c:out value="${c.libelle}"/></td>
            <td class="elLibelleTableau">
            	<c:if test="${c.hasTaux}"> 
			    <c:out value="${c.taux}"/>&nbsp;%
				</c:if>
			<c:if test="${not c.hasTaux}"> 
			    <c:out value="pas de taux"/>
			</c:if></td>
            <td class="elLibelleTableau">
			<c:if test="${c.hasSeuil}">
				<c:out value="${c.seuil}"/>&nbsp;&euro;
			</c:if>
			<c:if test="${not c.hasSeuil}">
				<c:out value="pas de seuil"/>
			</c:if>
			</td>
            <td class="elLibelleTableau"><c:out value="${c.solde}"/>&nbsp;&euro;</td>
          </tr>
          </c:forEach>
        </table>
        <p>
          <a href="<c:url value="/menu"/>" >
            <img src="<c:url value="/images/menu.gif"/>" width="98" height="33" border="0" alt="" />
          </a>
        </p>
      </td>
    </tr>
  </table>
</form>
</body>
<script>
function setNoCompte (noCompte)
{
	//alert(noCompte);
	frmListeCompte.noCompte.value = noCompte;
	frmListeCompte.submit();
	//alert ("input hiddern : " + frmListeCompte.noCompte.value);
}
</script>
</html>
