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
		<link rel="stylesheet" type="text/css" href="../css/search-style.css">
		<link rel="stylesheet" type="text/css" href="../css/menu-component.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	</head>
	<body>
		<script>
			const loggedUserId = '${loggedUserId}';
		</script>
		
		<div class="grid-container">
			<jsp:include page="/WEB-INF/view/components/menu.jsp"></jsp:include>
			<div class="grid-item cerca">
				<div class="search-bar">
					<input type="text" placeholder="Cerca utenti" class="search" id="cercaUser">
					<div><span class="search-button"><a href="" id="cercaUsBt"><i class="bi bi-search"></i></a></span></div>
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
												<c:when test="${utente.photo != null}">
													<img alt="Immagine Profilo" src="${utente.photoPath}" class="img-exist-user">
												</c:when>
												<c:otherwise>
													<img class="img-user" alt="Immagine Profilo" src="/User">
												</c:otherwise>
											</c:choose>
											<span class="user-of-result"><h1><c:out value="${utente.username}"/></h1>
											<h2><c:out value="${utente.name}"/> <c:out value="${utente.surname}"/></h2></span>
										</a>
									</div><br>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="grid-item spazio">
					
				</div>
				<div class="grid-item altro-spazio">
					
				</div>
				<div class="grid-item footer">
					
				</div>
			</div>
			
			<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
			<script type="text/javascript" src="../js/search-user.js"></script>
			<script type="text/javascript" src="../js/notifications.js"></script>
		</body>
	</html>		