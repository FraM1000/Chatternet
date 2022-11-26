<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="it-IT">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chatternet | Cerca</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
	<link rel="icon" type="image/x-icon" href="/Icona">
   <link rel="stylesheet" type="text/css" href="../css/ricercaStyle.css">
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
  </head>
  <body>
  <script>
  const loggedUserId = '${loggedUserId}';
  </script>
  
  <div class="grid-container">
  <jsp:include page="/WEB-INF/view/components/menu.jsp"></jsp:include>
  <div class="grid-item cerca">
  <div class="searchBar">
  <input type="text" placeholder="Cerca utenti" class="search" id="cercaUser">
  <div><span class="searchButton"><a href="" id="cercaUsBt"><i class="bi bi-search"></i></a></span></div>
  </div>
  <div class="results">
  <c:choose>
  <c:when test="${listaUtenti == null}">
  <p class="resmex">Nessun utente &eacute stato trovato.
  </c:when>
  <c:otherwise>
  <c:forEach var="utente" items="${listaUtenti}">
  <div class="user">
  <a href="/mostraChat?id=<c:out value="${utente.id}"/>" id="usLink">
  <c:choose>
  <c:when test="${utente.foto != null}">
  <img alt="Immagine Profilo" src="${utente.fotoPath}" class="imgExistUser">
  </c:when>
  <c:otherwise>
  <img class="imgUser" alt="Immagine Profilo" src="/User">
  </c:otherwise>
  </c:choose>
  <span class="userOfResult"><h1><c:out value="${utente.username}"/></h1>
  <h2><c:out value="${utente.nome}"/> <c:out value="${utente.cognome}"/></h2></span>
  </a>
  </div><br>
  </c:forEach>
  </c:otherwise>
  </c:choose>
  </div>
  </div>
  <div class="grid-item spazio">
  
  </div>
  <div class="grid-item altroSpazio">
  
  </div>
  <div class="grid-item footer">
  
  </div>
  </div>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
  <script type="text/javascript" src="../js/ricercaUser.js"></script>
  <script type="text/javascript" src="../js/notifiche.js"></script>
	</body>
	</html>