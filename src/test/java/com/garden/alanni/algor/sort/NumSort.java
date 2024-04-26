package com.garden.alanni.algor.sort;

import java.security.SecureRandom;
import java.util.*;

/**
 * @author 吴宇伦
 */
public class NumSort {
    public static void main(String[] args) {
//        int[] nums = new int[]{5, 3, 9, 2 ,8};
//        bubbling(nums);
//        for (int val : nums) {
//            System.out.println(val);
//        }

//        int[] nums = new int[]{5, 7, 9, 12 ,18};
//        searchInsert(nums, 6);
//        System.out.println("haha");

        int[] arr = {1,3,5,7,2,4,6,8};
        int k = 4;
        Solution solution = new Solution();
        solution.smallestK(arr, k);
    }

    static class Solution {
        int k;
        public int[] smallestK(int[] arr, int _k) {
            k = _k;
            int n = arr.length;
            int[] ans = new int[k];
            if (k == 0) return ans;
            qsort(arr, 0, n - 1);
            for (int i = 0; i < k; i++) ans[i] = arr[i];
            return ans;
        }
        void qsort(int[] arr, int l, int r) {
            if (l >= r) return ;
            int i = l, j = r;
            int bound = r - l + 1;
            int random = new Random().nextInt(bound);
            int ridx = random + l;
            swap(arr, ridx, l);
            int x = arr[l];
            while (i < j) {
                while (i < j && arr[j] >= x) j--;
                while (i < j && arr[i] <= x) i++;
                swap(arr, i, j);
            }
            swap(arr, i, l);
            // 集中答疑：因为题解是使用「基准点左侧」来进行描述（不包含基准点的意思），所以这里用的 k（写成 k - 1 也可以滴
            if (i > k) qsort(arr, l, i - 1);
            if (i < k) qsort(arr, i + 1, r);
        }
        void swap(int[] arr, int l, int r) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }

    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static int[] bubbling(int[] nums) {
        String s = new String();
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            for (int j = i + 1; j < len; ++j) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }
}
