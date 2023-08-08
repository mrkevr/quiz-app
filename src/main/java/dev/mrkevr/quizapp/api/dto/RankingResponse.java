package dev.mrkevr.quizapp.api.dto;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RankingResponse {
	
	String categoryId;
	
	String categoryName;
	
	Map<String, Double> usernamePercentage;
}
