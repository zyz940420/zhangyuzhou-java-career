package com.zhangyuzhou.book01.chapter07.basic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基本类型原子操作类
 */
public class BaseTypeAtomicDemo {
    /**
     * 原子操作类特性和原理
     * 1、线程安全
     * 2、采用CAS+自旋实现
     */
    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new BaseTypeAtomicJob(), "Work01");
        Thread threadTwo = new Thread(new BaseTypeAtomicJob(), "Work02");
        threadOne.start();
        threadTwo.start();

        /*
         * 1、Thread.join() 底层还是调用线程对象的await()方法
         * 2、调用join()方法的线程执行完成后，会调用该想成对象的notifyAll()方法，通知该线程返回
         */
        threadOne.join();
        threadTwo.join();
        System.err.println(atomicInteger.get());
        System.err.println(Thread.currentThread().getName() + "执行完成");
    }



    static class BaseTypeAtomicJob implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                atomicInteger.getAndAdd(1);
            }
            System.err.println(Thread.currentThread().getName() + "执行完成");
        }
    }
}
