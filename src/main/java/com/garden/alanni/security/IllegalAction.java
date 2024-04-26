package com.garden.alanni.security;

/**
 * @author 吴宇伦
 */
public class IllegalAction {
    static {
        System.out.println("this is illegal");
    }

    public IllegalAction() {
        System.out.println("this is construct method");
    }
}
