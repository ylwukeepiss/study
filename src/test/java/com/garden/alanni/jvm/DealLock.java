package com.garden.alanni.jvm;

/**
 * @author 吴宇伦
 */
public class DealLock {
    static class SynAddRunnable implements Runnable {
        int a, b;
        public SynAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                // 处于节省内存和创建对象次数的考虑，Integer 会缓存 -128~127之间的数字
                // 如果在两个synchornized之间发生了一次切换的话，很可能出现
                // 线程a在等待线程b持有的Integer.valueOf(1)对象释放
                // 线程b在等待线程a持有的Integer.valueOf(2)对象释放
                // 因此造成死锁
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; ++i) {
            new Thread(new SynAddRunnable(1, 2)).start();
            new Thread(new SynAddRunnable(2, 1)).start();
        }
    }
}
