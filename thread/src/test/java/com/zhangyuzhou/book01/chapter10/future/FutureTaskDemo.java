package com.zhangyuzhou.book01.chapter10.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        runnableTest();
        callableTask();
    }

    private static void callableTask() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000L);
                return 999;
            }
        });

        Thread thread = new Thread(futureTask);
        thread.start();
        System.err.println(futureTask.isDone());
        long start = System.currentTimeMillis();
        Integer result = futureTask.get();
        System.err.println(futureTask.isDone() + " " + result);
        System.err.println("耗时：" + String.valueOf(System.currentTimeMillis() - start));
    }

    private static void runnableTest() throws ExecutionException, InterruptedException {

        FutureTask<String> future = new FutureTask<String>(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {

                }
            }
        }, "future");

        Thread thread = new Thread(future);
        thread.start();
        System.err.println(future.isDone());
        long start = System.currentTimeMillis();
        String result = future.get();
        System.err.println(future.isDone() + " " + result);
        System.err.println("耗时：" + String.valueOf(System.currentTimeMillis() - start));
    }
}
