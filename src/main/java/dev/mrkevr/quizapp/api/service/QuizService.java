package dev.mrkevr.quizapp.api.service;

import java.util.List;

import dev.mrkevr.quizapp.api.model.Quiz;

public interface QuizService {

	List<Quiz> getAll();

	Quiz getById(String quizId);

	Quiz save(Quiz quiz);

	Quiz updateById(String id, Quiz quiz);

	void deleteById(String quizId);

	List<Quiz> getAllByAuthor(String author);

	List<Quiz> getAllByCategoryId(String categoryId);

}
