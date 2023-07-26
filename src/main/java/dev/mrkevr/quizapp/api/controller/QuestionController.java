package dev.mrkevr.quizapp.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mrkevr.quizapp.api.model.Question;
import dev.mrkevr.quizapp.api.service.QuestionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionController {

	QuestionService questionServ;

	@GetMapping("/{id}")
	ResponseEntity<Question> getById(String questionId) {
		Question question = questionServ.getById(questionId);
		return ResponseEntity.ok(question);
	}
	
	ResponseEntity<List<Question>> getByQuizId(String quizId) {
		
		return null;
	}

}
