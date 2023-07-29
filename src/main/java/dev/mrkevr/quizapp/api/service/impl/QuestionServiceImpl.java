package dev.mrkevr.quizapp.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.mrkevr.quizapp.api.dto.QuestionRequest;
import dev.mrkevr.quizapp.api.dto.QuestionResponse;
import dev.mrkevr.quizapp.api.exception.ResourceNotFoundException;
import dev.mrkevr.quizapp.api.model.Question;
import dev.mrkevr.quizapp.api.model.QuestionMapper;
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
	QuestionMapper questionMapper;

	@Override
	public QuestionResponse getById(String questionId) {
		Question question = questionRepo.findById(questionId).orElseThrow(() -> new ResourceNotFoundException(questionId));
		return  questionMapper.toQuestionResponse(question);
	}

	@Override
	public List<QuestionResponse> getAllById(List<String> questionIds) {
		Iterable<Question> iterable = questionRepo.findAllById(questionIds);
		List<Question> questions = new ArrayList<>();
		iterable.forEach(questions::add);
		return questionMapper.toQuestionResponse(questions);
	}
	
	@Override
	public List<QuestionResponse> getAllByCategoryId(String categoryId) {
		List<Question> questions = questionRepo.findByCategoryId(categoryId);
		return questionMapper.toQuestionResponse(questions);
	}

	@Override
	public QuestionResponse add(QuestionRequest questionRequest) {
		
		// validation logic here
		
		
		try {
			Question savedQuestion = questionRepo.save(questionMapper.toQuestion(questionRequest));
			return questionMapper.toQuestionResponse(savedQuestion);
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
	public QuestionResponse update(String questionId, Question question) {
		Question questionToUpdate = questionRepo.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException(questionId));

		// logic here

		return null;
	}

	
}
