package com.garden.alanni.spring.cycle;

/**
 * @author 吴宇伦
 */
public class CycleA {
    private CycleB cycleB;

    public CycleB getCycleB() {
        return cycleB;
    }

    public void setCycleB(CycleB cycleB) {
        this.cycleB = cycleB;
    }
}
