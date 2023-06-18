<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="search-panel">
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
						<c:choose>
							<c:when test="${utente.foto != null}">
								<img class="img-exist-user" alt="Immagine Profilo" src="${utente.fotoPath}">
							</c:when>
							<c:otherwise>
								<img class="img-user" alt="Immagine Profilo" src="/User">
							</c:otherwise>
						</c:choose>
						<h2><c:out value="${utente.username}"/></h2>
						<c:choose>
							<c:when test="${utente.accountBloccato == true}">
								<a href="javascript:void(0);" onclick="lockOrUnlockAUser('${utente.username}', 'N')" class="sblocca-button">Sblocca</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" onclick="lockOrUnlockAUser('${utente.username}', 'Y')" class="blocca-button">Blocca</a>
							</c:otherwise>
						</c:choose>
					</div><br>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</div>