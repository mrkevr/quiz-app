package dev.mrkevr.quizapp.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.mrkevr.quizapp.api.exception.ResourceNotFoundException;
import dev.mrkevr.quizapp.api.model.Question;
import dev.mrkevr.quizapp.api.repository.QuestionRepository;
import dev.mrkevr.quizapp.api.service.QuestionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

	QuestionRepository questionRepo;

	@Override
	public List<Question> getByQuizId(String quizId) {
		return questionRepo.findByQuizId(quizId);
	}

	@Override
	public Question getById(String questionId) {
		return questionRepo.findById(questionId).orElseThrow(() -> new ResourceNotFoundException(questionId));
	}

	@Override
	public Question add(Question question) {
		try {
			return questionRepo.save(question);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}
	}
}
