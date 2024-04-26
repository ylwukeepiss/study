package com.garden.alanni.spring.cycle;

/**
 * @author 吴宇伦
 */
public class CycleB {
    private CycleA cycleA;

    public CycleA getCycleA() {
        return cycleA;
    }

    public void setCycleA(CycleA cycleA) {
        this.cycleA = cycleA;
    }
}
