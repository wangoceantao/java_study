package com.hit.wangoceantao.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashSet;

/**
 * Created by wanghaitao on 17/10/23.
 */
public class GsonTest {
    public static void main(String[] args) {
        HashSet<String> set = new Gson().fromJson("[\n" +
                "    \"1\",\n" +
                "    \"2\",\n" +
                "    \"3\",\n" +
                "    \"1\",\n" +
                "    \"2\",\n" +
                "    \"3\",\n" +
                "    \"1\",\n" +
                "    \"2\",\n" +
                "    \"3\",\n" +
                "    \"1\",\n" +
                "    \"2\"\n" +
                "]", new TypeToken<HashSet<String>>() {
        }.getType());
        System.out.print(set);
        double inputNumber = 0.000D;
        System.out.println(inputNumber != 0);
        isValidColor("#11223");
        isValidColor("#112233");
        isValidColor("#112s33");
        isValidColor("#11223344");
        isValidColor("#112s3344");
        isValidColor("#112233445");
        isValidColor("0x12345");
        isValidColor("0x123456");
        isValidColor("0x12s456");
        isValidColor("0x12345678");
        isValidColor("0x12s45678");
        isValidColor("0x123456789");
        System.out.println(Long.parseLong("ff",16));
        String colorString = "0xff";
        System.out.println((int) Long.parseLong(colorString.substring("0x".length()), 16));
    }

    public static void isValidColor(String colorString) {
        System.out.println("input:" + colorString + "   result:" + ColorUtils.isValidColor(colorString));
    }

    private static class ColorUtils {
        public static boolean isValidColor(String colorString) {
            if (colorString == null || colorString.length() == 0) {
                return false;
            }

            if (!colorString.startsWith("#") && !colorString.startsWith("0x")) {
                return false;
            }

            if (colorString.startsWith("#")) {
                if (colorString.length() != 7 && colorString.length() != 9) {
                    return false;
                }
            }

            if (colorString.startsWith("0x")) {
                if (colorString.length() != 8 && colorString.length() != 10) {
                    return false;
                }
            }

            String colorRealString = colorString;
            int prefixLength = (colorString.startsWith("#")) ? 1 : 2;
            if (colorString.startsWith("#")) {

                final int len = colorString.length();
                if ((len - prefixLength) == 6) {
                    // rrggbb 透明度设置完全不透明
                    colorRealString = "ff" + colorString.substring(prefixLength);
                } else if ((len - prefixLength) == 8) {
                    //aarrggbb
                    colorRealString = colorString.substring(prefixLength);
                }
            } else if (colorString.startsWith("0x")) {
                colorRealString = colorString.substring(prefixLength);
            }

            try {
                int color = (int) Long.parseLong(colorRealString, 16);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }
    }
}
