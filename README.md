# CHECK-IN TO A SPECIALIST QUEUE APP

This is a Java Maven project that has backend features and the REST API, an application for Check-in to a specialist queue.
The project is developed using spring boot as the backend service, react as the frontend application and H2 as the database.
The backend was implemented with Spring Boot, the web server running is Tomcat and database runs on H2.
This project has all REST endpoints required for the frontend project SpecialistQueueApp_Front to run (In production).

## Libraries used
1. Spring Boot 2.4.4,
2. Hibernate,
3. Swagger API,
4. Project Lombok
5. Spring Security
6. JWT

## Installation
Running the Application Run Spring boot:

`mvn clean spring-boot:run`

Documentation API (Swagger) by accessing

`http://localhost:8080/swagger-ui.html`

Front end React.js application:
`https://github.com/deividasbarzdenis/SpecualistQueueApp_Front`

## H2 Console
`http://localhost:8080/h2` 
jdbc:h2:mem:specialistApp
Username: user
password: password

## Main Features
1. Specialist can be added by admin [in production],
2. Client can choose the specialist and register.
3. Client after registration get queue number and time left to visit specialist.
4. Client can delete reservation to specialist.
5. Specialist have to log in to the system.
6. Specialist can delete clients for queue also will pause queue (in production).
7. For all users it is available main monitoring screen where seen all queue number [shown first 5]. 