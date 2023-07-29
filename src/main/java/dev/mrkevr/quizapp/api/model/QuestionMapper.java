package dev.mrkevr.quizapp.api.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.mrkevr.quizapp.api.dto.QuestionRequest;
import dev.mrkevr.quizapp.api.dto.QuestionResponse;

@Component
public class QuestionMapper {
	
	public QuestionResponse toQuestionResponse(Question question) {
		return QuestionResponse.builder()
				.questionId(question.getQuestionId())
				.categoryId(question.getCategoryId())
				.question(question.getQuestion())
				.option(question.getOption()).build();
	}
	
	public List<QuestionResponse> toQuestionResponse(List<Question> questions){
		return questions.stream().map(q -> this.toQuestionResponse(q)).collect(Collectors.toList());
	}
	
	public Question toQuestion(QuestionRequest questionRequest) {
		return Question.builder()
				.categoryId(questionRequest.getCategoryId())
				.question(questionRequest.getQuestion())
				.option(questionRequest.getOption())
				.rightAnswer(questionRequest.getRightAnswer())
				.build();
	}
	
	public List<Question> toQuestion(List<QuestionRequest> questionRequests) {
		return questionRequests.stream().map(q -> this.toQuestion(q)).collect(Collectors.toList()); 
	}
}
