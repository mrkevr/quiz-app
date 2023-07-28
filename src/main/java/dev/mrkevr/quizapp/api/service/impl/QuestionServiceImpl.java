package dev.mrkevr.quizapp.api.service.impl;

import java.util.ArrayList;
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
	public Question getById(String questionId) {
		return questionRepo.findById(questionId).orElseThrow(() -> new ResourceNotFoundException(questionId));
	}

	@Override
	public List<Question> getAllById(List<String> questionIds) {
		Iterable<Question> iterable = questionRepo.findAllById(questionIds);
		List<Question> questions = new ArrayList<>();
		iterable.forEach(questions::add);
		return questions;
	}
	
	@Override
	public List<Question> getAllByCategoryId(String categoryId) {
		List<Question> questions = questionRepo.findByCategoryId(categoryId);
		return questions;
	}

	@Override
	public Question add(Question question) {
		
		// validation logic here
		
		try {
			return questionRepo.save(question);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}
	}

	@Override
	public void deleteById(String questionId) {
		Question question = questionRepo.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException(questionId));
		questionRepo.delete(question);
	}

	@Override
	public Question update(String questionId, Question question) {
		Question questionToUpdate = questionRepo.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException(questionId));

		// logic here

		return null;
	}

	
}
