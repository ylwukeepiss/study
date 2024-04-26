package com.garden.alanni;

/**
 * 斐波那契数列 基本公式为 F(n) = F(n-1) + F(n-2)
 * 边界条件为 F(0) = 0; F(1) = 1
 * 每一项的和等于前面两项相加的和
 * 存在递推关系
 * @author 吴宇伦
 */
public class Fib {
    public static void main(String[] args) {
//        int result = fibReCusive(10);
//         int result = fibDp(5);
        long result = tribonacci(25);
        System.out.println(result);

        int[] test = new int[50];
        for (int i : test) {
            System.out.println(i);
        }
    }

    /**
     * 最暴力的方式 使用递归
     */
    private static int fibReCusive(int n) {
        if (n < 2) {
            return n;
        }
        return (fibReCusive(n - 1) + fibReCusive(n - 2));
    }

    /**
     * 斐波那契存在着递推关系 因此可以用动态规划来求解
     * 利用状态转移方程 和 边界条件 ，再根据 [数组滚动思想]
     * 可以设计出 时间复杂度为 O(n) 空间复杂度为O(1)的计算方式
     */
    private static int fibDp(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0
     * t(n+3) = t(n+2) + t(n+1) + t(n)
     * 泰波那契数列 求第n个泰波那契数
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    private static long tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        long p = 0, q = 1, r = 1;
        for (int i = 3; i <= n; ++i) {
            long s = p + q + r;
            p = q;
            q = r;
            r = s;
        }
        long result = Math.min(p+q, q+q);
        return r;
    }
}
