package dev.mrkevr.quizapp.api.model;

import javax.validation.constraints.NotBlank;

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
public class Option {

	@NotBlank(message = "Option 'a' must not be blank.")
	String a;

	@NotBlank(message = "Option 'b' must not be blank.")
	String b;

	@NotBlank(message = "Option 'c' must not be blank.")
	String c;

	@NotBlank(message = "Option 'd' must not be blank.")
	String d;
}
