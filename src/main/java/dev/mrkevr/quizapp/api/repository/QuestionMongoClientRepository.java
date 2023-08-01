package dev.mrkevr.quizapp.api.repository;

import java.util.List;

import dev.mrkevr.quizapp.api.model.Question;

public interface QuestionMongoClientRepository {

	List<Question> searchByKeyword(String keyword, String sort, long limit);

	List<Question> findRandom(String categoryId, int size);
	
	List<String> findRandomQuestionId(String categoryId, int size);
}
