# igor-taren-repo

Igor Taren's repository

## TASK#13

### _Hibernate_

#### Description:

### Task 13

Add the implementation of working with the database using hibernate to the project.

Technical requirements:
- The application comply with the principles of OOP and the patterns of "high cohesion" and "low coupling";
- apply patterns in development: mvc, dao;
- the application must have high-quality exception handling;
- errors should be logged to a file;
- transactions for working with the database must be processed qualitatively;
- the application must be embedded in the git in a separate branch with all configuration files, 
scripts for creating a database and filling it in;

All work with dependencies is done with the help of maven.

#### Stack

- java.version 11
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

#### cmd to application start

_mvn clean install exec:java -pl hotel-ui_ 