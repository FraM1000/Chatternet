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
  <a href="/profilo"><i class="bi bi-person"></i></a>
  </div>
  <div class="grid-item profilo">
  <h1>Profilo</h1><br>
  <div class ="flexContainer">
  <br><br>
  <img class="imgProfile" alt="Immagine Profilo" src="/User"><br>
  <label class="mex" for="avatar"><i class="bi-camera-fill"> Imposta foto</i></label>
  <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg">
  <h2>Username</h2>
  <h3>Nome Cognome</h3>
  <h3>Età: 18</h3>
  </div>
  </div>
  <div class="grid-item spazio">
  
  </div>
  <div class="grid-item altroSpazio">
  
  </div>
  <div class="grid-item footer">
  
  </div>
  </div>
	</body>
	</html>