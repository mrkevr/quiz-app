# QUIZ REST API
![quiz_rest_api_thumbnail](https://github.com/mrkevr/quiz-app/assets/98044708/d95dd1f3-4010-4505-a3e6-8fb2e74f4035)
This API allows you to manage your quizzes and questions. The API is built using Spring framework and uses MongoDB as its database.

- [Introduction](#introduction)
- [Dependencies](#dependencies)
- [How To Run](#how-to-run)
- [Swagger Documentation](#swagger-documentation)
- [Endpoints](#endpoints)

## Introduction
Welcome to the Quiz App REST API, a robust and flexible solution for managing questions and quizzes built using Spring Boot. This project aims to provide a seamless and efficient platform for creating, storing, and retrieving quiz-related data through a RESTful API.
### This API has four resources :
| Resource | |
|---|---|
| Categories | Endpoints for managing categories. Every question has a category based on their subject. |
| Questions| Manages quesions. A question has three parts : question, the four choices and the correct answer. |
| Quizzes| Endpoints that manages quizzes. A quiz is a set of questions that share a common category. |
| Rankings| You can use these endpoints to view the ranking of each category. Rankings are automatically updated when a user submits their quiz. |

## Dependencies
| Dependency | |
| ------------- | ------------- |
| [Spring Boot](https://spring.io/projects/spring-boot) | Spring Boot is an open source Java-based framework used to create microservices. This serves as the backbone of the API. |
| [MongoDB](https://www.mongodb.com) | MongoDB is a widely used, open-source NoSQL database management system that falls under the category of document-oriented databases. It stores data in flexible, JSON-like documents, allowing for dynamic and schema-free data models. |
| [Swagger](https://swagger.io) | An open-source framework that simplifies the design, documentation, and testing of RESTful APIs. |

## How To Run
1. Clone the repository
2. For building and running the project, you need to have [Java 17](https://www.oracle.com/java/technologies/downloads/#java8](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)) and [Maven 3](https://maven.apache.org/download.cgi) installed on your computer
3. Go to `quiz-app/src/main/resources/application.yml` and configure your MongoDB credentials
```
spring :
  data :
    mongodb :
      uri : <your-uri-and-credentials-here>
      database : <your-database>
```
4. Build the project by running mvn clean package
```
C:\Your\Directory\quiz-app>mvn clean package
...
...
...
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  10.654 s
[INFO] Finished at: 2023-11-11T22:23:52+08:00
[INFO] ------------------------------------------------------------------------
```
5. Once successfully built, you can run the jar file using one of these commands :
```
java -jar quiz-app-0.0.1-SNAPSHOT.jar 
java -jar quiz-app-0.0.1-SNAPSHOT.jar --server.port=YourPort
```
> [!NOTE]  
> The application will run on port 8085 if not defined in the command or not changed on the config file

6. Once the application runs you should see something like this :
```
2023-11-11T14:33:27.371+08:00  INFO 7736 --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8085 (http) with context path ''
2023-11-11T14:33:27.390+08:00  INFO 7736 --- [main] d.m.q.Application          : Started Application in 2.722 seconds (process running for 3.218)
```

## Swagger Documentation
```
http://localhost:8085/swagger-ui.html
```

## Endpoints
### Category
| Method | URI | Description |
| ------------- | ------------- | ------------- |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/categories` | Retrieve all categories |
| [![](https://img.shields.io/badge/POST-green?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/categories` | Create a new category |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/categories/{id}` | Retrieve a specific category by ID |

### Question
| Method | URI | Request Parameters | Request Body | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions` | limit, page | - | Retrieve all questions |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/{id}` | - | - | Retrieve a specific question by ID |
| [![](https://img.shields.io/badge/POST-green?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions` | - | JSON | Create a question |
| [![](https://img.shields.io/badge/PUT-yellow?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/{id}` | - | JSON | Update an existing question by ID |
| [![](https://img.shields.io/badge/DELETE-red?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/{id}` | - | - | Delete an existing question by ID |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/category/{categoryId}` | - | - | Retrieve all questions by Category ID |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/quiz/{quizId}` | - | - | Retrieve all questions by Quiz ID |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/random` | size, categoryId | - | Retrieve random questions by Category ID |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/search` | keyword, limit, sort | - | Search questions using request parameners |

### Quiz
| Method | URI | Request Parameters | Request Body | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/quizzes` | - | - | Retrieve all quizzes |
| [![](https://img.shields.io/badge/POST-green?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/quizzes` | - | JSON | Create a quiz |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/quizzes{id}` | - | - | Retrieve a specific quiz by ID |
| [![](https://img.shields.io/badge/DELETE-red?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/quizzes/{id}` | - | - | Delete an existing quiz by ID |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/quizzes/category/{categoryId}` | - | - | Retrieve all quizzes by Category ID |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/quizzes/random` | categoryId | - | Retrieve a random question by Category ID |
| [![](https://img.shields.io/badge/POST-green?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/quizzes/generate` | author, categoryId, size | - | Generate a quiz by Category ID and item size |
| [![](https://img.shields.io/badge/POST-green?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/quizzes/check` | - | JSON | Get the result and update the rankings |




















