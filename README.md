# Qiuz REST API
This API allows you to manage your quizzes and questions. The API is built using RESTful principles.

- [Introduction](#introduction)
- [Dependencies](#dependencies)
- [How To Run](#how-to-run)
- [Swagger Documentation](#swagger-documentation)
- [Endpoints](#endpoints)

## Introduction
Welcome to the Quiz App REST API, a robust and flexible solution for managing questions and quizzes built using Spring Boot. This project aims to provide a seamless and efficient platform for creating, storing, and retrieving quiz-related data through a RESTful API.
### There are four resource of the API :
| Resource | Description |
|---|---|
| Categories | Endpoints for managing categories. Every question has a category based on their subject. |
| Questions| Manages quesions. A question has three parts : question, the four choices and the correct answer. |
| Quizzes| Endpoints that manages quizzes. A quiz is a set of questions that share a common category. |
| Rankings| You can use these endpoints to view the ranking of each category. Rankings are automatically updated when a user submits their quiz. |
