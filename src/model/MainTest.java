package model;


import com.hit.wangoceantao.entity.Pair;

import java.util.*;

/**
 * Created by zhangnannan on 16/8/27.
 */
public class MainTest {
    public static void main(String[] args) {
//        valueTransfer();

//        System.out.println(linkedlist());
//        System.out.println(priorityQuene());

        print(linkedHashMap());

    }

    private static void valueTransfer() {
        Pair<String> pair1 = new Pair<>();
        pair1.setValue("123");
        Pair<String> pair2 = new Pair<>();
        pair2.setValue("456");
        swap(pair1, pair2);
        System.out.println("pair1:" + pair1.getValue() + ";pair2:" + pair2.getValue());
        change(pair1);
        System.out.println("pair1:" + pair1.getValue());
    }

    private static void swap(Pair pair1, Pair pair2) {
        Pair tmp = pair1;
        pair1 = pair2;
        pair2 = tmp;
    }

    private static void change(Pair pair) {
        pair.setValue(pair.getValue() + "-change");
    }

    private static LinkedList<String> linkedlist() {
        LinkedList<String> list = new LinkedList();
        for (int index = 0; index < 10; index++) {
            list.add("index:" + (index + 1));
        }
        ListIterator<String> listIterator = list.listIterator();

        while (listIterator.hasNext()) {
            //保留前两位
            if (listIterator.nextIndex() == 5) {
                listIterator.next();
                listIterator.remove();
            } else {
                listIterator.next();
            }
        }
        listIterator.add("tail:1");
        while (listIterator.hasPrevious()) {
            print("previousIndex:" + listIterator.previousIndex());
            listIterator.previous();
        }

        listIterator.add("head:1");
        return list;
    }


    private static PriorityQueue priorityQuene() {
        PriorityQueue<Bean> queue = new PriorityQueue<Bean>();
        queue.add(new Bean(4444));
        queue.add(new Bean(2222));
        queue.add(new Bean(3333));
        queue.add(new Bean(1111));
        return queue;
    }

    private static <T> void print(T t) {
        System.out.println(t);
    }

    static class Bean implements Comparable {
        private int priority;

        public Bean(int priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(Object o) {
            if (this == o) {
                return 0;
            }
            if (o == null) {
                return 1;
            }
            Bean other = (Bean) o;

            return this.priority - other.priority;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "priority=" + priority +
                    '}';
        }
    }

    private static Map<Integer, Bean> linkedHashMap() {
        Map<Integer, Bean> map = new LinkedHashMap<Integer, Bean>(10, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 4;
            }
        };
        for (int index = 0; index < 10; index++)
            map.put(index, new Bean(index));
        return map;
    }
}
