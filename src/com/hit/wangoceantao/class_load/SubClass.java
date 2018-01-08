package com.hit.wangoceantao.class_load;

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }

    public static int sub_value = 1 << 1;

}