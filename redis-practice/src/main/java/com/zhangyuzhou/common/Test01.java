package com.zhangyuzhou.common;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test01 {

//    private static ReentrantLock lock = new ReentrantLock();

//    private static Condition one = lock.newCondition();

//    private static Condition two = lock.newCondition();

    private static volatile ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(20);

    public static void main(String[] args) {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        queue.put(new Object());
                        System.err.println("生产了一个Object");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        queue.take();
                        System.err.println("消费了一个Object");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        producer.start();
//        consumer.start();
    }
}
