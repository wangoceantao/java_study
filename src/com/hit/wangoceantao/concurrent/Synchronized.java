package com.hit.wangoceantao.concurrent;

/**
 * Created by zhangnannan on 17/2/22.
 */
public class Synchronized {
    public static void main(String[] args) {
        synchronized (Synchronized.class) {

        }
        test();

    }

    private static synchronized void test() {
    }
}
