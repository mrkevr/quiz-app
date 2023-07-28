package dev.mrkevr.quizapp.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	ResponseEntity<Question> getById(@PathVariable String id) {
		Question question = questionServ.getById(id);
		return ResponseEntity.ok(question);
	}

	@GetMapping
	ResponseEntity<List<Question>> getAllById(@RequestBody(required = true) List<String> questionIds) {
//		List<String> ids = idWrapper.getIds();
		List<Question> questions = questionServ.getAllById(questionIds);
		return ResponseEntity.ok(questions);
	}

	@GetMapping("/category/{categoryId}")
	ResponseEntity<List<Question>> getAllByCategoryId(@PathVariable String categoryId) {
		List<Question> questions = questionServ.getAllByCategoryId(categoryId);
		return ResponseEntity.ok(questions);
	}

	@PostMapping
	ResponseEntity<Question> save(@RequestBody Question question) {
		Question savedQuestion = questionServ.add(question);
		return ResponseEntity.ok(savedQuestion);
	}
}