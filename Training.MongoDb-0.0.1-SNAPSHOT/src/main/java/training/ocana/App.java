package training.ocana;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class App {

	public static void main(String[] args) throws UnknownHostException {
		System.out.println("Hello world");
		
		MongoClient mongoClient = new MongoClient(new ServerAddress("127.0.0.1", 27017));
		
		DB db = mongoClient.getDB("test");
		
		DBCollection myColl = db.getCollection("mycollection");
		
		DBObject dbo = myColl.findOne();
		
		System.out.println(dbo);
	}

}
