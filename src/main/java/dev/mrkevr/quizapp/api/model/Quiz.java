
package dev.mrkevr.quizapp.api.model;

import java.util.Random;
import java.util.Set;

import javax.persistence.PrePersist;

import org.bson.types.ObjectId;
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
@Document(collection = "quizzes")
public class Quiz {

	@Id
	String quizId;

	String categoryId;

	String author;

	Difficulty difficulty;

	Set<ObjectId> questionIds;

	@PrePersist
	private void prePersist() {
		this.setQuizId(AppUtil.getRandomDocumentId());
	}
}
