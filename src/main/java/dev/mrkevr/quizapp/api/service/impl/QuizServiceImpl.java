package dev.mrkevr.quizapp.api.service.impl;

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
	public Quiz getById(String quizId) {
		return quizRepo.findById(quizId).orElseThrow(() -> new ResourceNotFoundException(quizId));
	}
}
