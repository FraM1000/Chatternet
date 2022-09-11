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
  <div class="grid-container">
  <jsp:include page="/WEB-INF/view/components/menu.jsp"></jsp:include>
  <div class="grid-item chats">
  <span class="titoloPagina">Chat</span><br>
  <div class="results" id= "resultId">
  <c:if test="${!listaChat.isEmpty()}"> 
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
  <a href="" onclick="deleteChat(${user.id})"><i class="bi bi-trash"></i></a>
  </a>
  </div><br>
  </c:forEach>
  </c:if>
  </div>
  </div>
  <div class="grid-item spazio">
  </div>
  <div class="grid-item altroSpazio">
  </div>
  <div class="grid-item footer">
  </div>
  </div>  
  
  <script type="text/javascript" src="../js/eliminaChat.js"></script>
	</body>
	</html>