package com.hit.wangoceantao.concurrent;

/**
 * Created by wanghaitao on 16/9/4.
 */
public class SynchroniszedStudy {
    private static volatile int count = 0;

    public static void main(String[] args) {
        new ThreadCount("thread1_").start();
        new ThreadCount("thread2_").start();
        SynchroniszedStudy.staticSynchronized();
    }

    static class ThreadCount extends Thread {
        private String name;

        public ThreadCount(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            SynchroniszedStudy object2 = new SynchroniszedStudy();
            object2.getCount(name);
        }
    }

    public synchronized void getCount(String name) {
        try {
            Thread.sleep(2000);
            System.out.println(name + " start");
            Thread.sleep(2000);
            count = count + 1;
            System.out.println(name + count);
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(name + " end");
        }
    }

    public static synchronized void staticSynchronized() {
        System.out.println("staticSynchronized");
    }

}
