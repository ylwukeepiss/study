package com.garden.alanni.concurrent;

/**
 * 通过cas模拟类{@link SimulatedCAS} 实现变量自增
 * @author 吴宇伦
 */
public class CasCnt {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = getValue();
        } while (!value.compareAndSet(v, v + 1));
        return v + 1;
    }
}
