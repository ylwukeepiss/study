package com.garden.alanni.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全 使用{@link java.util.concurrent.locks.Lock}来实现信号计数量
 * @author 吴宇伦
 */
public class SemaphoreOnLock {
    private final Lock lock = new ReentrantLock();
    private final Condition permitsAvailable = lock.newCondition();
    private int permits;

    SemaphoreOnLock(int initalPermits) {
        lock.lock();
        try {
            permits = initalPermits;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞 直到 permitsAvailable
     */
    public void acquire() {
        lock.lock();
        try {
            while (permits <= 0) {
                permitsAvailable.await();
            }
            permits--;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            ++permits;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }
}
