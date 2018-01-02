package com.hit.wangoceantao.algorithm.sort;

/**
 * @Description: ${TODO}
 * <p/>
 * Created by wanghaitao on 16/5/2 12:25.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class ShellSort extends BaseSort {
    @Override
    public void executeSort(int[] array) {
        int length = array.length;
        int gap = length / 2;
        while (gap > 0) {
            for (int index = 0; index < length; index = index + gap) {
                int tmp = array[index];
                int j = index - gap;
                while (j >= 0 && tmp < array[j]) {
                    array[j + gap] = array[j];
                    j = j - gap;
                }
                array[j + gap] = tmp;
            }
            gap = gap / 2;
        }
    }

    public static void main(String[] arg) {
        ShellSort shellSort = new ShellSort();
        executeTest(shellSort);
    }
}
