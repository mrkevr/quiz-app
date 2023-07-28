package dev.mrkevr.quizapp.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mrkevr.quizapp.api.model.Category;
import dev.mrkevr.quizapp.api.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

	CategoryService categoryServ;

	@GetMapping
	ResponseEntity<List<Category>> getAll() {
		List<Category> categories = categoryServ.getAll();
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{id}")
	ResponseEntity<Category> getById(@PathVariable String id) {
		Category category = categoryServ.getById(id);
		return ResponseEntity.ok(category);
	}

	@PostMapping
	ResponseEntity<Category> save(@RequestBody Category category) {
		String name = category.getName();
		Category savedCategory = categoryServ.save(name);
		return ResponseEntity.ok(savedCategory);
	}
}
