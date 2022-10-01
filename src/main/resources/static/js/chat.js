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
	let data = formattaData(messaggio.ora);
	const messaggioRicevutoHtml = 
	`<div class="mexageReceived">
	<div class="mexageTextReceived">
	${messaggio.testo}
	<br><small class="mexageDate">
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
	}
}

document.getElementById('inviaMexBtMob').onclick = (e) => {
	e.preventDefault();
	let messaggio = document.getElementById('inviaBar').value;
	if (messaggio.trim() !== "") {
		inviaMessaggio(messaggio);
		mostraMessaggiInviati(messaggio);
	}
}

function inviaMessaggio(messaggio) {
	var now = new Date();
	now.setHours(now.getHours() + 2);
	const ilMessaggio = {
		testo: messaggio,
		ora: now,
		utenteInviante: loggedUserId,
		utenteRicevente: userToChatWithId
	};
	stompClient.send("/app/chat", {}, JSON.stringify(ilMessaggio));
}

function mostraMessaggiInviati(messaggio){
	let data = formattaData(new Date());
	const messaggioInviatoHtml = 
	`<div class="mexageSended">
	<div class="mexageTextSended">
	${messaggio}
	<br><small class="mexageDate">
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