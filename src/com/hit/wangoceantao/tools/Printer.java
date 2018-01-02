package com.hit.wangoceantao.tools;

/**
 * @Description: ${TODO}
 * <p/>
 * Created by wanghaitao on 16/4/29 21:37.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class Printer {
    public static void printArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        for (int index = 0; index < array.length; index++) {
            System.out.print(array[index]+"  ");
        }
        System.out.print("\n");
    }
}
