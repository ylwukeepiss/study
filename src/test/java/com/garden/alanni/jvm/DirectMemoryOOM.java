package com.garden.alanni.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * jdk version: 1.8
 * VM Args: -Xmx20m -XX:MaxDirectMemorySize=10m
 * @author 吴宇伦
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
