package dev.mrkevr.quizapp.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.mrkevr.quizapp.api.model.Ranking;

public interface RankingRepository extends MongoRepository<Ranking, String> {
	
	Optional<Ranking> findByCategoryId(String categoryId);
}
