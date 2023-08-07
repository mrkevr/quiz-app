package dev.mrkevr.quizapp.api.dto;

import java.util.Set;

import dev.mrkevr.quizapp.api.model.Difficulty;

public class QuizResponse {
	
	String quizId;

	String categoryId;
	
	String categoryName;

	String author;

	Difficulty difficulty;

	Set<String> questionIds;
}
