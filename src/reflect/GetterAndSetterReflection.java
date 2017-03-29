package reflect;

import java.lang.reflect.Method;

/**
 * Created by wanghaitao on 17/2/28.
 */
public class GetterAndSetterReflection {
    private String name;
    private int old;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public static void main(String[] args) {
        Method[] methods = GetterAndSetterReflection.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + " isGetter:" + ReflectionTools.isGetter(method)
                    + " ;isSetter:" + ReflectionTools.isSetter(method));
        }
    }


}
