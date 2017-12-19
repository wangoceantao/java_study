package test;

import java.util.Arrays;

/**
 * Created by wanghaitao on 17/4/12.
 */
public class Test {
    static {
        i = 101;
    }
    private static int i = 100;


    public static void main(String[] args) {
        System.out.println("i:" + i);

        int left = -10;
        System.out.println("left:" + Integer.valueOf(Integer.toBinaryString(left & 0x1f), 2));
        int[] array = new int[]{1, 4, 6, 10, 13};
        System.out.println(Arrays.binarySearch(array, 13));
        find(array, 11);
        find(array, 12);
        find(array, 16);
        find(array, 10);
    }


    private static void find(int[] array, int findKey) {
        int pos = Arrays.binarySearch(array, findKey);
        if (pos < -1) {
            int oldLength = array.length;
            array = Arrays.copyOf(array, array.length + 1);
            int newIndex = -1 - pos;
            System.arraycopy(array, newIndex, array, newIndex + 1, oldLength - newIndex);
            array[newIndex] = findKey;
            System.out.println(Arrays.toString(array));
        }
    }
}
