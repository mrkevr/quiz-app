package dev.mrkevr.quizapp.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.mrkevr.quizapp.api.exception.ResourceNotFoundException;
import dev.mrkevr.quizapp.api.model.Quiz;
import dev.mrkevr.quizapp.api.repository.QuizRepository;
import dev.mrkevr.quizapp.api.service.QuizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

	QuizRepository quizRepo;

	@Override
	public List<Quiz> getAll() {
		return quizRepo.findAll();
	}

	@Override
	public Quiz getById(String quizId) {
		return quizRepo.findById(quizId).orElseThrow(() -> new ResourceNotFoundException(quizId));
	}

	@Override
	public Quiz save(Quiz quiz) {
		return quizRepo.save(quiz);
	}

	@Override
	public Quiz updateById(String id, Quiz quiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quiz> getAllByCategoryId(String categoryId) {
		return quizRepo.findByCategoryId(categoryId);
	}

	@Override
	public void deleteById(String quizId) {
		Quiz quiz = quizRepo.findById(quizId).orElseThrow(() -> new ResourceNotFoundException(quizId));
		quizRepo.delete(quiz);
	}

	@Override
	public List<Quiz> getAllByAuthor(String author) {
		return quizRepo.findByAuthorIgnoreCase(author);
	}

}
