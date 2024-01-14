import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.function.Consumer;

@SuppressWarnings("ALL")

public class MongoDBFasadAnställd {

    MongoClient client;
    MongoDatabase dbA;
    MongoCollection<Document> collection;

    String connString = "mongodb://localhost:27017";
    String collectionName = "anställda";
    String databaseName = "Anställd";


    public MongoDBFasadAnställd(String connString, String databaseName, String collectionName) {
        this.connString = connString;
        this.collectionName = collectionName;
        this.databaseName = databaseName;
        Connect();
    }


    public MongoDBFasadAnställd() {
        Connect();
    }


    public void createIndex() {
        collection.createIndex(new Document("namn", 1),
                new IndexOptions().unique(false));
    }


    public void insertOne(Anställd anställningsnummer) {
        Document doc = anställningsnummer.toDoc();
        collection.insertOne(doc);
    }


    public Anställd FindByAnställningsnummer(String anställningsnummer) {
        Document doc = new Document("anställningsnummer", anställningsnummer);
        Document search = collection.find(doc).first();
        return Anställd.fromDoc(search);
    }

    public void updateOne(String anställningsnummer, Anställd updatedAnställd) {
        Document filter = new Document("anställningsnummer", anställningsnummer);
        Document update = new Document("$set", updatedAnställd.toDoc());
        collection.updateOne(filter, update);
    }


    public void Delete(String anställningsnummer) {
        Document doc = new Document("anställningsnummer", anställningsnummer);
        collection.deleteOne(doc);
    }


    public ArrayList<Anställd> Find(String name) {
        Document doc = new Document("namn", name);
        FindIterable<Document> result = collection.find(doc);
        ArrayList<Anställd> namner = new ArrayList<>();

        result.forEach((Consumer<? super Document>) anställd -> namner.add(Anställd.fromDoc(anställd)));

        return namner;
    }

    public ArrayList<Anställd> findAll() {
        FindIterable<Document> result = collection.find();
        ArrayList<Anställd> anställda = new ArrayList<>();

        result.forEach((Consumer<? super Document>) anställd -> anställda.add(Anställd.fromDoc(anställd)));

        return anställda;
    }

    public void Connect() {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connString))
                .build();

        try {
            client = MongoClients.create(settings);
            dbA = client.getDatabase(databaseName);
            collection = dbA.getCollection(collectionName);
        } catch (Exception ex) {
            System.out.println("Ooops!");
            System.out.println(ex.getMessage());
        }

        try {
            createIndex();
        } catch (Exception ex) {
            System.out.println(";)");
        }
    }
}

