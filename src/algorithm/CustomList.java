package algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wanghaitao on 17/3/11.
 */
public class CustomList {
    final static int COUNT = 80000;

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>(COUNT);
        list(integerList);
        List<Integer> linkList = new LinkedList<>();
        list(linkList);
    }

    private static void list(List<Integer> integerList) {
        inputData(integerList);
        long now = System.currentTimeMillis();
        find2ByIterator(integerList);
        long duration = System.currentTimeMillis() - now;
        System.out.println(integerList.getClass() + "'duration:" + duration);
    }

    private static void find2ByList(List<Integer> integerList) {
        for (Integer index : integerList) {
            if (index % 2 == 0) {
                integerList.remove(index);
            }
        }
    }

    private static void find2ByIterator(List<Integer> integerList) {
        Iterator<Integer> iterator = integerList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                iterator.remove();
            }
        }
    }

    private static void inputData(List<Integer> src) {
        for (int index = 0; index < COUNT; index++) {
            src.add(index);
        }
    }

    private static <T> void print(List<T> src) {
        for (T t : src) {
            System.out.print(t + ",");
        }
        System.out.println();
    }
}
