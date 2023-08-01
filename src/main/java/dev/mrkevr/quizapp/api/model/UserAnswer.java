package dev.mrkevr.quizapp.api.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAnswer {
	
	String questionId;
	
	String answer;
}
