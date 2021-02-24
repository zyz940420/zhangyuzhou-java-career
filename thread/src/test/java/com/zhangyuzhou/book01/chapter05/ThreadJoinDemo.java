package com.zhangyuzhou.book01.chapter05;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangyuzhou
 * @description
 * @date DATE:2021-02-24 16:00
 */
public class ThreadJoinDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread current = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new JoinDemoJob(current), "thread:" + i);
            thread.start();
            current = thread;
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class JoinDemoJob implements Runnable {

        private Thread thread;

        public JoinDemoJob(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
                System.err.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
