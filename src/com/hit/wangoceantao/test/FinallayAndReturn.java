package com.hit.wangoceantao.test;

/**
 * Created by wanghaitao on 17/3/7.
 */
public class FinallayAndReturn {

    public static void main(String[] args) {
        System.out.println("print:" + test());
    }

    private static int test() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }
}
