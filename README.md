# Single Rest Endpoint Demo
It is Spring Boot (Web, JPA, H2 database, and Springfox Swagger2) Project for demo purpose.


## Project Requirement
- JDK 1.8+
- Gradle 4+
- Spring Boot 2.0.4.RELEASE
- Springfox Swagger 2.8.0
- Jaxb api 2.3.0
- Any IDE (STS/Eclipse)


## Project Resources
- Web/Spring MVC (spring-boot-starter-web)
- JPA (spring-boot-starter-data-jpa)
- h2 database (h2)
- Swagger 2 (springfox-swagger2)
- Swagger UI (springfox-swagger-ui)
- Jaxb api (jaxb-api)


## Project Dependency in build.gradle
### Web is Spring MVC (required)
compile('org.springframework.boot:spring-boot-starter-web')
### JPA - Java Persistent API (required)
compile('org.springframework.boot:spring-boot-starter-data-jpa')
### H2 Database (required)
runtime('com.h2database:h2')
### Swagger is the library to build API Docs (optional)
compile('io.springfox:springfox-swagger2:2.8.0')
compile('io.springfox:springfox-swagger-ui:2.8.0')
### Jaxb api is the helper library to bind Swagger XML (optional)
compile('javax.xml.bind:jaxb-api:2.3.0')


## API Docs
http://localhost:8088/swagger-ui.html


## API Endpoints
### put /user/{id}


## Note
### Need to config in application.yml for JPA:
spring:
  jpa:
    hibernate:
      ddlAuto: update

### if not config, it will error like this:
org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL via JDBC Statement
...
Caused by: java.sql.SQLSyntaxErrorException: Schema 'SA' does not exist
...

### Why need Jaxb api? It will error like this if not add dependency:
o.s.boot.SpringApplication              [0;39m [2m:[0;39m Application run failed
Error creating bean with name 'xmlModelPlugin': Lookup method resolution failed;
Caused by: java.lang.IllegalStateException: Failed to introspect Class [springfox.documentation.schema.XmlModelPlugin] from ClassLoader [jdk.internal.loader.ClassLoaders$AppClassLoader@28c97a5]
