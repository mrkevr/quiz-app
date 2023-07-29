package dev.mrkevr.quizapp.api.service;

import java.util.List;

import dev.mrkevr.quizapp.api.dto.QuestionRequest;
import dev.mrkevr.quizapp.api.dto.QuestionResponse;
import dev.mrkevr.quizapp.api.model.Question;

public interface QuestionService {

	QuestionResponse getById(String questionId);

	QuestionResponse add(QuestionRequest question);

	void deleteById(String questionId);

	QuestionResponse update(String questionId, Question question);
	
	List<QuestionResponse> getAllById(List<String> questionIds);
	
	List<QuestionResponse> getAllByCategoryId(String categoryId);
}
