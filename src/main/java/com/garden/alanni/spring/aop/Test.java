package com.garden.alanni.spring.aop;

/**
 * @author 吴宇伦
 */
public class Test {
    public static void main(String[] args) {
        User user = new UserImpl();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(user);
        User proxy = (User) invocationHandler.getProxy();
        proxy.add();
    }
}
