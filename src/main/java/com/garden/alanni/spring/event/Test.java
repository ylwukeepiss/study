package com.garden.alanni.spring.event;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 吴宇伦
 */
public class Test {
    public static void main(String[] args) {
        Resource resource = new ClassPathResource("EventListener.xml");
        try {
            InputStream inputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ApplicationContext ac = new ClassPathXmlApplicationContext("EventListener.xml");
        TestEvent event = new TestEvent("hello", "msg");
        ac.publishEvent(event);
    }
}
