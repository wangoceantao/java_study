package com.hit.wangoceantao.string;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description: ${TODO}
 * <p/>
 * Created by wanghaitao on 16/5/6 20:23.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class Test {
    public static void main(String[] arg) {
        Integer a = 4;
        test(a);
        System.out.println("a:" + a);
        String string = "com/hit/wangoceantao/test";
        test(string);
        System.out.println("com.hit.wangoceantao.string:" + string);
        productAndConsume();
    }

    private static void test(Integer a) {
        a = 10;
    }

    private static void test(String a) {
        a = "12455";
    }

    public static void productAndConsume() {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(3);
        ProductThread productThread = new ProductThread(linkedBlockingQueue);
        ConsumeThread consumeThread = new ConsumeThread(linkedBlockingQueue);
        productThread.start();
        consumeThread.start();
        Singleton.INSTANCE.getName();

    }

    static class ProductThread extends Thread {
        LinkedBlockingQueue linkedBlockingQueue;

        public ProductThread(LinkedBlockingQueue linkedBlockingQueue) {
            this.linkedBlockingQueue = linkedBlockingQueue;
        }

        @Override
        public void run() {
            try {
                for (int index = 0; index < 10; index++) {
                    System.out.println("Produced: " + index);
                    linkedBlockingQueue.put(index);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class ConsumeThread extends Thread {
        LinkedBlockingQueue linkedBlockingQueue;

        public ConsumeThread(LinkedBlockingQueue linkedBlockingQueue) {
            this.linkedBlockingQueue = linkedBlockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Consumed: " + linkedBlockingQueue.take());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
