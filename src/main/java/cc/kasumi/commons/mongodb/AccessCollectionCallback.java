package cc.kasumi.commons.mongodb;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public interface AccessCollectionCallback {

    void onCollectionAccessed(MongoCollection<Document> collection);
}
