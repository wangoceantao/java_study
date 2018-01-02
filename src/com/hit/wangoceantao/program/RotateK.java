package com.hit.wangoceantao.program;

import java.util.Arrays;

/**
 * Created by zhangnannan on 16/11/13.
 */
public class RotateK {
    /**
     * 旋转数组
     *
     * @param array
     * @param k
     * @return
     */
    public int[] rotateK(int[] array, int k) {
        if (array == null || array.length < k) {
            return array;
        }
        int iMax = array.length - 1;
        reverse(array, 0, iMax);
        reverse(array, 0, k - 1);
        reverse(array, k, iMax);
        return array;
    }

    private void reverse(int[] array, int start, int end) {
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6};
        RotateK rotateK = new RotateK();
        int[] result = rotateK.rotateK(array, 2);
        System.out.println(Arrays.toString(result));
    }
}
