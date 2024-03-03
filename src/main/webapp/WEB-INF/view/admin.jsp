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
			let yearlyChartXAxis = "${yearlyChartDates}";
			yearlyChartXAxis = yearlyChartXAxis.replace('[', '').replace(']', '');
			yearlyChartXAxis = yearlyChartXAxis.split(",");
			let yearlyChartYAxis = "${yearlyChartRegisteredUsers}";
			yearlyChartYAxis = yearlyChartYAxis.replace('[', '').replace(']', '');
			yearlyChartYAxis = yearlyChartYAxis.split(",");
			let monthlyChartXAxis = "${monthlyChartDates}";
			monthlyChartXAxis = monthlyChartXAxis.replace('[', '').replace(']', '');
			monthlyChartXAxis = monthlyChartXAxis.split(",");
			let monthlyChartYAxis = "${monthlyChartRegisteredUsers}";
			monthlyChartYAxis = monthlyChartYAxis.replace('[', '').replace(']', '');
			monthlyChartYAxis = monthlyChartYAxis.split(",");
			let weeklyChartXAxis = "${weeklyChartDates}";
			weeklyChartXAxis = weeklyChartXAxis.replace('[', '').replace(']', '');
			weeklyChartXAxis = weeklyChartXAxis.split(",");
			let weeklyChartYAxis = "${weeklyChartRegisteredUsers}";
			weeklyChartYAxis = weeklyChartYAxis.replace('[', '').replace(']', '');
			weeklyChartYAxis = weeklyChartYAxis.split(",");
		</script> 
		
		<div class="grid-container">
			<div class="grid-item spaziotop">
			</div>
			<div class="grid-item panel">
				<div class="panel-top-bar">
					<a href="/logout"><i class="bi bi-box-arrow-left"></i></a>
					<h1>Bentornato, <c:out value="${loggedUserName}"/>!</h1>
				</div>
				<jsp:include page="/WEB-INF/view/components/chart.jsp"></jsp:include>
				<jsp:include page="/WEB-INF/view/components/search.jsp"></jsp:include>
				<div class="menu-mobile">
					<div class="menu-buttons">
						<a href="javascript:void(0);" onclick="displayChartView()" id="chartButton" class="button-clicked">
						<i class="bi bi-graph-up ic" id="chartIcon"></i>
						</a>
						<a href="javascript:void(0);" onclick="displaySearchView()" id="searchButton" class="button">
						<i class="bi bi-search" id="searchIcon"></i>
						</a>
					</div>
				</div>
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
		<script type="text/javascript" src="../js/chart.js"></script>
		<script type="text/javascript" src="../js/search-user-as-admin.js"></script>
		<script type="text/javascript" src="../js/admin-menu-mobile.js"></script>
	</body>
</html>											