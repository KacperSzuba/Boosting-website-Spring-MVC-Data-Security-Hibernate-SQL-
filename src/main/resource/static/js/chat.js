'use strict';

let dialogueForm = document.querySelector('#messageForm');

window.addEventListener('load', connect, true);
dialogueForm.addEventListener('submit', sendMessage, true);

let stompClient = null;
let name = '1234user';

function connect(event) {
    let socket = new SockJS('/websocketApp');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, connectionSuccess);

    event.preventDefault();
}

function connectionSuccess() {
    stompClient.subscribe('/topic/javainuse', onMessageReceived);
}

function sendMessage(event) {
    let messageContent = document.querySelector('#message').value.trim();

    if (messageContent && stompClient) {
        let chatMessage = {
            senderName : name,
            content : document.querySelector('#message').value
        };

        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        document.querySelector('#message').value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    let message = JSON.parse(payload.body);

    let messageWrapper = document.createElement('div');
    messageWrapper.classList.add('clearfix');

    let messageElement = document.createElement('p');

    messageElement.classList.add('your-message');

    let usernameElement = document.createElement('span');
    let usernameText = document.createTextNode(message.senderName);
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);

    let textElement = document.createElement('p');
    let messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    document.querySelector('#messages').appendChild(messageWrapper).appendChild(messageElement);
    document.querySelector('#messages').scrollTop = document.querySelector('#messages').scrollHeight;
}
