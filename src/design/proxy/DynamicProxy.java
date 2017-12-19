package design.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wanghaitao on 17/5/15.
 */
public class DynamicProxy implements InvocationHandler {
    Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(object, args);
    }
}
