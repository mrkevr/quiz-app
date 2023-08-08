package dev.mrkevr.quizapp.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.mrkevr.quizapp.api.dto.RankingResponse;
import dev.mrkevr.quizapp.api.model.Ranking;
import dev.mrkevr.quizapp.api.service.CategoryService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RankingMapper {
	
	private final CategoryService categoryServ;
	
	public RankingResponse toResponse(Ranking ranking) {
		String categoryName = categoryServ.getById(ranking.getCategoryId()).getName();
		return RankingResponse.builder()
				.categoryId(ranking.getCategoryId())
				.categoryName(categoryName)
				.usernamePercentage(ranking.getUsernamePercentage())
				.build();
	}
	
	public List<RankingResponse> toResponse(List<Ranking> list){
		return list.stream()
				.map(r -> this.toResponse(r))
				.collect(Collectors.toList());
	}
	
}
