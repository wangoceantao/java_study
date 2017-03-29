package reflect;

/**
 * Created by wanghaitao on 17/3/7.
 */
public class FinallayAndReturn {

    public static void main(String[] args) {
        System.out.println("print:" + test());
    }

    private static int test() {
        int x;
        int y = 0;
        try {
            x = 1;
            y = 1;
            System.out.println("try");
            throw new IllegalArgumentException("errot");
//            return x;
        } catch (Exception e) {
            x = 2;
            y = 2;
            System.out.println("catch");
            return x;
        } finally {
            x = 3;
            y = 3;
            System.out.println("finally");
        }
    }
}
