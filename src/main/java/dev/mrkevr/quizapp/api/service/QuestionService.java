package dev.mrkevr.quizapp.api.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import dev.mrkevr.quizapp.api.dto.QuestionRequest;
import dev.mrkevr.quizapp.api.dto.QuestionResponse;

public interface QuestionService {
	
	List<QuestionResponse> getAll(PageRequest pageRequest);
	
	QuestionResponse getById(String questionId);

	QuestionResponse add(QuestionRequest question);

	void deleteById(String questionId);

	QuestionResponse update(String questionId, QuestionRequest questionRequest);

	List<QuestionResponse> getAllById(List<String> questionIds);

	List<QuestionResponse> getAllByCategoryId(String categoryId);

	List<QuestionResponse> search(String keyword, String sort, long limit);
	
	List<QuestionResponse> getRandom(String categoryId, int size);
}
