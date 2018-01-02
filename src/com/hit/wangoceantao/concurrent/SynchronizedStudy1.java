package com.hit.wangoceantao.concurrent;

/**
 * @Description: ${TODO}
 * <p/>
 * Created by wanghaitao on 16/5/16 22:46.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class SynchronizedStudy1 extends Thread {
    private static Object lock = new Object();
    public static int count = 0;

    private void increase() {
        synchronized (lock) {
            count++;
        }
    }

    @Override
    public void run() {
        for (int index = 0; index < 10; index++) {
            increase();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        int length = threads.length;
        for (int index = 0; index < length; index++) {
            threads[index] = new SynchronizedStudy1();
        }
        for (int index = 0; index < length; index++) {
            threads[index].start();
        }
        for (int index = 0; index < length; index++) {
            threads[index].join();
        }
        System.out.print("count:" + count);
    }
}
