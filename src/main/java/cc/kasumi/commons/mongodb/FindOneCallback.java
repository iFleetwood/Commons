package cc.kasumi.commons.mongodb;

import org.bson.Document;

public interface FindOneCallback {

    void onQueryDone(Document result);
}
