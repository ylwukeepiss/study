package com.garden.alanni.algor.wantmore;

/**
 * @author 吴宇伦
 */
public class More {
    public static int jump(int[] nums) {
        int start = 0;
        int end = 1;
        int maxPos = 0;
        int step = 0;
        int len = nums.length;
        while (end < len) {
            for (int i = start; i < end; ++i) {
                maxPos = Math.max(maxPos, i + nums[i]);
            }
            step++;
            start = end;
            end = maxPos + 1;
        }
        return step;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        jump(nums);
    }
}
