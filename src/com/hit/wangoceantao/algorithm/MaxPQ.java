package com.hit.wangoceantao.algorithm;


/**
 * Created by wanghaitao on 17/3/20.
 */
public class MaxPQ<ENTITT extends Comparable<ENTITT>> extends BasePG<ENTITT> {
    public MaxPQ(int maxCount) {
        if (maxCount < 0) {
            throw new IllegalArgumentException("maxCount can not < 0");
        }
        values = (ENTITT[]) new Comparable[maxCount + 1];
    }

    public void insert(ENTITT value) {
        values[++N] = value;
        swim(N);
    }

    public boolean isEmpty() {
        return N != 0;
    }

    public int size() {
        return N;
    }

    public ENTITT top() {
        return values[1];
    }

    public ENTITT delMax() {
        ENTITT max = values[1];
        exch(1, N--);
        sink(1);
        values[N + 1] = null;
        return max;
    }

    void swim(int index) {
        while (index / 2 > 0 && less(index / 2, index)) {
            exch(index / 2, index);
            index = index / 2;
        }
    }

    void sink(int index) {
        while (index * 2 < N) {
            int k = index * 2;
            if (k < N && less(k, k + 1)) {
                k++;
            }
            if (!less(index, k)) {
                break;
            }
            exch(k, index);
            index = k;
        }
    }


    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>(2000);
        for (int index = 100; index > 0; index--) {
            maxPQ.insert(index);
        }
        for (int index = 200; index > 0; index--) {
            maxPQ.insert(index);
        }
        System.out.println("---------------------------");
        for (int index = 0; index < 4; index++) {
            System.out.println(maxPQ.delMax());
        }
        System.out.println("size:" + maxPQ.size());
    }
}
