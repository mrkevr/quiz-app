package dev.mrkevr.quizapp.api.service;

import java.util.List;

import dev.mrkevr.quizapp.api.dto.QuizResult;
import dev.mrkevr.quizapp.api.dto.RankingResponse;
import dev.mrkevr.quizapp.api.model.Ranking;

public interface RankingService {
	
	List<RankingResponse> getAll();
	
	RankingResponse getById(String rankingID);
	
	RankingResponse getByCategoryId(String categoryId);
	
	Ranking saveResult(QuizResult quizResult);
}
