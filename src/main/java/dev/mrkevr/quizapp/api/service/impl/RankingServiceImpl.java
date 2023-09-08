package dev.mrkevr.quizapp.api.service.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.mrkevr.quizapp.api.dto.QuizResult;
import dev.mrkevr.quizapp.api.dto.RankingResponse;
import dev.mrkevr.quizapp.api.exception.ResourceNotFoundException;
import dev.mrkevr.quizapp.api.mapper.RankingMapper;
import dev.mrkevr.quizapp.api.model.Category;
import dev.mrkevr.quizapp.api.model.Ranking;
import dev.mrkevr.quizapp.api.repository.CategoryRepository;
import dev.mrkevr.quizapp.api.repository.RankingRepository;
import dev.mrkevr.quizapp.api.service.RankingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RankingServiceImpl implements RankingService {

	RankingRepository rankingRepo;
	CategoryRepository categoryRepo;
	RankingMapper rankingMapper;
	
	@Override
	@Transactional
	public Ranking saveResult(QuizResult quizResult) {
		// Fetch ranking by categoryId, create new if it doesn't exist
		if(rankingRepo.findByCategoryId(quizResult.getCategoryId()).isEmpty()) {
			// Create new ranking
			String categoryName = categoryRepo.findById(quizResult.getCategoryId()).get().getName();
			Ranking ranking = Ranking.builder()
					.categoryId(quizResult.getCategoryId())
					.categoryName(categoryName)
					.usernamePercentage(new HashMap<>())
					.build();
			
			// Adding the username and percentage to the map
			ranking.getUsernamePercentage().put(quizResult.getUsername(), quizResult.getPercentage());
			// Persist the object to the database and return it
			return rankingRepo.save(ranking);
		}
		
		// Fetching the ranking by categoryId
		Ranking ranking = rankingRepo.findByCategoryId(quizResult.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException(quizResult.getCategoryId(), Category.class));
		
		// Adding the username and percentage to the map
		ranking.getUsernamePercentage().put(quizResult.getUsername(), quizResult.getPercentage());
		
		// If the ranking's map is more than 10, remove the entry with the least percentage
		this.updateRanking(ranking);
		
		// Persist the object to the database and return it
		return rankingRepo.save(ranking);
	}

	@Override
	public List<RankingResponse> getAll() {
		return rankingMapper.toResponse(rankingRepo.findAll());
	}

	@Override
	public RankingResponse getById(String rankingID) {
		 Ranking ranking = rankingRepo.findById(rankingID)
				.orElseThrow(() -> new ResourceNotFoundException(rankingID, Ranking.class));
		return rankingMapper.toResponse(ranking);
	}

	@Override
	public RankingResponse getByCategoryId(String categoryId) {
		Ranking ranking = rankingRepo.findByCategoryId(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(categoryId, Category.class));
		return rankingMapper.toResponse(ranking);
	}
	
	// Helper method to update the ranking entries
	private void updateRanking(Ranking ranking) {
		Map<String, Double> usernamePercentage = ranking.getUsernamePercentage();
		// Reduce map if the size is more than 10, remove the one with the least percentage
		if (usernamePercentage.size() > 10) {
			
			//  Sort values using Java 8 Stream.sorted() method and get 1st Entry
	        Entry<String, Double> lowestPercentageEntry = usernamePercentage
	                .entrySet()
	                .stream()
	                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
	                .findFirst()
	                .get();
			
	        // Remove entry with the least percentage
	        usernamePercentage.entrySet()
	        	.removeIf(entry -> entry.getValue().equals(lowestPercentageEntry.getValue()));
		}
	}
}
