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

##!!Important 
- The database must be on the standart port 5432, if you want to change some config, you must change application.properties as well 

- Clone this repository, and run it on your IDE, then you can test all routes with postman or insomnia

##Documentation: 
- To see all routes and the request body/params, after run application access http://localhost:8080/swagger-ui.html
