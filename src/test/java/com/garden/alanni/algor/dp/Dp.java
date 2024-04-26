package com.garden.alanni.algor.dp;

import java.io.FileInputStream;
import java.util.Arrays;

/**
 * @author 吴宇伦
 */
public class Dp {
    public static int[][] cache;

    public static int uniquePath(int m, int n) {
        cache = new int[m][n];
        for (int i = 0; i < m; ++i) {
            int[] hits = new int[n];
            Arrays.fill(hits, -1);
            cache[i] = hits;
        }
        return recuUniquePath(m, n, 0, 0);
    }

    public static int recuUniquePath(int m, int n, int x, int y) {
        if (x == m - 1 || y == n - 1) {
            return 1;
        }
        if (cache[x][y] == -1) {
            if (cache[x + 1][y] == -1) {
                cache[x + 1][y] = recuUniquePath(m, n, x + 1, y);
            }
            if (cache[x][y + 1] == -1) {
                cache[x][y + 1] = recuUniquePath(m, n, x, y + 1);
            }
            cache[x][y] = cache[x + 1][y] + cache[x][y + 1];
        }
        return cache[x][y];
    }
    public static int[][] flipAndInvertImage(int[][] image) {
        int start = 0, end = 0;
        int m = image.length, n = m;
        for (int i = 0; i < m; ++i) {
            start = 0;
            end = m - 1;
            while (start <= end) {
                int temp = image[i][start];
                image[i][start++] = (1 - image[i][end]) & 1;
                image[i][end--] = (1 - temp) & 1;
            }
        }
        return image;
    }

    public static boolean judgeSquareSum(int c) {
        int a = 0, b = (int)Math.sqrt(c);
        while (a <= b) {
            int cur = a * a + b * b;
            if (cur == c) {
                return true;
            } else if (cur > c) {
                b--;
            } else {
                a++;
            }
        }
        return false;
    }

    public static boolean judgeSquareSumSecond(int c) {
        int max = (int)Math.sqrt(c);
        for (int a = 0; a <= max; ++a) {
           int b = (int) Math.sqrt(c - a * a);
            if (a * a + b * b == c) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[][] image = {{1,1,0},{1,0,1},{0,0,0}};
//        int[][] ints = flipAndInvertImage(image);
//        System.out.println(ints);
        judgeSquareSum(2147483600);
    }
}
