package dev.mrkevr.quizapp.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.mrkevr.quizapp.api.model.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {
	
	List<Question> findByCategoryId(String categoryId);
}
