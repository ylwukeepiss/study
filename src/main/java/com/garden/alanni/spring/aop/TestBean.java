package com.garden.alanni.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author 吴宇伦
 */
public class TestBean {

    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("AspectJTest.xml");
        TestBean tb = (TestBean) ac.getBean("test");
        tb.test();
    }
}
