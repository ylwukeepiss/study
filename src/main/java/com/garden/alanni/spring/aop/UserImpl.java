package com.garden.alanni.spring.aop;

/**
 * @author 吴宇伦
 */
public class UserImpl implements User{
    @Override
    public void add() {
        System.out.println("add");
    }
}
