package com.hit.wangoceantao.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wanghaitao on 2016/11/9.
 */
public class ReentrantLockStudy {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();

    public void read() {
        try {
            readLock.lock();
            System.out.print(Thread.currentThread().getName() + " read  --start--\n");
            int index = 0;
            while (index < 10) {
                System.out.print(Thread.currentThread().getName() + "read\n");
                index++;
                Thread.sleep(100);
            }
            System.out.print(Thread.currentThread().getName() + " read  --end--\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }

    }

    public void write() {
        try {
            writeLock.lock();
            System.out.print(Thread.currentThread().getName() + " write --start--\n");
            Thread.sleep(1000);
            System.out.print(Thread.currentThread().getName() + " write --end--\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockStudy reentrantLockStudy = new ReentrantLockStudy();
        Thread writeThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockStudy.write();
            }
        }, "write_1");
        Thread writeThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockStudy.write();
            }
        }, "write_2");
        Thread readThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockStudy.read();
            }
        }, "read_1");
        Thread readThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockStudy.read();
            }
        }, "read_2");
        readThread1.start();
        writeThread1.start();
        writeThread2.start();
        readThread2.start();
    }
}
