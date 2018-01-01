package concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by zhangnannan on 16/11/26.
 */
public class BoundedHashSet<T> {
    private Set<T> set;
    private Semaphore sem;

    public BoundedHashSet(int count) {
        set = Collections.synchronizedSet(new HashSet<>());
        sem = new Semaphore(count);
    }

    public boolean add(T entry) throws InterruptedException {
        sem.acquire();
        boolean add = false;
        try {
            add = set.add(entry);
            return add;
        } finally {
            if (!add) {
                sem.release();
            }
        }
    }

    public boolean remove(T entry) {
        boolean wasRemoved = set.remove(entry);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }


}
