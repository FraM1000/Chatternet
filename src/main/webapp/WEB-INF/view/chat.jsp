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
    <title>Chatternet | Chat</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
	<link rel="icon" type="image/x-icon" href="/Icona">
   <link rel="stylesheet" type="text/css" href="../css/chatStyle.css">
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
  </head>
  <body>
  <script>
  const loggedUserId = '${loggedUserId}';
  const userToChatWithId = '${utenteConCuiChattare.id}';
  </script>
 
  <div class="grid-container">
  <div class="grid-item menu">
  <a href=""><i class="bi bi-box-arrow-left"></i></a>
  <a href=""><i class="bi bi-search"></i></a>
  <a href=""><i class="bi bi-chat"></i></a>
  <a href=""><i class="bi bi-person"></i></a>
  </div>
  <div class="grid-item chat">
  <div class="overlay">
  <div>
  <div class="aUser">
  <a href="#" onclick="history.back()"><i class="bi bi-arrow-left"></i></a>
  <c:choose>
  <c:when test="${utenteConCuiChattare.foto != null}">
  <img alt="Immagine" src="${utenteConCuiChattare.fotoPath}" class="imgExistUser">
  </c:when>
  <c:otherwise>
  <img class="imgUser" alt="Immagine" src="/User">
  </c:otherwise>
  </c:choose>
  <div class="posUsNam"><h2><c:out value="${utenteConCuiChattare.username}"/></h2>
  </div>
  <c:if test="${statoOnline eq true}"><span class="onlineDot"></span></c:if>
  </div>
  <div class="mexSpace" id="mexSpace">
  <c:if test="${listaMessaggi != null}">
  <c:forEach var="messaggio" items="${listaMessaggi}">
  <c:choose>
  <c:when test="${messaggio.utenteInviante != loggedUserId}">
  <div class="mexageReceived">
  <div class="mexageTextReceived">
  <c:out value="${messaggio.testo}"/>
  <br><small class="mexageDate">
  <c:out value="${messaggio.ora}"/>
  </small>
  </div></div>
  </c:when>
  <c:otherwise>
  <div class="mexageSended">
  <div class="mexageTextSended">
  <c:out value="${messaggio.testo}"/>
  <br><small class="mexageDate">
  <c:out value="${messaggio.ora}"/>
  </small>
  </div></div>
  </c:otherwise>
  </c:choose>
  </c:forEach>
  </c:if>
  </div>
  <div class="inviaMex">
  <input type="text" placeholder="Messaggio" id="inviaBarDskt">
  <div><span class="inviaButton"><a href="" id="inviaMexBt"><i class="bi bi-send"></i></a></span></div>
  </div>
  </div>
  </div>
  </div>
  <div class="grid-item spazio">
  
  </div>
  <div class="aUserMob">
  <a href="#" onclick="history.back()"><i class="bi bi-arrow-left"></i></a>
  <c:choose>
  <c:when test="${utenteConCuiChattare.foto != null}">
  <img alt="Immagine" src="${utenteConCuiChattare.fotoPath}" class="imgExistUser">
  </c:when>
  <c:otherwise>
  <img class="imgUser" alt="Immagine" src="/User">
  </c:otherwise>
  </c:choose>
  <div class="posUsNam"><h2><c:out value="${utenteConCuiChattare.username}"/></h2>
  </div>
  <c:if test="${statoOnline eq true}"><span class="onlineDot"></span></c:if>
  </div>
  <div class="mexSpaceMob" id="mexSpaceMob">
  <c:if test="${listaMessaggi != null}">
  <c:forEach var="messaggio" items="${listaMessaggi}">
  <c:choose>
  <c:when test="${messaggio.utenteInviante != loggedUserId}">
  <div class="mexageReceived">
  <div class="mexageTextReceived">
  <c:out value="${messaggio.testo}"/>
  <br><small class="mexageDate">
  <c:out value="${messaggio.ora}"/>
  </small>
  </div></div>
  </c:when>
  <c:otherwise>
  <div class="mexageSended">
  <div class="mexageTextSended">
  <c:out value="${messaggio.testo}"/>
  <br><small class="mexageDate">
  <c:out value="${messaggio.ora}"/>
  </small>
  </div></div>
  </c:otherwise>
  </c:choose>
  </c:forEach>
  </c:if>
  </div>
  <div class="inviaMexMob">
  <input type="text" placeholder="Messaggio" id="inviaBar">
  <span class="inviaButton"><a href="" id="inviaMexBtMob"><i class="bi bi-send"></i></a></span>
  </div>
  </div>
 
 <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
 <script type="text/javascript" src="../js/chat.js"></script>
  </body>
</html>