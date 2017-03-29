package algorithm;

import algorithm.interfaces.IPlalindromeDetecter;

/**
 * Created by wanghaitao on 17/3/29.
 */
public class PlalindromeMiddleDetecter implements IPlalindromeDetecter {
    @Override
    public boolean detect(String source) {
        if (source == null || source.length() == 0) {
            return false;
        }
        int middle = ((source.length() >> 1) - 1 >= 0) ? ((source.length() >> 1) - 1) : 0;
        int front = middle;
        int back = source.length() - 1 - middle;
        while (front >= 0) {
            if (source.charAt(front--) != source.charAt(back++)) {
                return false;
            }
        }
        return true;
    }
}
