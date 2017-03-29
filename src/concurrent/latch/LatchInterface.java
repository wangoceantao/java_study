package concurrent.latch;

/**
 * Created by wanghaitao on 17/3/1.
 */
public interface LatchInterface {
    void countDown();

    void await() throws InterruptedException;
}
