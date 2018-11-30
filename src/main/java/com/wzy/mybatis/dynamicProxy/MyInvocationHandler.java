package com.wzy.mybatis.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

    private Person target;

    public Object getInstance(Person target) throws Exception{
        this.target = target;

        Class clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理开始");
        method.invoke(this.target, args);
        System.out.println("代理结束");
        System.out.println(proxy.getClass());
        return null;
    }
}
