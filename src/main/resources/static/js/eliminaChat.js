function deleteChat(idUtenteConCuiAbbiamoChattato, nomeUtenteConCuiAbbiamoChattato) {
	let elimina = confirm("Vuoi eliminare la chat con l'utente " + nomeUtenteConCuiAbbiamoChattato + "?");
	if (elimina == true) {
		let formData = new FormData();
		formData.append('idUtenteConCuiAbbiamoChattato', idUtenteConCuiAbbiamoChattato);
		let request = new XMLHttpRequest();
		let url = 'http://localhost:8081/eliminaChat';
		request.open('DELETE', url);
		request.send(formData);

		request.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				window.location.reload();
			}
		};
	}
}