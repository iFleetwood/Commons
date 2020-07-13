package cc.kasumi.commons.mongodb;

import cc.kasumi.commons.Commons;
import com.mongodb.client.MongoCollection;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;

@Getter
public class MCollection {

    private final MDatabase mDatabase;
    private final MongoCollection<Document> collection;

    public MCollection(MDatabase mDatabase, String collectionName) {
        this.mDatabase = mDatabase;
        this.collection = mDatabase.getDatabase().getCollection(collectionName);

        /*
        AccessCollectionAsync(collectionName, mongoCollection ->  {
            this.collection = mongoCollection;
        });
         */
    }

    /*
    public void AccessCollectionAsync(String collectionName, final AccessCollectionCallback callback) {
        Bukkit.getScheduler().runTaskAsynchronously(Commons.getInstance(), () -> {
            MongoCollection<Document> mongoCollection = mDatabase.getDatabase().getCollection(collectionName);

            Bukkit.getScheduler().runTask(Commons.getInstance(), () -> {
                callback.onCollectionAccessed(mongoCollection);
            });
        });
    }
     */

    public void getDocumentAsync(Document key, final FindOneCallback callback) {
        Bukkit.getScheduler().runTaskAsynchronously(Commons.getInstance(), () -> {
            final Document result = getDocument(key);

            Bukkit.getScheduler().runTask(Commons.getInstance(), () -> {
                callback.onQueryDone(result);
            });
        });
    }

    public void setDocumentAsync(Document document) {
        Bukkit.getScheduler().runTaskAsynchronously(Commons.getInstance(), () -> {
            setDocument(document);
        });
    }

    public void updateDocumentAsync(Document key, Document updated) {
        Bukkit.getScheduler().runTaskAsynchronously(Commons.getInstance(), () -> {
            updateDocument(key, updated);
        });
    }

    public Document getDocument(Document key) {
        return collection.find(key).first();
    }

    public void setDocument(Document document) {
        collection.insertOne(document);
    }

    public void updateDocument(Document key, Document updated) {
        final Document result = getDocument(key);

        if (result == null) {
            setDocument(updated);

            return;
        }

        collection.updateOne(key, new Document("$set", updated));
    }
}
