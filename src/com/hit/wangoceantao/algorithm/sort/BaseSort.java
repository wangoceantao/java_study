package com.hit.wangoceantao.algorithm.sort;


import com.hit.wangoceantao.TestDataGenerator;
/**
 * @Description: ${TODO}
 * <p/>
 * Created by wanghaitao on 16/4/29 23:17.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public abstract class BaseSort implements SortInterface{
    public int[] sort(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }
        int[] data = array.clone();
        executeSort(data);
        return data;
    }

    public abstract void executeSort(int[] array);

    public static void executeTest(SortInterface instance){
        int[] datas = TestDataGenerator.getInstance().getArrayData().clone();
        System.out.print("排序前：\n");
        com.hit.wangoceantao.tools.Printer.printArray(datas);
        int[] result = instance.sort(datas);
        System.out.print("排序后：\n");
        com.hit.wangoceantao.tools.Printer.printArray(result);
        System.exit(0);
    }

}
