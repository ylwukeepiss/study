package com.garden.alanni.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 吴宇伦
 */
public abstract class GetBeanTest {
    public abstract Person getBean();

    public void showMe() {
        this.getBean().showMe();
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("GetBeanTest.xml");
        GetBeanTest test = (GetBeanTest) ac.getBean("getBeanTest");
        test.showMe();
    }
}
