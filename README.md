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

_mvn exec:java -pl hotel-ui_ 