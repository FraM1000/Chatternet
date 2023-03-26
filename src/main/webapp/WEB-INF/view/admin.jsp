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
		<link rel="stylesheet" type="text/css" href="../css/admin-style.css">
		<link rel="stylesheet" type="text/css" href="../css/chart-component.css">
		<link rel="stylesheet" type="text/css" href="../css/search-component.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	</head>
	<body>
		<script>
			const asseXGraficoAnnuale = ['Gennaio','Febbraio','Marzo','Aprile','Maggio','Giugno','Luglio','Agosto','Settembre','Ottobre','Novembre','Dicembre'];
			const asseYGraficoAnnuale = [30,50,40,30,50,80,60,130,80,80,90,120];
		
			const asseXGraficoMensile = ['01 Mar','02 Mar','03 Mar','04 Mar','05 Mar','06 Mar','07 Mar','08 Mar','09 Mar','10 Mar','11 Mar','12 Mar','13 Mar','14 Mar','15 Mar','16 Mar','17 Mar','18 Mar','19 Mar','20 Mar','21 Mar','22 Mar','23 Mar','24 Mar','25 Mar','26 Mar','27 Mar','28 Mar','29 Mar','30 Mar'];
			const asseYGraficoMensile = [2,1,1,0,3,2,3,1,2,2,2,1,4,3,2,0,1,0,2,1,3,1,2,2,3,4,2,1,1,2];
		
			const asseXGraficoSettimanale = ['01 Lun','02 Mar','03 Mer','04 Gio','05 Ven','06 Sab','07 Dom'];
			const asseYGraficoSettimanale = [2,1,1,0,3,2,3];
		</script> 
		
		<div class="grid-container">
			<div class="grid-item spaziotop">
			</div>
			<div class="grid-item panel">
				<div class="panel-top-bar">
					<a href="/logout"><i class="bi bi-box-arrow-left"></i></a>
					<h1>Bentornato, Francesco!</h1>
				</div>
				<jsp:include page="/WEB-INF/view/components/chart.jsp"></jsp:include>
				<jsp:include page="/WEB-INF/view/components/search.jsp"></jsp:include>
			</div>
			<div class="grid-item spaziosx">
			</div>
			<div class="grid-item spaziodx">
			</div>
			<div class="grid-item footer">
			</div>
		</div>  
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.23.0/moment.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
		<script type="text/javascript" src="../js/grafico.js"></script>
	</body>
</html>											