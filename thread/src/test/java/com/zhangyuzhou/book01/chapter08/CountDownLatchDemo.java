package com.zhangyuzhou.book01.chapter08;

import java.util.concurrent.CountDownLatch;

/**
 * 当for循环里面的次数取不同的值是，会有不同的结果输出
 */
public class CountDownLatchDemo {

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new CountDownLatchJob(), "thread-" + i);
            thread.start();
        }
        countDownLatch.await();
        System.err.printf("线程【%s】从await返回%n", Thread.currentThread().getName());
    }

    static class CountDownLatchJob implements Runnable {

        @Override
        public void run() {
            countDownLatch.countDown();
            System.err.printf("线程【%s】执行countDown%n", Thread.currentThread().getName());
        }
    }
}
