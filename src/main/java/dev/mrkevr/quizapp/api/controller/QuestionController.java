package dev.mrkevr.quizapp.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.mrkevr.quizapp.api.dto.QuestionRequest;
import dev.mrkevr.quizapp.api.dto.QuestionResponse;
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
	ResponseEntity<QuestionResponse> getById(@PathVariable String id) {
		QuestionResponse question = questionServ.getById(id);
		return ResponseEntity.ok(question);
	}

	@GetMapping
	ResponseEntity<List<QuestionResponse>> getAll(@RequestBody(required = false) List<String> questionIds,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int limit) {

		// Search for the question by given list of id
		if (questionIds != null) {
			List<QuestionResponse> questions = questionServ.getAllById(questionIds);
			return ResponseEntity.ok(questions);
		} else {
			// Return a page of documents if the request body is null
			PageRequest pageRequest = PageRequest.of(page, limit);
			List<QuestionResponse> questions = questionServ.getAll(pageRequest);
			return ResponseEntity.ok(questions);
		}
	}
	
	@GetMapping("/random")
	ResponseEntity<List<QuestionResponse>> getrandom(
			@RequestParam(name = "size", defaultValue = "5", required = false) int size,
			@RequestParam(name = "categoryId", required = true) String categoryId) {
		
		List<QuestionResponse> questions = questionServ.getRandom(categoryId, size);
		return ResponseEntity.ok(questions);
	}

	@GetMapping("/category/{categoryId}")
	ResponseEntity<List<QuestionResponse>> getAllByCategoryId(@PathVariable String categoryId) {
		List<QuestionResponse> questions = questionServ.getAllByCategoryId(categoryId);
		return ResponseEntity.ok(questions);
	}

	@PostMapping
	ResponseEntity<QuestionResponse> save(@RequestBody QuestionRequest questionRequest) {

		QuestionResponse savedQuestion = questionServ.add(questionRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedQuestion.getQuestionId()).toUri();
		return ResponseEntity.created(uri).body(savedQuestion);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id) {
		questionServ.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<QuestionResponse>> search(
			@RequestParam(name = "keyword", required = true) String keyword,
			@RequestParam(name = "sort", required = false, defaultValue = "_id") String sort,
			@RequestParam(name = "limit", required = false, defaultValue = "100") long limit) {
		List<QuestionResponse> customers = questionServ.search(keyword, sort, limit);
		return ResponseEntity.ok(customers);
	}
}