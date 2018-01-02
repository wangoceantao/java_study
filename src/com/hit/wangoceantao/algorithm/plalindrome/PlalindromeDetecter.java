package com.hit.wangoceantao.algorithm.plalindrome;

import com.hit.wangoceantao.algorithm.plalindrome.interfaces.IPlalindromeDetecter;

/**
 * Created by wanghaitao on 17/3/29.
 */
public class PlalindromeDetecter implements IPlalindromeDetecter{

    public boolean detect(String source) {
        if (source == null || source.length() == 0) {
            return false;
        }
        int front = 0;
        int back = source.length() - 1;
        while (front < back) {
            if (source.charAt(front) != source.charAt(back)) {
                return false;
            }
            front++;
            back--;
        }

        return true;
    }
}
