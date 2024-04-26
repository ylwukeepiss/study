package com.garden.alanni.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴宇伦
 * VM参数： -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class FillHeap {
    static class OOMObject {
        public byte[] placeHolder = new byte[64 * 1024];
    }

    public static void fill(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; ++i) {
            // 稍作延迟 令监视曲线变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
//            System.out.println("正在运行");
        }
//        System.out.println("即将full gc");
        System.gc();

    }

    public static void main(String[] args) throws InterruptedException {
        // 以 64kb/50ms 的速度填充堆
        fill(1000);

    }
}
