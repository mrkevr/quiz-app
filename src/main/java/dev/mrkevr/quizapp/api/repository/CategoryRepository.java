package dev.mrkevr.quizapp.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.mrkevr.quizapp.api.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
