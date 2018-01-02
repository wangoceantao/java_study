package com.hit.wangoceantao.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by wanghaitao on 16/9/5.
 */
public class TwinsLock implements Lock {
    private Sync sync = new Sync(3);

    private final static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int num) {
            setState(num);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }

        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        TwinsLock locker = new TwinsLock();
        class Worker implements Runnable {
            @Override
            public void run() {
                while (true) {
                    locker.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    } finally {
                        locker.unlock();
                    }
                }
            }
        }

        for (int index = 0; index < 10; index++) {
            Thread thread = new Thread(new Worker());
            thread.setDaemon(true);
            thread.start();
        }
        for (int index = 0; index < 10; index++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

    }
}
