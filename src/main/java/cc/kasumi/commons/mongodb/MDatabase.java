package cc.kasumi.commons.mongodb;

import cc.kasumi.commons.Commons;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bukkit.Bukkit;


@Getter
public class MDatabase {

    private MongoDatabase database;

    public MDatabase(String uri, String databaseName) {
        ConnectionString connString = new ConnectionString(uri);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(databaseName);

        /*
        establishConnectionAsync(uri, databaseName, mongoDatabase -> {
             this.database = mongoDatabase;
        });
         */
    }

    /*
    public void establishConnectionAsync(String uri, String databaseName, final EstablishConnectionCallback callback) {
        Bukkit.getScheduler().runTaskAsynchronously(Commons.getInstance(), () -> {
            ConnectionString connString = new ConnectionString(uri);

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .retryWrites(true)
                    .build();

            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);

            Bukkit.getScheduler().runTask(Commons.getInstance(), () -> {
                callback.onConnectionEstablished(mongoDatabase);
            });
        });
    }
     */
}
