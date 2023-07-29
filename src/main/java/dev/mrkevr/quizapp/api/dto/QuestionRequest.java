package dev.mrkevr.quizapp.api.dto;

import dev.mrkevr.quizapp.api.model.Option;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionRequest {
	
	String categoryId;

	String question;

	Option option;

	String rightAnswer;
}
