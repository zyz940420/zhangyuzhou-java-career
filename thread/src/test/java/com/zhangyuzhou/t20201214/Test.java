package com.zhangyuzhou.t20201214;

/**
 * @author zhangyuzhou
 * @description
 * @date DATE:2021-02-05 16:30
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1= new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("thread1 ");
            }
        });

        Thread thread2= new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("thread2 ");
            }
        });

        thread1.start();
        thread2.start();

//        thread1.join();
//        thread2.join();

        Thread.sleep(100000000000L);
    }

}
