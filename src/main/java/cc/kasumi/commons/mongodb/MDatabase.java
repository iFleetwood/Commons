package cc.kasumi.commons.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;


@Getter
public class MDatabase {

    private MongoDatabase database;
    private MongoClient mongoClient;
    private String databaseName;

    public MDatabase(String uri, String databaseName) {
        this.databaseName = databaseName;

        ConnectionString connString = new ConnectionString(uri);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();

        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(databaseName);
    }
}
