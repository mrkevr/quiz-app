package dev.mrkevr.quizapp.api.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserQuizAnswer {
	
	String quizId;
	List<UserAnswer> userAnswers;
}
