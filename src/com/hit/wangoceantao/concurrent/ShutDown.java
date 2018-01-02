package com.hit.wangoceantao.concurrent;

/**
 * Created by zhangnannan on 17/2/22.
 */
public class ShutDown {
    public static void main(String[] args) {
        Runner runner = new Runner();
        Thread thread = new Thread(runner);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runner.cancel();
        System.out.println("thread interrupt:" + thread.isInterrupted());

    }

    static class Runner implements Runnable {
        private volatile boolean stop = false;
        private int count = 0;

        public void cancel() {
            stop = true;
        }

        @Override
        public void run() {
            while (!stop && !Thread.interrupted()) {
                count++;

            }
            System.out.println(count);
        }
    }
}
