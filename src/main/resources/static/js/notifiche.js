let stompClient;

Notification.requestPermission().then(permission => {
	if(permission === "granted"){
		let socket = new SockJS('/ws');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, () => {
			stompClient.subscribe("/user/" + loggedUserId + "/queue/messages", function (messaggio) {
				messaggio = JSON.parse(messaggio.body);
				inviaNotifica(messaggio);
			});
		});
	}
})

function inviaNotifica(messaggio){
	let request = new XMLHttpRequest();
	let url = 'http://localhost:8081/ricercaUtentePerId?idUtente=' + messaggio.utenteInviante;
	request.open('GET',url);
	request.send();
	request.onload = function() {
    	if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
    		let utente = JSON.parse(request.responseText);
    		new Notification(utente.username + " ti ha inviato un messaggio!", {
				body: messaggio.testo,
				icon: "/user-photos/" + messaggio.utenteInviante + "/" + utente.foto,
			});
    	}
	}
}