package com.zhangyuzhou.common;

import java.util.concurrent.locks.ReentrantLock;

public class Test {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(10000000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
            }
        });

        thread1.start();
        thread2.start();
    }
}
