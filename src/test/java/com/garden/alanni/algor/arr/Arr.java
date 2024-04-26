package com.garden.alanni.algor.arr;

import java.util.Arrays;

/**
 * @author 吴宇伦
 */
public class Arr {
    public static int removeDuplicates(int[] nums) {
        return process(nums, 2);
    }
    public static int process(int[] nums, int k) {
        int u = 0;
        for (int x : nums) {
            if (u < k || nums[u - k] != x) {
                nums[u++] = x;
            }
        }
        return u;
    }

    public static int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int left = 0, right = len - 1;
        int[] ans = new int[2];
        while (left < right) {
            int vall = nums[left];
            int valr = nums[right];
            int res = vall + valr;
            if (res == target) {
                ans[0] = left;
                ans[1] = right;
                return ans;
            } else if (res > target) {
                right--;
            } else {
                left++;
            }
        }
        return ans;
    }

    public static class ValWithIdx implements Comparable<ValWithIdx> {
        private int val;
        private int idx;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public ValWithIdx(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(ValWithIdx o) {
            return this.val - o.val;
        }
    }

    private static int[] twoSumBinarySearch(int[] nums, int target) {
        int len = nums.length;
        ValWithIdx[] val2idxs = new ValWithIdx[len];
        for (int i = 0; i < len; i++) {
            val2idxs[i] = new ValWithIdx(nums[i], i);
        }
        Arrays.sort(val2idxs);
        for (int i = 0; i < len; ++i) {
            ValWithIdx val2idx = val2idxs[i];
            int diff = target - val2idx.val;
            int idx = binarySearch(val2idxs, len, diff, i + 1);
            if (idx != -1) {
                return new int[]{val2idx.idx, idx};
            }
        }
        return new int[]{};
    }

    private static int binarySearch(ValWithIdx[] nums, int len, int value, int left) {
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            ValWithIdx  val2idx = nums[mid];
            if (value > val2idx.val) {
                left = mid + 1;
            } else if (value < val2idx.val) {
                right = mid - 1;
            } else {
                return val2idx.idx;
            }
        }
        return -1;
    }

    public int maximumDifference(int[] nums) {
        int len = nums.length;
        int ans = -1, premin = nums[0];
        for (int i = 0; i < len; ++i) {
            if (nums[i] > premin) {
                ans = Math.max(ans, nums[i] - premin);
            } else {
                premin = nums[i];
            }
        }
        return ans;
    }

    public static void moveZeroes(int[] nums) {
        int i = 0, cur = i, len = nums.length;
        while (cur < len - 2) {
            while (nums[i] != 0 && i < len - 1) {
                i++;
            }
            while (nums[cur] == 0 && cur < len - 1) {
                cur++;
            }
            int temp = nums[i];
            nums[i] = nums[cur];
            nums[cur] = temp;
            while (nums[i] != 0) {
                i++;
            }
            while (nums[i] != 0 && i < len - 1) {
                i++;
            }
            while (nums[cur] == 0 && cur < len - 1) {
                cur++;
            }
        }
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1,1,1,1,1,1,2,2,2,2,2,2,3};
//        process(nums, 2);

//        int[] nums = new int[]{2,7,11,15};
//        int target = 9;
//        twoSum(nums, target);

//        int[] nums = new int[]{3, 2, 4};
//        String s = "";
//        s.toCharArray();
//        int target = 6;
//        twoSumBinarySearch(nums, target);

        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
        System.out.println("hellow");
    }
}
