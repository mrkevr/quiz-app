package dev.mrkevr.quizapp.api.service;

import java.util.List;

import dev.mrkevr.quizapp.api.model.Question;

public interface QuestionService {

	Question getById(String questionId);

	Question add(Question question);

	void deleteById(String questionId);

	Question update(String questionId, Question question);
	
	List<Question> getAllById(List<String> questionIds);
	
	List<Question> getAllByCategoryId(String categoryId);
}
