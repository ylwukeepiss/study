package com.garden.alanni.thread;

/**
 * @author 吴宇伦
 */
public class Test {
    public static void main(String[] args) {
        Thread t1 = new Thread(){

        };
        t1.start();
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("diyici");
        Thread t2 = new Thread();
        tl.set("第二次");
        tl.set("第san次");
        String s = tl.get();
        System.out.println(s);
        int val = 0, cnt = 0;
    }
}
