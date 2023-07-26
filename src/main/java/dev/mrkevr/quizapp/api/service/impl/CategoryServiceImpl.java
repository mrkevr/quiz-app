package dev.mrkevr.quizapp.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.mrkevr.quizapp.api.exception.ResourceNotFoundException;
import dev.mrkevr.quizapp.api.model.Category;
import dev.mrkevr.quizapp.api.repository.CategoryRepository;
import dev.mrkevr.quizapp.api.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

	CategoryRepository categoryRepo;

	@Override
	public List<Category> getAll() {
		return categoryRepo.findAll();
	}

	@Override
	public Category getById(String id) {
		return categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	@Transactional
	public Category saveCategory(String name) {
		Category category = new Category();
		category.setName(name);
		return categoryRepo.save(category);
	}

	@Override
	@Transactional
	public Category updateById(String id, String name) {
		Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		category.setName(name);
		return categoryRepo.save(category);
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		categoryRepo.delete(category);
	}

}
