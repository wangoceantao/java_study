package com.hit.wangoceantao.annotation;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhangnannan on 17/2/28.
 */
public class ArrayReflection {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        int[] intArray = (int[]) Array.newInstance(int.class, 3);
        Array.set(intArray, 0, 1000);
        Array.set(intArray, 1, 1001);
        Array.set(intArray, 2, 1002);
        for (int index : intArray) {
            System.out.println("index:" + index);
        }

        Class intArrayClass = intArray.getClass();
        Class intArrayClassComponentType = intArrayClass.getComponentType();
        System.out.println("intArrayClassComponentType:" + intArrayClassComponentType);

    }
}
