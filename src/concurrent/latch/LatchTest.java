package concurrent.latch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wanghaitao on 17/3/1.
 */
public class LatchTest {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 1000;
        countdownSelf(threadCount, new LatchByWaitNotify(threadCount), LatchByWaitNotify.class.getSimpleName());
        countdownSelf(threadCount, new LatchByReetrantLock(threadCount), LatchByReetrantLock.class.getSimpleName());
        countdownSystem(threadCount);
    }

    private static void countdownSelf(int threadCount, LatchInterface latch, String latchName) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        CountDownLatch swith =new CountDownLatch(1);
        for (int index = 0; index < threadCount; index++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        swith.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    doSomething();
                    latch.countDown();
                }
            }, "thread-" + index).start();
        }
        swith.countDown();
        latch.await();
        long duration = System.currentTimeMillis() - startTime;
        System.out.println(latchName + "--" + "end" + duration);
    }

    private static void countdownSystem(int threadCount) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(threadCount);
        CountDownLatch swith =new CountDownLatch(1);
        for (int index = 0; index < threadCount; index++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        swith.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    doSomething();
                    latch.countDown();
                }
            }, "thread-" + index).start();
        }
        swith.countDown();
        latch.await();
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("system--" + "end" + duration);
    }

    private static void doSomething() {
//        try {
//            Thread.sleep(100);
//            //System.out.println(Thread.currentThread().getName());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
