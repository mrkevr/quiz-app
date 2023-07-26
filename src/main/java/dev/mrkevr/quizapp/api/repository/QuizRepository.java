package dev.mrkevr.quizapp.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.mrkevr.quizapp.api.model.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {

}
