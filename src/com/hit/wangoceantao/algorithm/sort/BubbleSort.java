package com.hit.wangoceantao.algorithm.sort;

/**
 * @Description: 冒泡排序
 * <p/>
 * Created by wanghaitao on 16/4/29 23:46.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class BubbleSort extends BaseSort {
    @Override
    public void executeSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    public static void main(String[] arg) {
        BubbleSort bubbleSort = new BubbleSort();
        executeTest(bubbleSort);
    }
}
