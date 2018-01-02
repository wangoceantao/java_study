package com.hit.wangoceantao.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangnannan on 17/2/22.
 */
public class NotifyAndWait {
    private static Object lock = new Object();
    private static boolean flag = true;

    public static void main(String[] args) {
        new Thread(new WaitRunnable(), "WaitThread").start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new NotifyRunnable(), "NotifyThread").start();
    }

    static class WaitRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    System.out.println(Thread.currentThread() + "flag is true waiting @" + new SimpleDateFormat("HH:MM:SS").format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread() + "flag is false running @" + new SimpleDateFormat("HH:MM:SS").format(new Date()));

            }
        }
    }

    static class NotifyRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock @" + new SimpleDateFormat("HH:MM:SS").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock again @" + new SimpleDateFormat("HH:MM:SS").format(new Date()));

            }
        }
    }
}
