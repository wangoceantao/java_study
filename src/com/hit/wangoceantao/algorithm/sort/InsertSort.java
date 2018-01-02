package com.hit.wangoceantao.algorithm.sort;

/**
 * @Description: 直接插入排序
 * <p/>
 * Created by wanghaitao on 16/5/2 11:40.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class InsertSort extends BaseSort {
    @Override
    public void executeSort(int[] array) {
        int length = array.length;
        for (int index = 0; index < length; index++) {
            int tmp = array[index];
            int j = index - 1;
            while (j >= 0 && tmp < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = tmp;
        }
    }

    public static void main(String[] arg) {
        InsertSort insertSort = new InsertSort();
        executeTest(insertSort);
    }
}
