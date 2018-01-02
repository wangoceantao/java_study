package com.hit.wangoceantao.algorithm.sort;

/**
 * @Description: ${TODO}
 * <p/>
 * Created by wanghaitao on 16/5/3 23:01.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class MergeSort extends BaseSort {
    @Override
    public void executeSort(int[] array) {
        int length = array.length;
        int interval;
        for (interval = 1; interval < length; interval = interval * 2) {
            mergePass(array, interval);
        }
    }

    private void mergePass(int[] array, int interval) {
        int length = array.length;
        int index;
        for (index = 0; index + 2 * interval - 1 < length; index = index + 2 * interval) {
            merge(array, index, index + interval - 1, index + 2 * interval - 1);
        }
        //解决剩余不足interval
        if (index + interval - 1 < length) {
            merge(array, index, index + interval - 1, length - 1);
        }
    }

    private void merge(int[] array, int low, int middle, int high) {
        int[] cacheArray = new int[high - low + 1];
        int i = low;
        int j = middle + 1;
        int cacheIndex = 0;
        while (i <= middle && j <= high) {
            if (array[i] <= array[j]) {
                cacheArray[cacheIndex] = array[i];
                cacheIndex++;
                i++;
            } else {
                cacheArray[cacheIndex] = array[j];
                cacheIndex++;
                j++;
            }
        }
        while (i <= middle) {
            cacheArray[cacheIndex] = array[i];
            i++;
            cacheIndex++;
        }
        while (j <= high) {
            cacheArray[cacheIndex] = array[j];
            j++;
            cacheIndex++;
        }
        for (cacheIndex = 0, i = low; i <= high; cacheIndex++, i++) {
            array[i] = cacheArray[cacheIndex];
        }
    }

    public static void main(String[] arg) {
        MergeSort mergeSort = new MergeSort();
        executeTest(mergeSort);
    }
}
