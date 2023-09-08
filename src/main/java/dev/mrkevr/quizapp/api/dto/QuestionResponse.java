package dev.mrkevr.quizapp.api.dto;

import dev.mrkevr.quizapp.api.model.Option;
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
public class QuestionResponse {

	String questionId;
	
	String categoryId;
	
	String question;
	
	Option option;
}
