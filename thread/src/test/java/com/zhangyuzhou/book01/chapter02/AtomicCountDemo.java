package com.zhangyuzhou.book01.chapter02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangyuzhou
 * @description 原子操作累加
 * @date DATE:2021-02-11 9:10
 */
public class AtomicCountDemo {

    private static int sum = 0;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();

        long begin = System.currentTimeMillis();
        List<Thread> threadList = new ArrayList<>(300);
        for (int i = 0; i < 300; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    count();
                    safeCount(atomicInteger);
                }
            }, i + "线程");
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();
        }
        System.err.println("sum的值:" + sum);
        System.err.println("atomic的值:" + atomicInteger.get());
        System.err.println("耗时:" + (System.currentTimeMillis() - begin));
    }

    private static void safeCount(AtomicInteger atomicInteger) {
        while (true) {
            int temp = atomicInteger.get();
            boolean flag = atomicInteger.compareAndSet(temp, ++temp);
            if (flag) {
                break;
            }
        }
    }

    private static void count() {
        sum++;
    }
}
