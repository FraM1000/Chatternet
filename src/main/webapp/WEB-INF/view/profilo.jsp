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
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
  </head>
  <body>
  <div class="grid-container">
  <div class="grid-item menu">
  <a href="/logout"><i class="bi bi-box-arrow-left"></i></a>
  <a href=""><i class="bi bi-search"></i></a>
  <a href=""><i class="bi bi-chat"></i></a>
  <a href="/paginaProfilo"><i class="bi bi-person"></i></a>
  </div>
  <div class="grid-item profilo">
  <h1>Profilo</h1><br>
  <div class ="flexContainer">
  <br><br>
  <c:choose>
  <c:when test="${foto != null}">
  <img alt="Immagine Profilo" src="${foto}" class="imgImpProfile"><br>
  </c:when>
  <c:otherwise>
  <img class="imgProfile" alt="Immagine Profilo" src="/User"><br>
  </c:otherwise>
  </c:choose>
  <label class="mex" for="avatar"><i class="bi-camera-fill"> Imposta foto</i></label>
  <input onchange="insImage()" type="file" id="avatar" name="avatar" accept="image/png, image/jpeg">
  <h2><c:out value="${username}"/></h2>
  <h3><c:out value="${nome}"/> <c:out value="${cognome}"/></h3>
  <h3>Et&aacute: <c:out value="${eta}"/></h3>
  </div>
  </div>
  <div class="grid-item spazio">
  
  </div>
  <div class="grid-item altroSpazio">
  
  </div>
  <div class="grid-item footer">
  
  </div>
  </div>
  
  <script>
  function insImage(){
  let file = document.getElementById("avatar");
  if(file.files.length == 0){
  return false;
  }else{
  let formData = new FormData();
  formData.append('avatar',file.files[0]);
  let request = new XMLHttpRequest();
  let url = 'http://localhost:8081/inserisciFoto';
  request.open('POST',url);
  request.send(formData);
  
  request.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        window.location.reload();
       }
     };
  }
  }
  </script>
	</body>
	</html>