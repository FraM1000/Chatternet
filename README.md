# What is Chatternet?
Chatternet is a web-based one-to-one messaging application which allows users to send private messages to each other.

<img width="157" alt="image" src="https://github.com/FraM1000/Chatternet/assets/88930554/60fa5e04-03ce-4732-9322-f41acf8d41ab">


This is a complete application that includes a form-based authentication built with Spring Security. In case of a successful login if the user is not an admin it is redirected to the homepage where all the chats are displayed, also a websocket connection is established between a client and a server and it is implemented thanks to Spring Websocket. A user can choose a chat and send a text message to another user, Since websocket is just a communication protocol, it doesn’t know how to send a message to a particular user. STOMP is a messaging protocol which is useful for these functionalities and that is used on the client. After the text message is sended it will be persisted in the MySQL Database.

## Features
* Login
* Registration
* Admin dashboard
* Homepage
* Profile page
* Search users
* Send messages

## Installation
Prerequisites:

* Java 8
* Maven
* MySQL database
* Run sqlScripts folder against the MySQL database


```bash
# This will get a copy of the project installed locally.
git clone https://github.com/FraM1000/Chatternet.git
cd Chatternet

# This will package the application as a standalone war.
mvn clean install

# This will run our application.
java -jar target/chatternet-0.0.1-SNAPSHOT.war
```
