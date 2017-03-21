package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;

/**
 * Created by wanghaitao on 16/9/6.
 */
public class CyclicBarrierStudy {
    public static void main(String[] args) throws InterruptedException {
//        cyclicBarrierTest();
//        countDownLatchTest();
        exchangeTest();
    }

    private static void exchangeTest() {
        final Exchanger<String> exchanger = new Exchanger<String>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String to = "to_123";
                String result = "";
                try {
                    result = exchanger.exchange(to);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("result:" + result);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String to = "456";
                String result = "";
                try {
                    Thread.sleep(2000);
                    result = exchanger.exchange(to);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void countDownLatchTest() throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        System.out.println("countDownLatch main thread start");
        Thread.sleep(2000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("countDownLatch sub thread start");
                    Thread.sleep(1000);
                    countDownLatch.countDown();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("countDownLatch sub thread end");
            }
        }).start();
        countDownLatch.await();
        Thread.sleep(2000);
        System.out.println("countDownLatch main thread end");
    }

    private static void cyclicBarrierTest() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("sub thread start");

                try {
                    Thread.sleep(1000);

                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("sub thread end");

            }
        }).start();
        try {
            System.out.println("main thread start");
            Thread.sleep(3000);
            cyclicBarrier.await();
            System.out.println("main thread end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
