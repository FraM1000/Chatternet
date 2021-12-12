<!DOCTYPE html>
<html lang="it-IT">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chatternet | Registrati</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
	<link rel="icon" type="image/x-icon" href="/Icona">
    <link rel="stylesheet" type="text/css" href="../css/registrazioneStyle.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
  </head>
  <body>
  <div class="grid-container">
  <div class="grid-item us">
  <div class="logo"><img src="/Icona" height="35px" width="45px">Chatternet</div>
  <div class="intro">Ti aiutiamo a rimanere in contatto con i tuoi amici.</div>
  </div>
  <div class="grid-item enter">
  <form class="fom" action="/">
  <input type="text" name="nome" placeholder="Nome" required>
  <input type="text" name="cognome" placeholder="Cognome" required><br>
  <input type="text" name="user" placeholder="Username" required>
  <div class="input-group"><input type="password" name="pass" placeholder="Password" required id="password">
  <div><span class="input-group-text"><i class="bi bi-eye-slash" id="occhio"></i></span></div>
  </div><br>
  <input type="date" name="dataNascita"
       value="2021-12-11"
       min="1940-01-01" max="2013-12-31" required>
  <input type="radio" id="uomo" value="uomo" name="sesso" required>
  <label for="uomo">Uomo</label>
  <input type="radio" id="donna" value="donna" name="sesso" required>
  <label for="donna">Donna</label><br><br>
  <input type="submit" value="Registrati" class="pulsanteReg">
  </form> 
  </div>
  <div class="grid-item footer">
  
  </div>
  </div>
   <script>
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
  </script>
	</body>
	</html>