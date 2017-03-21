package algorithm;

/**
 * Created by wanghaitao on 17/3/20.
 */
public class BasePG<ENTITT extends Comparable<ENTITT>> {
    ENTITT[] values;
    int N;

    boolean less(int i, int j) {
        return values[i].compareTo(values[j]) < 0;
    }

    void exch(int i, int j) {
        ENTITT t = values[i];
        values[i] = values[j];
        values[j] = t;
    }
}