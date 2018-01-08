package com.hit.wangoceantao.class_load;

/**
 * Created by wanghaitao on 18/1/8.
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init");
    }

    public static final int SUB_FINAL_INT_VALUE = 1 << 2;
    public static final String SUB_FINAL_STRING_VALUE = "sub_final_string_value";

}
