package cc.kasumi.commons.mongodb;

import com.mongodb.client.MongoCursor;
import org.bson.Document;

public interface FindCursorCallback {

    void onQueryDone(MongoCursor<Document> cursor);
}
