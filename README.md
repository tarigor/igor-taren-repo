# igor-taren-repo

Igor Taren's repository

## TASK#12

### _Maven and Logging_

#### Description:

### Task 12.1

Transfer the main project to Maven. The project should have a modular structure.

### Task 12.2

Сonnect any logging library and configure logging in the project as a dependency (logback, log4j2)

### Task 12.3

Add a plugin to the project assembly that checks code style. Configure it (including linking the plugin to
the execution phase). Recommendations for choosing a plugin: maven-checkstyle-plugin, checkstyle.

(https://maven.apache.org/plugins/maven-checkstyle-plugin/usage.html)

Config files can be taken here:

https://drive.google.com/open?id=1UgfDCZDNINSsoBgjn65bgIdYPZLGCU9G

https://drive.google.com/open?id=1KbO2r4FEEUcKfwq7cKeaOMvYSMv8FiCN

#### Stack

- Java 11;
- Lombok 1.18.26;
- gson 2.10.1;
- jackson-annotations-2.15.2;
- jackson-core-2.15.2;
- jackson-databind-2.15.2;
- mysql-connector-java-8.0.25

#### cmd to application start

_mvn exec:java -pl hotel-ui_ 