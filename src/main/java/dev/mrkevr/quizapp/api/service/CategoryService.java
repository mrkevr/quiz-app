package dev.mrkevr.quizapp.api.service;

import java.util.List;

import dev.mrkevr.quizapp.api.model.Category;

public interface CategoryService {

	List<Category> getAll();

	Category getById(String id);

	Category saveCategory(String name);

	Category updateById(String id, String name);

	void deleteById(String id);
}
