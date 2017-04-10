import java.security.NoSuchAlgorithmException;

/**
 * @Description: ${TODO}
 * <p>
 * Created by wanghaitao on 2017/4/1 16:37.
 * <p>
 * Email：wanghaitao01@hecom.cn
 */

public class CountWork implements Runnable {
    int count = 0;
    int threadCount;
    Thread[] threads;
    boolean open = false;

    public CountWork(int threadCount) {
        this.threadCount = threadCount;
        threads = new Thread[threadCount];
    }

    @Override
    public void run() {

        for (int index = 0; index < threadCount; index++) {
            Thread thread = new Worker("index:" + index);
            threads[index] = thread;
            thread.start();
        }
    }

    private class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!open) {
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

    public void open() {
        open = true;
    }

    public int getCount() throws InterruptedException {
        for (int index = 0; index < threadCount; index++) {
            threads[index].join();
        }
        return count;
    }


    public static void main(String[] args) {
        final CountWork countWork = new CountWork(100);
        new Thread(countWork).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countWork.open();
            }

        }).start();

    }


}
