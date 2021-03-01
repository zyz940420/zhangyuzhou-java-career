package com.zhangyuzhou.book01.chapter08;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 主线程、子线程执行由CPU调度完成
 */
public class CyclicBarrierDemo01 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            Thread thread = new Thread(new CyclicBarrierJob(), "thread" + i);
            thread.start();
        }
        System.err.printf("线程【%s】已经执行%n", Thread.currentThread().getName());
    }

    static class CyclicBarrierJob implements Runnable {

        @Override
        public void run() {
            try {
                System.err.printf("线程【%s】即将执行await%n", Thread.currentThread().getName());
                cyclicBarrier.await();
                System.err.printf("线程【%s】从await返回%n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
