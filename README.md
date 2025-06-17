# Introduction
Email Service is a Spring Boot application designed to send emails using Java Mail Sender and Thymeleaf templates. It provides a simple REST API to send emails with dynamic content.
The email service is implemented with two providers—SMTP and Mailgun.
The SMTP provider uses Java Mail Sender to send emails, while the Mailgun provider uses the Mailgun API to send emails.
you need to get your own smtp or mail gun account and update the application properties accordingly.

# Project Structure
The project follows a standard Maven structure with the following key components:

# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.email-service' is invalid and this project uses 'com.email_service' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.3/maven-plugin/build-image.html)
* [Java Mail Sender](https://docs.spring.io/spring-boot/3.4.3/reference/io/email.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.3/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.3/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Thymeleaf](https://docs.spring.io/spring-boot/3.4.3/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)

### Guides
The following guides illustrate how to use some features concretely:
you need to get your own smtp or mail gun account and update the application properties accordingly.

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

