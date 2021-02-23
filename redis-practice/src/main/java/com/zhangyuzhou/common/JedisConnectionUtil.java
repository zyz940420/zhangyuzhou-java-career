package com.zhangyuzhou.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Objects;

public class JedisConnectionUtil {

    private static volatile JedisPool jedisPool;

    public static JedisPool getJedisPool() {
        if (Objects.isNull(jedisPool)) {
            jedisPool = new JedisPool(new JedisPoolConfig(), "106.15.191.119", 6379);
        }
        return jedisPool;
    }

    public static void closeJedis(Jedis jedis) {
        if (Objects.nonNull(jedis)) {
            jedis.close();
        }
    }
}
