package dev.mrkevr.quizapp.api.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
public class QuestionRequest {

	@NotBlank(message = "categoryId must not be blank.")
	String categoryId;

	@NotBlank(message = "question must not be blank.")
	@Length(min = 12, max = 120, message = "question must be 12-120 characters long.")
	String question;

	@Valid
	@NotNull(message = "option nust not be null.")
	Option option;

	@NotBlank(message = "rightAnswer must not be blank.")
	@Length(min = 1, max = 1, message = "rightAnswer must be a single character.")
	String rightAnswer;
}
