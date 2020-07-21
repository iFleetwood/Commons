package cc.kasumi.commons.mongodb;

import cc.kasumi.commons.Commons;
import cc.kasumi.commons.framework.Command;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.concurrent.CompletableFuture;

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

    @Deprecated
    public void getDocumentCursorAsync(final FindCursorCallback callback) {
        Bukkit.getScheduler().runTaskAsynchronously(Commons.getInstance(), () -> {
            final MongoCursor<Document> result = getDocumentCursor();

            Bukkit.getScheduler().runTask(Commons.getInstance(), () -> {
                callback.onQueryDone(result);
            });
        });
    }

    @Deprecated
    public void getDocumentAsync(Document key, final FindOneCallback callback) {
        Bukkit.getScheduler().runTaskAsynchronously(Commons.getInstance(), () -> {
            final Document result = getDocument(key);

            Bukkit.getScheduler().runTask(Commons.getInstance(), () -> {
                callback.onQueryDone(result);
            });
        });
    }

    //

    public CompletableFuture<MongoCursor<Document>> getDocumentCursorAsync() {
        return CompletableFuture.supplyAsync(this::getDocumentCursor);
    }

    public CompletableFuture<Document> getDocumentAsync(Document key) {
        return CompletableFuture.supplyAsync(() -> getDocument(key));
    }

    @Deprecated
    public void setDocumentAsync(Document document) {
        CompletableFuture.runAsync(() -> setDocument(document));
    }

    public void updateDocumentAsync(Document key, Document updated) {
        CompletableFuture.runAsync(() -> updateDocument(key, updated));
    }

    public MongoCursor<Document> getDocumentCursor() {
        return collection.find().cursor();
    }

    public Document getDocument(Document key) {
        return collection.find(key).first();
    }

    @Deprecated
    public void setDocument(Document document) {
        collection.insertOne(document);
    }

    public void updateDocument(Document key, Document updated) {
        final Document result = getDocument(key);

        if (result == null) {
            setDocument(updated);

            return;
        }

        collection.replaceOne(key, updated, new ReplaceOptions().upsert(true));
    }
}
