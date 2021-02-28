package com.zhangyuzhou.book01.chapter04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程优先级
 * 线程优先级不能保证线程执行的先后顺序 默认线程的优先级为5
 */
public class PriorityDemo {

    private static volatile boolean notStart = true;

    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws InterruptedException {
        List<PriorityJob> jobList = new ArrayList<PriorityJob>();

        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            PriorityJob priorityJob = new PriorityJob(priority);
            jobList.add(priorityJob);

            Thread thread = new Thread(priorityJob, "Thread:" + i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;

        for (PriorityJob priorityJob : jobList) {
            String msg = String.format("PriorityJob priority is [%s] jobCount is [%s]", priorityJob.priority, priorityJob.jobCount);
            System.err.println(msg);
        }
        TimeUnit.SECONDS.sleep(10000);
    }

    static class PriorityJob implements Runnable {

        private int priority;

        private long jobCount;

        public PriorityJob(int priority) {
            this.priority = priority;
        }

        public void run() {
            while (notStart) {
                Thread.yield();
            }

            while (notEnd) {
                Thread.yield();
                jobCount++;
            }
        }
    }
}


