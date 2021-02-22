package com.zhangyuzhou.book01.chapter01;

/**
 * 死锁描述：
 * A线程持有 RESOURCE_A 等待 RESOURCE_B
 * B线程持有 RESOURCE_B 等待 RESOURCE_A
 *
 * 解决办法：
 * - 避免一个线程同时获取多个锁
 * - 避免一个线程在锁内同时占用多个资源，尽量保证每个锁只占用一个资源
 * - 锁资源的顺序尽量一致
 * - 尝试使用定时锁，使用lock.tryLock（timeout）来替代使用内部锁机制
 */
public class DeadLockDemo {

    private static final String RESOURCE_A = "RESOURCE_A";

    private static final String RESOURCE_B = "RESOURCE_B";

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            System.err.println("threadA尝试获取：" + DeadLockDemo.RESOURCE_B);
            synchronized (DeadLockDemo.RESOURCE_B) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("threadA获取到:" + DeadLockDemo.RESOURCE_B);
                System.err.println("threadA尝试获取:" + DeadLockDemo.RESOURCE_A);
                synchronized (DeadLockDemo.RESOURCE_A) {
                    System.err.println("threadA获取到:" + DeadLockDemo.RESOURCE_A);
                }
            }
        });

        Thread threadB = new Thread(() -> {
            System.err.println("threadB尝试获取：" + DeadLockDemo.RESOURCE_A);
            synchronized (DeadLockDemo.RESOURCE_A) {
                System.err.println("threadB获取到:" + DeadLockDemo.RESOURCE_A);
                System.err.println("threadB尝试获取:" + DeadLockDemo.RESOURCE_B);
                synchronized (DeadLockDemo.RESOURCE_B) {
                    System.err.println("threadB获取到:" + DeadLockDemo.RESOURCE_B);
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
