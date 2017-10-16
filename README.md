## Microservices SP - Java Projects

Main structure for Microservices projects implemented in Java, using Maven and Java 8.

[TOC]

### Documentation API

Simple API to help documenting REST endpoints with Swagger. Contains abstractions that aim to ease the process of creating good quality and durable documents for REST services.

For more information about Swagger, check [here](https://swagger.io).

### Samples

In this structure we have all samples presented or created by community during our Meetups, in order to demonstrate the concepts, libraries usage or other important topics for real-life projects.

> Depends on Documentation API

*Folder structure:*

	├── samples
    |   └── sample-spring (Spring REST documented example)

##### Sample Spring Boot Project
Example of a Spring Boot application with one REST Service, documented with Swagger.

- [ ] Build: mvn clean install -U
- [ ] Run 01: mvn spring-boot:run OU java -jar ./target/sample-spring-0.9.jar
- [ ] Check Swagger JSON at: http://localhost:8080/doc
- [ ] Check Swagger Interface at: http://localhost:8080/swagger-ui.html
- [ ] Sample Endpoint 01 (GET): http://localhost:8080/entity/action
- [ ] Sample Endpoint 02 (POST): http://localhost:8080/entity/action_accepted

** **