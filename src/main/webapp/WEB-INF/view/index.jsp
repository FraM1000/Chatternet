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
    <title>Chatternet | Home</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
	<link rel="icon" type="image/x-icon" href="/Icona">
   <link rel="stylesheet" type="text/css" href="../css/homeStyle.css">
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
  </head>
  <body>
  <script>
  let nessunaChat = false;
  const loggedUserId = '${loggedUserId}';
  </script>  

  <div class="grid-container">
  <jsp:include page="/WEB-INF/view/components/menu.jsp"></jsp:include>
  <div class="grid-item chats">
  <span class="titoloPagina">Chat</span><br>
  <div class="results" id= "resultId">
  <c:choose>
  <c:when test="${!listaChat.isEmpty()}"> 
  <c:forEach var="user" items="${listaChat}">
  <div class="user">
  <a href="/mostraChat?id=<c:out value="${user.id}"/>" id="usLink">
  <c:choose>
  <c:when test="${user.foto != null}">
  <img alt="Immagine Profilo" src="${user.fotoPath}" class="imgExistUser">
  </c:when>
  <c:otherwise>
  <img class="imgUser" alt="Immagine Profilo" src="/User">
  </c:otherwise>
  </c:choose>
  <h1><c:out value="${user.username}"/></h1>
  <c:if test="${user.messaggiRicevutiNonLetti != 0}"><h3 class="alertNewMexs"><c:out value="${user.messaggiRicevutiNonLetti}"/> nuovi messaggi !</h3></c:if>
  <a href="" onclick="deleteChat(${user.id})"><i class="bi bi-trash"></i></a>
  </a>
  </div><br>
  </c:forEach>
  </c:when>
  <c:otherwise>
  <pre class="homemex" id="homemex">Ricerca i tuoi amici e inizia
a chattare con loro.</pre>
  <script>
  nessunaChat = true;
  </script>
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
  <script type="text/javascript" src="../js/eliminaChat.js"></script>
  <script type="text/javascript" src="../js/messaggioBenvenuto.js"></script>
  <script type="text/javascript" src="../js/notifiche.js"></script>
	</body>
	</html>