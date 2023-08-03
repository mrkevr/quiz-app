package dev.mrkevr.quizapp.api.repository;

import dev.mrkevr.quizapp.api.model.Quiz;

public interface QuizMongoClientRepository {
	
	Quiz getRandom(String categoryId);
	
}
