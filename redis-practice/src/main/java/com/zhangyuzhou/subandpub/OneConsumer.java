package com.zhangyuzhou.subandpub;

import com.zhangyuzhou.common.Constants;
import com.zhangyuzhou.common.JedisConnectionUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class OneConsumer implements Constants, Runnable {

    @Override
    public void run() {
        String msg = String.format("订阅者OneConsumer阻塞在--> %s", CHANNEL);
        System.out.println(msg);
        JedisPool jedisPool = JedisConnectionUtil.getJedisPool();
        Jedis jedis = jedisPool.getResource();
        ;
        try {
            //通过subscribe的api去订阅，入参是订阅者和频道名
            jedis.subscribe(new Subscriber(), CHANNEL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisConnectionUtil.closeJedis(jedis);
        }
    }
}
