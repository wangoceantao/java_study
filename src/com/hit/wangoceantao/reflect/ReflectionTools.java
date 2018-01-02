package com.hit.wangoceantao.reflect;

import java.lang.reflect.Method;

/**
 * Created by wanghaitao on 17/2/28.
 */
public class ReflectionTools {

    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        }

        if (method.getParameterTypes().length != 1) {
            return false;
        }
        return true;
    }

    public static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        return true;
    }
}
