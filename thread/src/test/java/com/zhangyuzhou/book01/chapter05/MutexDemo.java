package com.zhangyuzhou.book01.chapter05;

import java.util.Collection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zhangyuzhou
 * @description
 * @date DATE:2021-02-24 13:04
 */
public class MutexDemo implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {

        //当前状态为0时获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            if (super.compareAndSetState(0, 1)) {
                super.setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //释放锁
        @Override
        protected boolean tryRelease(int arg) {
            if (super.getState() == 0) {
                throw new IllegalMonitorStateException("当前status状态已经为0");
            }
            super.setState(0);
            super.setExclusiveOwnerThread(null);
            return true;
        }

        //是否处于独占状态
        @Override
        protected boolean isHeldExclusively() {
            return super.getState() == 1;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public Collection<Thread> getQueuedThreads() {
        return sync.getQueuedThreads();
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        Thread thread1 = new Thread(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName() + "获取到锁");
            try {
                TimeUnit.SECONDS.sleep(180);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName() + "获取到锁");
        }, "Thread-2");
        thread2.start();

        Thread thread3 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println(Thread.currentThread().getName() + "获取到锁");
        }, "Thread-3");
        thread3.start();

        Thread thread4 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                System.err.println(semaphore.getQueueLength());
            }
        }, "Thread-4");
        thread4.start();

        TimeUnit.SECONDS.sleep(1000000);
    }
}
