package com.hit.wangoceantao.concurrent;

/**
 * Created by wanghaitao on 16/9/1.
 */
public class DeadLockDemo {
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new DeadLockDemo().deadLock();
            }
        },"thread deadlock").start();

    }

    private void deadLock() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    try {
                        System.out.println("thread 1 start");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("thread 1 end");
                    }
                }

            }
        },"thread-1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    synchronized (lock1) {
                        System.out.println("thread 2");
                    }
                }

            }
        },"thread-2");
        thread1.start();
        thread2.start();
    }
}
