package com.garden.alanni.jvm;

/**
 * @author 吴宇伦
 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
 */
public class TestTenuringThreshold {
    public static void main(String[] args) {
        byte[] allo1, allo2, allo3;
        // 什么时候进入老年代决定于-XX:MaxTenuringThreshold 参数配置
        allo1 = new byte[1024 * 1024 / 4];
        allo2 = new byte[1024 * 1024 * 4];
        allo3 = new byte[1024 * 1024 * 4];
        allo3 = null;
        allo3 = new byte[1024 * 1024 * 4];
    }
}
