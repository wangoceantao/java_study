package com.hit.wangoceantao.concurrent.latch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wanghaitao on 17/3/1.
 */
public class LatchByReetrantLock implements LatchInterface {
    private int count;
    private ReentrantLock lock;
    private Condition condition;


    public LatchByReetrantLock(int count) {
        this.count = count;
        this.lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    @Override
    public void countDown() {
        lock.lock();
        try {
            count--;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void await() {
        lock.lock();
        try {
            while (count > 0) {
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
