package dev.mrkevr.quizapp.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mrkevr.quizapp.api.model.Quiz;
import dev.mrkevr.quizapp.api.service.QuizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuizController {

	QuizService quizServ;

	@GetMapping
	public ResponseEntity<List<Quiz>> getAll() {
		List<Quiz> quizzes = quizServ.getAll();
		return ResponseEntity.ok(quizzes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Quiz> getById(@PathVariable String id) {
		Quiz quiz = quizServ.getById(id);
		return ResponseEntity.ok(quiz);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Quiz>> getAllByCategoryId(@PathVariable String categoryId) {
		List<Quiz> quizzes = quizServ.getAllByCategoryId(categoryId);
		return ResponseEntity.ok(quizzes);
	}

	@GetMapping("/author/{author}")
	public ResponseEntity<List<Quiz>> getAllByAuthor(@PathVariable String author) {
		List<Quiz> quizzes = quizServ.getAllByAuthor(author);
		return ResponseEntity.ok(quizzes);
	}

	@PostMapping
	public ResponseEntity<Quiz> save(@RequestBody Quiz quiz) {

		// validation logic here

		Quiz savedQuiz = quizServ.save(quiz);
		return ResponseEntity.ok(savedQuiz);
	}

	@DeleteMapping("/{quizId}")
	public ResponseEntity<?> delete(@PathVariable String quizId) {
		quizServ.deleteById(quizId);
		return ResponseEntity.noContent().build();
	}

}