package com.zhangyuzhou.book01.chapter07.array;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子操作数据
 */
public class ArrayTypeAtomicDemo {

    private static final int[] value = {0, 1, 2, 3, 4, 5, 6};

    private static final AtomicIntegerArray array = new AtomicIntegerArray(value);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 7; i++) {
            Thread thread = new Thread(new ArrayTypeAtomicJob(i, i + 1), "Thread" + i);
            thread.start();
        }
    }

    static class ArrayTypeAtomicJob implements Runnable {
        private int index;

        private int value;

        public ArrayTypeAtomicJob(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public void run() {
            array.getAndSet(index, value);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String msg = String.format("当前线程【%s】，数组结果【%s】", Thread.currentThread().getName(), array);
            System.err.println(msg);
        }
    }
}
