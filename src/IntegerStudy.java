/**
 * Created by wanghaitao on 16/8/29.
 */
public class IntegerStudy {
    public static void main(String[] args) {
        print("isIntegerEquals:" + isIntegerEquals(123));
        print("isLongEquals:" + isLongEquals(123));
        print("isStringEquals:" + isStringEquals("123"));
        print("isStringBuilderEquals:" + isStringBuilderEquals("123"));
        print("isNewStringEquals:" + isNewStringEquals("123"));

        String str1 = "hello";
        String str2 = "hello 1234";
        String str3 = str2.substring(0, str1.length());
        String str4 = new String("hello").intern();
        print("substring:" + (str1 == str3));
        print("intern:" + (str1 == str4));

        String str5 = "he" + "ll" + "o";
        print(str1 == str5);

    }

    private static boolean isIntegerEquals(int var) {
        int a = var;
        int b = var;
        return Integer.valueOf(a) == Integer.valueOf(b);
    }

    private static boolean isStringEquals(String var) {
        String a = var;
        String b = var;
        return a == b;
    }

    private static boolean isNewStringEquals(String var) {
        String a = new String(var);
        String b = new String(var);
        return a == b;
    }

    private static boolean isStringBuilderEquals(String var) {
        StringBuilder a = new StringBuilder(var);
        StringBuilder b = new StringBuilder(var);
        return a == b;
    }

    private static boolean isLongEquals(long var) {
        long a = var;
        long b = var;
        return Long.valueOf(a) == Long.valueOf(b);
    }

    private static <T> void print(T t) {
        System.out.println(t);
    }
}
