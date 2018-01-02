package com.hit.wangoceantao.model;

import java.lang.invoke.MethodType;

/**
 * Created by zhangnannan on 17/2/7.
 */
public class Test {
    static class GrandFather {
        void thinking() {
            System.out.println("I am GrandFather");
        }
    }

    static class Father extends GrandFather {
        static {
            A = 2;
        }

        public static int A = 1;

        void thinking() {
            System.out.println("I am Father");
        }
    }

    static class Son extends Father {
        public static int B = A;

        void thinking() {
            MethodType mt=MethodType.methodType(void.class);
        }
    }

    public static void main(String[] args) {
        new Son().thinking();
    }
}
