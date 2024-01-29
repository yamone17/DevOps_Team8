/** 
    * Java file that connected with mongodb and run from dockerfile
    * It connects to a MongoDB server using the MongoClient class, inserts a document into a specified database and collection
    * Make sure MongoDB server is running in a Docker container named "mongo-dbserver".
*/
package DevOps_Team8;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class App
{
    public static void main(String[] args)
    {
        // Connect to MongoDB
        MongoClient mongoClient = new MongoClient("mongo-dbserver");
        // Get a database - will create when we use it
        MongoDatabase database = mongoClient.getDatabase("mydb");
        // Get a collection from the database
        MongoCollection<Document> collection = database.getCollection("test");
        // Create a document to store
        Document doc = new Document("name", "Team8")
                .append("class", "Hons 7 & 8");
        // Add document to collection
        collection.insertOne(doc);

        // Check document in collection
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
    }
}
