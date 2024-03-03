const eyeIcon = document.getElementById("occhio");
eyeIcon.addEventListener("click", showText);

function showText() {
	const password = document.getElementById("password");
	if (password.type === "password") {
		password.type = "text"
		eyeIcon.classList.remove('bi-eye-slash');
		eyeIcon.classList.add('bi-eye');
	} else {
		password.type = "password";
		eyeIcon.classList.remove('bi-eye');
		eyeIcon.classList.add('bi-eye-slash');
	}
}