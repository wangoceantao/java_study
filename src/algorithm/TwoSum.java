package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 从给定的整数组中找到两个数的和为定值的所有整数对
 * Created by wanghaitao on 17/3/30.
 */
public class TwoSum {

    public static class Result {
        public int first;
        public int second;
    }

    /**
     * 返回所有整数对
     *
     * @param source 给定的整数组
     * @param sum    和
     * @return
     */
    public List<Result> find(int[] source, int sum) {
        List<Result> result = new ArrayList<>();
        if (!isSorted(source)) {
            sort(source);
        }
        int i = 0;
        int j = source.length - 1;
        while (i < j) {
            int currSum = source[i] + source[j];
            if (currSum == sum) {
                Result r = new Result();
                r.first = source[i];
                r.second = source[j];
                result.add(r);
                i++;
                j--;
            } else if (currSum < sum) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }

    /**
     * 数组是否升序
     *
     * @param source
     * @return
     */
    private boolean isSorted(int[] source) {
        for (int index = 0; index < source.length - 1; index++) {
            if (source[index] > source[index + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 排序
     * @param source
     */
    private void sort(int[] source) {
        Arrays.sort(source);
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] source = new int[]{
                1, 14, 2, 3, 5, 6, 7, 8, 9, 10, 11
        };

        List<Result> twoSums = twoSum.find(source, 15);
        for (Result result : twoSums) {
            System.out.println(result.first + ":" + result.second);
        }
    }
}
