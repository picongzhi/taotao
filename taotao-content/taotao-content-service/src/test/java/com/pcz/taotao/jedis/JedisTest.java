package com.pcz.taotao.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void jedisClusterTest() throws IOException {
        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        hostAndPortSet.add(new HostAndPort("127.0.0.1", 7000));
        hostAndPortSet.add(new HostAndPort("127.0.0.1", 7001));
        hostAndPortSet.add(new HostAndPort("127.0.0.1", 7002));
        hostAndPortSet.add(new HostAndPort("127.0.0.1", 7003));
        hostAndPortSet.add(new HostAndPort("127.0.0.1", 7004));
        hostAndPortSet.add(new HostAndPort("127.0.0.1", 7005));
        hostAndPortSet.add(new HostAndPort("127.0.0.1", 7006));

        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet);
        jedisCluster.set("key", "1234567");
        System.out.println(jedisCluster.get("key"));
        jedisCluster.close();
    }
}
