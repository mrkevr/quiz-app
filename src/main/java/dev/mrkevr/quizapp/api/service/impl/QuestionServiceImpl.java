package dev.mrkevr.quizapp.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.mrkevr.quizapp.api.dto.QuestionRequest;
import dev.mrkevr.quizapp.api.dto.QuestionResponse;
import dev.mrkevr.quizapp.api.exception.InvalidRequestException;
import dev.mrkevr.quizapp.api.exception.ResourceNotFoundException;
import dev.mrkevr.quizapp.api.mapper.QuestionMapper;
import dev.mrkevr.quizapp.api.model.Category;
import dev.mrkevr.quizapp.api.model.Question;
import dev.mrkevr.quizapp.api.repository.CategoryRepository;
import dev.mrkevr.quizapp.api.repository.QuestionMongoClientRepository;
import dev.mrkevr.quizapp.api.repository.QuestionRepository;
import dev.mrkevr.quizapp.api.service.QuestionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionServiceImpl implements QuestionService {

	QuestionRepository questionRepo;
	CategoryRepository categoryRepo;
	QuestionMongoClientRepository QuestionMongoClientRepo;
	QuestionMapper questionMapper;
	Validator validator;
	
	@Override
	public List<QuestionResponse> getAll(PageRequest pageRequest) {
		Page<Question> page = questionRepo.findAll(pageRequest);
		List<Question> list = page.getContent();
		return questionMapper.toQuestionResponse(list);
	}

	@Override
	public QuestionResponse getById(String questionId) {
		Question question = questionRepo.findById(questionId).orElseThrow(() -> new ResourceNotFoundException(questionId, Question.class));
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
	@Transactional
	public QuestionResponse add(QuestionRequest questionRequest) {
		
		this.validateQuestionRequest(questionRequest);
		
		try {
			Question savedQuestion = questionRepo.save(questionMapper.toQuestion(questionRequest));
			return questionMapper.toQuestionResponse(savedQuestion);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void deleteById(String questionId) {
		Question question = questionRepo.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException(questionId , Question.class));
		questionRepo.delete(question);
	}
	
	@Override
	@Transactional
	public QuestionResponse update(String questionId, QuestionRequest questionRequest) {
		Question questionToUpdate = questionRepo.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException(questionId,  Question.class));

		this.validateQuestionRequest(questionRequest);
		
		questionToUpdate.setCategoryId(questionRequest.getCategoryId());
		questionToUpdate.setQuestion(questionRequest.getQuestion());
		questionToUpdate.setOption(questionRequest.getOption());
		questionToUpdate.setRightAnswer(questionRequest.getRightAnswer());
		
		try {
			Question savedQuestion = questionRepo.save(questionMapper.toQuestion(questionRequest));
			return questionMapper.toQuestionResponse(savedQuestion);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}
	}

	@Override
	public List<QuestionResponse> search(String keyword, String sort, long limit) {
		List<Question> questions = QuestionMongoClientRepo.searchByKeyword(keyword, sort, limit);
		return questionMapper.toQuestionResponse(questions);
	}

	@Override
	public List<QuestionResponse> getRandom(String categoryId, int size) {
		return questionMapper.toQuestionResponse(QuestionMongoClientRepo.findRandom(categoryId, size));
	}
	
	private void validateQuestionRequest(QuestionRequest questionRequest) {
		// annotation validations
		Set<ConstraintViolation<QuestionRequest>> violations = validator.validate(questionRequest);
		if (!violations.isEmpty()) {
			 throw new ConstraintViolationException("Error occurred.", violations);
		}
		// categoryId check
		if(!categoryRepo.existsById(questionRequest.getCategoryId())) {
			throw new ResourceNotFoundException(questionRequest.getCategoryId(), Category.class);
		}
		// rightAnswer validation
		List<String> validAnswer = List.of("a", "b", "c", "d");
		if(!validAnswer.contains(questionRequest.getRightAnswer().toLowerCase())) {
			throw new InvalidRequestException("rightAnswer must be 'a', 'b', 'c' or 'd'");
		}
	}
}
