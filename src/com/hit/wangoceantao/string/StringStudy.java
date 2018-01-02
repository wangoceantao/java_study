package com.hit.wangoceantao.string;

import java.math.BigDecimal;

/**
 * @Description: ${TODO}
 * <p/>
 * Created by wanghaitao on 16/5/5 08:41.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class StringStudy {
    public static void main(String[] arg) {
//        String s1 = "hello com.hit.wangoceantao.test";
//        String s4 = "hello com.hit.wangoceantao.test";
//        System.out.println(s1 == s4);
//        String s3 = "hello ";
//        String s2 = (s3 + "com.hit.wangoceantao.test").intern();
//        System.out.println(s1 == s2);
        StringBuffer s1 = new StringBuffer("s1");
        StringBuffer s2 = new StringBuffer("s2");
        appendString(s1, s2);
        System.out.println("s1-->>" + s1 + ";s2-->>" + s2);
        s2 = s1;
        System.out.println("s1-->>" + s1 + ";s2-->>" + s2);

        System.out.println(2.00 - 1.10);
        System.out.printf(new BigDecimal("2.00").subtract(new BigDecimal("1.10")).toString());
//        longDivide();
        System.out.println("--长整除---");
        final long MICROS_PER_DAY = 24L * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24L * 60 * 60 * 1000;
        long ret = MICROS_PER_DAY / MILLIS_PER_DAY;
        System.out.println("rest:" + ret);
        System.out.println(
                Long.toHexString(0x100000000L + 0xcafebabe));
        System.out.println("截取字符串");
        System.out.println(subString("截取abcdefgjkaklal", 8));
    }

    private static void longDivide() {

        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }

    private static void appendString(StringBuffer s1, StringBuffer s2) {
        s1.append(s2);
        s2 = s1;
    }

    private static String subString(String src, int count) {
        if (src == null || "".equals(src)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (int i = 0; i < count; i++) {
                char s = src.charAt(i);
                stringBuffer.append(s);
                if (isCheneseString(s)) {
                    count = count - 1;
                }
            }
        } catch (Exception e) {

        }
        return stringBuffer.toString();
    }

    private static boolean isCheneseString(char c) throws Exception {
        return String.valueOf(c).getBytes("GBK").length > 1;
    }

}
