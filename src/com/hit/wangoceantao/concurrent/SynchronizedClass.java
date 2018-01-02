package com.hit.wangoceantao.concurrent;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by wanghaitao on 2016/12/19.
 */
public class SynchronizedClass {
    public static void main(String[] args) {
        ArrayList<String> userCodes = new ArrayList<String>();
        userCodes.add("com/hit/wangoceantao/test");
        userCodes.add("test1");
        System.out.println("data:" + userCodes);
        String path = "1234.1..1jpg";
        path = getRealPath(path);
        System.out.println("path:" + path);
        System.out.println(new File("com/hit/wangoceantao/test").exists());

    }

    public static String getRealPath(String path) {
        int lastIndexOfPostfixFormat = path.lastIndexOf(".");
        if (lastIndexOfPostfixFormat != -1) {
            path = path.substring(0, lastIndexOfPostfixFormat);
        }
        return path;
    }
}
