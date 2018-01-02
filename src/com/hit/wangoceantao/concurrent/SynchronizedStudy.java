package com.hit.wangoceantao.concurrent;

/**
 * @Description: ${TODO}
 * <p/>
 * Created by wanghaitao on 16/5/16 22:46.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class SynchronizedStudy implements Runnable {
    public static int count = 0;

    private synchronized void increase() {
        count++;
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
        SynchronizedStudy synchronizedStudy = new SynchronizedStudy();
        for (int index = 0; index < length; index++) {
            threads[index] = new Thread(synchronizedStudy);
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
