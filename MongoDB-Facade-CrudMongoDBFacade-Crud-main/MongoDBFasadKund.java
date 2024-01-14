import com.mongodb.*;
import org.bson.Document;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.IndexOptions;
import java.util.ArrayList;
import java.util.function.Consumer;

@SuppressWarnings("ALL")

public class MongoDBFasadKund  {
    MongoClient client;
    MongoDatabase dbK;
    MongoCollection<Document> collection;

    String connString = "mongodb://localhost:27017";
    String collectionName = "allKunderna";
    String databaseName = "Kund";


    public MongoDBFasadKund(String connString, String databaseName, String collectionName) {
        this.connString = connString;
        this.collectionName = collectionName;
        this.databaseName = databaseName;
        Connect();
    }

    public MongoDBFasadKund() {
        Connect();
    }
    public void createIndex() {
        try {
            collection.createIndex(new Document("namn", 1),
                    new IndexOptions().unique(false));
            System.out.println("Index created successfully.");
        } catch (Exception e) {
            System.err.println("Error creating index: " + e.getMessage());
        }
    }

    public void insertOne(Kund kund){
        Document doc = kund.toDoc();
        collection.insertOne(doc);

    }

    public Kund FindByKundnummer(String kundnummer) {
        Document doc = new Document("kundnummer", kundnummer);
        Document search = collection.find(doc).first();
        return Kund.fromDoc(search);
    }

    public void updateOne(String kundnummer, Kund updatedKund) {
        Document filter = new Document("kundnummer", kundnummer);
        Document update = new Document("$set", updatedKund.toDoc());
        collection.updateOne(filter, update);
    }

    public void Delete(String kundnummer) {
        Document doc = new Document("kundnummer", kundnummer);
        collection.deleteOne(doc);
    }


    public ArrayList<Kund> Find(String namn) {
        Document doc = new Document("namn", namn);
        FindIterable<Document> result = collection.find(doc);
        ArrayList<Kund> kunder = new ArrayList<>();

        result.forEach((Consumer<? super Document>) kund -> kunder.add(Kund.fromDoc(kund)));

        return kunder;
    }

    public ArrayList<Kund> findAll() {
        FindIterable<Document> result = collection.find();
        ArrayList<Kund> kund = new ArrayList<>();

        result.forEach((Consumer<? super Document>) kunder -> kund.add(Kund.fromDoc(kunder)));

        return kund;
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
            dbK = client.getDatabase(databaseName);
            collection = dbK.getCollection(collectionName);
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
