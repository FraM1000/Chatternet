let stompClient;

window.onload = () => {
	let socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, () => {
		stompClient.subscribe("/user/" + loggedUserId + "/queue/messages", function (message) {
			message = JSON.parse(message.body);
			printReceivedMessages(message);
		});
	});
}

function printReceivedMessages(message){
	let date = formatDate(new Date(message.ora));
	const receivedMessageHtml = 
	`<div class="mexage-received">
	<div class="mexage-text-received">
	${message.testo}
	<br><small class="mexage-date">
	${date}
	</small>
	</div></div>` ;
	printMessagesOnWebpage(receivedMessageHtml);
}

document.getElementById('inviaMexBt').onclick = (e) => {
	e.preventDefault();
	let message = document.getElementById('inviaBarDskt').value;
	if (message.trim() !== "") {
		sendMessage(message);
		printSendedMessages(message);
		document.getElementById('inviaBarDskt').value = "";
	}
}

document.getElementById('inviaMexBtMob').onclick = (e) => {
	e.preventDefault();
	let message = document.getElementById('inviaBar').value;
	if (message.trim() !== "") {
		sendMessage(message);
		printSendedMessages(message);
		document.getElementById('inviaBar').value = "";
	}
}

function sendMessage(message) {
	var now = new Date();
	now.setHours(now.getHours() + 1);
	const theMessage = {
		text: message,
		time: now,
		sender: loggedUserId,
		receiver: userToChatWithId
	};
	stompClient.send("/app/chat", {}, JSON.stringify(theMessage));
}

function printSendedMessages(message){
	var now = new Date();
	now.setHours(now.getHours());
	let date = formatDate(now);
	const sendedMessageHtml = 
	`<div class="mexage-sended">
	<div class="mexage-text-sended">
	${message}
	<br><small class="mexage-date">
	${date}
	</small>
	</div></div>` ;
	printMessagesOnWebpage(sendedMessageHtml);
}

function formatDate(date){
	y = date.getFullYear();
	m = date.getMonth() + 1;
	d = date.getDate();
	h = date.getHours() + ':' + date.getMinutes() + ":" + date.getSeconds();
	return y + "-" + m + "-" + d + " " + h;
}

function printMessagesOnWebpage(HtmlMessage) {
	let desktopWebpage = document.getElementById('mexSpace');
	let mobileWebpage = document.getElementById('mexSpaceMob');
	let messageDiv = document.createElement('div');
	messageDiv.innerHTML = HtmlMessage;
	let mobileMessageDiv = document.createElement('div');
	mobileMessageDiv.innerHTML = HtmlMessage;
	desktopWebpage.append(messageDiv);
	mobileWebpage.append(mobileMessageDiv);
}