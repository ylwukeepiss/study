package com.garden.alanni;

import org.junit.Test;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.HashMap;

/**
 * @author 吴宇伦
 */
public class Alg {
    public static void main(String[] args) {
        // A & B -> ∞
        // 大数相乘 结果计算
        //
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.putIfAbsent("1", "2");
        System.out.println();
    }
    @Test
    private String cal(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        // 对数对长度做遍历，将合适的类型装载到合适的数据结构中，进行相应的计算
        // n维遍历 维数取决于 aLength * bLength
        // 遍历 a 的每一个数 charAt 以 10^a * charA 去表示每一个数
        // 同理 将 b 中每一个数 通过 10^b * charB 去表示
        // 这里 将 乘法 化为 加法 去表示一组数的相乘结果
        // charA * charB & (a + b) -> charA * 10^a * (charB * 10^b)
        // 最后 将每一组这样子的数相加时，通过遍历 获取最大 的 一组 (a + b) 的大小
        // 初始化一组 结果字符串 逐个塞进字符串中
        // 如果超出 最后再做truncated 截断头部为0的情况
        for (int i = 0; i < aLength; ++i) {
            char charA = a.charAt(i);
            int numA = (int) charA;
            if (numA > 0) {
                // 10 的 幂次数
                int expA = aLength - i;
                for (int j = 0; j < bLength; ++j) {
                    char charB = b.charAt(j);
                    int numB = (int) charB;
                    if (numB > 0) {
                        int expB = j;
                        int calResult = numA * numB;
                        int exp = expA + expB;

                    }
                }
            }
        }
        return "";
    }
}
