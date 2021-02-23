package com.zhangyuzhou.subandpub;

import com.zhangyuzhou.common.Constants;
import com.zhangyuzhou.common.JedisConnectionUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Publisher implements Constants, Runnable {

    public void run() {
        JedisPool jedisPool = JedisConnectionUtil.getJedisPool();
        Jedis jedis = jedisPool.getResource();

        System.err.println("请输入一条发布消息：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    //从 CHANNEL 的频道上推送消息
                    jedis.publish(CHANNEL, line);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                JedisConnectionUtil.closeJedis(jedis);
            }
        }
    }
}
