package com.hit.wangoceantao.design.proxy;

import com.hit.wangoceantao.design.ILawsuit;

import java.lang.reflect.Proxy;

/**
 * Created by wanghaitao on 17/5/15.
 */
public class Client {
    public static void main(String[] args) {
        Person person = new Person();
        DynamicProxy proxy = new DynamicProxy(person);
        ClassLoader classLoader = person.getClass().getClassLoader();
        ILawsuit lawsuit = (ILawsuit) Proxy.newProxyInstance(classLoader, new Class[]{ILawsuit.class}, proxy);
        lawsuit.submit();
    }
}
