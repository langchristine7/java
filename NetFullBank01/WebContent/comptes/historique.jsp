<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Historique de vos operations.</title>
 <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
 <link href="<c:url value="/css/banque.css" />" rel="stylesheet" type="text/css">
 <link href="<c:url value="/css/calendar.css" />" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="<c:url value="jqueryui/jquery-ui.css"/>">
 <script language="JavaScript" src="<c:url value="/librairie/Calendarcode.js"/>" type="text/javascript"></script>
 <script src="<c:url value="jqueryui/external/jquery/jquery.js"/>"></script>
 <script src="<c:url value="jqueryui/jquery-ui.js"/>"></script>
 <link rel="stylesheet" href="<c:url value="/resources/demos/style.css"/>">
 
<script>

</script>
</head>

<body class="elBody" >

<div id="popupcalendar" class="text">&nbsp;</div>
<form id="frmListeOperations" name="frmListeOperations" action="<c:url value="/historique"/>" method="post">

  <input type="hidden" name="noCompte" value="${noCompte}" />

  <table border="0" width="100%">
    <tr>
      <td align="center" valign="top">
        <img src="<c:url value="/images/titre.jpg"/>"  border="0" height="98" alt="" />
      </td>
    </tr>
    <tr>
      <td><hr></td>
    </tr>
    <tr>
      <td align="center" >
        <p style="font-size=25px;color=red"><!-- Erreur --></p>
        <p>&nbsp;</p>
        <p style="font-size=18px">Historique de vos op&eacute;rations effectu&eacute;es sur le compte n&deg; <c:out value="${noCompte}"/></p>
        <p style="font-size=16px">Crit&egrave;res de recherche :</p>
        <table width="70%" border="1">
          <tr>
            <td width="446" bgcolor="#ffffff" class="elLibelleTableau" >
               Date
            </td>
            <td width="138" bgcolor="#ffffff" class="elLibelleTableau"  >
               Type
            </td>
          </tr>
          <tr>
            <td width="460" bgcolor="#fae6a0" class="elLibelleTableau">
			<!-- Du
         
              <input type="text" id="inDateDebut" name="inDateDebut" value=""/>
              
              <a class="so-BtnLink" onclick="showCalendar('frmListeOperations','inDateDebut','IMG_DATE_DEBUT');return false;">
                <img align="MIDDLE"
                     border="0"
                     name="IMG_DATE_DEBUT"
                     id="IMG_DATE_DEBUT"
                     src="<c:url value="/images/date_icon.gif"/>"
                     WIDTH="14" HEIGHT="14" alt=""/>
              </a>
              
              <input type="text" id="datepickerDebut" name="datepickerDebut">
				&nbsp;&nbsp;
              Au
              <input type="text" name="inDateFin" id="inDateFin" value=""/>
              <a class="so-BtnLink"
                 onclick="showCalendar('frmListeOperations','inDateFin','IMG_DATE_FIN');return false;">
                <img align="MIDDLE"
                     border="0"
                     name="IMG_DATE_FIN"
                     id="IMG_DATE_FIN"
                     src="<c:url value="/images/date_icon.gif"/>"
                     WIDTH="14" HEIGHT="14" alt=""/>
              </a>
-->
         Du <input type="text" id="inDateDebut" name="inDateDebut" placeholder="JJ/MM/AAAA" 
         				style="margin-right:5px; margin-bottom:0px;" value="<c:out value="${dateDebut}"/>"/>
         &nbsp;au <input type="text" name="inDateFin" id="inDateFin" 
         				placeholder="JJ/MM/AAAA" style="margin-right:5px; margin-bottom:0px;" 
         				value="<c:out value="${dateFin}"/>"/>
            </td>
            <td width="138" bgcolor="#fae6a0" class="elLibelleTableau">
             <p>Credit
                 <input type="checkbox" name="inCredit" checked="checked"/>
              </p>
              <p>Debit
                 <input type="checkbox" name="inDebit" checked="checked"/>
              </p>
            </td>
          </tr>
        </table>
        <p><a href="javascript:frmListeOperations.submit()">
             <img src="<c:url value="/images/rechercher.gif"/> " width="98" height="33" border="0" alt=""/>
           </a>
        </p>
        <c:if test="${not empty error}">
                  		Attention : <c:out value="${error}"/>
        </c:if>
        <c:if test="${empty error}">
                  		&nbsp;
        </c:if>
        <p>&nbsp;</p>
        <table width="70%" border="1">
          <tr bgcolor="white">
            <td class="elLibelleTableau">Date</td>
            <td class="elLibelleTableau">Libell&eacute;</td>
            <td class="elLibelleTableau">Montant</td>
          </tr>
  		<c:forEach var="ope" items="${lstOperation}" >
          <tr class="elLigneTableau1">
            <td class="elLibelleTableau"><c:out value="${ope.date}"/></td>
            <td class="elLibelleTableau"><c:out value="${ope.libelle}"/></td>
            <td class="elLibelleTableau"><c:out value="${ope.montant}"/> Euros</td>
          </tr>
          </c:forEach>

        </table>
        <p>&nbsp;</p>
        <table width="150">
          <tr>
            <td><a href="<c:url value="listeComptes"/>" >
                   <img src="<c:url value ="/images/liste-comptes.gif"/>" width="103" height="33" border="0" alt="" />
                </a>
            </td>
            <td>&nbsp;</td>
            <td><a href="<c:url value="menu"/>" > 
                  <img src="<c:url value="/images/menu.gif"/>" width="98" height="33" border="0" alt=""/>
                </a>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>

<script>

$(document).ready( function () {
    /*$('#table_id').DataTable(); */
 
 $( "#inDateDebut" ).datepicker({
  changeMonth: true,
  changeYear: true,
  showOn: "button",
  buttonImage: "<c:url value="images/calendar.jpg"/>",
  buttonImageOnly: true,
  buttonText: "Select date",
  maxDate: 0,
  showWeek: true,
  firstDay: 1,
  dateFormat: "dd/mm/yy",
  onClose: function( selectedDate ) {
   $( "#inDateFin" ).datepicker( "option", "minDate", selectedDate );
  }
 });
 $( "#inDateFin" ).datepicker({
  changeMonth: true,
  changeYear: true,
  showOn: "button",
  buttonImage: "<c:url value="images/calendar.jpg"/>",
  buttonImageOnly: true,
  buttonText: "Select date",
  maxDate: 0,
  showWeek: true,
  firstDay: 1,
  dateFormat: "dd/mm/yy",
  onClose: function( selectedDate ) {
   $( "#inDateDebut" ).datepicker( "option", "maxDate", selectedDate );
  }
 });
 
 $("#inDateDebut").mask("99/99/9999",{placeholder:"JJ/MM/AAAA"});
 $("#inDateFin").mask("99/99/9999",{placeholder:"JJ/MM/AAAA"});
});


</script>

</html>
