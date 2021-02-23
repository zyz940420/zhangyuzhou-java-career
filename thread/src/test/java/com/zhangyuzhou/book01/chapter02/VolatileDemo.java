package com.zhangyuzhou.book01.chapter02;

/**
 * @author zhangyuzhou
 * @description volatile 关键概念
 * @date DATE:2021-02-10 23:23
 */
public class VolatileDemo {

    /**
     *
     */
    private static Integer score = 100;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                score += 10;
                System.err.println(Thread.currentThread().getName() + ":" + score);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, i + "线程");
            thread.start();
//            thread.join();
        }

        score += 100;
        System.err.println(Thread.currentThread().getName() + ":" + score);
    }
}
