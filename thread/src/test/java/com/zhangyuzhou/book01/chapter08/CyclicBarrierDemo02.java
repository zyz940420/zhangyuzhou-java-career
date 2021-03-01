package com.zhangyuzhou.book01.chapter08;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 分段统计实现
 */
public class CyclicBarrierDemo02 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    private static Executor executor = Executors.newFixedThreadPool(4);

    private static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //计算流水，存放在concurrentHashMap
                    concurrentHashMap.put(Thread.currentThread().getName(), 10);
                    //存放完成后，应该等待其他线程也完成
                    try {
                        System.err.println(concurrentHashMap);
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.err.println(concurrentHashMap);
        //主线程负责统计汇总
        AtomicInteger result = new AtomicInteger();
        concurrentHashMap.forEach((k, v) -> {
            result.addAndGet(v);
        });
        concurrentHashMap.put(Thread.currentThread().getName(), result.intValue());
        System.err.println(concurrentHashMap);
    }
}
