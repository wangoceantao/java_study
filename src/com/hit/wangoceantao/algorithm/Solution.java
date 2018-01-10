package com.hit.wangoceantao.algorithm;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
//        int[] src = new int[]{1, 2, 2, 3, 3, 4};
        int[] src = new int[]{1, 2, 2, 3, 4};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.removeDuplicates(src)));
    }

    public int[] removeDuplicates(int[] nums) {
        int length = nums.length;
        int startPos = length - 1;
        int markPos = startPos;
        int repeatCount = 0;
        while (startPos >= 0) {
            while (startPos > 0 && nums[startPos - 1] == nums[startPos]) {
                startPos--;
                repeatCount++;
            }
            nums[markPos] = nums[startPos];
            markPos--;
            startPos--;
        }
        int resultLength = length - repeatCount;
        int[] resultArray = new int[resultLength];
        System.arraycopy(nums, markPos + 1, resultArray, 0, resultLength);
        return resultArray;
    }
}