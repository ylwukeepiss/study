package com.garden.alanni.algor.doublepointer;

import org.apache.tomcat.jni.Socket;
import org.apache.tomcat.util.net.NioEndpoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author 吴宇伦
 */
public class Pointer {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int val = nums[i] + nums[j] + nums[k];
                if (val > 0) {
                    int diff = val - nums[k];
                    while (j < k && diff + nums[k] > 0) {
                        k--;
                    }
                } else if (val < 0) {
                    int diff = val - nums[j];
                    while (j < k && diff + nums[j] < 0) {
                        j++;
                    }
                } else {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                }
            }
        }
        return ans;
    }

    public static int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int len = nums.length;
        int i = 0, j;
        while (i < len) {
            j = i;
            while (j < len && nums[i] == nums[j]) {
                j++;
            }
            pq.offer(new int[]{j - i, nums[i]});
            i = j;
        }
        int top = 0;
        int[] ans = new int[k];
        while (top < k) {
            int[] poll = pq.poll();
            ans[top++] = poll[1];
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] nums = {-1,0,1,2,-1,-4};
//        int[] nums = {0,0,0,0};
//        threeSum(nums);

//        int[] nums = {1,1,1,2,2,3};
//        int[] ints = topKFrequent(nums, 2);

        int[] nums = {3,0,1,0};
        int[] ints = topKFrequent(nums, 1);
        System.out.println(ints);
    }

}
