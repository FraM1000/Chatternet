const pulsante = document.getElementById("cercaUsBt");
pulsante.addEventListener("click", cerca);

function cerca() {
	let utente = document.getElementById("cercaUser").value;
	if (utente == "" || utente == " ") {
		return false;
	} else {
		pulsante.href = "/admin/cercaUtente?nomeUtente=" + utente;
	}
}

function lockOrUnlockAUser(username, accountLockChoice) {
	let formData = new FormData();
	formData.append('username', username);
	formData.append('accountLockChoice', accountLockChoice);
	let request = new XMLHttpRequest();
	let url = 'http://localhost:8081/admin/lockOrUnlockAccount';
	request.open('PUT', url);
	request.send(formData);

	request.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			window.location.reload();
		}
	};
}