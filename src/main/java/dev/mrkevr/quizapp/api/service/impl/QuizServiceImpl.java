package dev.mrkevr.quizapp.api.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.mrkevr.quizapp.api.dto.UserQuizAnswer;
import dev.mrkevr.quizapp.api.exception.InvalidRequestException;
import dev.mrkevr.quizapp.api.exception.ResourceNotFoundException;
import dev.mrkevr.quizapp.api.model.Category;
import dev.mrkevr.quizapp.api.model.Difficulty;
import dev.mrkevr.quizapp.api.model.Question;
import dev.mrkevr.quizapp.api.model.Quiz;
import dev.mrkevr.quizapp.api.model.QuizResult;
import dev.mrkevr.quizapp.api.repository.CategoryRepository;
import dev.mrkevr.quizapp.api.repository.QuestionMongoClientRepository;
import dev.mrkevr.quizapp.api.repository.QuestionRepository;
import dev.mrkevr.quizapp.api.repository.QuizMongoClientRepository;
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
	CategoryRepository categoryRepo;
	QuestionRepository questionRepo;
	QuestionMongoClientRepository questionMongoClientRepo;
	QuizMongoClientRepository quizMongoClientRepo;
	
	@Override
	public List<Quiz> getAll() {
		return quizRepo.findAll();
	}

	@Override
	public Quiz getById(String quizId) {
		return quizRepo.findById(quizId).orElseThrow(() -> new ResourceNotFoundException(quizId, Quiz.class));
	}

	@Override
	public Quiz save(Quiz quiz) {
		return quizRepo.save(quiz);
	}
	
	@Override
	public Quiz generateQuiz(String author,String categoryId, int size, Difficulty difficulty) {
		
		if(size > 10 || size < 1) {
			throw new InvalidRequestException("Quiz size must not be 1-10");
		}
		if(!categoryRepo.existsById(categoryId)) {
			throw new ResourceNotFoundException(categoryId, Category.class);
		}
		
		List<String> questionIds = questionMongoClientRepo.findRandom(categoryId, size).stream()
			.map(q -> q.getQuestionId())
			.collect(Collectors.toList());
		
		Quiz quiz = Quiz.builder()
			.author(author)
			.difficulty(difficulty)
			.categoryId(categoryId)
			.questionIds(questionIds.stream().collect(Collectors.toSet()))
			.quizId(null)
			.build();
		
		Quiz savedQuiz = quizRepo.save(quiz);
		return savedQuiz;
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
		Quiz quiz = quizRepo.findById(quizId).orElseThrow(() -> new ResourceNotFoundException(quizId, Quiz.class));
		quizRepo.delete(quiz);
	}

	@Override
	public List<Quiz> getAllByAuthor(String author) {
		return quizRepo.findByAuthorIgnoreCase(author);
	}

	@Override
	public QuizResult getResult(UserQuizAnswer userQuizAnswer) {
		
		Quiz quiz = quizRepo.findById(userQuizAnswer.getQuizId())
				.orElseThrow(() -> new ResourceNotFoundException(userQuizAnswer.getQuizId(), Quiz.class));
		
		List<String> quizQuestionIds = new ArrayList<String>();
		quizQuestionIds.addAll(quiz.getQuestionIds());
		List<String> userQuestionIds = userQuizAnswer.getUserAnswers().stream().map(e -> e.getQuestionId()).collect(Collectors.toList());
		// match the ids from two lists
		boolean isQuestionIdsMatch = this.idEqualIgnoreOrder(quizQuestionIds, userQuestionIds);
		if(!isQuestionIdsMatch) {
			throw new InvalidRequestException("User's question id's do not match with the quiz's.");
		}
		
		// ====================== //
		int[] scoreArray = { 0 };
		
		Iterable<Question> iterable = questionRepo.findAllById(quizQuestionIds);
		iterable.forEach(question -> {
			
			boolean getScore = userQuizAnswer.getUserAnswers().stream()
					.anyMatch(answer -> answer.getQuestionId().equals(question.getQuestionId()) && answer.getAnswer().equals(question.getRightAnswer()));
			
			// increment score if the answer is correct
			if(getScore) {
				scoreArray[0]++;
			}
		});
		
		double scorePercentage = (scoreArray[0] / (double)quiz.getQuestionIds().size()) * 100;
		BigDecimal bd = new BigDecimal(scorePercentage).setScale(2, RoundingMode.HALF_UP);  
		double roundedPercentage = bd.doubleValue(); 
		
//		DecimalFormat decimalFormat = new DecimalFormat("#.##");
//        String formattedPercentage = decimalFormat.format(scorePercentage)+"%";

		return QuizResult.builder()
				.username(userQuizAnswer.getUsername())
				.categoryId(userQuizAnswer.getCategoryId())
				.quizId(userQuizAnswer.getQuizId())
				.score(scoreArray[0])
				.items(quiz.getQuestionIds().size())
				.percentage(roundedPercentage)
				.build();
	}
	
	private boolean idEqualIgnoreOrder(List<String> list1, List<String> list2) {
		Collections.sort(list1);
		Collections.sort(list2);
		return list1.equals(list2);
	}

	@Override
	public Quiz getRandom(String categoryId) {
		if(!categoryRepo.existsById(categoryId)) {
			throw new ResourceNotFoundException(categoryId, Category.class);
		}
		Quiz quiz = quizMongoClientRepo.getRandom(categoryId);
		return quiz;
	}
}
