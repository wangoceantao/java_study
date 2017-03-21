package test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wanghaitao on 2016/10/31.
 */
public class Tester {
    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        System.out.print("test");
//        System.out.print("test");
//        loadRemoteIMSecCards(new int[]{0, 1, 2}, System.currentTimeMillis());
//        String test = "f566036410e4f89d8c970b9ce99cf0ffa22a7805";
//        System.out.println("git sha-1 length:" + test.length());
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReentrantLock lock = new ReentrantLock();
                for (int index = 0; index < 10; index++) {
                    new SleepThread(lock, "thread_" + index).start();
                    synchronized (lock) {
                        try {
                            lock.wait();
                            System.out.println("next:" + (index + 1));
                        } catch (InterruptedException e) {
                        }
                    }

                }
            }
        }).start();

    }

    static class SleepThread extends Thread {
        private Object lock;

        public SleepThread(Object lock, String name) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "start");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "end");
                synchronized (lock) {
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void loadRemoteIMSecCards(int[] types, long timeStamp) {
//        JSONArray typeArray = new JSONArray();
//        if (EmptyUtils.isNotEmpty(types)) {
//            for (int type : types) {
//                typeArray.put(String.valueOf(type));
//            }
//        }
//        System.out.println("typeArray: \n"+typeArray);
    }
}
