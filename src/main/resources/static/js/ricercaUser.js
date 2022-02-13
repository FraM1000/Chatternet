const pulsante = document.getElementById("cercaUsBt");
pulsante.addEventListener("click", cerca);

function cerca() {
	let utente = document.getElementById("cercaUser").value;
	if (utente == "" || utente == " ") {
		return false;
	} else {
		pulsante.href = "/cercaUtente?nomeUtente=" + utente;
	}
}