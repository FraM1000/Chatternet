const button = document.getElementById("cercaUsBt");
button.addEventListener("click", search);

function search() {
	let user = document.getElementById("cercaUser").value;
	if (user == "" || user == " ") {
		return false;
	} else {
		button.href = "/cercaUtente?nomeUtente=" + user;
	}
}