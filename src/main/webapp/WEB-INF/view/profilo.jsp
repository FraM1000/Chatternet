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
		<title>Chatternet | Profilo</title>
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
		<link rel="icon" type="image/x-icon" href="/Icona">
		<link rel="stylesheet" type="text/css" href="../css/profiloStyle.css">
		<link rel="stylesheet" type="text/css" href="../css/menuStyle.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	</head>
	<body>
		<script>
			const loggedUserId = '${loggedUserId}';
		</script>
		
		<div class="grid-container">
			<jsp:include page="/WEB-INF/view/components/menu.jsp"></jsp:include>
			<div class="grid-item profilo">
				<h1>Profilo</h1><br>
				<div class ="flexContainer">
					<br><br>
					<c:choose>
						<c:when test="${foto != null}">
							<img alt="Immagine Profilo" src="${foto}" class="imgImpProfile">
							<div class="delImgIcon"><a href="" onclick="delImage()"><i class="bi bi-x"></i></a></div>
						</c:when>
						<c:otherwise>
							<img class="imgProfile" alt="Immagine Profilo" src="/User"><br>
							<label class="mex" for="avatar"><i class="bi-camera-fill"> Imposta foto</i></label>
							<input onchange="insImage()" type="file" id="avatar" name="avatar" accept="image/png, image/jpeg">
						</c:otherwise>
					</c:choose>
					<h2><c:out value="${username}"/></h2>
					<h3><c:out value="${nome}"/> <c:out value="${cognome}"/></h3>
					<h3>Et&aacute: <c:out value="${eta}"/></h3>
					<a class="passButton" id="passButt">Modifica password</a>
					<p class="succMod"><c:if test="${passwordModificata eq true}">La password &eacute stata modificata con successo.</c:if></p>
				</div>
				<div class="overlay" id="pop">
					<div class="popup">
						<span id="closePopup"><i class="bi bi-x-lg"></i></span><br><br><br>
						<form class="fom" action="/modificaPassword" method="post">
							<p id="errMex"> </p><br>
							<input type="password" name="pass" placeholder="Nuova password" id="password1"><br>
							<input type="password" name="passFinale" placeholder="Digita nuovamente la password" id="password2"><br><br>
							<input onclick="return checkPassword()" type="submit" value="Modifica" class="modifica">
						</form>
					</div>
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
		<script type="text/javascript" src="../js/fotoProfilo.js"></script>
		<script type="text/javascript" src="../js/modificaPassword.js"></script>
		<script type="text/javascript" src="../js/notifiche.js"></script>
	</body>
</html>