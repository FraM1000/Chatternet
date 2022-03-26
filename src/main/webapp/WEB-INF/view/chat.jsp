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
  <a href=""><i class="bi bi-arrow-left"></i></a>
  <img class="imgUser" alt="Immagine" src="/User">
  <div class="posUsNam"><h2>Username</h2>
  </div>
  </div>
  <div class="mexSpace">
  
  </div>
  <div class="inviaMex">
  <input type="text" placeholder="Messaggio" id="inviaBar">
  <div><span class="inviaButton"><a href="" id="inviaMexBt"><i class="bi bi-send"></i></a></span></div>
  </div>
  </div>
  </div>
  </div>
  <div class="grid-item spazio">
  
  </div>
  <div class="aUserMob">
  <a href=""><i class="bi bi-arrow-left"></i></a>
  <img class="imgUser" alt="Immagine" src="/User">
  <div class="posUsNam"><h2>Username</h2>
  </div>
  </div>
  <div class="mexSpaceMob">
   
  </div>
  <div class="inviaMexMob">
  <input type="text" placeholder="Messaggio" id="inviaBar">
  <span class="inviaButton"><a href="" id="inviaMexBt"><i class="bi bi-send"></i></a></span>
  </div>
  </div>
 
  </body>
</html>