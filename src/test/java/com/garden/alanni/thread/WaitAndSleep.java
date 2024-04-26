package com.garden.alanni.thread;


/**
 * @author 吴宇伦
 * wait() 与 sleep() 的区别
 * wait 是 Object类中的方法 让线程放弃当前对象但锁，进入等待此对象的等待池 只有针对此对象调用notify方法后才能进入对象锁定池，准备获取对象锁进入运行状态
 * sleep 是 Thread类中的方法 停止本线程运行，让出cpu给其它线程，但不释放对象锁资源以及监控状态
 */
public class WaitAndSleep {
    public static void main(String[] args) {
//        PriorityQueue<Integer> p = new PriorityQueue<>((a, b) -> a - b);
//        p.add(1);
//        new Thread(new ThreadA()).start();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(new ThreadB()).start();
        int i = 10;
        i >>= 2;
        System.out.println(i);
    }

    public boolean isPowerOfFour(int n) {
        if (n > 0) {
            int sqrt = (int) Math.sqrt(n);
            if ((sqrt * sqrt == n) && (sqrt & -sqrt) == sqrt) {

            }

            if (sqrt * sqrt == n && (sqrt & -sqrt) == sqrt) {

            }
        }
        return false;
    }

    public static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (WaitAndSleep.class) {
                System.out.println("in ThreadA");
                System.out.println("ThreadA is going to wait");
                try {
                    WaitAndSleep.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadA is going on");
                System.out.println("ThreadA is over");
            }
        }
    }
    public static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (WaitAndSleep.class) {
                System.out.println("in ThreadB");
                System.out.println("ThreadB is sleep");
                WaitAndSleep.class.notify();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadB is going on");
                System.out.println("ThreadB is over");
            }
        }
    }
}
