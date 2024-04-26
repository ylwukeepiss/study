package com.garden.alanni.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author 吴宇伦
 */
@Aspect
public class AspectJTest {
    @Pointcut("execution(* *.test(..))")
    public void test() {

    }

    @Before("test()")
    public void before() {
        System.out.println("before");
    }

    @After("test()")
    public void after() {
        System.out.println("after");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p) {
        System.out.println("before1");
        Object o = null;
        try {
            o = p.proceed();
        }  catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("after1");
        return o;
    }
}
