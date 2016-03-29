package com.mongodb.m101j.crud;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Homework2_3 {
	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase database = client.getDatabase("students");
		MongoCollection<Document> collection = database.getCollection("grades");

		Bson sort = ascending("student_id", "score");
		Bson filter = and(eq("type", "homework"));
		List<Document> all = collection.find().sort(sort).filter(filter).into(new ArrayList<Document>());
		int pastStudent = -1;
		for (Document cur : all) {
			if (pastStudent != cur.getInteger("student_id")) {
				pastStudent = cur.getInteger("student_id");
				collection.deleteOne(eq("_id", cur.getObjectId("_id")));
			}
		}
	}
}
