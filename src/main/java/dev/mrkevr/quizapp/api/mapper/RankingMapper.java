package dev.mrkevr.quizapp.api.mapper;

import org.springframework.stereotype.Component;

import dev.mrkevr.quizapp.api.dto.RankingResponse;
import dev.mrkevr.quizapp.api.model.Ranking;
import dev.mrkevr.quizapp.api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RankingMapper {
	
	private final CategoryRepository categoryRepo;
	
}
