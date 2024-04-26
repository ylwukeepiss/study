package com.garden.alanni.algor.encode;

/**
 * @author 吴宇伦
 */
public class Test {
    public static boolean validUtf8(int[] data) {
        int len = data.length;
        int i = 0;
        int cnt;
        while (i < len) {
            cnt = 0;
            int val = data[i];
            while (((val >> (7 - cnt)) & 1) != 0) {
                cnt++;
            }
            if (cnt == 1 || cnt > 4) {
                return false;
            }
            int j = i + 1;
            int dealNum = cnt - 1;
            for (; j < len && j < i + cnt; ++j) {
                val = data[j];
                if (val >> 6 != 2) {
                    return false;
                }
                dealNum--;
            }
            if (cnt > 0 && dealNum > 0) {
                return false;
            }
            i = j;
        }
        return true;
    }

    public static void main(String[] args) {
        // true
//        int[] utf8 = {10};
        // false
//        int[] utf8 = {255};
        // true
//        int[] utf8 = {10, 10};
        // true
//        int[] utf8 = {197,130,1};
        // false
//        int[] utf8 = {235,140,4};
        // true
//        int[] utf8 = {228,189,160,229,165,189,13,10};
        // false
        int[] utf8 = {237};
        System.out.println(validUtf8(utf8));
    }
}
