const button = document.getElementById("cercaUsBt");
button.addEventListener("click", search);

function search() {
	let user = document.getElementById("cercaUser").value;
	if (user == "" || user == " ") {
		return false;
	} else {
		button.href = "/admin/cercaUtente?nomeUtente=" + user;
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