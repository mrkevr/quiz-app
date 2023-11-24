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
### There are four resource of the API :
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

## Swagger Documentation
```
http://localhost:8085/swagger-ui.html
```
[![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant)

[![](https://img.shields.io/badge/POST-green?style=for-the-badge)](https://github.com/hamzamohdzubair/redant)

[![](https://img.shields.io/badge/PUT-yellow?style=for-the-badge)](https://github.com/hamzamohdzubair/redant)

[![](https://img.shields.io/badge/DELETE-red?style=for-the-badge)](https://github.com/hamzamohdzubair/redant)


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
| [![](https://img.shields.io/badge/DELETE-red?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/{id}` | - | JSON | Delete an existing question by ID |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/category/{categoryId}` | - | - | Retrieve all questions by Category ID |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/quiz/{quizId}` | - | - | Retrieve all questions by Quiz ID |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/random` | size, categoryId | - | Retrieve random questions by Category ID |
| [![](https://img.shields.io/badge/GET-blue?style=for-the-badge)](https://github.com/hamzamohdzubair/redant) | `/api/questions/search` | keyword, limit, sort | - | Search questions using request parameners |






