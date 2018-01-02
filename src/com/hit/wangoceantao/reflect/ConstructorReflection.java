package com.hit.wangoceantao.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wanghaitao on 17/2/28.
 */
public class ConstructorReflection {
    private String arg;
    public static String staticField = "";

    public ConstructorReflection(String arg) {
        this.arg = arg;
    }

    public void print() {
        System.out.println("print:" + this.arg + staticField);
    }

    public void print(String arg) {
        System.out.println(arg);
    }

    public static void main(String[] args) throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException,
            NoSuchFieldException {

        //获取构造器
        Constructor<ConstructorReflection> constructor = ConstructorReflection.class.getConstructor(new Class[]{String.class});
        ConstructorReflection instance = constructor.newInstance("main-print");
        instance.print();

        //获取私有变量
        Field arg = ConstructorReflection.class.getDeclaredField("arg");
        arg.setAccessible(true);
        String oldValue = (String) arg.get(instance);
        arg.set(instance, oldValue + ":add by reflection");
        instance.print();

        //获取静态变量
        Field staticField = ConstructorReflection.class.getDeclaredField("staticField");
        staticField.setAccessible(true);
        staticField.set(null, ":staticField");
        instance.print();

        Method printMethod = ConstructorReflection.class.getMethod("print", new Class[]{String.class});
        printMethod.invoke(instance, "invoke by reflection");
    }
}
