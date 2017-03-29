package concurrent.latch;

/**
 * Created by wanghaitao on 17/3/1.
 */
public class LatchByWaitNotify implements LatchInterface {
    private int count;

    public LatchByWaitNotify(int count) {
        this.count = count;
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (count > 0) {
                wait();
            }
        }
    }

    public void countDown() {
        synchronized (this) {
            count--;
            if (count <= 0) {
                notifyAll();
            }
        }
    }

}
