package com.hit.wangoceantao.algorithm.sort;

/**
 * @Description: 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
 * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
 * <p/>
 * Created by wanghaitao on 16/4/29 22:59.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class SimpleSelectSort extends BaseSort {
    @Override
    public void executeSort(int[] array) {
        //简单的选择排序
        int length = array.length;
        for (int index = 0; index < length; index++) {
            //find min
            int min_value = array[index];
            int min_position = index;
            //找到最小的数
            for (int j = index + 1; j < array.length; j++) {
                if (array[j] < min_value) {
                    min_value = array[j];
                    min_position = j;
                }
            }
            //交换位置
            if (min_position != index) {
                array[min_position] = array[index];
                array[index] = min_value;
            }

        }
    }

    public static void main(String[] arg) {
        SimpleSelectSort simpleSelectSort = new SimpleSelectSort();
        executeTest(simpleSelectSort);
    }

}
