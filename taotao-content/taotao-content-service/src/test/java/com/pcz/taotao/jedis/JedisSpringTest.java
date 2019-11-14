package com.pcz.taotao.jedis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml"})
public class JedisSpringTest {
    @Autowired
    private JedisClient jedisClient;

    @Test
    public void jedisClientPoolTest() {
        String key = "name";
        String value = "picongzhi";
        jedisClient.set(key, value);
        Assert.assertEquals(value, jedisClient.get(key));
    }

    @Test
    public void jedisClientClusterTest() {
        String key = "age";
        String value = "26";
        jedisClient.set(key, value);
        Assert.assertEquals(value, jedisClient.get(key));
    }
}
