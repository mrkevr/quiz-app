package dev.mrkevr.quizapp.api.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dev.mrkevr.quizapp.api.model.Question;
import dev.mrkevr.quizapp.api.repository.QuestionMongoClientRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class QuestionMongoClientRepositoryImpl implements QuestionMongoClientRepository {
	
	MongoClient mongoClient;
	MongoConverter mongoConverter;
	
	@Override
	public List<Question> searchByKeyword(String keyword, String sort, long limit) {
		MongoCollection<Document> collection = this.getCollection();
		
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
				new Document("$search",
						new Document("text",
								new Document("query", keyword).append("path",
										Arrays.asList("question", "author", "questionId", "categoryId")))),
				new Document("$sort", new Document(sort, 1L)), new Document("$limit", limit)));
		
		return this.convert(result);
	}

	@Override
	public List<Question> findRandom(String categoryId, int size) {
		MongoCollection<Document> collection = this.getCollection();
		AggregateIterable<Document> result;
		
		result = collection.aggregate(Arrays.asList(
				new Document("$match", new Document("categoryId", categoryId)),
				new Document("$sample", new Document("size", size))));

		return this.convert(result);
	}
	
	// Not working, do not use yet
	@Override
	public List<String> findRandomQuestionId(String categoryId, int size) {
		MongoCollection<Document> collection = this.getCollection();
		
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
				new Document("$match", new Document("categoryId", categoryId)),
				new Document("$sample", new Document("size", size)),
				new Document("$unset", List.of("_class", "option", "question", "categoryId", "rightAnswer"))));
		
		List<String> questionIds = new ArrayList<>();
		result.forEach(doc -> questionIds.add(mongoConverter.read(String.class, doc)));
		return null;
	}
	
	private MongoCollection<Document> getCollection(){
		MongoDatabase database = mongoClient.getDatabase("quiz-app-db");
		return  database.getCollection("questions");
	}
	
	// Converts iterable to list
	private List<Question> convert(AggregateIterable<Document> iterable){
		List<Question> questions = new ArrayList<>();
		iterable.forEach(doc -> questions.add(mongoConverter.read(Question.class, doc)));
		return questions;
	}
}
