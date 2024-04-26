package com.garden.alanni.jvm;

/**
 * 空间分配担保
 * VM 参数： -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
 * （JDK7之后 HandlePromitonFailure参数就失效了）
 * @author 吴宇伦
 */
public class HandlePromotion {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allo1, allo2, allo3, allo4, allo5, allo6, allo7;
        allo1 = new byte[2 * _1MB];
        allo2 = new byte[2 * _1MB];
        allo3 = new byte[2 * _1MB];
        allo1 = null;
        allo4 = new byte[2 * _1MB];
        allo5 = new byte[2 * _1MB];
        allo6 = new byte[2 * _1MB];
        allo4 = null;
        allo5 = null;
        allo6 = null;
        allo7 = new byte[2 * _1MB];
    }
}
