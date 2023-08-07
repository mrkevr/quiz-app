package dev.mrkevr.quizapp.api.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "rankings")
public class Ranking {
	
	@Id
	String rankingId;
	
	String categoryId;
	
	String categoryName;
	
	Map<String, Double> usernamePercentage;
}
