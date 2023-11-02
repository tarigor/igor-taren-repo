#### _igor-taren-repo_

# HOTEL SERVICE

### TASK#16

### _Spring MVC_

#### Description:

Task 16 (difficulty 10)

Modify the application from previous tasks to a web application.

Task requirements:

- For implementation use Spring MVC;
- Add DTO (Data transfer objects) to the project to describe controller responses;
- Responses to requests must be in JSON and/or XML format;
- The application must have a REST architecture (use the Postman tool for verification);
- All the requirements for the functionality of the application must be available in the controllers;
- Processing and issuing exceptions to the user should be implemented using @ExceptionHandler and @Controlleradvice;
- Rewrite previously implemented work with transactions via Hibernate to use the @Transactional annotation in the
  service layer;

Build the application in WAR and deploy it on Tomcat or Jetty.

#### Stack

- java.version 17
- maven.compiler.plugin.version 3.11.0
- maven.surefire.plugin.version 3.1.2
- maven.checkstyle.plugin.version 3.3.0
- maven.site.plugin 3.12.0
- lombok.version 1.18.22
- gson.version 2.10.1
- jackson.annotations.version 2.15.2
- jackson.core.version 2.15.2
- jackson.databind.version 2.15.2
- mysql.connector.version 8.1.0
- maven.surefire-plugin.version 3.1.2
- maven.compiler-plugin.version 3.11.0
- logback.core.version 1.4.11
- slf4j.api.version 2.0.9
- logback.classic.version 1.4.11
- maven.checkstyle.plugin.version 3.3.0
- maven.site.plugin.version 3.12.0
- hibernate.core 6.3.1.Final
- spring-boot-starter 3.1.5
- spring-boot-starter-data-jpa 3.1.5
- spring-boot-maven-plugin 3.1.5

#### cmd to application start

_mvn -pl hotel-ui spring-boot:run_ 