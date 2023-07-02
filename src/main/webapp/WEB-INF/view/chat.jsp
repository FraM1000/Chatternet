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
		<link rel="stylesheet" type="text/css" href="../css/chat-style.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	</head>
	<body>
		<script>
			const loggedUserId = '${loggedUserId}';
			const userToChatWithId = '${utenteConCuiChattare.id}';
		</script>
		
		<div class="grid-container">
			<jsp:include page="/WEB-INF/view/components/menu.jsp"></jsp:include>
			<div class="grid-item chat">
				<div class="overlay">
					<div>
						<div class="aUser">
							<a href="#" onclick="history.back()"><i class="bi bi-arrow-left"></i></a>
							<c:choose>
								<c:when test="${utenteConCuiChattare.foto != null}">
									<img alt="Immagine" src="${utenteConCuiChattare.fotoPath}" class="img-exist-user">
								</c:when>
								<c:otherwise>
									<img class="img-user" alt="Immagine" src="/User">
								</c:otherwise>
							</c:choose>
							<div class="pos-us-nam"><h2><c:out value="${utenteConCuiChattare.username}"/></h2>
							</div>
							<c:if test="${statoOnline eq true}"><span class="online-dot"></span></c:if>
						</div>
						<div class="mex-space" id="mexSpace">
							<c:if test="${listaMessaggi != null}">
								<c:forEach var="messaggio" items="${listaMessaggi}">
									<c:choose>
										<c:when test="${messaggio.utenteInviante != loggedUserId}">
											<div class="mexage-received">
												<div class="mexage-text-received">
													<c:out value="${messaggio.testo}"/>
													<br><small class="mexage-date">
														<c:out value="${messaggio.ora}"/>
													</small>
												</div></div>
										</c:when>
										<c:otherwise>
											<div class="mexage-sended">
												<div class="mexage-text-sended">
													<c:out value="${messaggio.testo}"/>
													<br><small class="mexage-date">
														<c:out value="${messaggio.ora}"/>
													</small>
												</div></div>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
						</div>
						<div class="invia-mex">
							<input type="text" placeholder="Messaggio" id="inviaBarDskt">
							<div><span class="invia-button"><a href="" id="inviaMexBt"><i class="bi bi-send"></i></a></span></div>
						</div>
					</div>
				</div>
			</div>
			<div class="grid-item spazio">
				
			</div>
			<div class="aUser-mob">
				<a href="#" onclick="history.back()"><i class="bi bi-arrow-left"></i></a>
				<c:choose>
					<c:when test="${utenteConCuiChattare.foto != null}">
						<img alt="Immagine" src="${utenteConCuiChattare.fotoPath}" class="img-exist-user">
					</c:when>
					<c:otherwise>
						<img class="img-user" alt="Immagine" src="/User">
					</c:otherwise>
				</c:choose>
				<div class="pos-us-nam"><h2><c:out value="${utenteConCuiChattare.username}"/></h2>
				</div>
				<c:if test="${statoOnline eq true}"><span class="online-dot"></span></c:if>
			</div>
			<div class="mex-space-mob" id="mexSpaceMob">
				<c:if test="${listaMessaggi != null}">
					<c:forEach var="messaggio" items="${listaMessaggi}">
						<c:choose>
							<c:when test="${messaggio.utenteInviante != loggedUserId}">
								<div class="mexage-received">
									<div class="mexage-text-received">
										<c:out value="${messaggio.testo}"/>
										<br><small class="mexage-date">
											<c:out value="${messaggio.ora}"/>
										</small>
									</div></div>
							</c:when>
							<c:otherwise>
								<div class="mexage-sended">
									<div class="mexage-text-sended">
										<c:out value="${messaggio.testo}"/>
										<br><small class="mexage-date">
											<c:out value="${messaggio.ora}"/>
										</small>
									</div></div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
			</div>
			<div class="invia-mex-mob">
				<input type="text" placeholder="Messaggio" id="inviaBar">
				<span class="invia-button"><a href="" id="inviaMexBtMob"><i class="bi bi-send"></i></a></span>
			</div>
		</div>
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
		<script type="text/javascript" src="../js/chat.js"></script>
	</body>
</html>