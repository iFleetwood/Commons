package cc.kasumi.commons.mongodb;

import com.mongodb.client.MongoDatabase;

public interface EstablishConnectionCallback {

    void onConnectionEstablished(MongoDatabase database);
}
