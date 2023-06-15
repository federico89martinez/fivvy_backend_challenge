package com.example.demo.util;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class MongoDBUtil {

	public static final String DATABASE_NAME = "local";
	public static final String COLLECTION_NAME = "Fivvy";
	public static final String CONNECTION = "mongodb://localhost:27017";

	public static MongoCollection<Document> getCollection(String databaseName, String collectionName) {
		MongoDatabase database = MongoClients.create(CONNECTION)
		  .getDatabase(databaseName);
		return database.getCollection(collectionName);
	}

}
