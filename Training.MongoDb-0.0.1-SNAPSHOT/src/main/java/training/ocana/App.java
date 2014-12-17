package training.ocana;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class App {

	public static void main(String[] args) throws UnknownHostException {
		
		final Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(App.class, "/");
		
		MongoClient mongoClient = new MongoClient(new ServerAddress("127.0.0.1", 27017));
		DB db = mongoClient.getDB("test");
		final DBCollection myColl = db.getCollection("mycollection");
		
		Spark.get("/", new Route() {
			
			public Object handle(Request request, Response response) throws Exception {
				
				StringWriter sw = new StringWriter();
				
				Template template = configuration.getTemplate("HelloWorldTemplate.ftl");
				
				DBObject document = myColl.findOne();
								
				template.process(document, sw);
				
				return sw.toString();
			}
		});
	}

}
