package com.garden.alanni.concurrent.happenbefore;

/**
 * @author 吴宇伦
 */
public class VolatileWriteHappenBeforeRead {
    static int val = 2;
    static int a = 0;
    static int b = 1;
    static int c = 2;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                val = a;
            }
        });
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                val = a;
//            }
//        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                b = val;
                System.out.println(b);
                c = b;
                System.out.println(c);
            }
        });
//        Thread t4 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                c = b;
//                System.out.println(c);
//            }
//        });
        t1.start();
//        t2.start();
        t3.start();
//        t4.start();
    }
}
