#### _igor-taren-repo_

# HOTEL SERVICE

### TASK#14

### _Spring Framework_

#### Description:

Task 14.1 (difficulty 4)

Replace your (current) Dependency Injection implementation with DI using the Spring Framework (spring-context module).

- Use any of the configuration methods;
- Make sure that repositories and services exist each in a single instance;
- Configure PropertySourcesPlaceholderConfigurer to embed parameters into beans
  from configuration files using the @Value annotation.

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
- hibernate.core 6.3.1.Final

#### cmd to application start

_mvn clean install exec:java -pl hotel-ui_ 