package com.zhangyuzhou.book01.chapter01;

import org.junit.Test;

/**
 * 结论：
 * TOTAL = 100000001 时，耗时差不到
 * TOTAL < 100000001 时，串行<并发
 * TOTAL > 100000001 时，串行>并发
 */
public class ThreadContextDemo {

    private static final long TOTAL = 10000001L;

    @Test
    public void testSpeed() throws InterruptedException {
        this.singleSpeed();
        this.multiSpeed();
        Thread.sleep(20000000L);
    }

    private void multiSpeed() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread = new Thread(() -> {
            int a = 0;
            for (int i = 0; i < ThreadContextDemo.TOTAL; i++) {
                a += 5;
            }
        });
        thread.start();
        //模拟thread线程 和主线程切换 当总数10000时，单线程比多线程更快
        int b = 0;
        for (int i = 0; i < ThreadContextDemo.TOTAL; i++) {
            b--;
        }
        thread.join();
        String time = String.valueOf(System.currentTimeMillis() - start);

        System.err.println("multiSpeed执行完成耗时：" + time);
        System.err.println("multiSpeed执行完成时b的值为：" + b);
    }

    private void singleSpeed() {
        //单线程不切换线程的上下文
        long start = System.currentTimeMillis();
        int a = 0;
        int b = 0;
        for (int i = 0; i < ThreadContextDemo.TOTAL; i++) {
            a += 5;
        }
        for (int i = 0; i < ThreadContextDemo.TOTAL; i++) {
            b--;
        }

        String time = String.valueOf(System.currentTimeMillis() - start);
        System.err.println("singleSpeed执行完成耗时：" + time);
        System.err.println("singleSpeed执行完成时a的值：" + a);
        System.err.println("singleSpeed执行完成时b的值：" + b);
    }
}
