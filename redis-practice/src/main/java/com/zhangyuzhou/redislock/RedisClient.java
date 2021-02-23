package com.zhangyuzhou.redislock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Properties;

public class RedisClient {

    private JedisPool pool;

    private static RedisClient instance = new RedisClient();

    private RedisClient() {
        init();
    }

    public static RedisClient getInstance(){
        return instance;
    }

    public Object eval(String script, List<String> keys, List<String> args) {
        Jedis jedis = getJedis();
        Object result = jedis.eval(script, keys, args);
        jedis.close();
        return result;
    }

    public String get(final String key){
        Jedis jedis = getJedis();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    public String set(final String key, final String value, final String nxxx, final String expx, final int time) {
        Jedis jedis = getJedis();
        String result = jedis.set(key, value, nxxx, expx, time);
        jedis.close();
        return result;
    }

    private void init(){
        System.err.println();
        Properties redisConfig = PropsUtil.loadProps("redis.properties");
        int maxTotal = PropsUtil.getInt(redisConfig,"maxTotal",10);
        String ip = PropsUtil.getString(redisConfig,"ip","127.0.0.1");
        int port = PropsUtil.getInt(redisConfig,"port",6379);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        pool = new JedisPool(jedisPoolConfig, ip,port);
        String msg = String.format("连接池初始化成功 ip=%s, port=%s, maxTotal=%s",ip,port,maxTotal);
        System.err.println(msg);
    }

    private Jedis getJedis(){
        return pool.getResource();
    }
}
