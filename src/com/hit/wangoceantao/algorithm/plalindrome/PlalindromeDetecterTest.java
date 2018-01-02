package com.hit.wangoceantao.algorithm.plalindrome;

import com.hit.wangoceantao.algorithm.plalindrome.interfaces.IPlalindromeDetecter;

/**
 * 回文检测
 * Created by wanghaitao on 17/3/29.
 */
public class PlalindromeDetecterTest {
    public static void main(String[] args) {
        System.out.println("---PlalindromeDetecter---");
        IPlalindromeDetecter detecter = new PlalindromeDetecter();
        test(detecter);

        System.out.println("---PlalindromeMiddleDetecter---");
        IPlalindromeDetecter plalindromeMiddleDetecter = new PlalindromeMiddleDetecter();
        test(plalindromeMiddleDetecter);

    }

    private static void test(IPlalindromeDetecter detecter) {
        System.out.println("null:" + detecter.detect(null));
        System.out.println("空串：" + detecter.detect(""));
        System.out.println("1:" + detecter.detect("1"));
        String testString = "";
        for (int index = 0; index < 100; index++) {
            testString += "2112";
        }
        System.out.println("testString:" + detecter.detect(testString));
        System.out.println("12320:" + detecter.detect("12320"));
    }
}
