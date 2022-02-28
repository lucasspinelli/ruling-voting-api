# Ruling voting api

## Requirements

* Git
* Java 11
* IntelliJ Community
* Postgres

## DataBase

### Postgres
### Configuration : 
* Firts get postgres [Here](https://www.postgresql.org/download/)
* Folow the instalation and define a password, the standart is User : postgres Password : postgres
* After the instalation open pgAdmin4 
* Input your password
* Right click on "Servers" -> "Create" 
* Input the name of "voteapi" (without quotes)
* It's done, the database is now ready for running aplication 

### !!Important 
- The database must be on the standart port 5432, if you want to change some config, you must change application.properties as well 

- Clone this repository, and run it on your IDE, then you can test all routes with postman or insomnia

### Documentation: 
- To see all routes and the request body/params, after run application access http://localhost:8080/swagger-ui.html

### How to use 
* Step 1: You must register a new associate with a valid CPF
* * use route /api/{version}/associate/register
* Step 2: You must to create some ruling that you can vote
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
