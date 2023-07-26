package dev.mrkevr.quizapp.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

	ResponseEntity<List<Category>> getAll() {
		List<Category> categories = categoryServ.getAll();
		return ResponseEntity.ok(categories);
	}
	
	ResponseEntity<Category> getById(String id) {
        Category category = categoryServ.getById(id);
        return ResponseEntity.ok(category);
    }
	
}
