package dev.mrkevr.quizapp.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.mrkevr.quizapp.api.model.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {
	
	List<Quiz> findByAuthorIgnoreCase(String author);
	
	List<Quiz> findByCategoryId(String categoryId);
}
