import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wanghaitao on 16/8/30.
 */
public class ReentrantLockStudy {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new ReentrantLockThread().start();
        new ReentrantLockThread().start();

    }

    static class ReentrantLockThread extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    PrintUtils.print(Thread.currentThread().getId());
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
