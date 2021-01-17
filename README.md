# servicemanual-java

## About
Java Spring boot application recruitment test project required from the applicant when applying to software company called Etteplan More.
Application implements ackend API for Factory Device Maintenance management

## Application
Application project has been developed by using Eclipse IDE and the repository includes all project data also to enable easy import process. You may of course use any tool you like.

We are looking for long life-cycle application so we build it on Java 1.8 which currently has the longest support until December 2030. Using Java 11 didn't bring us any advantage and it anyway has support only to September 2026. No other LTS versions available.

Original version of the project was using older Spring Boot version 2.2.1 which was security risk and therefore was update to new version of 2.3.7. Also 2.3 gives more features to play with.
Newest available version 2.4 is currently in the beginning of the life-cycle so it should not be used right a way to ensure better working application.  

In test project H2 database was enabled and decided to move on with that to enable easy installation. Application loads devices history data into the memory on launch. Look for seeddata.csv for to see the seed data.

In a nutshell:
- Java 1.8
- Spring Boot 2.3.7
- H2 in memory database
- Lombok
- ModelMapper 2.3.9
- SpringDoc 1.2.32

## API description
Application provides simple API description with Swagger. 
Documentation can be accessed via this link http://localhost:8080/swagger-ui.html

## Security
Project requirements didn't include any kind of request to secure the application API endpoints or neither user management or API serving model. **CHECK THIS FROM THE CUSTOMER!! SECURITY RISK**