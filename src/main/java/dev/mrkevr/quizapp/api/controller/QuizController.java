package dev.mrkevr.quizapp.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.mrkevr.quizapp.api.dto.QuizResult;
import dev.mrkevr.quizapp.api.dto.UserQuizAnswer;
import dev.mrkevr.quizapp.api.model.Difficulty;
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
	
	@PostMapping("/generate")
	public ResponseEntity<Quiz> generate(
			@RequestParam(name = "author", defaultValue = "anonymous", required = false) String author,
			@RequestParam(name = "categoryId", required = true) String categoryId,
			@RequestParam(name = "size", defaultValue = "10", required = false) int size,
			@RequestParam(name = "difficulty", required = true) Difficulty difficulty
			) 
	{
		Quiz generatedQuiz = quizServ.generateQuiz(author, categoryId, size, difficulty);
		return ResponseEntity.ok(generatedQuiz);
	}

	@DeleteMapping("/{quizId}")
	public ResponseEntity<?> delete(@PathVariable String quizId) {
		quizServ.deleteById(quizId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/random")
	public ResponseEntity<?> getOneRandomByCategoryId(@RequestParam(name = "categoryId", required = true) String categoryId) {
		Quiz quiz = quizServ.getRandom(categoryId);
		return ResponseEntity.ok(quiz);
	}
	
	@PostMapping("/check")
	public ResponseEntity<?> check(@RequestBody UserQuizAnswer userQuizAnswer) {
			
		QuizResult result = quizServ.getResult(userQuizAnswer);
		return ResponseEntity.ok(result);
	}
}