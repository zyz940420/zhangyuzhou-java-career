package com.zhangyuzhou.book01.chapter02;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author zhangyuzhou
 * @description
 * @date DATE:2021-02-10 23:15
 */
public class LinkedTransferQueueDemo {
    private static LinkedTransferQueue queue = new LinkedTransferQueue();

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            queue.add(i);
        }
        System.err.println(queue);
    }
}
