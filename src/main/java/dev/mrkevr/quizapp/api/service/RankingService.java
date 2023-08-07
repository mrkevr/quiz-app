package dev.mrkevr.quizapp.api.service;

import java.util.List;

import dev.mrkevr.quizapp.api.dto.QuizResult;
import dev.mrkevr.quizapp.api.model.Ranking;

public interface RankingService {
	
	List<Ranking> getAll();
	
	Ranking getById(String rankingID);
	
	Ranking getByCategoryId(String categoryId);
	
	Ranking saveResult(QuizResult quizResult);
}
