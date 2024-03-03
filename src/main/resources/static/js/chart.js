let theChart = document.getElementById("theChart");
let weeklyButton = document.getElementById("weeklyButton");
let monthlyButton = document.getElementById("monthlyButton");
let yearlyButton = document.getElementById("yearlyButton");

let chart = new Chart(theChart, {
	type: "line",
	data: {
		labels: yearlyChartXAxis,
		datasets: [{
			label: "Utenti iscritti",
			backgroundColor: "#1877f2",
			borderColor: "#1877f2",
			fill: false,
			data: yearlyChartYAxis
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

weeklyButton.addEventListener("click", () => {
	weeklyButton.className = "button-clicked";
	monthlyButton.className = "button";
	yearlyButton.className = "button";
	chart.data.labels = weeklyChartXAxis;
	chart.data.datasets[0].data = weeklyChartYAxis;
	chart.update();
});

monthlyButton.addEventListener("click", () => {
	weeklyButton.className = "button";
	monthlyButton.className = "button-clicked";
	yearlyButton.className = "button";
	chart.data.labels = monthlyChartXAxis;
	chart.data.datasets[0].data = monthlyChartYAxis;
	chart.update();
});

yearlyButton.addEventListener("click", () => {
	weeklyButton.className = "button";
	monthlyButton.className = "button";
	yearlyButton.className = "button-clicked";
	chart.data.labels = yearlyChartXAxis;
	chart.data.datasets[0].data = yearlyChartYAxis;
	chart.update();
});