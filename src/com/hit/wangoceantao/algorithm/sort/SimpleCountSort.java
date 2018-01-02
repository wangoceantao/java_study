package com.hit.wangoceantao.algorithm.sort;

/**
 * @Description: ${TODO}
 * <p/>
 * Created by wanghaitao on 16/5/3 23:49.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class SimpleCountSort extends BaseSort {
    @Override
    public void executeSort(int[] array) {
        int length = array.length;
        int max = 0;
        for (int index = 0; index < length; index++) {
            if (max < array[index]) {
                max = array[index];
            }
        }
        int[] counterArray = new int[max + 1];
        for (int index = 0; index < max; index++) {
            counterArray[index] = 0;
        }
        for (int index = 0; index < length; index++) {
            counterArray[array[index]]++;
        }
        int k = 0;
        for (int index = 0; index <= max; index++) {
            for (int j = 1; j <= counterArray[index]; j++) {
                array[k++] = index;
            }
        }
    }

    public static void main(String[] arg) {
        SimpleCountSort simpleCountSort = new SimpleCountSort();
        executeTest(simpleCountSort);
    }
}
