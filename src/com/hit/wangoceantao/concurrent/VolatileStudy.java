package com.hit.wangoceantao.concurrent;

/**
 * Created by wanghaitao on 16/9/9.
 */
public class VolatileStudy {
    private static  int count = 0;

    public static void increase() {
        synchronized (VolatileStudy.class) {
            count++;
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int index = 0; index < threads.length; index++) {
            threads[index] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int index = 0; index < 1000; index++) {
                        increase();
                    }
                }
            });
            threads[index].start();
        }
        int index=0;
        while (index < 10) {
            try {
                threads[index].join();
                index++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("count:" + count);
    }
}
