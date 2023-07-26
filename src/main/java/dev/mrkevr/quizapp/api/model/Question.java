package dev.mrkevr.quizapp.api.model;

import javax.persistence.PrePersist;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.mrkevr.quizapp.api.service.AppUtil;
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
@Document(collection = "questions")
public class Question {
	
	@Id
	String questionId;

	String quizId;

	String question;

	Option option;

	String answer;
	
	@PrePersist
	private void prePersist() {
		this.setQuestionId(AppUtil.getRandomDocumentId());
	}
}
