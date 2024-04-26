package com.garden.alanni.jvm;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * JVM逃逸分析
 * 简单地理解就是 共享对象——逃逸  对象仅线程可见——未逃逸
 * <p>
 *     常用于：
 *     堆分配转栈分配：如果一个对象无逃逸，仅线程可见，可以将该对象实例分配在栈上，生命周期交给线程管理，降低gc压力
 *     锁消除：如果一个对象不会发生逃逸，只被一个线程访问，那么在这个对象上的操作就不需要同步
 *     分离对象或标量替换：如果一个对象不会发生逃逸，就将该对象分离成一个个基本变量，分配到栈上，好处 1：不用分配对象头，节省空间 2：回收更高效
 * </p>
 * <p>
 *     判断对象是否逃逸的标准：
 *     对象被赋值给堆中的变量或栈中的变量
 *     对象被传进了不确定的代码中运行
 * </p>
 * @author 吴宇伦
 */
public class EscapeAnalyze {
    public static void main(String[] args) throws InterruptedException {

    }
    public static Object globalObject;

    public Object instance;

    public void globalEscape() {
        // 静态变量 外部线程可见 发生逃逸
        globalObject = new Object();
    }

    public void instanceEscape() {
        // 赋值给堆中实例变量 外部线程可见 发生逃逸
        instance = new Object();
    }

    public Object returnObject() {
        // 返回实例 外部线程可见 发生逃逸
        return new Object();
    }

    public void noEscape() {
        synchronized (new Object()) {
            // 仅线程对象可见 对象无逃逸 jvm会将锁消除
        }
        // 仅线程可见 未发生逃逸
        Object noEscape = new Object();
    }
}
