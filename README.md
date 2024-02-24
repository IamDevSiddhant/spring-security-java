# Spring Boot Security with JWT Authentication

This Spring Boot project demonstrates how to implement JWT (JSON Web Token) authentication in a secure Spring Boot application. It includes functionalities to create JWT tokens, verify tokens, and validate usernames from both the token and the database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Make sure you have the following installed:

- Java 8 or later
- Maven
- MySQL (or any other database of your choice)
- Postman (for testing APIs)

### Installation

1. Clone the repository:

```bash
git clone https://github.com/IamDevSiddhant/spring-security-java
mvn clean install
java -jar target/spring-security-java-0.0.1-SNAPSHOT.jar
```

### Usage
Once the application is up and running, you can interact with it using Postman or any other HTTP client.

### Endpoints

- <b>/products/add - It let's you add new User in SQL Database with encrypted password
- /products/authenticate - It will Create JWT Token using UserName & password, will generate token only if user's name & password is present in Db
- /products/findAll - First it will authenticate every user based on username & based on respective role of every user it will authorize user to access allocated resources to that user.
- /products/findbyid/** - Similar to findAll endpoint, only difference is it will fetch data based on product id as name suggests.</b>

