package com.garden.alanni.concurrency;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * volatile变量自增测试
 * 开启20个线程，每个线程对volatile修饰的变量增加1w 如果是线程安全，则结果应是20w，但不管运行多少次，结果都是一个小于20w的数字
 * 原因在于volatile只保证了变量修改时的线程可见性 而race++并不是一个原子操作，因此数据更新时，出现了线程不安全
 * 总结：volatile变量只能保证内存可见性 并且禁止指令重排
 * @author 吴宇伦
 */
public class VolatileTest {
    public static volatile int race = 0;

    public static final int THREAD_COUNTS = 20;

    public static void incr() {
        race++;
    }

    public static void main(String[] args) {
        LocalDateTime begin = LocalDateTime.now();
        Thread[] threads = new Thread[THREAD_COUNTS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; ++j) {
                        incr();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println(race);
        System.out.println(Duration.between(begin, end).getSeconds());
    }
}
