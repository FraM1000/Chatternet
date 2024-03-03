let button = document.getElementById("passButt");
button.addEventListener("click", () => {
	document.getElementById("pop").style.display = 'block';
});

let closeButton = document.getElementById("closePopup");
closeButton.addEventListener("click", () => {
	document.getElementById("pop").style.display = 'none';
});

function checkPassword() {
	let password1 = document.getElementById("password1").value;
	let password2 = document.getElementById("password2").value;
	if (password1 != password2) {
		document.getElementById("errMex").innerHTML = "Le password inserite non sono uguali";
		return false;
	}
}