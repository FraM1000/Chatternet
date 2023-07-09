let stompClient;

window.onload = () => {
	let socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, () => {
		stompClient.subscribe("/user/" + loggedUserId + "/queue/messages", function (messaggio) {
			messaggio = JSON.parse(messaggio.body);
			mostraMessaggiRicevuti(messaggio);
		});
	});
}

function mostraMessaggiRicevuti(messaggio){
	let data = formattaData(new Date(messaggio.ora));
	const messaggioRicevutoHtml = 
	`<div class="mexage-received">
	<div class="mexage-text-received">
	${messaggio.testo}
	<br><small class="mexage-date">
	${data}
	</small>
	</div></div>` ;
	stampaMessaggiInPagina(messaggioRicevutoHtml);
}

document.getElementById('inviaMexBt').onclick = (e) => {
	e.preventDefault();
	let messaggio = document.getElementById('inviaBarDskt').value;
	if (messaggio.trim() !== "") {
		inviaMessaggio(messaggio);
		mostraMessaggiInviati(messaggio);
		document.getElementById('inviaBarDskt').value = "";
	}
}

document.getElementById('inviaMexBtMob').onclick = (e) => {
	e.preventDefault();
	let messaggio = document.getElementById('inviaBar').value;
	if (messaggio.trim() !== "") {
		inviaMessaggio(messaggio);
		mostraMessaggiInviati(messaggio);
		document.getElementById('inviaBar').value = "";
	}
}

function inviaMessaggio(messaggio) {
	var now = new Date();
	now.setHours(now.getHours() + 1);
	const ilMessaggio = {
		testo: messaggio,
		ora: now,
		utenteInviante: loggedUserId,
		utenteRicevente: userToChatWithId
	};
	stompClient.send("/app/chat", {}, JSON.stringify(ilMessaggio));
}

function mostraMessaggiInviati(messaggio){
	var now = new Date();
	now.setHours(now.getHours());
	let data = formattaData(now);
	const messaggioInviatoHtml = 
	`<div class="mexage-sended">
	<div class="mexage-text-sended">
	${messaggio}
	<br><small class="mexage-date">
	${data}
	</small>
	</div></div>` ;
	stampaMessaggiInPagina(messaggioInviatoHtml);
}

function formattaData(data){
	y = data.getFullYear();
	m = data.getMonth() + 1;
	d = data.getDate();
	h = data.getHours() + ':' + data.getMinutes() + ":" + data.getSeconds();
	return y + "-" + m + "-" + d + " " + h;
}

function stampaMessaggiInPagina(messaggio) {
	let paginaMessaggiDesktop = document.getElementById('mexSpace');
	let paginaMessaggiMobile = document.getElementById('mexSpaceMob');
	let divMessaggio = document.createElement('div');
	divMessaggio.innerHTML = messaggio;
	let divMessaggioMob = document.createElement('div');
	divMessaggioMob.innerHTML = messaggio;
	paginaMessaggiDesktop.append(divMessaggio);
	paginaMessaggiMobile.append(divMessaggioMob);
}