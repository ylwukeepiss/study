package com.garden.alanni.spring.cycle;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author 吴宇伦
 */
@Configuration
public class CycleTest {
    public static void main(String[] args) {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("Cycle.xml");
//        CycleA cycleA = (CycleA) ac.getBean("cycleA");
//        CycleB cycleB = (CycleB) ac.getBean("cycleB");
//        System.out.println("hello");
//        Deque<Integer> deque = new LinkedList<>();
//        deque.push(1);
//        deque.push(2);
//        deque.addFirst(3);
//        deque.addLast(4);
//        deque.addLast(5);
//        Integer integer = deque.pollLast();
//        System.out.println("");
        StringBuffer buffer = new StringBuffer();
    }
}
