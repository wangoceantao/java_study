package concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhangnannan on 16/11/26.
 */
public class CountPrinter {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors:" + availableProcessors);
    }

    private class OddNumberRunnable implements Runnable {
        private AtomicInteger atomicInteger;

        public OddNumberRunnable(AtomicInteger atomicInteger) {
            this.atomicInteger = atomicInteger;
        }

        @Override
        public void run() {

        }
    }
}
