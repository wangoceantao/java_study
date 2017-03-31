package algorithm;

/**
 * Created by wanghaitao on 17/3/30.
 */
public class MaxSub {

    private static int computeCount = 0;

    public static class Result {
        public int sum;
        public int start;
        public int end;

        @Override
        public String toString() {
            return "Result{" +
                    "sum=" + sum +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public Result find(int[] input) {
        int sum = 0;
        Result result = new Result();
        for (int i = 0; i < input.length; i++) {
            for (int j = i; j < input.length; j++) {
                int temp = sum(input, i, j);
                if (sum < temp) {
                    sum = temp;
                    result.sum = sum;
                    result.start = i;
                    result.end = j;
                }
            }
        }
        return result;
    }

    private int sum(int[] input, int start, int end) {
        int result = 0;
        for (int index = start; index <= end; index++) {
            result += input[index];
            computeCount++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] source = new int[]{
                -4, -1, -2, -3, 5, -3, -2
        };
        MaxSub maxSub = new MaxSub();
        System.out.println(maxSub.find(source));
        System.out.println("source count:" + source.length + "computeCount:" + computeCount);

    }
}
