let chartButton = document.getElementById("chartButton");
let searchButton = document.getElementById("searchButton");
let chartIcon = document.getElementById("chartIcon");
let searchIcon = document.getElementById("searchIcon");

function displayChartView() {
	chartButton.className = "button-clicked";
	chartIcon.className = "bi bi-graph-up ic";
	searchButton.className = "button";
	searchIcon.className = "bi bi-search";
	document.styleSheets[1].cssRules[18].cssRules[0].style.removeProperty("display");
	document.styleSheets[1].cssRules[22].cssRules[0].style.removeProperty("display");
	document.styleSheets[1].cssRules[23].cssRules[0].style.removeProperty("display");
	document.styleSheets[1].cssRules[18].cssRules[1].style.setProperty("display", "none");
	document.styleSheets[1].cssRules[22].cssRules[1].style.setProperty("display", "none");
	document.styleSheets[1].cssRules[23].cssRules[1].style.setProperty("display", "none");
}

function displaySearchView() {
	chartButton.className = "button";
	chartIcon.className = "bi bi-graph-up";
	searchButton.className = "button-clicked";
	searchIcon.className = "bi bi-search ic";
	document.styleSheets[1].cssRules[18].cssRules[0].style.setProperty("display", "none");
	document.styleSheets[1].cssRules[22].cssRules[0].style.setProperty("display", "none");
	document.styleSheets[1].cssRules[23].cssRules[0].style.setProperty("display", "none");
	document.styleSheets[1].cssRules[18].cssRules[1].style.removeProperty("display");
	document.styleSheets[1].cssRules[22].cssRules[1].style.removeProperty("display");
	document.styleSheets[1].cssRules[23].cssRules[1].style.removeProperty("display");
}