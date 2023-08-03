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

import dev.mrkevr.quizapp.api.model.Quiz;
import dev.mrkevr.quizapp.api.repository.QuizMongoClientRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class QuizMongoClientRepositoryImpl implements QuizMongoClientRepository {
	
	MongoClient mongoClient;
	MongoConverter mongoConverter;

	@Override
	public Quiz getRandom(String categoryId) {
		
		MongoCollection<Document> collection = this.getCollection();
		
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
				new Document("$match", new Document("categoryId", categoryId)), 
			    new Document("$sample", 
			    new Document("size", 1L))));
		
		return this.convert(result).get(0);
	}
	
	private MongoCollection<Document> getCollection(){
		MongoDatabase database = mongoClient.getDatabase("quiz-app-db");
		return  database.getCollection("quizzes");
	}
	
	private List<Quiz> convert(AggregateIterable<Document> iterable){
		List<Quiz> quizzes = new ArrayList<>();
		iterable.forEach(doc -> quizzes.add(mongoConverter.read(Quiz.class, doc)));
		return quizzes;
	}

}
