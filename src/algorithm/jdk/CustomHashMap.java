package algorithm.jdk;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by wanghaitao on 17/12/25.
 */
public class CustomHashMap<K, V> implements IMap<K, V> {
    Entry<K, V>[] table;
    int threshold;
    float loadFactor;
    int size;

    public CustomHashMap() {
        this(8, 0.75F);
    }

    public CustomHashMap(int capacity, float loadFactor) {
        table = new Entry[capacity];
        threshold = (int) (capacity * loadFactor);

    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            putNullKey();
        }
        int hash = hash(key.hashCode());
        int index = indexFor(hash, table.length);
        for (Entry<K, V> e = (Entry) table[index]; e != null; e = e.next) {
            if (e.hash == hash && (e.key.equals(key))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        addNewEntry(hash, key, value, index);
        return null;
    }

    private void addNewEntry(int hash, K key, V value, int index) {
        Entry<K, V> e = table[index];
        table[index] = new Entry<>(hash, key, value, e);
        if (++size > threshold) {
            int newCapacity = 2 * table.length;
            resize(newCapacity);
            threshold = (int) (loadFactor * newCapacity);
        }
    }

    private void resize(int newCapacity) {
        Entry<K, V>[] newTable = new Entry[newCapacity];
        for (int index = 0; index < table.length; index++) {
            Entry<K, V> e = table[index];
            while (e != null) {
                int newPos = indexFor(e.hash, newCapacity);
                Entry next = e.next;
                Entry tmp = newTable[newPos];
                newTable[newPos] = e;
                e.next = tmp;
                e = next;
            }
        }
        table = newTable;
    }

    private void putNullKey() {

    }

    private int hash(int hash) {
        return hash ^ (hash >>> 16);
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }


    @Override
    public V get(K key) {
        int hash = hash(key.hashCode());
        int index = indexFor(hash, table.length);
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (key.hashCode() == hash && key.equals(e.key)) {
                return e.value;
            }
        }
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new MapIterator();
    }

    private class MapIterator implements Iterator<Entry<K, V>> {
        private Entry next = null;
        private int index = 0;

        public MapIterator() {
            findNextHead();
        }

        private void findNextHead() {
            while (index < table.length && next == null) {
                next = table[index];
                index++;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Entry<K, V> next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            Entry e = next;
            if ((next = e.next) == null) {
                findNextHead();
            }
            return e;

        }
    }

    ;

    private static class Entry<K, V> {
        int hash;
        K key;
        V value;
        Entry next;

        public Entry(int hash, K key, V value, Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        CustomHashMap<Integer, String> customHashMap = new CustomHashMap<>();
        customHashMap.put(1, "w");
        customHashMap.put(2, "a");
        customHashMap.put(3, "n");
        customHashMap.put(4, "g");
        customHashMap.put(9, "hai");
        customHashMap.put(10, "tao");
        customHashMap.put(10, "tao_1");
//        customHashMap.put(10, "tao_1");
        Iterator<Entry<Integer, String>> iterator = customHashMap.iterator();
        while (iterator.hasNext()) {
            Entry<Integer, String> entry = iterator.next();
            System.out.println("key:" + entry.getKey() + ";value:" + entry.value);
        }
        System.out.println("-- test get ----");
        System.out.println("key:3" + ";value:" + customHashMap.get(3));
        System.out.println("key:2" + ";value:" + customHashMap.get(2));
        System.out.println("key:0" + ";value:" + customHashMap.get(0));
        System.out.println("key:10" + ";value:" + customHashMap.get(10));
    }
}
