# Spring Boot + Maven + MongoDB + React

* Requirements: docker  and docker-compose.

### Run Instructions
* Terminal to techdebt directory.
* Run "mvn clean package"
* Run "docker-compose up --build"
* Content will be on localhost:8080.
* MongoDB container creates volume in local "data" directory. Delete directory if you want to wipe data each run.
* Initial engineer load relies on content from "Engineers.csv" in src/main/resources.
* Static resources are served from src/main/resources/static
