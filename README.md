# What is Chatternet?
Chatternet is a web-based one-to-one messaging application which allows users to send private messages to each other.

<img width="157" alt="image" src="https://github.com/FraM1000/Chatternet/assets/88930554/41562b25-f554-4c2d-b151-22c652f0ffa9">

## Installation
***Prerequisites:***
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
