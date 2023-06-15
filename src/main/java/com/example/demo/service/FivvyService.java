package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.FivvyConstans;
import com.example.demo.entities.Acceptance;
import com.example.demo.entities.Disclaimer;

import com.example.demo.util.MongoDBUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

@Service
public class FivvyService {

	public Disclaimer addDisclaimer(Disclaimer disclaimer) {

		MongoCollection<Document> collection = MongoDBUtil.getCollection(MongoDBUtil.DATABASE_NAME, MongoDBUtil.COLLECTION_NAME);
		Document searchDocument = createDocument(disclaimer);
		//insert
		collection.insertOne(searchDocument);
		return disclaimer;
	}

	public List<Document> getDisclaimer(String text) {
		
		//replace the Booking collection with that of your local environment
		MongoCollection<Document> collection = MongoDBUtil.getCollection(MongoDBUtil.DATABASE_NAME, MongoDBUtil.COLLECTION_NAME);
		List<Document> listDocuments = new ArrayList<>();

		Document searchDocument = new Document(FivvyConstans.TEXT, text);
		List<Document> foundDocuments = foundDocument(searchDocument, collection, listDocuments);

		Disclaimer disclaimer = new Disclaimer();
		disclaimer.setId((Integer) foundDocuments.get(0).get(FivvyConstans.ID));
		disclaimer.setName(foundDocuments.get(0).get(FivvyConstans.TEXT).toString());
		disclaimer.setVersion(foundDocuments.get(0).get(FivvyConstans.VERSION).toString());
		disclaimer.setText(text);
		disclaimer.setCreate_at(foundDocuments.get(0).get(FivvyConstans.CREATE_AT).toString());
		disclaimer.setUpdate_at(foundDocuments.get(0).get(FivvyConstans.UPDATE_AT).toString());

		return foundDocuments;
	}

	public void deleteDisclaimer(String text) {

		MongoCollection<Document> collection = MongoDBUtil.getCollection(MongoDBUtil.DATABASE_NAME, MongoDBUtil.COLLECTION_NAME);
		List<Document> listDocuments = new ArrayList<>();

		Document searchDocument = new Document(FivvyConstans.TEXT, text);
		List<Document> foundDocuments = foundDocument(searchDocument, collection, listDocuments);

		collection.deleteOne(foundDocuments.get(0));

	}

	public void updateDisclaimer(String text, Disclaimer disclaimer) {
		MongoCollection<Document> collection = MongoDBUtil.getCollection(MongoDBUtil.DATABASE_NAME, MongoDBUtil.COLLECTION_NAME);
		List<Document> listDocuments = new ArrayList<>();

		Document searchDocument = new Document(FivvyConstans.TEXT, text);
		List<Document> foundDocuments = foundDocument(searchDocument, collection, listDocuments);

		Document a = new Document("$set", new Document());
		a.get("$set", Document.class).append(FivvyConstans.ID, disclaimer.getId())
		  .append(FivvyConstans.NAME, disclaimer.getName())
		  .append(FivvyConstans.TEXT, disclaimer.getText())
		  .append(FivvyConstans.VERSION, disclaimer.getVersion())
		  .append(FivvyConstans.CREATE_AT, disclaimer.getCreate_at())
		  .append(FivvyConstans.UPDATE_AT, disclaimer.getUpdate_at());

		collection.updateMany(foundDocuments.get(0),a);

	}

	public Document createDocument(Disclaimer disclaimer) {

		return new Document(FivvyConstans.ID, disclaimer.getId())
		  .append(FivvyConstans.NAME, disclaimer.getName())
		  .append(FivvyConstans.TEXT, disclaimer.getText())
		  .append(FivvyConstans.VERSION, disclaimer.getVersion())
		  .append(FivvyConstans.CREATE_AT, disclaimer.getCreate_at())
		  .append(FivvyConstans.UPDATE_AT, disclaimer.getUpdate_at());
	}

	public List<Document> foundDocument (Document searchDocument, MongoCollection<Document> collection, List<Document> foundDocuments) {
		try (MongoCursor<Document> cursor = collection.find(searchDocument).iterator()) {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				foundDocuments.add(document);
			}
		}
		return foundDocuments;
	}

	public Document createDocumentAcceptance(Acceptance acceptance) {

		return new Document(FivvyConstans.DISCLAIMER_ID, acceptance.getDisclaimer_id())
		  .append(FivvyConstans.USER_ID, acceptance.getUser_id())
		  .append(FivvyConstans.CREATE_AT_ACCEPTANCE, acceptance.getCreate_at());

	}

	public Acceptance addAcceptance(Acceptance acceptance) {

		MongoCollection<Document> collection = MongoDBUtil.getCollection(MongoDBUtil.DATABASE_NAME, MongoDBUtil.COLLECTION_NAME);
		Document searchDocument = createDocumentAcceptance(acceptance);
		//insert
		collection.insertOne(searchDocument);
		return acceptance;

	}

	public List<Document> getAcceptance(int user_id) {

		//replace the Booking collection with that of your local environment
		MongoCollection<Document> collection = MongoDBUtil.getCollection(MongoDBUtil.DATABASE_NAME, MongoDBUtil.COLLECTION_NAME);
		List<Document> listDocuments = new ArrayList<>();

		Document searchDocument = new Document(FivvyConstans.USER_ID, user_id);
		List<Document> foundDocuments = foundDocument(searchDocument, collection, listDocuments);

		Acceptance acceptance = new Acceptance();
		acceptance.setDisclaimer_id((Integer) foundDocuments.get(0).get(FivvyConstans.DISCLAIMER_ID));
		acceptance.setUser_id(user_id);
		acceptance.setCreate_at(foundDocuments.get(0).get(FivvyConstans.CREATE_AT).toString());

		return foundDocuments;

	}

}
