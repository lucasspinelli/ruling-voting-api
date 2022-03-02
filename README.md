# Ruling voting api

## Requirements

* Git
* Java 11
* IntelliJ Community
* MySQL server

## DataBase

### MySQL
### Configuration : 
* First get mysql server [Here](https://dev.mysql.com/downloads/mysql/)
* Follow the installation and define a password, the standard is User : root Password : (empty) 
* After the installation the application will create database if it not exists

### !!Important 
- The database must be on the standard port 3306, if you want to change some config, you must change application.properties as well 

### Instalation
```shell script
$ git clone https://github.com/lucasspinelli/ruling-voting-api.git
$ cd ruling-voting-api
$ mvn clean package -Dmaven.test.skip
$ cd target 
$ java -jar {name of the file .jar generated}

```

- Clone this repository, and run it on your IDE, then you can test all routes with postman or insomnia

### Documentation: 
- To see all routes and the request body/params, after run application access http://localhost:8080/swagger-ui.html

### How to use 
* Step 1: You must register a new associate with a valid CPF
* * use route /api/{version}/associate/register
* Step 2: You must create some ruling that you can vote
* * use route /api/{version}/ruling/create it will return the session id linked in this ruligin
* Step 3: Now with your ruling created, you need to open a vote session, you can define a expiration time. The standart is 1min
* * use route /api/{version}/ruling/{session-id}/startSession
* Step 4: Vote! Access the vote route, input next to your CPF the vote and post it to your session.
* * use route /api/{version}/session/ruling/{ruling-id}/vote
* Step 4: You can follow the results in that session, you just need to make a get method on the sessio like: 
* * /api/{version}/session/{session-id}
* Vote formats in string:
 * * For yes: 'y', 'yes', 's', 'sim'
 * * for no: 'n', 'nao', 'no', 'não'

### Versioning 
* URI versioning - version the URI space using version indicators, if we need some upgrade, or use another methods for the paths, we can just change version, and for the same path, with diferent versions, we can have distincts results 
