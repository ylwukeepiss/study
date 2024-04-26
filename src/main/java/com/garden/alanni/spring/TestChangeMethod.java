package com.garden.alanni.spring;

import org.apache.naming.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 * @author 吴宇伦
 */
public class TestChangeMethod {
    public void changeMe() {
        System.out.println("changeMe");
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("MethodReplaceTest.xml");
        TestChangeMethod testChangeMethod = (TestChangeMethod) ac.getBean("testChangeMethod");
        testChangeMethod.changeMe();
//        ResourceLoader bf = new ClassPathXmlApplicationContext("xx");
    }
}
