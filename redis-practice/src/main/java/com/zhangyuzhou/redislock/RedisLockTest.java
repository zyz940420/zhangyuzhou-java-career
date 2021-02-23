package com.zhangyuzhou.redislock;

import com.zhangyuzhou.common.Constants;
import com.zhangyuzhou.common.JedisConnectionUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisLockTest implements Constants {

    private static final ThreadLocal<String> REQUEST_ID_MAP = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                MyRedisLock myRedisLock = MyRedisLockImpl.getInstance();
                System.err.println("t1尝试");
                String s = myRedisLock.lockAndRetry(REDIS_LOCK_KEY);
                System.err.println(s);
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myRedisLock.unLock(REDIS_LOCK_KEY,s);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MyRedisLock myRedisLock = MyRedisLockImpl.getInstance();
                System.err.println("t2尝试");
                String s = myRedisLock.lockAndRetry(REDIS_LOCK_KEY);
                System.err.println(s);
                myRedisLock.unLock(REDIS_LOCK_KEY,s);
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MyRedisLock myRedisLock = MyRedisLockImpl.getInstance();
                System.err.println("t3尝试");
                String s = myRedisLock.lockAndRetry(REDIS_LOCK_KEY);
                System.err.println(s);
                myRedisLock.unLock(REDIS_LOCK_KEY,s);
            }
        });


        t1.start();
        t2.start();
        t3.start();
    }
}
