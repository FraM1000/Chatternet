let theChart = document.getElementById("theChart");
let pulsanteSettimana = document.getElementById("weeklyButton");
let pulsanteMese = document.getElementById("monthlyButton");
let pulsanteAnno = document.getElementById("yearlyButton");

let grafico = new Chart(theChart, {
	type: "line",
	data: {
		labels: asseXGraficoAnnuale,
		datasets: [{
			label: "Utenti iscritti",
			backgroundColor: "#1877f2",
			borderColor: "#1877f2",
			fill: false,
			data: asseYGraficoAnnuale
		}]
	},
	options: {
		scales: {
			yAxes: [{
				ticks: {
					beginAtZero: true
				}
			}]
		},
	}
});

pulsanteSettimana.addEventListener("click", () => {
	pulsanteSettimana.className = "button-clicked";
	pulsanteMese.className = "button";
	pulsanteAnno.className = "button";
	grafico.data.labels = asseXGraficoSettimanale;
	grafico.data.datasets[0].data = asseYGraficoSettimanale;
	grafico.update();
});

pulsanteMese.addEventListener("click", () => {
	pulsanteSettimana.className = "button";
	pulsanteMese.className = "button-clicked";
	pulsanteAnno.className = "button";
	grafico.data.labels = asseXGraficoMensile;
	grafico.data.datasets[0].data = asseYGraficoMensile;
	grafico.update();
});

pulsanteAnno.addEventListener("click", () => {
	pulsanteSettimana.className = "button";
	pulsanteMese.className = "button";
	pulsanteAnno.className = "button-clicked";
	grafico.data.labels = asseXGraficoAnnuale;
	grafico.data.datasets[0].data = asseYGraficoAnnuale;
	grafico.update();
});