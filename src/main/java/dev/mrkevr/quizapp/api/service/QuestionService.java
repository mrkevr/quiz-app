package dev.mrkevr.quizapp.api.service;

import java.util.List;

import dev.mrkevr.quizapp.api.model.Question;

public interface QuestionService {
	
	 Question getById(String questionId);
	 
	 List<Question> getByQuizId(String quizId);
	 
	 Question add(Question question);
}
