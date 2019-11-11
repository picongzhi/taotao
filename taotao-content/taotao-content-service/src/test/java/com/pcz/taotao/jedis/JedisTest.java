package com.pcz.taotao.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {
    @Test
    public void jedisTest() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("key", "1234567");
        System.out.println(jedis.get("key"));
        jedis.close();
    }

    @Test
    public void jedisPoolTest() {
        JedisPool jedisPool = new JedisPool("localhost", 6379);
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("key"));
        jedis.close();
        jedisPool.close();
    }
}
