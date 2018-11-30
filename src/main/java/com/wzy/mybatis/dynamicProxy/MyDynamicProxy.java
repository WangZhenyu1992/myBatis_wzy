package com.wzy.mybatis.dynamicProxy;

public class MyDynamicProxy {
    public static void main(String[] args) throws Exception {
        Person person = (Person) new MyInvocationHandler().getInstance(new ZhangSan());

        person.say("哈哈，来代理我啊！");
    }
}
