package com.hit.wangoceantao.annotation;

/**
 * Created by wanghaitao on 2016/9/27.
 */
public class AnnotationTest {
    public static void main(String[] args) {
        try {
            Class appCls = Class.forName("com.hit.wangoceantao.annotation.Apps");
            MethodInfo annotation = (MethodInfo) appCls.getAnnotation(MethodInfo.class);
            String author = annotation.author();
            int version = annotation.version();
            System.out.println("author:" + author + ";version:" + version);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String[] array = new String[]{"1", "2"};
        for (String element : array) {
            System.out.println("element:" + element);
        }
    }
}
