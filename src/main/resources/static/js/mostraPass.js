const occhio = document.getElementById("occhio");
occhio.addEventListener("click", mostraTesto);
  
function mostraTesto(){
const password = document.getElementById("password");
if(password.type === "password"){
 password.type = "text"
 occhio.classList.remove('bi-eye-slash');
 occhio.classList.add('bi-eye');
 } else{
  password.type = "password";
  occhio.classList.remove('bi-eye');
  occhio.classList.add('bi-eye-slash');
  }
 }