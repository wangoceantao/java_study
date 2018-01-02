package com.hit.wangoceantao.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by zhangnannan on 17/2/28.
 */
@ClassDesc(name = "com/hit/wangoceantao/annotation", value = "reflection")
public class AnonotationReflection {
    @MethodDesc(name = "wangoceantao")
    public void print() {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class cls = AnonotationReflection.class;
        Annotation[] annotations = cls.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof ClassDesc) {
                ClassDesc classDesc = (ClassDesc) annotation;
                System.out.println("name:" + classDesc.name() + ";value:" + classDesc.value());
            }
        }

        Method printMethod = cls.getDeclaredMethod("print");
        Annotation annotation = printMethod.getAnnotation(MethodDesc.class);
        if (annotation instanceof MethodDesc) {
            MethodDesc methodDesc = (MethodDesc) annotation;
            System.out.println("methodDesc:" + methodDesc.name());
        }
    }
}
