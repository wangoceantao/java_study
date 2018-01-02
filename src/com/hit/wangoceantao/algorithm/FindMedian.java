package com.hit.wangoceantao.algorithm;

/**
 * Created by wanghaitao on 17/7/14.
 */
public class FindMedian {
    public static void main(String... args) {
//        FindMedian findMedian = new FindMedian();
//        int[] firstArray = new int[]{1, 3, 5};
//        int[] secondArray = new int[]{2, 4, 6};
//        Integer integer = findMedian.find(firstArray, secondArray);
//        System.out.println("result:" + integer);
//
////        BigDecimal bd = new BigDecimal("340256010353");
//        BigDecimal bd = new BigDecimal("3.40256010353E11");
////        然后转换成字符串：
//        String str = bd.toPlainString();
////        如果这个数字的长度是在int的范围内的话，是可以转换成int类型：
//        long a = Long.parseLong(str);
//        System.out.println("result:" + str);
        System.out.println("getSafelyResult Byte:" + getSafelyResult(null, Byte.class));
        System.out.println("getSafelyResult Short:" + getSafelyResult(null, Short.class));
        System.out.println("getSafelyResult Integer:" + getSafelyResult(null, Integer.class));
        System.out.println("getSafelyResult Long:" + getSafelyResult(null, Long.class));
        System.out.println("getSafelyResult Float:" + getSafelyResult(null, Float.class));
        System.out.println("getSafelyResult Double:" + getSafelyResult(null, Double.class));
        System.out.println(""+((-90)%(360)));
    }

    protected static <T> T getSafelyResult(Object result, Class<T> resultType) {
        if (result == null) {
            if (resultType.equals(Byte.class)) {
                return (T) Byte.valueOf("0");
            } else if (resultType.equals(Short.class)) {
                return (T) Short.valueOf("0");
            } else if (resultType.equals(Integer.class)) {
                return (T) Integer.valueOf("0");
            } else if (resultType.equals(Long.class)) {
                return (T) Long.valueOf("0");
            } else if (resultType.equals(Float.class)) {
                return (T) Float.valueOf("0");
            } else if (resultType.equals(Double.class)) {
                return (T) Double.valueOf("0");
            }
            return null;
        }
        return (T) result;
    }

    public int find(int[] firstArray, int[] secondArray) {
        int fisrtPos = find(firstArray, 0, firstArray.length - 1);
        int secondPos = find(secondArray, 0, secondArray.length - 1);
        if (firstArray[fisrtPos] < secondArray[secondPos]) {

        }
        return -1;
    }

    private int find(int[] array, int start, int end) {
        int middlePos = (start + end) / 2;
        return middlePos;
    }
}
