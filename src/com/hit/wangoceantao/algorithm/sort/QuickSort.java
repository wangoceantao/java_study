package com.hit.wangoceantao.algorithm.sort;

/**
 * @Description: 快速排序
 * <p/>
 * Created by wanghaitao on 16/4/29 21:36.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class QuickSort extends BaseSort {

    @Override
    public void executeSort(int[] array) {
        excuteSort(array, 0, array.length - 1);
    }

    private void excuteSort(int[] array, int low, int high) {
        if (low < high) {
            int middle = getMiddlePosition(array, low, high);
            excuteSort(array, 0, middle - 1);
            excuteSort(array, middle + 1, high);
        }
    }

    private int getMiddlePosition(int[] array, int low, int high) {
        //基准
        int temp = array[low];
        while (low < high) {
            //找到比基准元素小的位置
            while (low < high && array[high] >= temp) {
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low] <= temp) {
                low++;
            }
            array[high] = array[low];

        }
        array[low] = temp;
        return low;
    }

    public static void main(String[] arg) {
        QuickSort quickSort = new QuickSort();
        executeTest(quickSort);

    }
}
