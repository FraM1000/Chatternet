let stompClient;

Notification.requestPermission().then(permission => {
	if(permission === "granted"){
		let socket = new SockJS('/ws');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, () => {
			stompClient.subscribe("/user/" + loggedUserId + "/queue/messages", function (message) {
				message = JSON.parse(message.body);
				sendNotification(message);
			});
		});
	}
})

function sendNotification(message){
	let request = new XMLHttpRequest();
	let url = 'http://localhost:8081/ricercaUtentePerId?idUtente=' + message.utenteInviante;
	request.open('GET',url);
	request.send();
	request.onload = function() {
    	if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
    		let user = JSON.parse(request.responseText);
    		new Notification(user.username + " ti ha inviato un messaggio!", {
				body: message.testo,
				icon: "/user-photos/" + message.utenteInviante + "/" + user.foto,
			});
    	}
	}
}