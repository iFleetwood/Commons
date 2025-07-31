package cc.kasumi.commons.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import org.bson.Document;

import java.util.concurrent.CompletableFuture;

@Getter
public class MCollection {

    private final MDatabase mDatabase;
    private final MongoCollection<Document> collection;

    public MCollection(MDatabase mDatabase, String collectionName) {
        this.mDatabase = mDatabase;
        this.collection = mDatabase.getDatabase().getCollection(collectionName);
    }

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
        return collection.find().iterator();
    }

    public Document getDocument(Document key) {
        return collection.find(key).limit(1).first();
    }

    public boolean containsKey(Document key) {
        return getDocument(key) != null;
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
