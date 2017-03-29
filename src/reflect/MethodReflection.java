package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by wanghaitao on 17/2/28.
 */
public class MethodReflection {
    public static void main(String[] args) {
        Class cls = MethodReflection.class;
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            System.out.println("methodName:" + method.getName());
        }
        int clsModifiers = cls.getModifiers();
        boolean isPublic = Modifier.isPublic(clsModifiers);
        System.out.println("isPublic class: " + isPublic);
        Package clsPackage = cls.getPackage();
        String clsPackageName = clsPackage.getName();
        System.out.println("clsPackageName: " + clsPackageName);
    }
}
