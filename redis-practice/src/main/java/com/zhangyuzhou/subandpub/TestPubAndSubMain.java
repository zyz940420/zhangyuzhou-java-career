package com.zhangyuzhou.subandpub;

public class TestPubAndSubMain {

    public static void main(String[] args) {
        Thread oneThread = new Thread(new OneConsumer());
        oneThread.start();

        Thread pubThread = new Thread(new Publisher());
        pubThread.start();

    }
}
