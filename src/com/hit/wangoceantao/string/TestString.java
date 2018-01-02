package com.hit.wangoceantao.string;

/**
 * @Description: ${TODO}
 * <p/>
 * Created by wanghaitao on 16/5/19 18:15.
 * <p/>
 * Email：wanghaitao01@hecom.cn
 */
public class TestString {
    public static void main(String[] arg) {
        String test="\\/12345";
        System.out.println("\\/");
        System.out.println(test);
        String filteredProductClassic = test.replaceAll("\\\\", "");
        System.out.println(filteredProductClassic);
    }
}
