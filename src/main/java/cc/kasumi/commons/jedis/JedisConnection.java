package cc.kasumi.commons.jedis;

import lombok.Getter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

@Getter
public class JedisConnection {

    private JedisPool jedisPool;

    public JedisConnection(String host, int port, int timeout) {
        jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout);
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public Pipeline getJedisPipeline() {
        return getJedis().pipelined();
    }
}
