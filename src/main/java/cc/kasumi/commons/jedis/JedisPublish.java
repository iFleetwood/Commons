package cc.kasumi.commons.jedis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import redis.clients.jedis.Jedis;

import java.util.concurrent.CompletableFuture;

@Getter
@AllArgsConstructor
public class JedisPublish {

    private final Jedis jedis;
    private final String channel, message;

    public void publish() {
        CompletableFuture.runAsync(() -> {
            try  {
                jedis.publish(channel, message);
                jedis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
