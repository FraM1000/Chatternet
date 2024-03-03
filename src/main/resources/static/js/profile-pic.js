function insImage() {
	let file = document.getElementById("avatar");
	if (file.files.length == 0) {
		return false;
	} else {
		let formData = new FormData();
		formData.append('avatar', file.files[0]);
		let request = new XMLHttpRequest();
		let url = 'http://localhost:8081/inserisciFoto';
		request.open('PUT', url);
		request.send(formData);

		request.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				window.location.reload();
			}
		};
	}
}

function delImage() {
	let toDelete = confirm("Vuoi eliminare la tua foto profilo?");
	if (toDelete == true) {
		let request = new XMLHttpRequest();
		let url = 'http://localhost:8081/eliminaFoto';
		request.open('DELETE', url);
		request.send();

		request.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				window.location.reload();
			}
		};
	}
}