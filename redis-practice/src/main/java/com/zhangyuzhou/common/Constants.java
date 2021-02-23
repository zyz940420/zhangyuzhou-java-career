package com.zhangyuzhou.common;

public interface Constants {
    /**
     * redis管道
     */
    String CHANNEL = "channel";
    /**
     * 分布式锁KEY
     */
    String REDIS_LOCK_KEY = "REDIS_LOCK_KEY";
    /**
     * 锁超时时间
     */
    long expireTime = 10000000L;
    /**
     * 获取锁超时时间
     */
    long aquireTimeOur = 9999999;
}
