package com.garden.alanni.algor.slidwin;

import com.garden.alanni.algor.TreeNode;

import java.util.*;

/**
 * @author 吴宇伦
 */
public class SlidWin {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int size = len - k + 1;
        int[] ans = new int[size];
        if (len == 0 || len < k) {
            return ans;
        }
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < k; ++i) {
            int val = nums[i];
            maxQueue.offer(new int[]{i, val});
        }
        ans[0] = maxQueue.peek()[1];
        for (int i = k; i < len; ++i) {
            maxQueue.offer(new int[]{i, nums[i]});
            while (maxQueue.peek()[0] <= i - k) {
                maxQueue.poll();
            }
            ans[i - k + 1] = maxQueue.peek()[1];
        }
        return ans;
    }

    public static int maxArea(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int area = 0;
        while (left < right) {
            area = Math.max(area, (right - left) * Math.min(height[left], height[right]));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return area;
    }

    public static void main(String[] args) {
//        int[] nums = {1,3,-1, -3,5,3,6,7};
//        maxSlidingWindow(nums, 3);

//        int[] height = {1,8,6,2,5,4,8,3,7};
//        int i = maxArea(height);

    }
}
