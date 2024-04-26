package com.garden.alanni.jvm;

/**
 * @author 吴宇伦
 * VM参数：-verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 */
public class PretenureSizeThreshold {
    public static void main(String[] args) {
        byte[] allocation = new byte[5 * 1024 * 1024];
        byte[] allocation1 = new byte[4 * 1024 * 1024];
    }
}
