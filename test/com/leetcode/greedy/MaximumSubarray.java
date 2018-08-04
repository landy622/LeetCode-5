package com.leetcode.greedy;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int sum = 0, max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            sum = Math.max(0, sum);
        }

        return max;
    }
}
